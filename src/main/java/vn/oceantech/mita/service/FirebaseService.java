package vn.oceantech.mita.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.oceantech.mita.dto.NotificationDto;

@Service
@Slf4j
public class FirebaseService {

    private FirebaseMessaging firebaseMessaging;

    public FirebaseService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public String sendNotification(NotificationDto notificationDto, String token) {
        Message message = Message
                .builder()
                .setToken(token)
                .putData("content", notificationDto.getBody())
                .putData("title", notificationDto.getTitle())
                .putData("type", notificationDto.getType())
                .build();

        log.info("Push notification message: {}", message.toString());
        String response = null;
        try {
            response = firebaseMessaging.send(message);
            log.info("Push notification response: {}", response);
        } catch (FirebaseMessagingException e) {
            log.info("Push notification fail: {}", e.getMessage());
        }
        return response;
    }
}