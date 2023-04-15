package marumasa.clock_item_timer;

import marumasa.clock_item_timer.config.Config;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Events implements Listener {

    private final Config cfg;
    private final minecraft mc;

    private static final ItemStack clock = new ItemStack(Material.CLOCK);

    public Events(Config config, minecraft minecraft) {
        cfg = config;
        mc = minecraft;

        final ItemMeta itemMeta = clock.getItemMeta();
        if (itemMeta == null) return;
        itemMeta.setLore(cfg.lore);
    }

    @EventHandler
    private void onPlayerInteract(PlayerInteractEvent event) {

        final ItemStack itemStack = event.getItem();
        if (itemStack == null) return;

        final ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) return;

        final List<String> lore = itemMeta.getLore();

        if (lore == null) {

            itemMeta.setLore(cfg.lore);
            itemStack.setItemMeta(itemMeta);

        } else if (lore.equals(cfg.lore)) {

            final Action action = event.getAction();
            if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
                Timer.Reset(event.getPlayer(), cfg.message);
            } else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                Timer.Start_Stop(event.getPlayer(), cfg.message, mc);
            }
        }
    }
}
