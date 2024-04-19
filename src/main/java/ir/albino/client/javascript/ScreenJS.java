package ir.albino.client.javascript;

import dev.slangware.ultralight.ViewController;
import ir.albino.client.AlbinoClient;
import net.janrupf.ujr.api.javascript.*;

public class ScreenJS {
    private static JSClass cachedJsClass;

    /**
     * Retrieves the JavaScript class for instances of this object.
     *
     * @return the JavaScript class for instances of this object
     */
    public static JSClass getJavaScriptClass() {

        if (cachedJsClass == null) {
            cachedJsClass = new JSClassBuilder(ScreenJS.class.getSimpleName())
                    .onInitialize(ScreenJS::onInitialize)
                    .onFinalize(ScreenJS::onFinalize)
                    .onHasProperty(ScreenJS::onHasProperty)
                    .onGetProperty(ScreenJS::onGetProperty)
                    .onSetProperty(ScreenJS::onSetProperty)
                    .onDeleteProperty(ScreenJS::onDeleteProperty)
                    .onCallAsFunction(ScreenJS::onCallAsFunction)
                    .onCallAsConstructor(ScreenJS::onCallAsConstructor)
                    .onConvertToType(ScreenJS::onConvertToType)
                    .build();
        }

        return cachedJsClass;
    }

    private static void onInitialize(JSContext context, JSObject object) {
        // This method is called when a new instance of this class is created in JavaScript.
        // You can use this to initialize the object.
    }


    private static void onFinalize(JSObject object) {
        // This method is called when the JavaScript object is garbage collected.
        // You can use this to clean up resources.

        // NOTE: DO NOT CALL ANYTHING RELATED TO JSContext IN THIS METHOD!
    }

    private static boolean onHasProperty(JSContext context, JSObject object, String name) {
        if (name.equals("Symbol.toPrimitive")) {
            // This is important in this case! since log4j is calling toString() on the object,
            // this leads to infinite recursion if we don't return false here.
            return false;
        }

        // This method is called when a property is accessed on the JavaScript object.
        // You can use this to implement custom properties.

        return name.equals("testProperty");
    }

    private static JSValue onGetProperty(JSContext context, JSValue object, String name) throws JavaScriptValueException {
        // This method is called when a property is accessed on the JavaScript object.
        // You can use this to implement custom properties.
        if (name.equals("testProperty")) {
            return context.makeString("Hello from Java!");
        }

        throw new JavaScriptValueException(context.makeError("Property not found"), "Property not found");
    }

    private static boolean onSetProperty(JSContext context, JSObject object, String name, JSValue value) throws JavaScriptValueException {
        // This method is called when a property is set on the JavaScript object.
        // You can use this to implement custom properties.

        // This example only allows setting the property "testProperty",
        // returning false will cause JavaScriptCore to delegate the property to the prototype chain.
        return name.equals("testProperty");
    }

    private static boolean onDeleteProperty(JSContext context, JSObject object, String name) throws JavaScriptValueException {
        // This method is called when a property is deleted on the JavaScript object.
        // You can use this to implement custom properties.

        // This example only allows deleting the property "testProperty",
        // returning false will cause JavaScriptCore to delegate the property to the prototype chain.

        // NOTE: We simply ignore the delete operation here
        return name.equals("testProperty");
    }

    private static JSValue onCallAsFunction(
            JSContext context,
            String functionName,
            JSObject function,
            JSObject thisObject,
            JSValue[] arguments
    ) {
        System.out.println("testtt");
        return context.makeString("Hello from Java!");
    }

    private static JSObject onCallAsConstructor(JSContext context, JSObject constructor, JSValue[] arguments) throws JavaScriptValueException {
        // This method is called when the JavaScript object is called as a constructor.
        // You can use this to implement custom constructors.
        return context.makeFromJSONString("{\"testProperty\": \"Hello from Java!\"}").toObject();
    }

    private static JSValue onConvertToType(JSContext context, JSObject object, JSType type) throws JavaScriptValueException {
        // This method is called when the JavaScript object is converted to a different type.
        // You can use this to implement custom conversions.

        // DO NOT attempt to print the object here, this will lead to infinite recursion!

        if (type == JSType.STRING) {
            return context.makeString("ScreenJS");
        }

        throw new JavaScriptValueException(context.makeError("Conversion not supported"), "Conversion not supported");
    }

}
