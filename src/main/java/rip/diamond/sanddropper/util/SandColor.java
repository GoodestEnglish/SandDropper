package rip.diamond.sanddropper.util;

import org.bukkit.Material;

import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class SandColor {

    public static Map<Color, Material> values = new HashMap<>();

    public static void init() {
        //Sand
        put(new Color(219, 212, 160), Material.SAND);
        put(new Color(169, 88, 33), Material.RED_SAND);
        //Concrete Powder
        put(new Color(227, 229, 229), Material.WHITE_CONCRETE_POWDER);
        put(new Color(155, 155, 148), Material.LIGHT_GRAY_CONCRETE_POWDER);
        put(new Color(155, 155, 148), Material.GRAY_CONCRETE_POWDER);
        put(new Color(26, 28, 33), Material.BLACK_CONCRETE_POWDER);
        put(new Color(125, 85, 54), Material.BROWN_CONCRETE_POWDER);
        put(new Color(168, 54, 51), Material.RED_CONCRETE_POWDER);
        put(new Color(228, 133, 33), Material.ORANGE_CONCRETE_POWDER);
        put(new Color(233, 199, 53), Material.YELLOW_CONCRETE_POWDER);
        put(new Color(125, 189, 42), Material.LIME_CONCRETE_POWDER);
        put(new Color(97, 118, 46), Material.GREEN_CONCRETE_POWDER);
        put(new Color(37, 146, 156), Material.CYAN_CONCRETE_POWDER);
        put(new Color(75, 182, 214), Material.LIGHT_BLUE_CONCRETE_POWDER);
        put(new Color(70, 73, 167), Material.BLUE_CONCRETE_POWDER);
        put(new Color(132, 56, 178), Material.PURPLE_CONCRETE_POWDER);
        put(new Color(193, 84, 184), Material.MAGENTA_CONCRETE_POWDER);
        put(new Color(229, 153, 181), Material.PINK_CONCRETE_POWDER);
    }

    private static void put(Color color, Material material) {
        values.put(color, material);
    }
    
    public static Material getSandByNearestColor(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        Map.Entry<Color, Material> result = values.entrySet().stream().min(Comparator.comparingInt(entry -> {
            int r1 = entry.getKey().getRed();
            int g1 = entry.getKey().getGreen();
            int b1 = entry.getKey().getBlue();
            return Math.abs(r - r1) + Math.abs(g - g1) + Math.abs(b - b1);
        })).orElseThrow(NoSuchElementException::new);

        return result.getValue();
    }
}
