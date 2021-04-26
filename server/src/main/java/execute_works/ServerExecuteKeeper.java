package execute_works;


import request_structure.RequestKeeper;
import server_validate.AnswerKeeper;
import server_validate.ResultKeeper;

import java.net.DatagramPacket;

public interface ServerExecuteKeeper {
    AnswerKeeper giveAnswer(DatagramPacket datagramPacket);

    ResultKeeper pickCommand(RequestKeeper request);

}
