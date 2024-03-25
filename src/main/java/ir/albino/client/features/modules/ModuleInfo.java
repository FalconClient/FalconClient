package ir.albino.client.features.modules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfo {
    String Module();
    String Version();
    String Description();
    boolean Draggable();

    Category Category();


    enum Category {
        VISUAL,
        PVP,
        PLAYER,
        MISCELLANEOUS
    }
}
