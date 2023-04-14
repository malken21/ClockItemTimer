package marumasa.clock_item_timer;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Timer {

    private static final Map<Player, PlayerTimerData> playerDataMap = new HashMap<>();

    public static void Start_Stop(Player player) {

        PlayerTimerData playerData = playerDataMap.get(player);
        if (playerData == null) {
            playerData = new PlayerTimerData();
            playerDataMap.put(player, playerData);

            player.sendMessage("start");

            return;
        }

        switch (playerData.status) {
            case 1 -> {
                player.sendMessage("stop");
                playerData.status = 2;
            }
            case 2 -> {
                player.sendMessage("restart");
                playerData.status = 1;
            }
        }
    }

    public static void Reset(Player player) {

        PlayerTimerData playerData = playerDataMap.get(player);
        if (playerData == null) return;

        player.sendMessage("reset");

        playerData.remove();
        playerDataMap.remove(player);
    }
}
