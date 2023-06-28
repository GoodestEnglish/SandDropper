package rip.diamond.sanddropper.placer.impl;

import com.google.common.collect.ImmutableList;
import me.goodestenglish.api.util.Common;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.material.Torch;
import org.bukkit.scheduler.BukkitRunnable;
import rip.diamond.sanddropper.SandDropper;
import rip.diamond.sanddropper.placer.ImagePlacer;
import rip.diamond.sanddropper.util.CC;
import rip.diamond.sanddropper.util.ImageUtil;
import rip.diamond.sanddropper.util.SandColor;
import rip.diamond.sanddropper.util.ShapeUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AsyncTorchLevitateImagePlacer extends ImagePlacer {
    private final List<BlockFace> facing = ImmutableList.of(BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST); // TODO: 27/6/2023 need?

    public AsyncTorchLevitateImagePlacer(Location location, BufferedImage image) {
        super(location, image);
    }

    public void run() {
        Common.broadcastMessage(CC.WHEAT + "正在開始運行 AsyncTorchLevitateImagePlacer...");

        //Display generation status message
        new BukkitRunnable() {
            @Override
            public void run() {
                if (finished) {
                    cancel();
                    return;
                }
                Common.broadcastMessage(CC.WHEAT + "已完成進度: " + CC.LIME_GREEN + SandDropper.DECIMAL.format(getPercentage()) + "% " + CC.GRAY + "(" + done + "/" + total + ")");
            }
        }.runTaskTimerAsynchronously(SandDropper.INSTANCE, 0L, 1L);

        final long start = System.currentTimeMillis();
        final int[][] rgb = CompletableFuture.supplyAsync(() -> ImageUtil.convertToRGB(image)).join();
        int y = 0;

        if (rgb.length != rgb[0].length) {
            throw new UnsupportedOperationException("The array is not a matrix!");
        }

        Material[][] materials = new Material[rgb.length][rgb.length];

        for (int x = 0; x < rgb.length; x++) {
            for (int z = 0; z < rgb[x].length; z++) {
                materials[x][z] = SandColor.getSandByNearestColor(new Color(rgb[x][z]));
            }
        }

        List<ShapeUtil.Location> locations = ShapeUtil.createSpiral(rgb.length);
        locations.sort(Comparator.comparing(ShapeUtil.Location::getOrder));

        for (ShapeUtil.Location loc : locations) {
            int x = loc.getX(), z = loc.getZ();

            Block block = location.clone().add(x, y++, z).getBlock();
            Block torch = block.getLocation().clone().add(0, -1 ,0).getBlock();
            BlockFace face = facing.stream().filter(blockFace -> torch.getRelative(blockFace).getType() != Material.AIR).findAny().orElse(null);

            block.setType(materials[x][z]);
            torch.setType(face == null ? Material.TORCH : Material.WALL_TORCH);

            if (torch.getType() == Material.WALL_TORCH && face != null) {
                Directional directional = (Directional) torch.getBlockData();
                directional.setFacing(face.getOppositeFace());
                torch.setBlockData(directional, false);
            }

            done++;
        }

        finished = true;
        Common.broadcastMessage(CC.LIME_GREEN + "完成執行! " + CC.GRAY + "(" + (System.currentTimeMillis() - start) + "ms)");
    }
}
