package rip.diamond.sanddropper.service;

import java.io.File;

public class FileService {

    private final String path;

    public FileService(String path) {
        this.path = path;

        createFolder();
    }

    private void createFolder() {
        File folder = new File(path);
        if (!folder.exists()) folder.mkdirs();
    }

    public File getImage(String name) {
        return new File(path + File.separator + name);
    }

    public boolean isImageExists(String name) {
        return getImage(name).exists();
    }

}
