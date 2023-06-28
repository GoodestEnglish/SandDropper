package rip.diamond.sanddropper.util.menu;

import com.github.stefvanschie.inventoryframework.gui.type.util.Gui;
import me.goodestenglish.api.util.Tasks;
import org.bukkit.entity.Player;

public abstract class Menu {

    public abstract Gui getGui();

    public void openGui(Player player) {
        Tasks.run(() -> getGui().show(player));
    }

}
