package client_works;

import ask_works.LanguageKeeper;
import ask_works.Poll;
import ask_works.PollKeeper;
import client_validate.ClientFieldsValidation;
import client_validate.ClientValidator;
import command_works.*;
import input_fields_works.ConsoleRepeater;
import input_fields_works.MarineSetter;
import input_fields_works.Repeater;
import input_fields_works.SetKeeper;
import messenger.*;
import print_works.PrintKeeper;
import print_works.PrintMachine;
import request_structure.RequestKeeper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Client {
    private final int SERVER_PORT = 2407;
    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;


    public static void main(String[] args) {
        Client client = new Client();
        client.startClient();
    }


    public void startClient() {
        try {
            this.datagramChannel = DatagramChannel.open();
            this.socketAddress = new InetSocketAddress("localhost", SERVER_PORT);
            PrintKeeper printMachine = new PrintMachine();
            ClientSendKeeper clientSender = new ClientSender(datagramChannel, socketAddress, printMachine);
            ClientReceiver clientReceiver = new ClientReceiver(datagramChannel, socketAddress, printMachine);

            LanguageKeeper languageKeeper = new LanguageKeeper(new RussianMessenger(), new EnglishMessenger(), printMachine);

            languageKeeper.putLanguage();
            RequestKeeper languageRequest = languageKeeper.inputLanguage();


            clientSender.send(languageRequest);

            Messenger messenger = languageKeeper.getChosenMessenger();

            Scanner scanner = new Scanner(System.in);

            ClientReadKeeper clientReadKeeper = new ClientReader(scanner);

            ClientValidator clientValidator = new ClientFieldsValidation(messenger);

            PollKeeper poll = new Poll(scanner, clientValidator, messenger, printMachine);

            SetKeeper marineSetter = new MarineSetter(poll);
            Repeater repeater = new ConsoleRepeater(marineSetter);
            ClientCommandKeeper clientCommandManager = new ClientCommandManager(messenger, printMachine, repeater);

            clientCommandManager.pushCommands();

            clientCommandManager.clientCommands();

            clientCommandManager.mapOfAvailableCommands();

            clientCommandManager.mapOfAskCommands();

            ClientExecuteKeeper clientExecuteKeeper = new ClientExecutor(clientCommandManager, poll, messenger, printMachine, clientSender);

            clientReceiver.start();
            clientReadKeeper.interactiveMode(clientExecuteKeeper);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
