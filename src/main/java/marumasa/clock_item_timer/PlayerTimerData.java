package marumasa.clock_item_timer;

import marumasa.clock_item_timer.config.Message;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerTimerData {
    public long timer = 0;

    /* status
    1 タイマーが起動中 初期状態
    2 タイマーが一時停止
     */
    public int status = 1;

    public void remove() {
        tick.cancel();
        removeActionBar();
    }

    public PlayerTimerData(Player player, Message message, minecraft minecraft) {
        target = player;
        msg = message;

        tick = new tickRunnable();
        tick.runTaskTimer(minecraft, 1, 1);
    }

    private final tickRunnable tick;
    private final Player target;
    private final Message msg;

    private class tickRunnable extends BukkitRunnable {
        @Override
        public void run() {
            sendActionBar();
            if (status == 2) return;
            timer++;
        }
    }

    private void sendActionBar() {
        target.spigot().sendMessage(
                ChatMessageType.ACTION_BAR,
                new TextComponent(Timer.toText(msg.timer, timer))
        );
    }

    private void removeActionBar() {
        target.spigot().sendMessage(
                ChatMessageType.ACTION_BAR,
                new TextComponent()
        );
    }
}
