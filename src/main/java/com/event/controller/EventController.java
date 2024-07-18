package com.event.controller;

import com.event.DTO.EventDTO;
import com.event.entity.Event;
import com.event.entity.User;
import com.event.exception.ResourceNotFoundException;
import com.event.service.EventService;
import com.event.util.UserAuthenticationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/events")
@Tag(name = "Events", description = "Event management APIs")
@Slf4j
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    @Operation(
            summary = "Testing endpoint",
            description = "Testing endpoint for secured API calls",
            tags = {"Get Events", "get"})
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        log.info("Fetching all entities");
        List<Event> events = eventService.getAllEvents();
        log.info("Fetched {} events", events.size());
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    //Pagination not working with Redis
  /*  @GetMapping
    public ResponseEntity<Page<EventDTO>> getAllEvents(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size) {
        log.info("Fetching all entities");
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> events = eventService.getAllEvents(pageable);
        log.info("Fetched {} events", events.getSize());
        Page<EventDTO> eventDTO =  events.map(EventDTO::mapToEventDTO);
        return new ResponseEntity<>(eventDTO, HttpStatus.OK);
    }*/

    @GetMapping("/user-events")
    public ResponseEntity<List<Event>> getUserEvents() {
        User user = UserAuthenticationUtil.getUserByAuthentication();
        log.info("Fetching only my events");
        List<Event> userEvents = eventService.getEventsByUser(user.getId());
        log.info("Fetched user {} events", userEvents.size());
//        List<EventDTO> eventDTO =  userEvents.stream().map(EventDTO::mapToEventDTO).collect(Collectors.toList());
        return new ResponseEntity<>(userEvents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable("id") Long eventId, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        try {
            log.info("Fetching event with ID {}", eventId);
            Optional<Event> optionalEvent = eventService.getEventById(eventId, locale);
            log.info("Found event with ID {}", eventId);
//          EventDTO eventDTO = EventDTO.mapToEventDTO(event);
            Event event = optionalEvent.orElse(new Event());
            return new ResponseEntity<>(event, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            log.error("Event with ID {} not found", eventId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage("event.not.found.msg", null, locale) + " " + eventId);
        }
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventDTO event) throws BadRequestException {
        log.info("Creating new event with code: {}");
        User user = UserAuthenticationUtil.getUserByAuthentication();
        Event createdEvent = eventService.createEvent(event, user, user.getId());
        log.info("Created Event with ID {}", createdEvent.getId());
        EventDTO eventDTO = EventDTO.mapToEventDTO(createdEvent);
        return new ResponseEntity<>(eventDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable("id") Long eventId, @Valid @RequestBody EventDTO eventDetails, @RequestHeader(name = "Accept-Language", required = false) Locale locale) throws BadRequestException {
        log.info("Updating event with ID {}", eventId);
        try {
            User user = UserAuthenticationUtil.getUserByAuthentication();
            Event updatedEvent = eventService.updateEvent(eventId, eventDetails, user, user.getId(), locale);
            log.info("Updated tool with ID {}", eventId);
//            EventDTO eventDTO = EventDTO.mapToEventDTO(updatedEvent);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            log.error("Event with ID {} not found for update", eventId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage("event.not.found.for.update.msg", null, locale) + " " + eventId);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable("id") Long eventId, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        log.info("Deleting event with ID {}", eventId);
        try {
            User user = UserAuthenticationUtil.getUserByAuthentication();
            eventService.deleteEvent(eventId, user, user.getId(), locale);
            log.info("Deleted event with ID {}", eventId);
            return ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage("event.deleted.successfully.msg", null, locale) + " " + eventId);
        } catch (ResourceNotFoundException ex) {
            log.error("Event with ID {} not found for deletion", eventId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(messageSource.getMessage("event.not.found.or.not.access.msg", null, locale) + " " + eventId);
        }
    }
}