package rip.diamond.sanddropper.menu;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.gui.type.util.Gui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.goodestenglish.api.util.Checker;
import me.goodestenglish.api.util.Common;
import me.goodestenglish.api.util.ItemBuilder;
import me.goodestenglish.api.util.procedure.Procedure;
import me.goodestenglish.api.util.procedure.ProcedureType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import rip.diamond.sanddropper.SandDropper;
import rip.diamond.sanddropper.placer.impl.AsyncTorchLevitateImagePlacer;
import rip.diamond.sanddropper.util.CC;
import rip.diamond.sanddropper.util.ImageUtil;
import rip.diamond.sanddropper.util.menu.Menu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class CreateImageMenu extends Menu {

    private String name;
    private int width = -1;

    @Override
    public Gui getGui() {
        int row = 1;
        ChestGui gui = new ChestGui(row, "照片創建");
        StaticPane pane = new StaticPane(0,0,9, row);
        gui.setOnGlobalDrag(event -> event.setCancelled(true));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        pane.addItem(new GuiItem(new ItemBuilder(Material.OAK_SIGN).name("<yellow>照片名稱").lore("", "<gray>現時數值: " + (name == null ? "<red>未設置" : "<aqua>" + name), "", CC.CHARTREUSE + "點擊 ➜ 更改照片名稱").build(), event -> {
            Player player = (Player) event.getWhoClicked();

            player.closeInventory();
            Procedure.buildProcedure(player, "<yellow>請輸入你想創建的照片<gold>名稱", ProcedureType.CHAT, o -> {
                this.name = (String) o;
                openGui(player);
            });
        }), 0, 0);

        pane.addItem(new GuiItem(new ItemBuilder(Material.ITEM_FRAME).name("<yellow>照片寬度").lore("", "<gray>現時數值: " + (width == -1 ? "<red>未設置" : "<aqua>" + width), "", CC.CHARTREUSE + "點擊 ➜ 更改照片寬度").build(), event -> {
            Player player = (Player) event.getWhoClicked();

            player.closeInventory();
            Procedure.buildProcedure(player, "<yellow>請輸入你想創建的照片<gold>寬度", ProcedureType.CHAT, o -> {
                String width = (String) o;
                if (!Checker.isInteger(width)) {
                    Common.sendMessage(player, "<red>照片寬度必須為數字");
                    return;
                }
                this.width = Integer.parseInt(width);
                openGui(player);
            });
        }), 1, 0);

        pane.addItem(new GuiItem(new ItemBuilder(Material.EMERALD_BLOCK).name(CC.LIME_GREEN + "創建").lore("", CC.CHARTREUSE + "點擊 ➜ 創建照片").build(), event -> {
            Player player = (Player) event.getWhoClicked();

            if (name == null) {
                Common.sendMessage(player, "<red>名字不可以為 null");
                return;
            }
            if (width == -1) {
                Common.sendMessage(player, "<red>寬度不能為 -1");
                return;
            }
            try {
                File file = SandDropper.INSTANCE.getFileService().getImage(name);
                BufferedImage image = ImageIO.read(file);
                BufferedImage resized = ImageUtil.resize(image, width, width); //The width and height should be the same

                new AsyncTorchLevitateImagePlacer(player.getLocation(), resized).run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }), 8, 0);

        gui.addPane(pane);

        return gui;
    }
}
