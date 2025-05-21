package client.eventHandlers;

import client.Client;
import client.ClientRequestSender;
import client.ClientResponseReceiver;
import client.GUI.MainPageGUI;
import client.dataStorage.CollectionView;
import client.interfaces.SortingHandlerInterface;
import client.other.PageParser;
import client.other.TableElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SortingHandler implements SortingHandlerInterface {
    private MainPageGUI mainPageGUI;
    private ClientRequestSender sender;
    private ClientResponseReceiver receiver;

    public SortingHandler(MainPageGUI mainPageGUI, ClientRequestSender sender, ClientResponseReceiver receiver) {
        this.mainPageGUI = mainPageGUI;
        this.sender = sender;
        this.receiver = receiver;
    }

    @Override
    public ArrayList<TableElement> sort(String parameter) {
        HashMap<String, String> params = new HashMap<>() {
            {
                put("name", "movie.name");
                put("id", "movie.id");
                put("oscars count", "movie.oscars_count");
                put("total box office", "movie.total_box_office");
                put("usa box office", "movie.usa_box_office");
                put("genre", "movie.genre");
            }
        };
        String param = params.get(parameter);
        Client.pageCounter = 1L;
        try {
            sender.send(new Object[]{"load_next_sorted_page", new Object[]{Client.pageCounter, param}, Client.currentClient.getUserName(), Client.currentClient.getUserPassword()});
            receiver.getResponce();
            Map<Long, String> response = CollectionView.getMovieView();
            ArrayList<TableElement> elements = new ArrayList<>();
            for (Long id : response.keySet()) {
                elements.add(new TableElement(id, response.get(id)));
            }
            return elements;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
