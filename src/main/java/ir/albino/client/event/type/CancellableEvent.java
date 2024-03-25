package ir.albino.client.event.type;

import ir.albino.client.event.Event;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancellableEvent extends Event {
    private boolean cancelled;
}