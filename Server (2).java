
import java.net.*;
import java.io.*;

class Server {

    public static void main(String args[]) throws Exception {

        ServerSocket ss = new ServerSocket(3333);
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String choice;
//        System.out.println("client's choice is : " + choice);

//        openMode(ss, s, din, dout, br);
//        secureMode(ss, s, din, dout, br);
//        if (choice.equals("1")) {
//            openMode(ss, s, din, dout, br);
//        } else if (choice.equals("2")) {
//            secureMode(ss, s, din, dout, br);
//        }
        do {
            choice = din.readUTF();
            switch (choice) {
                case "1":
                    openMode(ss, s, din, dout, br);break;
                case "2":
                    secureMode(ss, s, din, dout, br);break;
                case "3":
                    din.close();
                    s.close();
                    ss.close();
                    System.out.println("\nCnnection is closed\n");break;
            }

        } while (!choice.equals("3"));

    }

    public static void openMode(ServerSocket ss, Socket s, DataInputStream din, DataOutputStream dout, BufferedReader br) throws Exception {

        String str = "", str2 = "";
        while (!str.equals("back")) {
            str = din.readUTF();
//            str2 = br.readLine();
            if (str.equals("back")) {
//                System.out.println("Communication is closed..bye");
                break;
            }
            System.out.println("Client's Message:( "+ str+" )\n__________________________________\n");
            dout.writeUTF(str);
            dout.flush();
        }

//        din.close();
//        s.close();
//        ss.close();
    }

    public static void secureMode(ServerSocket ss, Socket s, DataInputStream din, DataOutputStream dout, BufferedReader br) throws Exception {

        StringBuffer encrypted;
        String str = "", str2 = "";
        while (!str.equals("back")) {
            str = din.readUTF();
//            str2 = br.readLine();
            if (str.equals("back")) {
//                System.out.println("Communication is closed..bye");
                break;
            }
            System.out.println("Client's Message:( " + str+" )\n__________________________________\n");
            str = encrypt(str, 4);
            dout.writeUTF(str);
            dout.flush();
        }
//        din.close();
//        s.close();
//        ss.close();

    }

    public static String encrypt(String text, int s) {

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i)
                        + s - 65) % 26 + 65);
                result.append(ch);
            } else {
                char ch = (char) (((int) text.charAt(i)
                        + s - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result.toString();
    }

}
