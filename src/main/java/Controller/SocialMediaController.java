package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;

import Model.Message;
import Service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
EDITED LOCALLY!!!
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    MessageService messageService = new MessageService();
    
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("/messages", this::retrieveAllMessages);
        app.get("/messages/{message_id}", context -> {
            String messageId = context.pathParam("message_id");

            Message message = messageService.retrieveMessageById(Integer.valueOf(messageId));
            System.out.println(message);
            context.json(message);
            context.status(200);
        });
        return app;
    }

    private void retrieveAllMessages(Context context) {
        List<Message> messages = messageService.retrieveAllMessages();

        context.json(messages);
        context.status(200);
    }



}
