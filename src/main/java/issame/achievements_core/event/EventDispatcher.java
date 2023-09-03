package issame.achievements_core.event;

import net.minecraft.src.*;

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

    public static void onCrafted(EntityPlayer player, ItemStack itemstack) {
        handleEvent(EventType.CRAFTED, player, itemstack);
    }

    public static void onCooked(EntityPlayer player, ItemStack itemstack) {
        handleEvent(EventType.COOKED, player, itemstack);
    }

    public static void onBrewed(EntityPlayer player, ItemStack itemstack) {
        handleEvent(EventType.BREWED, player, itemstack);
    }

    public static void onPickup(EntityPlayer player, ItemStack itemstack) {
        handleEvent(EventType.PICKUP, player, itemstack);
    }

    public static void onKilled(EntityPlayer player, EntityLiving entity) {
        handleEvent(EventType.KILLED, player, entity);
    }

    public static void onTraveledDimension(EntityPlayer player, int dimension) {
        handleEvent(EventType.PORTAL, player, dimension);
    }

    public static void onEntityInteraction(EntityPlayer player, Entity entity, ItemStack heldItemStack) {
        handleEvent(EventType.ENTITY_INTERACT, player, entity, heldItemStack);
    }

    public static void onBlockConverted(EntityPlayer player, Block block, int metadata) {
        handleEvent(EventType.CONVERTED_BLOCK, player, block, metadata);
    }

    public static void onDeath(EntityPlayer player, DamageSource damageSource) {
        handleEvent(EventType.DEATH, player, damageSource);
    }

    public static void onTraded(EntityPlayer player, MerchantRecipe recipe) {
        handleEvent(EventType.TRADED, player, recipe);
    }

    public static void onCured(EntityPlayer player, EntityZombie zombieVillager) {
        handleEvent(EventType.CURED, player, zombieVillager);
    }

    public static void onEaten(EntityPlayer player, ItemStack foodStack) {
        handleEvent(EventType.CONSUMED, player, foodStack);
    }

    private static void handleEvent(EventType type, Object... args) {
        for (Object listener : listeners) {
            invokeMethods(listener, type, args);
        }
    }

    private static void invokeMethods(Object listener, EventType type, Object... args) {
        Method[] methods = listener.getClass().getMethods();
        for (Method method : methods) {
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
