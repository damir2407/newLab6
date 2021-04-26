package execute_works;

import server_works.ServerSendKeeper;

import java.net.InetAddress;

public interface CreateKeeper {

    ServerExecuteKeeper createObjects(InetAddress inetAddress, int port,
                                             ServerSendKeeper serverSender, ServerReadKeeper serverReader);
}
