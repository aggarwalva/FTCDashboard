import javax.xml.crypto.Data;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ObjectClient {
    public static void main(String[] args){
        Socket client = null;
        String input = "", receivedData;
        DataObject data;
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        ObjectInputStream in = null;
        PrintWriter out = null;
        boolean connected = false;

        System.out.println("Waiting for server");
        while(!connected){
            try {
                client = new Socket("localhost", 4321);
                in = new ObjectInputStream(client.getInputStream());
                out = new PrintWriter(client.getOutputStream(),
                        true);
                System.out.println("Connected to server");
                connected = true;
            } catch (IOException e) {

            }
        }

        while(!input.equals("exit")){
            try{
                if(sc.ready()){
                    input = sc.readLine();
                }
                if(true){
                    int available = in.available();
                    data = (DataObject) in.readObject();
                    if(data.getData().equals("kill client")) {
                        input = "exit";
                        System.exit(0);
                    }
                    if(!data.getData().equals("kill server")){
                        System.out.println("Received " + data.getName() + ": " + data.getData());
                        //System.out.println("Available: " + available);
                    }
                }
            } catch (Exception e){
                System.out.println("Client Side Error: " + e.getMessage());
                    if(e.getMessage().equals(null)){
                        System.exit(0);
                    }
            }
        }
    }
}