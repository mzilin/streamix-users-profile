package com.mariuszilinskas.streamix.users.profile.consumer;

import com.mariuszilinskas.streamix.users.profile.event.UserDeletedEvent;
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
            topics = "${kafka.topics.user-deleted}",
            groupId = "${kafka.groups.user-deletion}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleUserDeleted(UserDeletedEvent event) {
        logger.info("Received UserDeleted event for User [userId: '{}']", event.userId());
        profileService.deleteAllUserProfiles(event.userId());
    }

}
