package com.carter.speers.brancher.command;

public sealed abstract class FreeCommand extends Command permits InitCommand {

    protected FreeCommand() {
        super(null);
    }

    public abstract void execute();
}
