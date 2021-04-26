package server_works;

import commands.LanguageManager;
import execute_works.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Server {
    private final int SERVER_PORT = 2407;
    private DatagramSocket socket;
    private InetAddress inetAddress;
    private static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }


    public void startServer() {
        try {
            this.inetAddress = InetAddress.getByName("localhost");
            this.socket = new DatagramSocket(SERVER_PORT);
            LanguageManager languageManager = new LanguageManager();
            ServerSendKeeper serverSender = new ServerSender(socket);
            ServerReadKeeper serverReadKeeper = new ServerReader();
            ServerExecuteKeeper serverExecutor = new ServerExecutor(languageManager, serverSender, serverReadKeeper);
            ServerReceiver serverReceiver = new ServerReceiver(socket, serverSender, serverExecutor);
            serverReceiver.setDaemon(true);
            serverReceiver.start();
            serverReadKeeper.read();
        } catch (UnknownHostException | SocketException e) {
            logger.error("Ошибка на сервере");
        }


    }


}
