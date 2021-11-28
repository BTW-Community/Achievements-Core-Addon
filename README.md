# Achievements Core Addon
Overhaul of the Achievement system for the Minecraft mod: Better Than Wolves.
This addon doesn't do much on its own, but it allows for addon authors to create their own custom achievements.

An example addon has been created to help you create your own achievements (*Currently this is slightly outdated, but may still be helpful*): [Achievements Example Addon](https://github.com/Joel-Paul/Achievement-Example-Addon)

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
</details>

If you would like to see more events, please create an Issue on GitHub or feel free to contribute one yourself.

## List of modified classes
<details>
  <summary>Client</summary>
  
  - Achievement
  - AchievementList
  - EntityItem
  - EntityPlayer
  - EntityPlayerMP
  - EntityPlayerSP
  - FCBlockCampfire
  - FCBlockFurnaceBrick
  - GuiAchievements
  - ItemInWorldManager
  - SlotBrewingStandPotion
  - SlotCrafting
</details>

<details>
  <summary>Server</summary>
  
  - Achievement
  - AchievementList
  - EntityItem
  - EntityPlayer
  - EntityPlayerMP
  - FCBlockCampfire
  - FCBlockFurnaceBrick
  - ItemInWorldManager
  - SlotBrewingStandPotion
  - SlotCrafting
</details>