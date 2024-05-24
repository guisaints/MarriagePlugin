/*
 * Copyright (c) 2024.
 *
 * Developer: DevGui
 *
 */

package com.minecraft.marriage.view;

import me.saiintbrisson.minecraft.View;
import me.saiintbrisson.minecraft.ViewContext;
import lombok.val;
import org.bukkit.entity.Player;

public class SkinView extends View {

    public SkinView() {
        super(4, "Skins de casamento");
        setCancelOnPickup(true);
        setCancelOnClick(true);
    }

    @Override
    protected void onRender(ViewContext context) {
        val player = (Player) context.getPlayer();

    }
}
