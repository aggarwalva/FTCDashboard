import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PIDTuner extends Frame implements ActionListener,WindowListener{
    double kP, kI, kD;
    Button set;
    TextField pText, iText, dText;
    PrintWriter out;
    boolean connected = false;

    public static void main(String[] args){
        PIDTuner instance = new PIDTuner();
    }

    public PIDTuner(){
        setLayout(new FlowLayout());
        Label pLabel = new Label("kP: ");
        add(pLabel);
        pText = new TextField("0", 10);
        pText.setEditable(true);
        add(pText);
        Label iLabel = new Label("kI: ");
        add(iLabel);
        iText = new TextField("0", 10);
        iText.setEditable(true);
        add(iText);
        Label dLabel = new Label("kD: ");
        add(dLabel);
        dText = new TextField("0", 10);
        dText.setEditable(true);
        add(dText);
        set = new Button("Set");
        set.addActionListener(this);
        add(set);

        setTitle("PID Tuner");
        setSize(800,270);

        setResizable(true);

        setLocation(300,200);
        this.addWindowListener(this);
        setVisible(true);

        ServerSocket server;
        Socket client = null;

        out = null;

        while(!connected){
            try {
                //server = new ServerSocket(4321);
                client = new Socket("192.168.49.1",4321);
                connected = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try{
            out = new PrintWriter(client.getOutputStream(),
                    true);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            kP = Double.parseDouble(pText.getText());
            kI = Double.parseDouble(iText.getText());
            kD = Double.parseDouble(dText.getText());

            if(connected){
                System.out.println("kP: " + kP + " kI: " + kI + " kD: " + kD);
                out.println(kP + "\n" + kI + "\n" + kD);
            } else{
                System.out.println("Not connected to server");
            }
        } catch (Exception ex){
            kP = 0;
            kI = 0;
            kD = 0;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        dispose();
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        dispose();
        System.exit(0);
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}