package dev.alvileg.extendedessay.loader;

import com.grinderwolf.swm.api.SlimePlugin;
import com.grinderwolf.swm.api.exceptions.*;
import com.grinderwolf.swm.api.loaders.SlimeLoader;
import com.grinderwolf.swm.api.world.SlimeWorld;
import com.grinderwolf.swm.api.world.properties.SlimeProperties;
import com.grinderwolf.swm.api.world.properties.SlimePropertyMap;
import dev.alvileg.extendedessay.ExtendedEssay;
import dev.alvileg.extendedessay.enums.CompressionAlgorithms;
import dev.alvileg.extendedessay.enums.WorldSize;
import dev.alvileg.extendedessay.exceptions.InvalidArgsException;
import dev.alvileg.extendedessay.stats.StatsMessage;
import dev.alvileg.extendedessay.utils.FileSize;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.text.ExtendedMessageFormat;
import org.bukkit.*;
import org.bukkit.entity.Player;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.common.base.Stopwatch;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WorldLoader {



    public static void loadWorld(WorldSize size, CompressionAlgorithms algorithm){
        UnloadAll();
        switch (size){
            case BIG:
                getAlgorithm(WorldSize.BIG, algorithm);
                break;
            case MEDIUM:
                getAlgorithm(WorldSize.MEDIUM, algorithm);
                break;
            case SMALL:
                getAlgorithm(WorldSize.SMALL, algorithm);
                break;
            default:
                Bukkit.getLogger().log(Level.SEVERE, "Something fucked up hard");
        }
    }
    public static void getAlgorithm(WorldSize size, CompressionAlgorithms algorithm){
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "Attempting to load " + ChatColor.GRAY +  ChatColor.BOLD + size.toString().substring(0,1) +  size.toString().substring(1).toLowerCase() + ChatColor.RESET + ChatColor.DARK_GRAY + " world with " + ChatColor.GREEN + ChatColor.BOLD + algorithm);
        switch (algorithm){
            case ZIP:
                LoadZIP(size);
                break;
            case ZLIB:
                LoadZLIB(size);
                break;
            case ZSTD:
                LoadZSTD(size);
                break;
            case SLIME:
                LoadSLIME(size);
                break;

        }

    }

    public static void LoadZIP(WorldSize size){
        ExtendedEssay.DeleteWorlds();



    }
    public static void LoadZLIB(WorldSize size){
        ExtendedEssay.DeleteWorlds();
        Stopwatch timer = Stopwatch.createStarted();
        List<String> normalWorlds =  new ArrayList<String>(ExtendedEssay.getInstance().getWorlds());
        for(String w : ExtendedEssay.getInstance().getWorlds()){
            if(w.contains(".")){
                normalWorlds.remove(w);
            }
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "Found " + ChatColor.DARK_GREEN + normalWorlds.size() + ChatColor.GREEN + " world(s)");

        if(!normalWorlds.contains(size.getFileName().toString())){
            Bukkit.getLogger().log(Level.SEVERE, "The world requested for the size template is not abviable");
            return;
        }
        if(normalWorlds.size() > 0){


            File srcFile = new File(ExtendedEssay.getInstance().getDataFolder() + "/worlds/" + size.getFileName());
            File srvRoot = new File(ExtendedEssay.getInstance().getServer().getWorldContainer().getAbsolutePath() + "/" + size.getFileName());

            try {
                srvRoot.mkdir();
                FileUtils.copyDirectory(srcFile, srvRoot);
            } catch (IOException e) {
                e.printStackTrace();
                Bukkit.getLogger().log(Level.SEVERE, "Something went horribly wrong while copying the world");
            }
            ExtendedEssay.getInstance().getServer().createWorld(new WorldCreator(size.getFileName()));
            for(Player p : Bukkit.getOnlinePlayers()){
                p.teleport(new Location(Bukkit.getWorld(size.getFileName()), size.getX(), size.getY(),size.getZ()));
            }
            ExtendedEssay.getLoadedWorlds().add(size.getFileName());
            StatsMessage.sendStatsMessage(size.getFileName(),timer.stop().toString(), FileSize.size(srcFile.toPath()), srcFile.toPath().toString(), size, "ZIP");

        }else{
            Bukkit.broadcastMessage(ChatColor.RED + "Couldn't find any worlds");
        }


    }
    public static void LoadZSTD(WorldSize size){

    }
    public static void LoadSLIME(WorldSize size){
        Stopwatch timer = Stopwatch.createStarted();



        List<String> normalWorlds =  new ArrayList<String>(ExtendedEssay.getInstance().getWorlds());
        for(String w : ExtendedEssay.getInstance().getWorlds()){
            if(!w.contains(".slime")){
                normalWorlds.remove(w);
            }
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "Found " + ChatColor.DARK_GREEN + normalWorlds.size() + ChatColor.GREEN + " world(s)");
        Bukkit.broadcastMessage(Arrays.toString(normalWorlds.toArray()));
        if(!normalWorlds.contains(size.getFileName().toString() + ".slime")){
            Bukkit.getLogger().log(Level.SEVERE, "The world requested for the size template is not abviable");
            return;
        }
        if(normalWorlds.size() > 0){
        File worldDir = new File(ExtendedEssay.getInstance().getDataFolder() + "/worlds/" );
        File slimeDir = new File(ExtendedEssay.getInstance().getServer().getWorldContainer() + "/slime_worlds/" + size.getFileName() + ".slime");
        String worldName = size.getFileName();
        SlimeLoader loader = ExtendedEssay.getInstance().getSlimePlugin().getLoader("worlds");

        try {
            ExtendedEssay.getInstance().getSlimePlugin().importWorld(worldDir, worldName, loader);
        } catch (WorldAlreadyExistsException | InvalidWorldException | WorldLoadedException | WorldTooBigException | IOException ex) {
            ex.printStackTrace();
        }
        try {
            // Note that this method should be called asynchronously
            SlimePropertyMap properties = new SlimePropertyMap();
            properties.setString(SlimeProperties.DIFFICULTY, "peaceful");
            properties.setInt(SlimeProperties.SPAWN_X, size.getX());
            properties.setInt(SlimeProperties.SPAWN_Y, size.getY());
            properties.setInt(SlimeProperties.SPAWN_Z, size.getZ());
            SlimeWorld world = ExtendedEssay.getInstance().getSlimePlugin().loadWorld(loader, size.getFileName(), true, properties);

            // This method must be called synchronously
            ExtendedEssay.getInstance().getSlimePlugin().generateWorld(world);

            for(Player p : Bukkit.getOnlinePlayers()){
                p.teleport(new Location(Bukkit.getWorld(size.getFileName()), size.getX(), size.getY(),size.getZ()));
            }
            StatsMessage.sendStatsMessage(size.getFileName(),timer.stop().toString(), FileSize.size(slimeDir.toPath()), slimeDir.toPath().toString(), size, "SLIME");
        } catch (UnknownWorldException | IOException | CorruptedWorldException | NewerFormatException | WorldInUseException e) {
           e.printStackTrace();
        }
        }else{
            Bukkit.broadcastMessage(ChatColor.RED + "Couldn't find any worlds");
        }
    }

    public static void UnloadAll(){
        for (World w : Bukkit.getWorlds()){
            if(Bukkit.getWorld(w.getUID()).getPlayers().size() > 0){
                for(Player p : Bukkit.getWorld(w.getUID()).getPlayers()){
                    p.teleport(new Location(Bukkit.getWorld("world"), 0, 64, 0));
                }
            }
                Bukkit.getServer().unloadWorld(w.getName(), false);

        }
    }


}
