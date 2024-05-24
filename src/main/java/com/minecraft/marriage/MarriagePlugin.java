/*
 * Copyright (c) 2024.
 *
 * Developer: DevGui
 *
 */

package com.minecraft.marriage;

import com.minecraft.marriage.core.data.user.User;
import com.minecraft.marriage.core.data.user.UserCache;
import com.minecraft.marriage.core.data.user.UserFactory;
import com.minecraft.marriage.core.data.user.UserTask;
import com.minecraft.marriage.core.plugin.AbstractPlugin;
import com.minecraft.marriage.database.MySQLProvider;
import com.minecraft.marriage.hook.PlaceholderHook;
import com.minecraft.marriage.scoreboard.Scoreboard;
import com.minecraft.marriage.task.ScoreboardTask;
import com.minecraft.marriage.view.MenuView;
import com.minecraft.marriage.view.SkinView;
import com.minecraft.marriage.view.others.MarriedView;
import lombok.Getter;
import org.bukkit.Bukkit;

@Getter
public class MarriagePlugin extends AbstractPlugin {

    private MySQLProvider mySQLProvider;
    private User user;
    private UserCache userCache;
    private UserTask userTask;
    private UserFactory userStorage;
    private UserFactory userFactory;
    private Scoreboard scoreboard;
    private ScoreboardTask scoreboardTask;
    @Override
    public void load() {

        mySQLProvider = new MySQLProvider(
                getConfig().getString("mysql.host"),
                getConfig().getInt("mysql.port"),
                getConfig().getString("mysql.database"),
                getConfig().getString("mysql.username"),
                getConfig().getString("mysql.password")
        );

        scoreboard = new Scoreboard();
        scoreboardTask = new ScoreboardTask();
        userCache = new UserCache();
        userFactory = new UserFactory(this);
        userTask = new UserTask(this);

        new PlaceholderHook().register();
    }

    @Override
    public void start() {
        saveDefaultConfig();
        mySQLProvider.init();
        registry();
    }

    @Override
    public void disable() {
        mySQLProvider.closeConnection();
        Bukkit.getOnlinePlayers().forEach(userFactory::remove);

    }


   public void registry() {
        registerCommands();
        registerListener();

        registerViews(
                new SkinView(),
                new MenuView(),
                new MarriedView()
        );

       Bukkit.getScheduler().runTaskTimerAsynchronously(
               this,
               new UserTask(this),
               0L, 8 * 60 * 20L
       );
    }



    public static MarriagePlugin getInstance() {
        return getPlugin(MarriagePlugin.class);
    }
}
