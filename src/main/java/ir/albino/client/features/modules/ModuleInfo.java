package ir.albino.client.features.modules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines the module information's
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfo {
    String module();

    String version();

    String description();

    boolean draggable();

    Category category();


    enum Category {
        VISUAL,
        PVP,
        PLAYER,
        MISCELLANEOUS
    }
}
