package com.advantal.requestPayload;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushNotificationRequestPayload {

	    private Long notificationId;
	    
	    private String titleName;
		
	    private String notification;
		
	    private Long userId;
			    
	    private Date creationDate;

}
