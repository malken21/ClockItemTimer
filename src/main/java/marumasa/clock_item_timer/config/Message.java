package marumasa.clock_item_timer.config;

import org.bukkit.configuration.file.FileConfiguration;

public class Message {
    public final String start;
    public final String stop;
    public final String restart;
    public final String reset;
    public final String copy;
    public final String timer;

    public Message(FileConfiguration config) {
        start = config.getString("message.start");
        stop = config.getString("message.stop");
        restart = config.getString("message.restart");
        reset = config.getString("message.reset");
        copy = config.getString("message.copy");
        timer = config.getString("message.timer");
    }
}
