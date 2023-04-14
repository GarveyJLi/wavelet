import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    //ArrayList<String> allStrings = new ArrayList<>();    
    
    HashMap<String, String> allStrings = new HashMap<String, String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return "Garvey's Search Engine!";
        } else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                allStrings.put(parameters[1], parameters[1]);
                return "Added "+ parameters[1] + " to the list!";
            } 
            else {
                return "404 Not Found!";
            }
        } else if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                String search = allStrings.get(parameters[1]);
                if (!search.equals(null)) {
                    return "Found " + parameters[1];
                }
                return "String not found :(";
            } 
            else {
                return "404 Not Found!";
            }
        }
        
        else {
            System.out.println("Path: " + url.getPath());
            return "404 Not Found!";
        }
    }
}

public class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
    
}
