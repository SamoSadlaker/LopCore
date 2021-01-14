package eu.samosadlaker.lopcore.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreBoardUpdate extends BukkitRunnable {

    @Override
    public void run() {
        if (Bukkit.getOnlinePlayers() == null){
            this.cancel();
        } else {
            for(Player p : Bukkit.getOnlinePlayers()){
                ScoreboardManager.createBoard(p);
            }
        }
    }
}
