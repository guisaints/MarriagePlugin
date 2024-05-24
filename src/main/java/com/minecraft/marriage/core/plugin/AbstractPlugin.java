/*
 * Copyright (c) 2024.
 *
 * Developer: DevGui
 *
 */

package com.minecraft.marriage.core.plugin;

import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.View;
import me.saiintbrisson.minecraft.ViewFrame;
import me.saiintbrisson.minecraft.command.message.MessageType;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import lombok.val;

public abstract class AbstractPlugin extends JavaPlugin {
    private final ViewFrame viewFrame = new ViewFrame(this);


    @Override
    public void onLoad() {
        this.getLogger().info(() -> "marriage enabling...");
        load();
    }

    @Override
    public void onEnable() {
        this.getLogger().info(() -> "marriage enabled.");
        start();
    }

    @Override
    public void onDisable() {
        this.getLogger().info(() -> "marriage disabled.");
        disable();
    }


    protected void registerListener(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }
    protected void registerViews(View... views) {
        viewFrame.register(views);
    }

    protected void registerCommands(Object... objects) {
        val frame = new BukkitFrame(this);
        val messageHolder = frame.getMessageHolder();

        messageHolder.setMessage(MessageType.NO_PERMISSION,"§cVocê não possuí permissão para utilizar este comando.");
        messageHolder.setMessage(MessageType.INCORRECT_USAGE, "§cUtilize /{usage}.");

        frame.registerCommands(objects);
    }
    public abstract void load();
    public abstract void start();
    public abstract void disable();
}
