package com.event.service;

import com.event.DTO.EventDTO;
import com.event.entity.Event;
import com.event.entity.User;
import com.event.exception.ResourceNotFoundException;
import com.event.repository.EventRepository;
import com.event.repository.UserRepository;
import com.event.util.UserAuthenticationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
@CacheConfig(cacheNames = "eventCache")
public class EventService {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Cacheable("events")
    public List<Event> getAllEvents() {
        doLongRunningTask();
        return eventRepository.findAll();
    }

    /* For pagination
    @Cacheable("events")
    public Page<Event> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }*/

    @Caching(evict = {@CacheEvict(cacheNames = "events", allEntries = true),
            @CacheEvict(cacheNames = "eventCache", key = "'eventsByUser:' + #userId")})
    public Event createEvent(EventDTO eventDTO, User user, Long userId) {
        Event event = EventDTO.mapToEvent(eventDTO);
        event.setUser(user);
        doLongRunningTask();
        return eventRepository.save(event);
    }

    @Cacheable(cacheNames = "event", key = "#id", unless = "#result == null")
    public Optional<Event> getEventById(Long id, Locale locale) {
        doLongRunningTask();
        return Optional.ofNullable(eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("event.not.found.msg", null, locale) + " " + id)));
    }

    @Cacheable(key = "'eventsByUser:' + #userId")
    public List<Event> getEventsByUser(Long userId) {
        doLongRunningTask();
        User user = UserAuthenticationUtil.getUserByAuthentication();
        return eventRepository.findByUser(user);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "events", allEntries = true),
            @CacheEvict(cacheNames = "eventCache", key = "'eventsByUser:' + #userId")})
    @CachePut(value = "event", key = "#eventId")
    public Event updateEvent(Long eventId, EventDTO eventDetails, User user, Long userId, Locale locale) throws ResourceNotFoundException {
        doLongRunningTask();
        Event event = eventRepository.findByIdAndUser(eventId, user)
                .orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("event.not.found.msg", null, locale) + " " + eventId));
        event.setName(eventDetails.getName());
        event.setCategory(eventDetails.getCategory());
        event.setDescription(eventDetails.getDescription());
        event.setLocation(eventDetails.getLocation());
        event.setEventEndDateAndTime(eventDetails.getEventEndDateAndTime());
        event.setEventStartDateAndTime(eventDetails.getEventStartDateAndTime());
        event.setUser(user);
        return eventRepository.save(event);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "event", key = "#eventId"),
            @CacheEvict(cacheNames = "eventCache", key = "'eventsByUser:' + #userId")})
    @CacheEvict(cacheNames = "events", allEntries = true)
    public void deleteEvent(Long eventId, User user, Long userId, Locale locale) {
        Event event = eventRepository.findByIdAndUser(eventId, user)
                .orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("event.not.found.msg", null, locale) + eventId));
        eventRepository.delete(event);
        log.info("Deleted the event with id " + eventId);
    }

    private void doLongRunningTask() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}