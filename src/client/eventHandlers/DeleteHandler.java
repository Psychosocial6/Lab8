package client.eventHandlers;

import client.GUI.ElementInfoPageGUI;
import client.interfaces.DeleteHandlerInterface;

public class DeleteHandler implements DeleteHandlerInterface {
    private ElementInfoPageGUI elementInfoPageGUI;

    public DeleteHandler(ElementInfoPageGUI elementInfoPageGUI) {
        this.elementInfoPageGUI = elementInfoPageGUI;
    }

    @Override
    public void delete() {
        //logic
    }
}
