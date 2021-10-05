import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
//import java.nio.Buffer;



public class SimpleHTTPServer {
    public static void main (String[] args) throws IOException {
        int port = 9000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Server is running on port: " + port);
        
        while(true) {
            Socket clientsocket = serverSocket.accept();
            System.err.println("Client connected");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
            String str;
            while((str = in.readLine())!=null) {
                System.out.println(str);
                if(str.isEmpty()) {
                    break;
                }
                
            }
            OutputStream clientOutput = clientsocket.getOutputStream();
            clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
            clientOutput.write("\r\n".getBytes());
            clientOutput.write("<b>Welcome To New HTTP server!</b>".getBytes());
            clientOutput.write("\r\n\r\n".getBytes());
            clientOutput.flush();
            System.err.println("Client connection closed!");
            in.close();
            clientOutput.close();
            serverSocket.close();
        }
       
    }
}