package eu.samosadlaker.lopcore.utils;

import com.sun.org.apache.xerces.internal.xs.StringList;
import eu.samosadlaker.lopcore.core.Colors;
import eu.samosadlaker.lopcore.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

/**
 * <h1>Creating Scoreboard!</h1>
 *
 * @author  SamoSadlaker
 * @version 1.0
 * @since   2021-01-14
 */
public class ScoreboardManager {

    static Main plugin = Main.getPlugin();
    static FileConfiguration config = plugin.getConfig();

    /**
     * This function create & set scoreboard with player deaths.
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-14
     */
    public static void createBoard (Player p){
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = board.registerNewObjective("Deaths", "dummy", Colors.formatColor(config.getString("scoreboard.title")));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score spacer2 = objective.getScore(Colors.formatColor(config.getString("scoreboard.spacer")));
        spacer2.setScore(0);

        int nextPlayer = 1;
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            String message = config.getString("scoreboard.format")
                    .replace("%player%", player.getName() + "")
                    .replace("%deaths%", player.getStatistic(Statistic.DEATHS) + "");
            objective.getScore(Colors.formatColor(message)).setScore(nextPlayer);
            nextPlayer++;
        }

        p.setScoreboard(board);
    }

    /**
     * This function updates scoreboard..
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-13
     */
    public static void updateBoard (Player p){
        int update = config.getInt("scoreboard.update") * 20;
        new BukkitRunnable(){
            @Override
            public void run(){
                if(p == null){
                    this.cancel();
                } else {
                    createBoard(p);
                }
            }
        }.runTaskTimer(plugin, 0, update);
    }

}
