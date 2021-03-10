package dev.alvileg.extendedessay;

import com.grinderwolf.swm.api.SlimePlugin;
import dev.alvileg.extendedessay.comands.LoadCommand;
import dev.alvileg.extendedessay.enums.CompressionAlgorithms;
import dev.alvileg.extendedessay.enums.WorldSize;
import dev.alvileg.extendedessay.loader.CustomSlimeLoader;
import dev.alvileg.extendedessay.loader.WorldLoader;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public final class ExtendedEssay extends JavaPlugin {

    @Getter
    private static ExtendedEssay instance;

    @Getter
    public static List<String> loadedWorlds = new ArrayList<>();

    @Getter
    SlimePlugin slimePlugin = (SlimePlugin) Bukkit.getPluginManager().getPlugin("SlimeWorldManager");

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getLogger().log(Level.INFO, "World Loader as been enabled successfully");
        this.getCommand("load").setExecutor(new LoadCommand());
        createWorldsFolder();
        for (String w : getWorlds()){
            Bukkit.getLogger().log(Level.INFO, "Loaded " + w);
        }
        File src = new File(ExtendedEssay.getInstance().getDataFolder() + "/worlds/" );
        slimePlugin.registerLoader("worlds", new CustomSlimeLoader(src));

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().log(Level.INFO, "Deleting worlds...");
        DeleteWorlds();
    }

    public void loadWorld(WorldSize size, CompressionAlgorithms algorithm){
        WorldLoader.loadWorld(size, algorithm);
    }
    public void createWorldsFolder(){
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        saveDefaultConfig();
        File f = new File(this.getDataFolder() + "/worlds");
        if(!f.exists()) f.mkdir();
    }
    public List<String> getWorlds(){
        File file = new File(this.getDataFolder() + "/worlds");
        List<String> directories = Arrays.asList(file.list(new FilenameFilter() {
    //Fucking macOS
            @Override
            public boolean accept(File dir, String name) {
                return !name.equals(".DS_Store");
            }
        }));
        //Fucking macOS
        directories.remove(".DS_Store");

        return directories;
    }

    public static void DeleteWorlds(){
        for(String w : getLoadedWorlds()){
            try {
                FileUtils.deleteDirectory(new File(ExtendedEssay.getInstance().getServer().getWorldContainer().getAbsolutePath() + "/" + w));
            }catch (IOException e){
                Bukkit.getLogger().log(Level.SEVERE, "Couldn't delete world " + w+  ". Fuck.") ;
            }
        }
    }
 }
