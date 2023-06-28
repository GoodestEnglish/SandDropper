package rip.diamond.sanddropper.util;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.gui.type.DropperGui;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import me.goodestenglish.api.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class GuiUtil {

    public static void checkIsTheSamePlayer(Player player, HumanEntity entity) {
        if (player != entity) {
            throw new UnsupportedOperationException("The player who opens the inventory does not match with the player who clicks on the item");
        }
    }

    public static PatternPane createBorderPane() {
        Pattern pattern = new Pattern(
                "111111111",
                "100000001",
                "100000001",
                "100000001",
                "100000001",
                "111111111"
        );
        PatternPane patternPane = new PatternPane(0, 0, 9, 6, Pane.Priority.LOW, pattern);
        patternPane.bindItem('1', new GuiItem(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ").build()));
        return patternPane;
    }

    public static void insertPane(ChestGui gui, Pane... panes) {
        for (Pane pane : panes) {
            gui.addPane(pane);
        }
    }

    public static void insertPane(DropperGui gui, Pane... panes) {
        for (Pane pane : panes) {
            gui.getContentsComponent().addPane(pane);
        }
    }

    public static void updatePane(ChestGui gui, Pane... panes) {
        gui.getPanes().clear();
        insertPane(gui, panes);
        gui.update();
    }

    public static void updatePane(DropperGui gui, Pane... panes) {
        gui.getContentsComponent().getPanes().clear();
        insertPane(gui, panes);
        gui.update();
    }

    public static void replacePane(ChestGui gui, Pane oldPane, Pane newPane) {
        gui.getPanes().remove(oldPane);
        gui.addPane(newPane);
        gui.update();
    }

}
