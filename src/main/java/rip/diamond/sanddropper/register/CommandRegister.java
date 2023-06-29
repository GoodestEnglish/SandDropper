package rip.diamond.sanddropper.register;

import me.goodestenglish.api.util.command.CommandService;
import rip.diamond.sanddropper.SandDropper;
import rip.diamond.sanddropper.command.CreateCommand;
import rip.diamond.sanddropper.command.FrameCommand;
import rip.diamond.sanddropper.command.TestCommand;

public class CommandRegister {

    private static boolean finished = false;

    public static void setup(SandDropper plugin) {
        if (finished) {
            throw new UnsupportedOperationException("CommandRegister has already run once!");
        }

        CommandService drink = SandDropper.API.getDrink();

        drink.register(new TestCommand(), "test");
        drink.register(new CreateCommand(), "create");
        drink.register(new FrameCommand(), "frame");

        drink.registerCommands();

        finished = true;
    }

}
