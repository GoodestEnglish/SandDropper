package rip.diamond.sanddropper.register;

import rip.diamond.sanddropper.SandDropper;

public class DatapackRegister {

    private static boolean finished = false;

    public static void setup(SandDropper plugin) {
        if (finished) {
            throw new UnsupportedOperationException("DatapackRegister has already run once!");
        }

        // TODO: 27/6/2023

        finished = true;
    }

}
