package com.event.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class EventServiceTestDiffblueTest {
    /**
     * Method under test: {@link EventServiceTest#createEvent()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateEvent() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.event.repository.EventRepository.save(Object)" because "this.eventRepository" is null
        //       at com.event.service.EventServiceTest.createEvent(EventServiceTest.java:78)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new EventServiceTest()).createEvent();
    }

    /**
     * Method under test: {@link EventServiceTest#invalidUser()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testInvalidUser() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <org.springframework.security.core.userdetails.UsernameNotFoundException> but was: <java.lang.NullPointerException>
        //       at org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
        //       at org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
        //       at org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
        //       at org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
        //       at com.event.service.EventServiceTest.invalidUser(EventServiceTest.java:102)
        //   java.lang.NullPointerException: Cannot invoke "com.event.service.UserServiceImpl.findById(java.lang.Long, java.util.Locale)" because "this.userService" is null
        //       at com.event.service.EventServiceTest.lambda$invalidUser$0(EventServiceTest.java:102)
        //       at org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:53)
        //       at org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
        //       at org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
        //       at com.event.service.EventServiceTest.invalidUser(EventServiceTest.java:102)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        EventServiceTest eventServiceTest = null;

        // Act
        eventServiceTest.invalidUser();

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link EventServiceTest#getEventById()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEventById() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.event.repository.EventRepository.findById(Object)" because "this.eventRepository" is null
        //       at com.event.service.EventServiceTest.getEventById(EventServiceTest.java:115)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new EventServiceTest()).getEventById();
    }

    /**
     * Method under test: {@link EventServiceTest#eventNotFount()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testEventNotFount() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <com.event.exception.ResourceNotFoundException> but was: <java.lang.NullPointerException>
        //       at org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
        //       at org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
        //       at org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
        //       at org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
        //       at com.event.service.EventServiceTest.eventNotFount(EventServiceTest.java:124)
        //   java.lang.NullPointerException: Cannot invoke "com.event.service.EventService.getEventById(java.lang.Long, java.util.Locale)" because "this.eventService" is null
        //       at com.event.service.EventServiceTest.lambda$eventNotFount$1(EventServiceTest.java:124)
        //       at org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:53)
        //       at org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
        //       at org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
        //       at com.event.service.EventServiceTest.eventNotFount(EventServiceTest.java:124)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        EventServiceTest eventServiceTest = null;

        // Act
        eventServiceTest.eventNotFount();

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link EventServiceTest#updateEvent()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateEvent() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.event.repository.EventRepository.findByIdAndUser(long, com.event.entity.User)" because "this.eventRepository" is null
        //       at com.event.service.EventServiceTest.updateEvent(EventServiceTest.java:141)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new EventServiceTest()).updateEvent();
    }

    /**
     * Method under test: {@link EventServiceTest#deleteEvent()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteEvent() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.event.repository.EventRepository.findByIdAndUser(long, com.event.entity.User)" because "this.eventRepository" is null
        //       at com.event.service.EventServiceTest.deleteEvent(EventServiceTest.java:182)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new EventServiceTest()).deleteEvent();
    }

    /**
     * Method under test: {@link EventServiceTest#deleteEvent_EventNotFound()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteEvent_EventNotFound() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.event.repository.EventRepository.findByIdAndUser(long, com.event.entity.User)" because "this.eventRepository" is null
        //       at com.event.service.EventServiceTest.deleteEvent_EventNotFound(EventServiceTest.java:198)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new EventServiceTest()).deleteEvent_EventNotFound();
    }
}
