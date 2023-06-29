package rip.diamond.sanddropper.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import rip.diamond.sanddropper.command.FrameCommand;

public class FrameListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        ItemStack itemStack = event.getItem();
        Block block = event.getClickedBlock();

        if (action == Action.LEFT_CLICK_BLOCK && itemStack != null && itemStack.getType() == Material.DIAMOND && block != null) {
            event.setCancelled(true);

            Location location = block.getLocation();

            //TODO: Stupid way to create a wall, fix this afterwards
            for (int x = 0; x < FrameCommand.FRAME_SIZE; x++) {
                for (int z = 0; z < FrameCommand.FRAME_SIZE; z++) {
                    location.clone().add(x, 1, z).getBlock().setType(Material.BLACK_CONCRETE);
                }
            }

            for (int x = 1; x < FrameCommand.FRAME_SIZE - 1; x++) {
                for (int z = 1; z < FrameCommand.FRAME_SIZE - 1; z++) {
                    location.clone().add(x, 1, z).getBlock().setType(Material.AIR);
                }
            }
        }
    }

}
