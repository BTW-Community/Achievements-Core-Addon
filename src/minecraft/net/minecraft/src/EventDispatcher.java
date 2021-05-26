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


public class EventDispatcher {
	private static EventDispatcher instance;
	private static List listeners = new ArrayList();
	
	public static void init() {
		getInstance();
	}
	
	public static EventDispatcher getInstance() {
		if (instance == null) {
			instance = new EventDispatcher();
		}
		return instance;
	}
	
	public static void register(Object obj) {
		if (!listeners.contains(obj)) {
			listeners.add(obj);
		}
	}
	
	private static boolean canHandleEvent(Method method, EventType type) {
		EventListener annotation = method.getAnnotation(EventListener.class);
		if (annotation != null && annotation.value() == type) { 
			return true;
		}
		return false;
	}
	
	private static void handleEvent(EventType type, Object... args) {
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
	
	public static void onCrafted(EntityPlayer player, ItemStack itemstack) {
		handleEvent(EventType.CRAFTED, player, itemstack);
	}
	
	public static void onCooked(EntityPlayer player, ItemStack itemstack) {
		handleEvent(EventType.COOKED, player, itemstack);
	}
	
	public static void onBrewed(EntityPlayer player, ItemStack itemstack) {
		handleEvent(EventType.BREWED, player, itemstack);
	}
	
	public static void onCollideWithPlayer(EntityPlayer player, ItemStack itemstack) {
		handleEvent(EventType.PICKUP, player, itemstack);
	}
	
	public static void onKilled(EntityPlayer player, EntityLiving entity) {
		handleEvent(EventType.KILLED, player, entity);
	}
	
	public static void onTraveledDimension(EntityPlayer player, int dimension) {
		handleEvent(EventType.PORTAL, player, dimension);
	}
	
	public static void onNeutered(EntityPlayer player) {
		handleEvent(EventType.NEUTERED, player);
	}
}
