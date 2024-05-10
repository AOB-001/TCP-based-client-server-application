
import java.net.*;
import java.io.*;
import java.util.Scanner;

class Client {

    public static void main(String args[]) throws Exception {

        try {
            Socket s = new Socket("localhost", 3333);
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Scanner in = new Scanner(System.in);

            String Mode;
            do {
                System.out.println("\nMAIN MENU :");
                System.out.println("1) OPEN MODE");
                System.out.println("2) SECURE MODE");
                System.out.println("3) Quit");
                System.out.print("\nEnter the number of choice :");
                Mode = in.next();
                System.out.println("_______________________________________________________");
                switch (Mode) {

                    case "1":
                        dout.writeUTF("1");
                        dout.flush();
                        call(s, din, dout, br,"1");
                        break;
                    case "2":
                        dout.writeUTF("2");
                        dout.flush();
                        call(s, din, dout, br,"2");
                        break;
                    case "3":
                        dout.writeUTF("3");
                        dout.flush(); // tell the server that th clients wants to Quit
                        dout.close(); // close clints connection
                        s.close();// close clints connection
                        System.out.println("\nCnnection is closed\n");
                        break;
                    default:
                        System.out.println("**please enter one of the valid number of the options**");
                }
            } while (!Mode.equals("3"));

        } catch (Exception e) {
            System.out.println("\n\nserver is down ):");
        }

    }

    public static void call(Socket s, DataInputStream din, DataOutputStream dout, BufferedReader br,String choice) throws Exception {

        String str = "", str2 = "";
        while (!str.equals("back")) {
            System.out.print("Enter your text :");
            str = br.readLine();
            dout.writeUTF(str);
            dout.flush();
            if (str.equals("back")) {break;}
            str2 = din.readUTF();
            
            if(choice.equals("1"))
            System.out.println("\n[Server]'s Message: " + str2);
            
            else if(choice.equals("2"))
            System.out.println("\n[Server]'s Message(encrypted): " + str2);
            
            System.out.println("_______________________________________________________");
            System.out.println("\n(Enter the word 'back' to return to the main menu...) \n");
        }

//        dout.close();
//        s.close();
    }
}
