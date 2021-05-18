package server_works;

import execute_works.ServerExecuteKeeper;
import execute_works.ServerExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.AnswerKeeper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerReceiver extends Thread {
    private DatagramSocket socket;
    private ServerSendKeeper serverSender;
    private ServerExecuteKeeper serverExecutor;
    private static final Logger logger = LogManager.getLogger();

    public ServerReceiver(DatagramSocket socket, ServerSendKeeper serverSender, ServerExecuteKeeper serverExecutor) {
        this.socket = socket;
        this.serverSender = serverSender;
        this.serverExecutor = serverExecutor;
    }


    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                byte[] bytes = new byte[16384];
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                socket.receive(datagramPacket);

                logger.info("Получен запрос от " + datagramPacket.getAddress() + ":" +
                        datagramPacket.getPort());

                AnswerKeeper answer = serverExecutor.giveAnswer(datagramPacket);
                if (answer.getObject() instanceof ServerExecuteKeeper) {
                    this.serverExecutor = (ServerExecutor) answer.getObject();
                } else serverSender.send(answer, datagramPacket.getAddress(), datagramPacket.getPort());
            } catch (IOException e) {
                logger.error("Ошибка!");
            }
        }
    }

}
