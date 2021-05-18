package execute_works;


import request_structure.RequestKeeper;
import utility.AnswerKeeper;
import utility.Result;

import java.net.DatagramPacket;

public interface ServerExecuteKeeper {

    AnswerKeeper giveAnswer(DatagramPacket datagramPacket);

    Result<Object> pickCommand(RequestKeeper request);

}
