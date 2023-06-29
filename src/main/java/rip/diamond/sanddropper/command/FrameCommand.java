package rip.diamond.sanddropper.command;

import me.goodestenglish.api.util.Common;
import me.goodestenglish.api.util.command.annotation.Command;
import me.goodestenglish.api.util.command.annotation.Require;
import me.goodestenglish.api.util.command.annotation.Sender;
import org.bukkit.entity.Player;
import rip.diamond.sanddropper.menu.CreateImageMenu;
import rip.diamond.sanddropper.util.CC;

public class FrameCommand {

    public static int FRAME_SIZE = 32 + 2;

    @Command(name = "", desc = "")
    @Require("op")
    public void root(@Sender Player player) {
        new CreateImageMenu().openGui(player);
    }

    @Command(name = "", desc = "")
    @Require("op")
    public void root(@Sender Player player, int size) {
        FRAME_SIZE = size + 2;
        Common.sendMessage(player, CC.LIME_GREEN + "成功把框架大小設置為 " + CC.AQUA + FRAME_SIZE + CC.GRAY + " (" + size + " + 2)");
    }

}
