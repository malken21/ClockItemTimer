package marumasa.clock_item_timer.config;

import marumasa.clock_item_timer.minecraft;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {

    public final List<String> lore;
    public final Message message;

    public Config(final minecraft plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();

        message = new Message(config);

        lore = config.getStringList("lore");
    }
}
