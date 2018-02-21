import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        String input = "";
        double kP, kI, kD;
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
                    kP = Double.parseDouble(in.readLine());
                    kI = Double.parseDouble(in.readLine());
                    kD = Double.parseDouble(in.readLine());
                    System.out.println("Received Data: P -" + kP + " I -" + kI + " D -" + kD);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}