package issame.achievements_core;

import issame.achievements_core.achievements.Achievement;
import issame.achievements_core.achievements.AchievementTab;
import issame.achievements_core.achievements.AchievementTabList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

import java.util.Iterator;
import java.util.List;

public class CommandAchievement extends CommandBase {
    @Override
    public String getCommandName() {
        return "achievement";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args) {
        if (args.length < 2) {
            throw new WrongUsageException("commands.achievement.usage");
        }

        if (!args[0].equalsIgnoreCase("unlock") && !args[0].equalsIgnoreCase("lock")) {
            throw new CommandException("commands.achievement.unknownOption", args[0]);
        }
        boolean unlock = args[0].equalsIgnoreCase("unlock");
        String op = args[0].toLowerCase();

        Achievement achievement = AchievementTabList.getAchievement(args[1]);
        if (achievement == null && !args[1].equals("*")) {
            throw new CommandException("commands.achievement.unknownAchievement", args[1]);
        }

        EntityPlayerMP player;
        if (args.length >= 3) {
            player = func_82359_c(commandSender, args[2]);
        } else {
            player = getCommandSenderAsPlayer(commandSender);
        }

        if (achievement == null) {
            // Apply to all achievements
            for (Iterator<AchievementTab> it = AchievementTabList.iterator(); it.hasNext(); ) {
                AchievementTab tab = it.next();
                for (Achievement a : tab) {
                    int amount = unlock ? a.threshold : 0;
                    AchievementsCore.trigger(a, player, amount, true);
                }
            }
            notifyAdmins(commandSender, "commands.achievement." + op + ".success.all", player.getCommandSenderName());
        } else {
            achievement.recursivelyTrigger(player, unlock);
            notifyAdmins(commandSender, "commands.achievement." + op + ".success.one", achievement.getUnlocalizedName(), player.getCommandSenderName());
        }
    }

    @Override
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args) {
        switch (args.length) {
            case 1:
                return getListOfStringsMatchingLastWord(args, "unlock", "lock");
            case 2:
                return getListOfStringsFromIterableMatchingLastWord(args, AchievementTabList.getAllNames());
            case 3:
                return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
        }
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return index == 2;
    }
}
