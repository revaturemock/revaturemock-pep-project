package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;


public class MessageDAO {

    // Connection connection = ConnectionUtil.getConnection();

   

    public List<Message> retrieveAllMessages() {

        List<Message> messages = new ArrayList<>();

        try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
    
            while(rs.next()) {
                Message message = new Message(
                    rs.getInt("message_id"), 
                    rs.getInt("posted_by"), 
                    rs.getString("message_text"), 
                    rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return messages;
    }

    public Message retrieveMessageById(int messageId) {

        try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
            preparedStatement.setInt(1, messageId);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                Message message = new Message(
                    rs.getInt("message_id"), 
                    rs.getInt("posted_by"), 
                    rs.getString("message_text"), 
                    rs.getLong("time_posted_epoch"));
                return message;          
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return new Message();
    }

}
