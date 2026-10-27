package com.mariuszilinskas.streamix.users.profile.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDeletedEvent(
        UUID eventId,
        String eventVersion,
        Instant occurredAt,
        UUID userId
) {}
