package com.minecraft.marriage.core.data.user;

import com.minecraft.marriage.MarriagePlugin;
import com.minecraft.marriage.database.MySQLProvider;
import lombok.val;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserStorage {
    private final MySQLProvider service = MarriagePlugin.getInstance().getMySQLProvider();

    public void insert(User user) {
        try (Connection connection = service.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO `system_marriage` VALUES(?,?,?);")) {
                statement.setString(1, user.getUuid().toString());
                statement.setString(2, user.getUsername());
                statement.setInt(3, user.getPartner());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void update(User user) {
        try (Connection connection = service.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE `system_marriage` SET kills=? WHERE user_id=?;")) {
                statement.setInt(1, user.getPartner());
                statement.setString(2, user.getUuid().toString());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public User find(String id) {
        try (Connection connection = service.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM `system_marriage` WHERE user_id=?;")) {
                statement.setString(1, id);
                try (ResultSet set = statement.executeQuery()) {
                    if (!set.next()) return null;

                    val uuid = UUID.fromString(set.getString("user_id"));
                    val name = set.getString("user_name");
                    val partner = set.getInt("partners");

                    return User.builder().uuid(uuid).username(name).partner(partner).build();
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}