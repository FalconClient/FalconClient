package ir.albino.client.features.ui.html.annotations;

import ir.albino.client.features.ui.html.modules.SerializerUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HTMLMap {
    String id() default "";

    String customTag() default "field";


    String defaultValue() default "";

    Class<?> deserialize() default SerializerUtils.class;

    String deserializeFunction() default "";

    String serializeFunction() default "";
}
