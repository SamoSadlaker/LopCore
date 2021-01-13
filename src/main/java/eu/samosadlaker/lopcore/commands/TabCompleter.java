package eu.samosadlaker.lopcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>TabCompleter!</h1>
 *
 * @author  SamoSadlaker
 * @version 3.0
 * @since   2021-01-13
 */
public class TabCompleter implements org.bukkit.command.TabCompleter {

    List<String> arguments = new ArrayList<String>();

    /**
     * TabCompleter function
     *
     * @author  SamoSadlaker
     * @version 1.0
     * @since   2021-1-13
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if(arguments.isEmpty()){
            arguments.add("reload");
        }

        List<String> result = new ArrayList<String>();
        if(args.length == 1){
            for(String a : arguments){
                if(a.toLowerCase().startsWith(args[0].toLowerCase())){
                    result.add(a);
                }
            }
            return result;
        }

        return null;
    }
}
