package com.jackting.lib_webview.command.mainprocess;


import com.jackting.lib_webview.command.base.Commands;
import com.jackting.lib_webview.utils.WebConstants;

public class BaseLevelCommands extends Commands {

    public BaseLevelCommands() {
    }

    @Override
    protected int getCommandLevel() {
        return WebConstants.LEVEL_BASE;
    }
}
