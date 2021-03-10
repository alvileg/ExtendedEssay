package dev.alvileg.extendedessay.comands;

import dev.alvileg.extendedessay.ExtendedEssay;
import dev.alvileg.extendedessay.enums.CompressionAlgorithms;
import dev.alvileg.extendedessay.enums.WorldSize;
import dev.alvileg.extendedessay.exceptions.InvalidArgsException;
import dev.alvileg.extendedessay.loader.WorldLoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoadCommand implements CommandExecutor {
    WorldLoader loader = new WorldLoader();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 2) {new InvalidArgsException("Too many arguments!", sender); return false; }
        if(args.length < 1) {new InvalidArgsException("Missing arguments", sender); return false;}
        try
        {
            WorldSize arg1 = WorldSize.valueOf(args[0].toUpperCase());
            CompressionAlgorithms arg2 = CompressionAlgorithms.valueOf(args[1].toUpperCase());
            ExtendedEssay.getInstance().loadWorld(arg1, arg2);
        }
        catch (IllegalArgumentException e) {new InvalidArgsException("Invalid arguments", sender); e.printStackTrace();}

        return true;
    }
}
