package marumasa.clock_item_timer;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {

    public final List<String> lore;

    public Config(final minecraft plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();

        lore = config.getStringList("lore");
    }
}
