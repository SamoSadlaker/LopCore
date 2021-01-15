package eu.samosadlaker.lopcore.commands;

import eu.samosadlaker.lopcore.core.Colors;
import eu.samosadlaker.lopcore.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * <h1>Admin commands!</h1>
 *
 * @author  SamoSadlaker
 * @version 3.0
 * @since   2021-01-13
 */
public class Admin implements CommandExecutor {

    static Main plugin = Main.getPlugin();
    FileConfiguration config = plugin.getConfig();
    String prefix = config.getString("prefix") + " &f» &3";


    /**
     * Check commands and arguments...
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-13
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(label.equalsIgnoreCase("lopcore")){
            if(args.length == 0){
                sender.sendMessage(Colors.formatColor(prefix + "&bPlease use &a/lopcore [reload]"));
                return false;
            }

            if (!sender.hasPermission(config.getString("permission"))){
                return false;
            }

            if(args[0].equalsIgnoreCase("reload")){
                plugin.getPluginLoader().disablePlugin(plugin);
                plugin.getPluginLoader().enablePlugin(plugin);
                sender.sendMessage(Colors.formatColor(prefix + config.getString("reload-completed")));
                return true;
            }
        }

        return false;
    }
}
