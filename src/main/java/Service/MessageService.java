package Service;

import Model.Message;

import java.util.ArrayList;
import java.util.List;

import DAO.MessageDAO;

public class MessageService {
    
    private MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    

    public List<Message> retrieveAllMessages() {

        return messageDAO.retrieveAllMessages();
    }
    
    public Message retrieveMessageById(int messageId) {

        return messageDAO.retrieveMessageById(messageId);
    }
    
    
}
