/*
 * Copyright (c) 2024.
 *
 * Developer: DevGui
 *
 */

package com.minecraft.marriage.view.others;

import me.saiintbrisson.minecraft.View;
import me.saiintbrisson.minecraft.ViewContext;
import lombok.val;
import org.bukkit.entity.Player;

public class MarriedView extends View {

    public MarriedView() {
        super(4, "Jogadores casados");
        setCancelOnPickup(true);
        setCancelOnClick(true);
    }

    @Override
    protected void onRender(ViewContext context) {
        val player = (Player) context.getPlayer();

    }
}
