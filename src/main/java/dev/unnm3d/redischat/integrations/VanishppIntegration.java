package dev.unnm3d.redischat.integrations;

import dev.unnm3d.redischat.RedisChat;
import dev.unnm3d.redischat.api.VanishIntegration;
import net.thecommandcraft.vanishpp.api.VanishAPI;
import net.thecommandcraft.vanishpp.api.events.VanishStateChangeEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class VanishppIntegration implements VanishIntegration {

    public VanishppIntegration(RedisChat plugin) {
        plugin.getServer().getPluginManager().registerEvents(new VanishListener(), plugin);
    }

    @Override
    public boolean canSee(CommandSender viewer, String playerName) {
        if (!(viewer instanceof Player viewerPlayer)) return true;
        Player target = viewer.getServer().getPlayerExact(playerName);
        if (target == null) return true;
        return VanishAPI.get().canSee(viewerPlayer, target);
    }

    @Override
    public boolean isVanished(Player player) {
        return VanishAPI.get().isVanished(player);
    }

    private static class VanishListener implements Listener {
        @EventHandler
        public void onVanishStateChange(VanishStateChangeEvent event) {
            // API handles state internally; event hook reserved for future use
        }
    }
}
