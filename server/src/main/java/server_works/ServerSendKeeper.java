package server_works;

import utility.AnswerKeeper;

import java.net.InetAddress;

public interface ServerSendKeeper {

     void send(AnswerKeeper answer, InetAddress address, int port);
}
