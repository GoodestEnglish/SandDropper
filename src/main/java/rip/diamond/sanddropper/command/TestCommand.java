package rip.diamond.sanddropper.command;

import me.goodestenglish.api.util.Common;
import me.goodestenglish.api.util.command.annotation.Command;
import me.goodestenglish.api.util.command.annotation.Require;
import me.goodestenglish.api.util.command.annotation.Sender;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import rip.diamond.sanddropper.SandDropper;
import rip.diamond.sanddropper.placer.impl.AsyncTorchLevitateImagePlacer;
import rip.diamond.sanddropper.util.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestCommand {

    @Command(name = "", desc = "")
    @Require("op")
    public void root(@Sender Player player, int r, int g, int b) {
        for (Material m: Material.values()) {
            if (m.hasGravity()) {
                Common.broadcastMessage(m.name());
            }
        }
    }

    @Command(name = "2", desc = "")
    @Require("op")
    public void root2(@Sender Player player, String name, int width, int height) throws IOException {
        File file = SandDropper.INSTANCE.getFileService().getImage(name);
        BufferedImage image = ImageIO.read(file);
        BufferedImage resized = ImageUtil.resize(image, width, height);

        new AsyncTorchLevitateImagePlacer(player.getLocation(), resized).run();
    }

}
