package tn.esprit.spring.calendar;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.repackaged.com.google.common.base.Preconditions;
import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar.Events;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;


import tn.esprit.spring.entities.*;

@Service
public class CalendarService {
	
	 private final static Log logger = LogFactory.getLog(CalendarService.class);
	 	private static final String APPLICATION_NAME = "EventsCalendar";
	 	private static HttpTransport httpTransport;
	 	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	 	private static com.google.api.services.calendar.Calendar client;
	 	
	 	URL url = getClass().getResource("auth.p12");
	 	File keyFile=new File(url.getPath());
	 	GoogleClientSecrets clientSecrets;
	 	GoogleAuthorizationCodeFlow flow;
	 	Credential credential;
	 	
	 	HttpTransport TRANSPORT ;
	 	private String SERVICE_ACCOUNT="travelagency@winter-sum-343611.iam.gserviceaccount.com";
	
 		
 		public void addEventToCalendar(tn.esprit.spring.entities.Event eventDB) throws IOException, GeneralSecurityException {
 	 		com.google.api.services.calendar.model.Events eventList;
 	 		String message;
 	 		
// 	 			TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectURI).execute();
// 	 			credential = flow.createAndStoreCredential(response, "userID"); //for Oauth2
 	 		Preconditions.checkArgument( !Strings.isNullOrEmpty(APPLICATION_NAME ),
 	 			      "applicationName cannot be null or empty!" );
 	 			try {
 	 			 	TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
 	 			 	
 	 			
 	 			credential = new GoogleCredential.Builder().setTransport(TRANSPORT)
 	 		            .setJsonFactory(JSON_FACTORY)
 	 		            .setServiceAccountId(SERVICE_ACCOUNT)
 	 		            .setServiceAccountScopes(Collections.singleton(CalendarScopes.CALENDAR))
 	 		            .setServiceAccountPrivateKeyFromP12File(keyFile)
 	 		            
 	 		            .build();
 	 			credential.refreshToken();
 	 			client = new com.google.api.services.calendar.Calendar.Builder(TRANSPORT, JSON_FACTORY, credential)
 	 					.setApplicationName(APPLICATION_NAME).build();
 	 			System.out.println(client);
 	 			Events events = client.events();
 	 			Date date1= eventDB.getDatedebut();
 	 			
 				Date date2= eventDB.getDatefin();
 				
 				eventList = events.list("primary").setTimeMin(new DateTime(date1)).setTimeMax(new DateTime(date2)).execute();
 	 			message = eventList.getItems().toString();
 	 		
 	 			Event event = new Event()
 	 				    .setSummary(eventDB.getSubject())
 	 				    .setLocation(eventDB.getLieu())
 	 				    .setDescription(eventDB.getSummary());
 	 				DateTime startDateTime = new DateTime(date1);
 	 				EventDateTime start = new EventDateTime()
 	 				    .setDateTime(startDateTime)
 	 				    .setTimeZone("Africa/Tunis");
 	 				event.setStart(start);
 	 				
 	 				Date datefin= new Date();
 	 				DateTime endDateTime = new DateTime(date2);
 	 				EventDateTime end = new EventDateTime()
 	 				    .setDateTime(endDateTime)
 	 				    .setTimeZone("Africa/Tunis");
 	 				event.setEnd(end);

 	 				String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
 	 				event.setRecurrence(Arrays.asList(recurrence));
 	 				
 	 				
 	 			//	EventAttendee attendees = new EventAttendee();
 	 				Set<User> users= eventDB.getUsers();
 	 			 
 	 				
 				  /*
 						  for (User user : users) 
 		 	 			    {
 							 EventAttendee attendees = new EventAttendee();
 							 attendees.setEmail(user.getEmail());
 			 				 event.setAttendees(Arrays.asList(attendees));
                             System.out.println(attendees);
 		 	 			    }
 						
 				  
 				   */
 			
 	 			    
 	 				EventReminder[] reminderOverrides = new EventReminder[] {
 	 				    new EventReminder().setMethod("email").setMinutes(24 * 60),
 	 				    new EventReminder().setMethod("popup").setMinutes(10),
 	 				};
 	 				Event.Reminders reminders = new Event.Reminders()
 	 				    .setUseDefault(false)
 	 				    .setOverrides(Arrays.asList(reminderOverrides));
 	 				event.setReminders(reminders);

 	 				String calendarId = "ubr1acv458b1scd98c9jr9h970@group.calendar.google.com";
 	 				event = client.events().insert(calendarId, event).execute();
 	 				System.out.printf("Event created: %s\n", event.getHtmlLink());
 	 				System.out.println(event);

 	 		System.out.println("cal message:" + message);
 	 		
 	 			}catch(IOException e)
 				 	{
 				 		System.out.println(e);
 				 	}
 	 	}

}
