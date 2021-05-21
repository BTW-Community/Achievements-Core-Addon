package net.minecraft.src;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.example.ExampleAchievements;


public class EventDispatcher implements CraftingListener, CollideWithPlayerListener {
	private static EventDispatcher instance;
	private static List listeners = new ArrayList();
	
	public static void init() {
		if (instance == null) {
			instance = getInstance();
			SlotCrafting.addListener(instance);
			EntityItem.addListener(instance);
		}
	}
	
	public static EventDispatcher getInstance() {
		if (instance == null) {
			instance = new EventDispatcher();
		}
		return instance;
	}
	
	public static void register(Object obj) {
		init();
		if (!listeners.contains(obj)) {
			listeners.add(obj);
		}
	}
	
	private boolean canHandleEvent(Method method, EventType type) {
		EventListener annotation = method.getAnnotation(EventListener.class);
		if (annotation != null && annotation.value() == type) { 
			return true;
		}
		return false;
	}
	
	private void handleEvent(EventType type, Object... args) {
		for (Object listener: listeners) {
			Method[] methods = listener.getClass().getMethods();
			for (Method method: methods) {
				if (canHandleEvent(method, type)) {
					try {
						method.invoke(listener, args);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void onCrafted(EntityPlayer player, ItemStack itemstack) {
		handleEvent(EventType.CRAFTED, player, itemstack);
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player, ItemStack itemstack) {
		handleEvent(EventType.PICKUP, player, itemstack);
	}
}
