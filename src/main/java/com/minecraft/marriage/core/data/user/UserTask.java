/*
 * Copyright (c) 2024.
 *
 * Developer: DevGui
 *
 */

package com.minecraft.marriage.core.data.user;

import com.minecraft.marriage.MarriagePlugin;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.Bukkit;

@RequiredArgsConstructor
public class UserTask implements Runnable {

    private final MarriagePlugin plugin;


    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach($ -> {
            val user = plugin.getUserCache().getByID($.getUniqueId());
            plugin.getUserStorage().update(user);
        });
    }
}

