package eu.samosadlaker.lopcore.core;

import eu.samosadlaker.lopcore.commands.Admin;
import eu.samosadlaker.lopcore.commands.TabCompleter;
import eu.samosadlaker.lopcore.listeners.ChatListener;
import eu.samosadlaker.lopcore.listeners.JoinListener;
import eu.samosadlaker.lopcore.listeners.MotdListener;
import eu.samosadlaker.lopcore.listeners.QuitListener;
import eu.samosadlaker.lopcore.utils.AutoMessage;
import eu.samosadlaker.lopcore.utils.ScoreBoardUpdate;
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
    int automessageinterval = getConfig().getInt("message-interval") * 20;
    int scoreboardinterval = getConfig().getInt("scoreboard.update") * 20;
    int automessagetaskID;
    int scoreboardtaskID;

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
        registerCommands();
        registerTabCompleter();

        automessagetaskID = new AutoMessage().runTaskTimer(this, 0, automessageinterval).getTaskId();
        scoreboardtaskID = new ScoreBoardUpdate().runTaskTimer(this, 0, scoreboardinterval).getTaskId();

        logger.sendMessage(Colors.formatColor("&b-------------------------------------"));
        logger.sendMessage(Colors.formatColor("&aPlugin &b" + pdf.getName() + " &asuccessfully enabled"));
        logger.sendMessage(Colors.formatColor("&a" + pdf.getVersion() + " &f| &a" + pdf.getAuthors().toString() + " &f| &a" + pdf.getWebsite() ));
        logger.sendMessage(Colors.formatColor("&b-------------------------------------"));

    }

    /**
     * This is a simple function. Initializing all eventlisteners.
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-11
     */
    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new MotdListener(), this);
    }

    /**
     * This is a simple function. Initializing all commands.
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-13
     */
    private void registerCommands(){
        getCommand("lopcore").setExecutor(new Admin());
    }

    /**
     * This is a simple function. Initializing all tab-completer.
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-13
     */
    private void registerTabCompleter(){
        getCommand("lopcore").setTabCompleter(new TabCompleter());
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

        Bukkit.getScheduler().cancelTask(automessagetaskID);
        Bukkit.getScheduler().cancelTask(scoreboardtaskID);

        // Plugin shutdown logic
        logger.sendMessage(Colors.formatColor("&b-------------------------------------"));
        logger.sendMessage(Colors.formatColor("&4Plugin &b" + pdf.getName() + " &4successfully disabled"));
        logger.sendMessage(Colors.formatColor("&c" + pdf.getVersion() + " &f| &c" + pdf.getAuthors().toString() + " &f| &c" + pdf.getWebsite() ));
        logger.sendMessage(Colors.formatColor("&b-------------------------------------"));
    }
}
