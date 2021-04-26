package tk.ynvaser.quiz.service.broadcast;

import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import java.util.function.Consumer;

/**
 * This cast represents a channel for a specific broadcast event sent to all clients from the server.
 * Clients can subscribe to a broadcast channel to receive it's transmissions.
 */
public class BroadcastChannel<T> {
    private final UnicastProcessor<T> transmitter = UnicastProcessor.create();
    private final Flux<T> receiver = transmitter.publish().autoConnect();

    public void subscribe(Consumer<T> consumer) {
        receiver.subscribe(consumer);
    }

    public void publish(T next) {
        transmitter.onNext(next);
    }
}
