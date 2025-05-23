package client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AddPageGUI {

    public static final int VERTICAL_STRUT = 5;

    public static final String ID_HINT = "id";
    public static final String NAME_HINT = "name";
    public static final String COORDINATE_X_HINT = "X";
    public static final String COORDINATE_Y_HINT = "Y";
    public static final String OSCARS_COUNT_HINT = "oscars count";
    public static final String TOTAL_BOX_OFFICE_HINT = "total box office";
    public static final String USA_BOX_OFFICE_HINT = "usa box office";
    public static final String GENRE_HINT = "genre";
    public static final String OPERATOR_NAME_HINT = "operator name";
    public static final String OPERATOR_PASSPORT_ID_HINT = "operator passport id";
    public static final String OPERATOR_LOCATION_X_HINT = "operator location X";
    public static final String OPERATOR_LOCATION_Y_HINT = "operator location Y";
    public static final String OPERATOR_LOCATION_Z_HINT = "operator location Z";

    public static final String BUTTON_TEXT = "Add";

    public static final Dimension WINDOW_SIZE = new Dimension(300, 500);
    public static final Dimension BUTTON_SIZE = new Dimension(100, 50);
    public static final Dimension TEXT_FIELD_SIZE = new Dimension(150, 30);

    public static final Color HINT_COLOR = Color.GRAY;
    public static final Color TEXT_COLOR = Color.BLACK;
    public static final Color BUTTON_COLOR = new Color(0, 169, 255);

    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField coordinateX;
    private JTextField coordinateY;
    private JTextField oscarsCount;
    private JTextField totalBoxOffice;
    private JTextField usaBoxOffice;
    private JTextField genre;
    private JTextField operatorName;
    private JTextField operatorPassportID;
    private JTextField operatorLocationX;
    private JTextField operatorLocationY;
    private JTextField operatorLocationZ;
    private JButton button;
    private JDialog window;

    public AddPageGUI() {
        window = new JDialog();
        window.setModal(true);
        window.setSize(WINDOW_SIZE);

        button = new JButton();
        button.setPreferredSize(BUTTON_SIZE);
        button.setMaximumSize(BUTTON_SIZE);
        button.setBackground(BUTTON_COLOR);
        button.setText(BUTTON_TEXT);

        idTextField = new JTextField();
        idTextField.setPreferredSize(TEXT_FIELD_SIZE);
        idTextField.setMaximumSize(TEXT_FIELD_SIZE);
        idTextField.setText(ID_HINT);
        idTextField.setForeground(HINT_COLOR);
        idTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (idTextField.getText().equals(ID_HINT)) {
                    idTextField.setText("");
                    idTextField.setForeground(TEXT_COLOR);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (idTextField.getText().isEmpty()) {
                    idTextField.setText(ID_HINT);
                    idTextField.setForeground(HINT_COLOR);
                }
            }
        });

        nameTextField = new JTextField();
        nameTextField.setPreferredSize(TEXT_FIELD_SIZE);
        nameTextField.setMaximumSize(TEXT_FIELD_SIZE);
        nameTextField.setText(NAME_HINT);
        nameTextField.setForeground(HINT_COLOR);
        nameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameTextField.getText().equals(NAME_HINT)) {
                    nameTextField.setText("");
                    nameTextField.setForeground(TEXT_COLOR);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameTextField.getText().isEmpty()) {
                    nameTextField.setText(NAME_HINT);
                    nameTextField.setForeground(HINT_COLOR);
                }
            }
        });

        coordinateX = new JTextField();
        coordinateX.setPreferredSize(TEXT_FIELD_SIZE);
        coordinateX.setMaximumSize(TEXT_FIELD_SIZE);
        coordinateX.setText(COORDINATE_X_HINT);
        coordinateX.setForeground(HINT_COLOR);
        coordinateX.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (coordinateX.getText().equals(COORDINATE_X_HINT)) {
                    coordinateX.setText("");
                    coordinateX.setForeground(TEXT_COLOR);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (coordinateX.getText().isEmpty()) {
                    coordinateX.setText(COORDINATE_X_HINT);
                    coordinateX.setForeground(HINT_COLOR);
                }
            }
        });
    }

    public void createAndShowWindow() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        idTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        coordinateX.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(idTextField);
        panel.add(Box.createVerticalStrut(VERTICAL_STRUT));
        panel.add(nameTextField);
        panel.add(Box.createVerticalStrut(VERTICAL_STRUT));
        panel.add(coordinateX);
        panel.add(Box.createVerticalStrut(VERTICAL_STRUT));
        panel.add(button);

        window.add(panel);
        window.setVisible(true);
    }
}
