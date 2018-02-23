import javax.xml.crypto.Data;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ObjectClient {
    public static void main(String[] args){
        String input = "", recievedData;
        DataObject data;
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        ObjectInputStream in = null;
        PrintWriter out = null;
        try {
            Socket client = new Socket("localhost", 4321);
            in = new ObjectInputStream(client.getInputStream());
            out = new PrintWriter(client.getOutputStream(),
                    true);
        } catch (IOException e) {
            e.printStackTrace();
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
                        System.out.println("Available: " + available);
                    }
                }
            } catch (Exception e){
                System.out.println("Client Side Error: " + e.getMessage());
            }
        }
    }
}