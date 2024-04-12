package dev.slangware.ultralight.util;

import net.janrupf.ujr.api.event.UlKeyCode;
import net.janrupf.ujr.api.event.UlKeyEventModifiers;
import org.lwjgl.input.Keyboard;

import java.util.EnumSet;

public class UltralightKeyMapper {

    /**
     * Generates the Ultralight key event modifiers based on the LWJGL key event modifiers.
     *
     * @return  an EnumSet of UlKeyEventModifiers representing the Ultralight key event modifiers.
     */public static EnumSet<UlKeyEventModifiers> lwjglModifiersToUltralight() {
        EnumSet<UlKeyEventModifiers> result = EnumSet.noneOf(UlKeyEventModifiers.class);

        // Check if left or right shift key is pressed
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            result.add(UlKeyEventModifiers.SHIFT);
        }

        // Check if left or right control key is pressed
        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
            result.add(UlKeyEventModifiers.CTRL);
        }

        // Check if left or right alt key is pressed
        if (Keyboard.isKeyDown(Keyboard.KEY_LMENU) || Keyboard.isKeyDown(Keyboard.KEY_RMENU)) {
            result.add(UlKeyEventModifiers.ALT);
        }

        // Check if left or right meta key is pressed
        if (Keyboard.isKeyDown(Keyboard.KEY_LMETA) || Keyboard.isKeyDown(Keyboard.KEY_RMETA)) {
            result.add(UlKeyEventModifiers.META);
        }

        return result;
    }

    /**
     * Converts a LWJGL key code to an Ultralight key code.
     *
     * @param  lwjglKey   the LWJGL key code to be converted
     * @return            the corresponding Ultralight key code
     */  public static int lwjglKeyToUltralight(int lwjglKey) {
        switch (lwjglKey) {
            case Keyboard.KEY_SPACE:
                return UlKeyCode.SPACE;
            case Keyboard.KEY_APOSTROPHE:
                return UlKeyCode.OEM_7;
            case Keyboard.KEY_COMMA:
                return UlKeyCode.OEM_COMMA;
            case Keyboard.KEY_MINUS:
                return UlKeyCode.OEM_MINUS;
            case Keyboard.KEY_PERIOD:
                return UlKeyCode.OEM_PERIOD;
            case Keyboard.KEY_SLASH:
                return UlKeyCode.OEM_2;
            case Keyboard.KEY_0:
                return UlKeyCode.NUMBER_0;
            case Keyboard.KEY_1:
                return UlKeyCode.NUMBER_1;
            case Keyboard.KEY_2:
                return UlKeyCode.NUMBER_2;
            case Keyboard.KEY_3:
                return UlKeyCode.NUMBER_3;
            case Keyboard.KEY_4:
                return UlKeyCode.NUMBER_4;
            case Keyboard.KEY_5:
                return UlKeyCode.NUMBER_5;
            case Keyboard.KEY_6:
                return UlKeyCode.NUMBER_6;
            case Keyboard.KEY_7:
                return UlKeyCode.NUMBER_7;
            case Keyboard.KEY_8:
                return UlKeyCode.NUMBER_8;
            case Keyboard.KEY_9:
                return UlKeyCode.NUMBER_9;
            case Keyboard.KEY_SEMICOLON:
                return UlKeyCode.OEM_1;
            case Keyboard.KEY_EQUALS:
            case Keyboard.KEY_NUMPADEQUALS:
                return UlKeyCode.OEM_PLUS;
            case Keyboard.KEY_A:
                return UlKeyCode.A;
            case Keyboard.KEY_B:
                return UlKeyCode.B;
            case Keyboard.KEY_C:
                return UlKeyCode.C;
            case Keyboard.KEY_D:
                return UlKeyCode.D;
            case Keyboard.KEY_E:
                return UlKeyCode.E;
            case Keyboard.KEY_F:
                return UlKeyCode.F;
            case Keyboard.KEY_G:
                return UlKeyCode.G;
            case Keyboard.KEY_H:
                return UlKeyCode.H;
            case Keyboard.KEY_I:
                return UlKeyCode.I;
            case Keyboard.KEY_J:
                return UlKeyCode.J;
            case Keyboard.KEY_K:
                return UlKeyCode.K;
            case Keyboard.KEY_L:
                return UlKeyCode.L;
            case Keyboard.KEY_M:
                return UlKeyCode.M;
            case Keyboard.KEY_N:
                return UlKeyCode.N;
            case Keyboard.KEY_O:
                return UlKeyCode.O;
            case Keyboard.KEY_P:
                return UlKeyCode.P;
            case Keyboard.KEY_Q:
                return UlKeyCode.Q;
            case Keyboard.KEY_R:
                return UlKeyCode.R;
            case Keyboard.KEY_S:
                return UlKeyCode.S;
            case Keyboard.KEY_T:
                return UlKeyCode.T;
            case Keyboard.KEY_U:
                return UlKeyCode.U;
            case Keyboard.KEY_V:
                return UlKeyCode.V;
            case Keyboard.KEY_W:
                return UlKeyCode.W;
            case Keyboard.KEY_X:
                return UlKeyCode.X;
            case Keyboard.KEY_Y:
                return UlKeyCode.Y;
            case Keyboard.KEY_Z:
                return UlKeyCode.Z;
            case Keyboard.KEY_LBRACKET:
                return UlKeyCode.OEM_4;
            case Keyboard.KEY_BACKSLASH:
                return UlKeyCode.OEM_5;
            case Keyboard.KEY_RBRACKET:
                return UlKeyCode.OEM_6;
            case Keyboard.KEY_GRAVE:
                return UlKeyCode.OEM_3;
            case Keyboard.KEY_ESCAPE:
                return UlKeyCode.ESCAPE;
            case Keyboard.KEY_RETURN:
                return UlKeyCode.RETURN;
            case Keyboard.KEY_TAB:
                return UlKeyCode.TAB;
            case Keyboard.KEY_BACK:
                return UlKeyCode.BACK;
            case Keyboard.KEY_INSERT:
                return UlKeyCode.INSERT;
            case Keyboard.KEY_DELETE:
                return UlKeyCode.DELETE;
            case Keyboard.KEY_RIGHT:
                return UlKeyCode.RIGHT;
            case Keyboard.KEY_LEFT:
                return UlKeyCode.LEFT;
            case Keyboard.KEY_DOWN:
                return UlKeyCode.DOWN;
            case Keyboard.KEY_UP:
                return UlKeyCode.UP;
            case Keyboard.KEY_PRIOR:
                return UlKeyCode.PRIOR;
            case Keyboard.KEY_NEXT:
                return UlKeyCode.NEXT;
            case Keyboard.KEY_HOME:
                return UlKeyCode.HOME;
            case Keyboard.KEY_END:
                return UlKeyCode.END;
            case Keyboard.KEY_CAPITAL:
                return UlKeyCode.CAPITAL;
            case Keyboard.KEY_SCROLL:
                return UlKeyCode.SCROLL;
            case Keyboard.KEY_NUMLOCK:
                return UlKeyCode.NUMLOCK;
            case Keyboard.KEY_SYSRQ:
                return UlKeyCode.SNAPSHOT;
            case Keyboard.KEY_PAUSE:
                return UlKeyCode.PAUSE;
            case Keyboard.KEY_F1:
                return UlKeyCode.F1;
            case Keyboard.KEY_F2:
                return UlKeyCode.F2;
            case Keyboard.KEY_F3:
                return UlKeyCode.F3;
            case Keyboard.KEY_F4:
                return UlKeyCode.F4;
            case Keyboard.KEY_F5:
                return UlKeyCode.F5;
            case Keyboard.KEY_F6:
                return UlKeyCode.F6;
            case Keyboard.KEY_F7:
                return UlKeyCode.F7;
            case Keyboard.KEY_F8:
                return UlKeyCode.F8;
            case Keyboard.KEY_F9:
                return UlKeyCode.F9;
            case Keyboard.KEY_F10:
                return UlKeyCode.F10;
            case Keyboard.KEY_F11:
                return UlKeyCode.F11;
            case Keyboard.KEY_F12:
                return UlKeyCode.F12;
            case Keyboard.KEY_F13:
                return UlKeyCode.F13;
            case Keyboard.KEY_F14:
                return UlKeyCode.F14;
            case Keyboard.KEY_F15:
                return UlKeyCode.F15;
            case Keyboard.KEY_F16:
                return UlKeyCode.F16;
            case Keyboard.KEY_F17:
                return UlKeyCode.F17;
            case Keyboard.KEY_F18:
                return UlKeyCode.F18;
            case Keyboard.KEY_F19:
                return UlKeyCode.F19;
            case Keyboard.KEY_NUMPAD0:
                return UlKeyCode.NUMPAD0;
            case Keyboard.KEY_NUMPAD1:
                return UlKeyCode.NUMPAD1;
            case Keyboard.KEY_NUMPAD2:
                return UlKeyCode.NUMPAD2;
            case Keyboard.KEY_NUMPAD3:
                return UlKeyCode.NUMPAD3;
            case Keyboard.KEY_NUMPAD4:
                return UlKeyCode.NUMPAD4;
            case Keyboard.KEY_NUMPAD5:
                return UlKeyCode.NUMPAD5;
            case Keyboard.KEY_NUMPAD6:
                return UlKeyCode.NUMPAD6;
            case Keyboard.KEY_NUMPAD7:
                return UlKeyCode.NUMPAD7;
            case Keyboard.KEY_NUMPAD8:
                return UlKeyCode.NUMPAD8;
            case Keyboard.KEY_NUMPAD9:
                return UlKeyCode.NUMPAD9;
            case Keyboard.KEY_DECIMAL:
                return UlKeyCode.DECIMAL;
            case Keyboard.KEY_DIVIDE:
                return UlKeyCode.DIVIDE;
            case Keyboard.KEY_MULTIPLY:
                return UlKeyCode.MULTIPLY;
            case Keyboard.KEY_SUBTRACT:
                return UlKeyCode.SUBTRACT;
            case Keyboard.KEY_ADD:
                return UlKeyCode.ADD;
            case Keyboard.KEY_RSHIFT:
            case Keyboard.KEY_LSHIFT:
                return UlKeyCode.SHIFT;
            case Keyboard.KEY_LCONTROL:
            case Keyboard.KEY_RCONTROL:
                return UlKeyCode.CONTROL;
            case Keyboard.KEY_LMENU:
            case Keyboard.KEY_RMENU:
                return UlKeyCode.MENU;
            case Keyboard.KEY_LMETA:
                return UlKeyCode.LWIN;
            case Keyboard.KEY_RMETA:
                return UlKeyCode.RWIN;
        }
        return UlKeyCode.UNKNOWN;
    }

}
