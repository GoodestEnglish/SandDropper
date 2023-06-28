package rip.diamond.sanddropper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import me.goodestenglish.api.DiamondAPI;
import org.bukkit.plugin.java.JavaPlugin;
import rip.diamond.sanddropper.listener.GeneralListener;
import rip.diamond.sanddropper.register.CommandRegister;
import rip.diamond.sanddropper.register.DatapackRegister;
import rip.diamond.sanddropper.service.FileService;
import rip.diamond.sanddropper.service.ImageService;
import rip.diamond.sanddropper.util.SandColor;

import java.text.DecimalFormat;

public final class SandDropper extends JavaPlugin {

    public static SandDropper INSTANCE;
    public static DiamondAPI API;
    public static DecimalFormat DECIMAL = new DecimalFormat("0.##");
    public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Getter private FileService fileService;
    @Getter private ImageService imageService;

    @Override
    public void onEnable() {
        INSTANCE = this;
        API = DiamondAPI.get().enable(this);

        SandColor.init();

        fileService = new FileService("plugins/" + getDescription().getName() + "/pictures");
        imageService = new ImageService();

        CommandRegister.setup(this);
        DatapackRegister.setup(this);

        getServer().getPluginManager().registerEvents(new GeneralListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
