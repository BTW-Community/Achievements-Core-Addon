package issame.achievements_core.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EventDispatcher {
    private static final List<Object> listeners = new ArrayList<>();

    public static void register(Object listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    private static void handleEvent(EventType type, Object... args) {
        for (Object listener: listeners) {
            invokeMethods(listener, type, args);
        }
    }

    private static void invokeMethods(Object listener, EventType type, Object... args) {
        Method[] methods = listener.getClass().getMethods();
        for (Method method: methods) {
            tryToInvoke(listener, method, type, args);
        }
    }

    private static void tryToInvoke(Object listener, Method method, EventType type, Object... args) {
        if (canHandleEvent(method, type)) {
            try {
                method.invoke(listener, args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean canHandleEvent(Method method, EventType type) {
        EventListener annotation = method.getAnnotation(EventListener.class);
        return annotation != null && annotation.value() == type;
    }
}
