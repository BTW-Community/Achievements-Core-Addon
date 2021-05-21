package net.minecraft.src;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class AchievementEventDispatcher implements CraftingListener {
	private static List listeners = new ArrayList();
	
	public static void init() {
		SlotCrafting.addListener(new AchievementEventDispatcher());
	}
	
	public static void register(Object obj) {
		init();
		if (!listeners.contains(obj)) {
			listeners.add(obj);
		}
	}
	
	private boolean canHandleEvent(Method method, AchievementType type) {
		AchievementListener annotation = method.getAnnotation(AchievementListener.class);
		if (annotation != null && annotation.value() == type) { 
			return true;
		}
		return false;
	}

	@Override
	public void onCrafted(EntityPlayer player, ItemStack itemstack) {
		for (Object listener: listeners) {
			Method[] methods = listener.getClass().getMethods();
			for (Method method: methods) {
				if (canHandleEvent(method, AchievementType.CRAFTED)) {
					try {
						method.invoke(listener, player, itemstack);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
