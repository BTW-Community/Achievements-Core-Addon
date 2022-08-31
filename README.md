# Achievements Core Addon
Overhaul of the Achievement system for the Minecraft mod: Better Than Wolves.
This addon doesn't do much on its own, but it allows for addon authors to create their own custom achievements.

An example addon has been created to help you create your own achievements: [Example Achievements](https://github.com/Joel-Paul/Achievement-Example-Addon)

## Currently Implemented Events
<details>
  
  - CRAFTED  (crafting an item)
  - COOKED  (taking an item out of a campfire/oven)
  - BREWED  (taking an item out of a brewing stand)
  - PICKUP  (picking up an item)
  - KILLED  (killing an entity)
  - POTAL  (travelling between dimensions)
  - ENTITY_INTERACT  (right-clicking an entity)
  - CONVERTED_BLOCK  (converting a block, i.e. creating a work stump)
  - DEATH  (getting killed)
  - TRADED  (trading with a villager)
  - CURED (curing a zombie villager)
  - CONSUMED (eating/drinking an item)
</details>

If you would like to see more events, please create an Issue on GitHub or feel free to contribute one yourself.

## Compatibility
<details>
  <summary>Modified classes</summary>
  
  - Achievement (Client|Server)
  - AchievementList (Client|Server)
  - EntityItem (Client|Server)
  - EntityPlayer (Client|Server)
  - EntityPlayerMP (Client|Server)
  - EntityPlayerSP (Client)
  - FCBlockCampfire (Client|Server)
  - FCBlockFurnaceBrick (Client|Server)
  - FCEntityUrn (Client|Server)
  - FCEntityVillager (Client|Server)
  - GuiAchievements (Client)
  - ItemInWorldManager (Client|Server)
  - ItemStack (Client|Server)
  - SlotBrewingStandPotion (Client|Server)
  - SlotCrafting (Client|Server)
</details>
