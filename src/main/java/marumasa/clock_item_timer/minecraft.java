package marumasa.clock_item_timer;

import marumasa.clock_item_timer.config.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class minecraft extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Events(
                new Config(this),
                this
        ), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
