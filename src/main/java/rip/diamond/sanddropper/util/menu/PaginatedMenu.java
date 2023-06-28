package rip.diamond.sanddropper.util.menu;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.gui.type.util.Gui;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import me.goodestenglish.api.util.ItemBuilder;
import org.bukkit.Material;
import rip.diamond.sanddropper.util.CC;
import rip.diamond.sanddropper.util.GuiUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class PaginatedMenu extends Menu {

    protected ChestGui gui;

    public abstract String getName();

    public abstract List<GuiItem> getItems();

    @Override
    public Gui getGui() {
        gui = new ChestGui(6, getName());
        gui.setOnGlobalDrag(event -> event.setCancelled(true));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        updatePane();

        return gui;
    }

    public List<Pane> getExtraPanes() {
        return Collections.emptyList();
    }

    public void updatePane() {
        List<Pane> panes = new ArrayList<>();
        panes.addAll(getPanes(gui));
        panes.addAll(getExtraPanes());

        GuiUtil.updatePane(gui, panes.toArray(new Pane[0]));
    }

    private List<Pane> getPanes(ChestGui gui) {
        PaginatedPane paginatedPane = new PaginatedPane(1,1,7,4, Pane.Priority.LOW);
        paginatedPane.populateWithGuiItems(getItems());

        Pattern pattern = new Pattern(
                "111111111",
                "100000001",
                "100000001",
                "100000001",
                "100000001",
                "211111113"
        );
        PatternPane patternPane = new PatternPane(0, 0, 9, 6, Pane.Priority.LOW, pattern);
        patternPane.bindItem('1', new GuiItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).name(" ").build()));
        patternPane.bindItem('2', new GuiItem(new ItemBuilder(Material.ARROW).name(CC.SPRING_GREEN + "上一頁").build(), event -> {
            if (paginatedPane.getPage() > 0) {
                paginatedPane.setPage(paginatedPane.getPage() - 1);
                gui.update();
            }
        }));
        patternPane.bindItem('3', new GuiItem(new ItemBuilder(Material.ARROW).name(CC.SPRING_GREEN + "下一頁").build(), event -> {
            if (paginatedPane.getPage() < paginatedPane.getPages() - 1) {
                paginatedPane.setPage(paginatedPane.getPage() + 1);
                gui.update();
            }
        }));

        return List.of(patternPane, paginatedPane);
    }
}
