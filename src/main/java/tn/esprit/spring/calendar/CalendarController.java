package tn.esprit.spring.calendar;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.repackaged.com.google.common.base.Preconditions;
import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.Calendar.Events;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;





@RequestMapping("/calendar")
@RestController
public class CalendarController {
    
	
     private final static Log logger = LogFactory.getLog(CalendarController.class);
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
 	 	
 	 	

 	

 	private Set<Event> events = new HashSet<>();

 	

 	public void setEvents(Set<Event> events) {
 		this.events = events;
 	}


 	@GetMapping("/add")
 	public ResponseEntity<String> oauth2Callback(HttpServletRequest request) throws IOException, GeneralSecurityException {
 		com.google.api.services.calendar.model.Events eventList;
 		String message;
 		
// 			TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectURI).execute();
// 			credential = flow.createAndStoreCredential(response, "userID"); //for Oauth2
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
 			Date date1= new Date();
 			
			Date date2= new Date();
			
			eventList = events.list("primary").setTimeMin(new DateTime(date1)).setTimeMax(new DateTime(date2)).execute();
 			message = eventList.getItems().toString();
 		
 			Event event = new Event()
 				    .setSummary("Workshop for developpers")
 				    .setLocation("Esprit , El ghazela")
 				    .setDescription("Workshop for developpers to improve their skills in Front end developpement.");
                Date datedebut= new Date();
 				DateTime startDateTime = new DateTime(datedebut);
 				EventDateTime start = new EventDateTime()
 				    .setDateTime(startDateTime)
 				    .setTimeZone("Africa/Tunis");
 				event.setStart(start);
 				
 				Date datefin= new Date();
 				DateTime endDateTime = new DateTime(datefin);
 				EventDateTime end = new EventDateTime()
 				    .setDateTime(endDateTime)
 				    .setTimeZone("Africa/Tunis");
 				event.setEnd(end);

 				String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
 				event.setRecurrence(Arrays.asList(recurrence));
//			EventAttendee[] attendees = new EventAttendee[] {
// 				    new EventAttendee().setEmail("greatgatsbylala18@gmail.com"),
//				    new EventAttendee().setEmail("himanshuranjan3030@gmail.com"),
//				};
//		event.setAttendees(Arrays.asList(attendees));

 				EventReminder[] reminderOverrides = new EventReminder[] {
 				    new EventReminder().setMethod("email").setMinutes(24 * 60),
 				    new EventReminder().setMethod("popup").setMinutes(10),
 				};
 				Event.Reminders reminders = new Event.Reminders()
 				    .setUseDefault(false)
 				    .setOverrides(Arrays.asList(reminderOverrides));
 				event.setReminders(reminders);

 				String calendarId = "primary";
 				event = client.events().insert("ubr1acv458b1scd98c9jr9h970@group.calendar.google.com", event).execute();
 				System.out.printf("Event created: %s\n", event.getHtmlLink());
 				System.out.println(event);

 		System.out.println("cal message:" + message);
 		return new ResponseEntity<>(message, HttpStatus.OK);
 			}catch(IOException e)
			 	{
			 		System.out.println(e);
			 	}
 			return new ResponseEntity<>("Nothing", HttpStatus.OK);
 	}

 	public Set<Event> getEvents() throws IOException {
 		return this.events;
 	}


 	
}