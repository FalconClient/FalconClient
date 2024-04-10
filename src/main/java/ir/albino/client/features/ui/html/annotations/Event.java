package ir.albino.client.features.ui.html.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Event {
    Type eventType();

    enum Type {
        BEFORE_SERIALIZE, AFTER_SERIALIZE
    }
}
