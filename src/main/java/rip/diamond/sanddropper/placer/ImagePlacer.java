package rip.diamond.sanddropper.placer;

import org.bukkit.Location;

import java.awt.image.BufferedImage;

public abstract class ImagePlacer {

    protected final Location location;
    protected final BufferedImage image;

    protected long done = 0;
    protected final long total;
    protected boolean finished = false;

    public ImagePlacer(Location location, BufferedImage image) {
        this.location = location;
        this.image = image;
        this.total = (long) image.getWidth() * image.getHeight();
    }

    public double getPercentage() {
        return (double) done / total * 100;
    }

    public abstract void run();

}
