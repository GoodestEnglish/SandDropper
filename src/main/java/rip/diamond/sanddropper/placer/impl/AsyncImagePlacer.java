package rip.diamond.sanddropper.placer.impl;

import me.goodestenglish.api.util.Common;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;
import rip.diamond.sanddropper.SandDropper;
import rip.diamond.sanddropper.placer.ImagePlacer;
import rip.diamond.sanddropper.util.CC;
import rip.diamond.sanddropper.util.ImageUtil;
import rip.diamond.sanddropper.util.SandColor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;

public class AsyncImagePlacer extends ImagePlacer {
    public AsyncImagePlacer(Location location, BufferedImage image) {
        super(location, image);
    }

    public void run() {
        Common.broadcastMessage(CC.WHEAT + "正在開始運行 AsyncImagePlacer...");

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

        for (int x = 0; x < rgb.length; x++) {
            for (int z = 0; z < rgb[x].length; z++) {
                Material material = SandColor.getSandByNearestColor(new Color(rgb[x][z]));
                location.clone().add(x, 0, z).getBlock().setType(material);

                done++;
            }
        }
        finished = true;
        Common.broadcastMessage(CC.LIME_GREEN + "完成執行! " + CC.GRAY + "(" + (System.currentTimeMillis() - start) + "ms)");
    }

}
