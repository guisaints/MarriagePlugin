/*
 * Copyright (c) 2024.
 *
 * Developer: DevGui
 *
 */

package com.minecraft.marriage.core.data.user;

import com.minecraft.marriage.core.cache.Cache;

import java.util.UUID;

public class UserCache extends Cache<User> {

    public User getByID(UUID uuid) {
        return getCached($ -> $.getUuid().equals(uuid));
    }
}
