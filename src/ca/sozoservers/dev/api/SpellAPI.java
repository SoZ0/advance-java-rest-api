package ca.sozoservers.dev.api;

import java.util.HashMap;

import ca.sozoservers.dev.database.Database;
import ca.sozoservers.dev.models.SpellModel;
import ca.sozoservers.dev.server.HTTPExchange;
import ca.sozoservers.dev.server.HTTPHandler;
import ca.sozoservers.dev.server.HTTPRequest;
import ca.sozoservers.dev.server.HTTPResource;
import ca.sozoservers.dev.server.HTTPSocket;


public class SpellAPI implements HTTPHandler{

    @Override
    public void handle(HTTPExchange exchange) {
        HTTPSocket socket = exchange.socket();
        HTTPRequest request = exchange.request();
        HTTPResource resource = request.resouce();
        HashMap<String, String> queries = resource.queries();
        if(!queries.containsKey("id")){
            spellNotFoundError(socket);
        }else{
            try{
                Integer id = Integer.parseInt(queries.get("id")); 
                SpellModel model = new SpellModel();
                System.out.println(id);
                model.id = id;
                if(!Database.get(model, true)){
                    spellNotFoundError(socket);
                }else{
                    spellFound(socket, model);
                }

            }catch(NumberFormatException ex){
                spellNotFoundError(socket);
            }
        }
        socket.closePrintStream();
    }

    public void spellNotFoundError(HTTPSocket socket){
        String response = "no spell found";
        socket.sendResponseHeaders(400, -1);
        socket.sendResponseBody(response);
    }

    public void spellFound(HTTPSocket socket, SpellModel model){
        String response = model.toJSON();
        socket.sendResponseHeaders(200, response.length());
        socket.sendResponseBody(response);
    }
  
}
