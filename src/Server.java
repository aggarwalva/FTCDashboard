import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args){
        ServerSocket server = null;
        Socket client = null;
        //Test Commit

        String input = "", getData;
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = null;
        PrintWriter out = null;


        try {
            server = new ServerSocket(4321);
            client = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            in = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(),
                    true);
        } catch(Exception e){
            e.printStackTrace();
        }

        while(!input.equals("exit")){
            try{
                input = sc.readLine();
                out.println(input);
                System.out.println(input);
                //System.out.println("Recieved data: " + in.readLine());
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        System.exit(0);
    }
}