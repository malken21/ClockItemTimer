package marumasa.clock_item_timer;

import marumasa.clock_item_timer.config.Message;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Timer {

    private static final Map<Player, PlayerTimerData> playerDataMap = new HashMap<>();

    private static TextComponent MessageGenerator(String text, Message message, PlayerTimerData playerData) {
        String time = toText(message.timer, playerData.timer);
        TextComponent textComponent = new TextComponent(String.format(text, time));
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, time));
        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(message.copy)));
        return textComponent;
    }

    public static void Start_Stop(Player player, Message message, minecraft minecraft) {

        PlayerTimerData playerData = playerDataMap.get(player);
        if (playerData == null) {
            playerData = new PlayerTimerData(player, message, minecraft);
            playerDataMap.put(player, playerData);

            player.sendMessage(message.start);

            return;
        }

        switch (playerData.status) {
            case 1 -> {
                player.spigot().sendMessage(MessageGenerator(message.stop, message, playerData));
                playerData.status = 2;
            }
            case 2 -> {
                player.spigot().sendMessage(MessageGenerator(message.restart, message, playerData));
                playerData.status = 1;
            }
        }
    }


    public static void Reset(Player player, Message message) {

        PlayerTimerData playerData = playerDataMap.get(player);
        if (playerData == null) return;

        player.spigot().sendMessage(MessageGenerator(message.reset, message, playerData));

        playerData.remove();
        playerDataMap.remove(player);
    }

    public static String toText(String timer, long tick) {//actionbar

        int ms, sec, min, hour;

        hour = (int) (tick / 20) / 3600;
        min = (int) ((tick / 20) % 3600) / 60;
        sec = (int) (tick / 20) % 60;
        ms = (int) (tick % 20) * 5;

        return String.format(
                timer,
                hour,
                min,
                sec,
                ms
        );
    }
}
