package client.eventHandlers;

import client.GUI.ElementInfoPageGUI;
import client.interfaces.EditHandlerInterface;

public class EditHandler implements EditHandlerInterface {
    private ElementInfoPageGUI elementInfoPageGUI;

    public EditHandler(ElementInfoPageGUI elementInfoPageGUI) {
        this.elementInfoPageGUI = elementInfoPageGUI;
    }

    @Override
    public void edit() {
        //logic
    }
}
