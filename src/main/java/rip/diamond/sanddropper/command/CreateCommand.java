package rip.diamond.sanddropper.command;

import me.goodestenglish.api.util.command.annotation.Command;
import me.goodestenglish.api.util.command.annotation.Require;
import me.goodestenglish.api.util.command.annotation.Sender;
import org.bukkit.entity.Player;
import rip.diamond.sanddropper.menu.CreateImageMenu;

public class CreateCommand {

    @Command(name = "", desc = "")
    @Require("op")
    public void root(@Sender Player player) {
        new CreateImageMenu().openGui(player);
    }

}
