package ir.albino.client.event.impl;

import ir.albino.client.event.type.CancellableEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServerChatEvent extends CancellableEvent {
    private String chat;
}
