package server_works;

import server_validate.AnswerKeeper;

import java.net.InetAddress;

public interface ServerSendKeeper {

     void send(AnswerKeeper answer, InetAddress address, int port);
}
