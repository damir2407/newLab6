package client_works;

import print_works.PrintKeeper;
import request_structure.RequestKeeper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientSender implements ClientSendKeeper {
    private DatagramChannel datagramChannel;
    private SocketAddress serverAddress;
    private ByteBuffer byteBuffer;
    private PrintKeeper printMachine;


    public ClientSender(DatagramChannel datagramChannel, SocketAddress serverAddress, PrintKeeper printMachine) {
        this.datagramChannel = datagramChannel;
        this.serverAddress = serverAddress;
        this.byteBuffer = ByteBuffer.allocate(16384);
        this.printMachine = printMachine;
    }


    @Override
    public void send(RequestKeeper request) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(request);
            byteBuffer.put(byteArrayOutputStream.toByteArray());
            objectOutputStream.flush();
            byteArrayOutputStream.flush();
            byteBuffer.flip();
            datagramChannel.send(byteBuffer, serverAddress);
            objectOutputStream.close();
            byteArrayOutputStream.close();
            byteBuffer.clear();

        } catch (IOException e) {
            printMachine.println("Ошибка во время отправки запроса!");
        }

    }


}
