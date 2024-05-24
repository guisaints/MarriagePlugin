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
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class UserFactory {

    private final MarriagePlugin plugin;

    public  void create(Player player) {
        val userCache = plugin.getUserCache();
        val storage = plugin.getUserStorage();

        val find = storage.find(player.getUniqueId().toString());
        if(find != null) {
            userCache.addCachedElement(find);
            return;
        }

        val user = User.builder()
                .uuid(player.getUniqueId())
                .username(player.getName())
                .build();

        storage.insert(user);
        userCache.addCachedElement(user);
    }

    public void remove(Player player) {
        val userCache = plugin.getUserCache();
        val storage = plugin.getUserStorage();

        val find = userCache.getByID(player.getUniqueId());
        if(find == null) return;

        storage.update(find);
        userCache.removeCachedElement(find);
    }

}
