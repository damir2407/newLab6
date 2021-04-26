package client_works;

import print_works.PrintKeeper;
import server_validate.Answer;
import server_validate.AnswerKeeper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientReceiver extends Thread {
    private DatagramChannel datagramChannel;
    private SocketAddress serverAddress;
    private ByteBuffer byteBuffer;
    private PrintKeeper printMachine;


    public ClientReceiver(DatagramChannel datagramChannel, SocketAddress serverAddress, PrintKeeper printMachine) {
        this.datagramChannel = datagramChannel;
        this.serverAddress = serverAddress;
        this.byteBuffer = ByteBuffer.allocate(16384);
        this.printMachine = printMachine;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                byteBuffer.clear();
                datagramChannel.connect(serverAddress);
                datagramChannel.receive(byteBuffer);
                byteBuffer.flip();

                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                AnswerKeeper answer = (Answer) objectInputStream.readObject();

                if (!answer.isOK()) {
                    printMachine.println("Получен ответ от сервера (Ошибка) \n" + answer.getErrorMessage());
                } else printMachine.println("Получен ответ от сервера: \n" + answer.getObject());

                byteBuffer.clear();
                datagramChannel.disconnect();
            } catch (PortUnreachableException e) {
                printMachine.println("Сервер недоступен!");
            } catch (IOException | ClassNotFoundException e) {
                printMachine.println("Ошибка!");
            }
        }
    }
}
