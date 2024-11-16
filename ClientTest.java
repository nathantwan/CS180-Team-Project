import org.junit.Test;

import static org.junit.Assert.*;

import java.io.*;
import java.net.*;

/**
 * Client Tests
 * <p>
 * Purdue University -- CS18000 -- Team Project
 *
 * @author Maria Simmons
 * @version Nov 16, 2024
 */

public class ClientTest {
    //Check the exception if the connection isn't established
    @Test
    public void mainConnection() {
        try {
            int portNumber = 7777;
            Socket socket = new Socket("localhost", portNumber);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void mainClosing() {
        //Test case to check the exception being thrown when closing reader and writer
        BufferedReader reader = null;
        PrintWriter writer = null;
        Socket socket = null;
        try {
            int portNumber = 4242;
            socket = new Socket("localhost", portNumber);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            socket.close(); //Improper closing to throw exception error
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                assertTrue(e.getMessage().contains("Stream closed") ||
                        e.getMessage().contains("Socket closed"));
            }
        }

    }

}
