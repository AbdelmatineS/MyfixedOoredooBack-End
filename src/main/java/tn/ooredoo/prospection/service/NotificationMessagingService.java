package tn.ooredoo.prospection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import tn.ooredoo.prospection.DTO.NotificationMessage;

@Service
public class NotificationMessagingService {

	@Autowired
	private FirebaseMessaging firebaseMessaging;
	
	public String sendNotificationByToken(NotificationMessage notificationMessage) {
		
		
		Notification notification = Notification
				.builder()
				.setTitle(notificationMessage.getTitle())
				.setBody(notificationMessage.getBody())
				.build();
		
		Message message = Message
				.builder()
				.setToken(notificationMessage.getRecipientToken())
				.setNotification(notification)
				.build();
		
		try {
			String res = firebaseMessaging.getInstance().send(message);
			return ("Success Sending Notification: "+res);
		}catch(FirebaseMessagingException e) {
			e.printStackTrace();
			return "Error Sending Notification";
		}
	}
}
