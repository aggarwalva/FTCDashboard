import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ObjectServer {
    public static void main(String[] args){
        ServerSocket server = null;
        Socket client = null;
        //Test Commit

        String input = "", getData;
        DataObject data;
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = null;
        ObjectOutputStream out = null;


        try {
            server = new ServerSocket(4321);
            client = server.accept();
            System.out.println("Client Accepted");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            in = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));
            out = new ObjectOutputStream(client.getOutputStream());
        } catch(Exception e){
            e.printStackTrace();
        }

        while(!input.equals("kill server")){
            try{
                input = sc.readLine();
                data = new DataObject("Data Object",input);
                out.writeObject(data);
                //System.out.println(input);
                //System.out.println("Recieved data: " + data.getData());
            } catch (Exception e){
                System.out.println("Server Side Error: " + e.getMessage());
            }
        }

        System.exit(0);
    }
}