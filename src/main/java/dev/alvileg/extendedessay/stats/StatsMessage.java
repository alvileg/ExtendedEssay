package dev.alvileg.extendedessay.stats;

import dev.alvileg.extendedessay.enums.WorldSize;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

import static jdk.nashorn.internal.objects.NativeMath.round;

public class StatsMessage {
    public static final String HR = ChatColor.STRIKETHROUGH + StringUtils.repeat(" ", 80);
    public static void sendStatsMessage(String worldName, String time, double size, String path, WorldSize world_size, String algorithm ){
       for(Player p : Bukkit.getOnlinePlayers()){
           p.sendMessage(ChatColor.GREEN + HR);
            p.sendMessage(ChatColor.WHITE + "Loaded world " + worldName + " from " +  ChatColor.YELLOW + path );
            p.sendMessage(ChatColor.WHITE + "Normal world size: " + ChatColor.GREEN + getWorldSize(world_size) +  " bytes");
           p.sendMessage(ChatColor.WHITE + "Loaded world size: " + ChatColor.GREEN + size + " bytes");
           p.sendMessage(ChatColor.WHITE + "File deflated: " + ChatColor.GREEN + sizeReduced(size, world_size));
           p.sendMessage(ChatColor.WHITE + "Time taken: " + ChatColor.GREEN + time);
           p.sendMessage(ChatColor.GREEN + HR);
       }

    }

    public static int getWorldSize(WorldSize size){
        switch (size){
            case SMALL:
                return 2601972;
            case MEDIUM:
                return 127415336;
            case BIG:
                return 29758774;
        }
        return 0;
    }

    public static String sizeReduced(double size, WorldSize worldSize){
        if((getWorldSize(worldSize) - size) < 100){
            return "N/A";
        }
        double raw  = size/getWorldSize(worldSize);
        DecimalFormat df = new DecimalFormat("###.########%");
        raw = Double.valueOf(df.format(raw));
        return raw + "%";
    }
}
