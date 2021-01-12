package eu.samosadlaker.lopcore.core;

import eu.samosadlaker.lopcore.listeners.JoinListener;
import eu.samosadlaker.lopcore.listeners.QuitListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * <h1>Main class of plugin LopCore!</h1>
 *
 * @author  SamoSadlaker
 * @version 3.0
 * @since   2021-01-11
 */
public final class Main extends JavaPlugin {

    static Main plugin;
    CommandSender logger = Bukkit.getConsoleSender();
    PluginDescriptionFile pdf = this.getDescription();

    /**
     * Get access to main class from other class.
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-11
     * @return plugin
     */
    public static Main getPlugin() {
        return plugin;
    }

    /**
     * Enabling plugin logic
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-11
     */
    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;
        File file = new File(getDataFolder(), "config.yml");
        if(!file.exists()) {
            getConfig().options().copyDefaults(true);
            getConfig().options().copyHeader(true);
            saveDefaultConfig();
        }
        reloadConfig();

        registerListeners();

        logger.sendMessage(Colors.formatColor("&b-------------------------------------"));
        logger.sendMessage(Colors.formatColor("&aPlugin &b" + pdf.getName() + " &asuccessfully enabled"));
        logger.sendMessage(Colors.formatColor("&a" + pdf.getVersion() + " &f| &a" + pdf.getAuthors().toString() + " &f| &a" + pdf.getWebsite() ));
        logger.sendMessage(Colors.formatColor("&b-------------------------------------"));

    }

    /**
     * This is a simple function. Initializing all even listeners.
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-11
     */
    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);
    }

    /**
     * Disabling plugin logic
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-11
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.sendMessage(Colors.formatColor("&b-------------------------------------"));
        logger.sendMessage(Colors.formatColor("&4Plugin &b" + pdf.getName() + " &4successfully disabled"));
        logger.sendMessage(Colors.formatColor("&c" + pdf.getVersion() + " &f| &c" + pdf.getAuthors().toString() + " &f| &c" + pdf.getWebsite() ));
        logger.sendMessage(Colors.formatColor("&b-------------------------------------"));
    }
}
