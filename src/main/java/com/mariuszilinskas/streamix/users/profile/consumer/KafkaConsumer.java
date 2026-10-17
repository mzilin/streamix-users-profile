package com.mariuszilinskas.streamix.users.profile.consumer;

import com.mariuszilinskas.streamix.users.profile.dto.CreateUserDefaultProfileRequest;
import com.mariuszilinskas.streamix.users.profile.event.UserRegisteredEvent;
import com.mariuszilinskas.streamix.users.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    private final ProfileService profileService;

    @KafkaListener(
            topics = "${kafka.topics.user-registered}",
            groupId = "${kafka.groups.profile-setup}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleUserRegistered(UserRegisteredEvent event) {
        logger.info("Received UserRegistered event for User [userId: '{}']", event.userId());
        profileService.createDefaultUserProfile(
                new CreateUserDefaultProfileRequest(event.userId(), event.firstName()));
    }

}
