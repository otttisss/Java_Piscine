package edu.school21.sockets.server;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;

@Component
@Parameters(separators = "=")
public class Server {
    @Parameter(names = "--port")
    private int port;

    private ServerSocket serverSocket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private UsersService usersService;

    @Autowired
    public Server(UsersService usersService) {
        this.usersService = usersService;
    }

    private void signUp() {
        String username;
        String password;

        try {

        } catch (Exception e) {

        }
    }

    private void writeToClient(String message) {
        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
