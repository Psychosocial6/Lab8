package client.eventHandlers;

import client.Client;
import client.ClientRequestSender;
import client.ClientResponseReceiver;
import client.GUI.MainPageGUI;
import client.interfaces.NextPageHandlerInterface;
import client.other.PageParser;
import client.other.TableElement;

import java.io.IOException;
import java.util.ArrayList;

public class NextPageHandler implements NextPageHandlerInterface {
    private MainPageGUI mainPageGUI;
    private ClientRequestSender sender;
    private ClientResponseReceiver receiver;

    public NextPageHandler(MainPageGUI mainPageGUI, ClientRequestSender sender, ClientResponseReceiver receiver) {
        this.mainPageGUI = mainPageGUI;
        this.sender = sender;
        this.receiver = receiver;
    }

    @Override
    public ArrayList<TableElement> getNextPage() {
        Client.pageCounter++;
        try {
            sender.send(new Object[]{"load_next_page", new Object[]{Client.pageCounter}, Client.currentClient.getUserName(), Client.currentClient.getUserPassword()});
            return PageParser.parsePage((String) receiver.getData());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
