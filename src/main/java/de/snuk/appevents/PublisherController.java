package de.snuk.appevents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.snuk.appevents.event.AsyncEvent;
import de.snuk.appevents.event.GenericEvent;
import de.snuk.appevents.event.SimpleEvent;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PublisherController {

	private static final Logger log = LoggerFactory.getLogger(PublisherController.class);

	private final ApplicationEventPublisher publisher;

	@GetMapping("/simple")
	public ResponseEntity<String> simple() {
		log.info("Publish simple event ...");
		publisher.publishEvent(new SimpleEvent("GetRequest"));
		log.info("after event");

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/generic")
	public ResponseEntity<String> generic() {
		log.info("Publish generic event ...");
		publisher.publishEvent(new GenericEvent<ObjectA>(new ObjectA("just a string", 1337)));
		publisher.publishEvent(new GenericEvent<ObjectB>(new ObjectB(false, 1338L)));

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/async")
	public ResponseEntity<String> async() {
		log.info("Publish asnyc event ...");
		publisher.publishEvent(new AsyncEvent("GetRequest"));
		log.info("after event");

		return ResponseEntity.noContent().build();
	}
}
