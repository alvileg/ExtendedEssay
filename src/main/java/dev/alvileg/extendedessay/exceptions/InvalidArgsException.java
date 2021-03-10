package dev.alvileg.extendedessay.exceptions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class InvalidArgsException extends Exception{

    public InvalidArgsException(String message, CommandSender sender){
        if(sender instanceof Player){
            Player player = (Player) sender;
            player.sendMessage(ChatColor.RED + message);
        }else {
            Bukkit.getLogger().log(Level.SEVERE, message);
        }

    }


}
