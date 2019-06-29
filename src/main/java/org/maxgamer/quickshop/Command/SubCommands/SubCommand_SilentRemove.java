package org.maxgamer.quickshop.Command.SubCommands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.*;
import org.maxgamer.quickshop.Command.CommandProcesser;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.Shop.Shop;
import org.maxgamer.quickshop.Util.MsgUtil;

public class SubCommand_SilentRemove implements CommandProcesser {
    private QuickShop plugin = QuickShop.instance;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] cmdArg) {
        if (cmdArg.length < 4)
            return true;
        Player p = (Player) sender;
        Shop shop = plugin.getShopManager().getShop(new Location(Bukkit.getWorld(cmdArg[0]), Integer.valueOf(cmdArg[1]),
                Integer.valueOf(cmdArg[2]), Integer.valueOf(cmdArg[3])));
        if (shop == null)
            return true;
        if (shop.getModerator().isModerator(p.getUniqueId()) || sender.hasPermission("quickshop.other.destroy")) {
            shop.onUnload();
            shop.delete();
        } else {
            sender.sendMessage(ChatColor.RED + MsgUtil.getMessage("no-permission"));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] cmdArg) {
        return new ArrayList<>();
    }
}
