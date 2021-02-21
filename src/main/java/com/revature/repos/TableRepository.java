package com.revature.repos;

import com.revature.reflectors.Metamodel;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The TableRepository class performs actions on the database directly.
 * They do this by accessing the Connection from the connectionFactory.
 * The Table Repository should be able to insert, remove, add, and update.
 */
public class TableRepository<T> implements CrudRepository {

    private ResultSet rs;

    /**
     * Runs an action that does not pull any information.
     * Things like Add, Remove should be done here because there is nothing to return.
     * Perhaps reconfigure to offer validation that the insert took place.
     */
    @Override
    public void save(String sql) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds all instances of something. Abstracted from its situation, just returns a list.
     * The sql passed in will be searching for multiple things.
     * @param sql
     * @return
     */
    @Override
    public ResultSet findAll(String sql) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
        } catch (SQLException e) {
            System.out.println("This SQL attempted to run, but there were problems: " + sql);
            e.printStackTrace();
        }

        return rs;
    }

    /**
     * Search by identifier. Just returns one Object based on the row.
     * @param sql SQL designating the search
     * @return row found from search.
     */
    @Override
    public ResultSet findById(String sql) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
        } catch (SQLException e) {
            System.out.println("This SQL attempted to run, but there were problems: " + sql);
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Updates an existing row. Searches for it, then updates it. Simple.
     * @param sql SQL designating the search
     * @return update's ability to update the value.
     */
    @Override
    public boolean update(String sql) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            return pstm.execute();
        } catch (SQLException e) {
            System.out.println("This SQL attempted to run, but there were problems: " + sql);
            e.printStackTrace();
        }

        return false;
    }


    /**
     * Deletes using the ID passed in.
     * @param sql SQL with the search for the ID
     * @return Validation that the value was deleted.
     */
    @Override
    public boolean deleteById(String sql) {
        int result = 0;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            result = pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("This SQL attempted to run, but there were problems: " + sql);
            e.printStackTrace();
        }

        return result == 1 ? true : false;
    }


}
