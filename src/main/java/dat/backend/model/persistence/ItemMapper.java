package dat.backend.model.persistence;

import dat.backend.model.entities.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class ItemMapper {
    static List<Item> getItem(ConnectionPool connectionPool) {
        List<Item> itemList = new ArrayList<>();

        String sql = "select * from item";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int id = rs.getInt("item_id");
                    String name = rs.getString("name");
                    boolean done = rs.getBoolean("done");
                    Timestamp created = rs.getTimestamp("created");
                    String username = rs.getString("username");

                    Item newItem = new Item(id,name,done,created,username);
                    itemList.add(newItem);
                }

            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return itemList;
    }
}
