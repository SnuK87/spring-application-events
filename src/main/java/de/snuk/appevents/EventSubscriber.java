package de.snuk.appevents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import de.snuk.appevents.event.AckEvent;
import de.snuk.appevents.event.AsyncEvent;
import de.snuk.appevents.event.GenericEvent;
import de.snuk.appevents.event.SimpleEvent;

@Component
class EventSubscriber {

	private static final Logger log = LoggerFactory.getLogger(EventSubscriber.class);

	@EventListener
	public AckEvent processSimple(SimpleEvent event) throws InterruptedException {
		log.info("Receive simple event: {}", event);
		Thread.sleep(3000); // blocks publishing thread

		return new AckEvent(true); // publishes new event
	}

	@EventListener
	@Order(99999)
	public void processGenericLast(GenericEvent<?> event) {
		log.info("Received generic event Order(99999): {}", event);
	}

	@EventListener
	@Order(1)
	public void processGenericFirst(GenericEvent<?> event) {
		log.info("Received generic event Order(1): {}", event);
	}

	@EventListener
	@Async
	public void processAsyncEvent(AsyncEvent event) throws InterruptedException {
		log.info("Receive simple async event: {}", event);
		Thread.sleep(3000); // processed in new thread
	}

	@EventListener
	public void processAck(AckEvent event) {
		log.info("Received ack: {}", event);
	}
}
