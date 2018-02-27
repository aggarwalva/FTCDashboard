import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class BasicClient {
    public static void main(String[] args){
        String input = "", recievedData;
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            Socket client = new Socket("localhost", 4321);
            in = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));
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
                if(in.ready()){
                    recievedData = in.readLine();
                    if(recievedData.equals("kill client")) {
                        input = "exit";
                        System.exit(0);
                    }
                    if(!recievedData.equals("kill server")){
                        System.out.println("Received Data: " + recievedData);
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
