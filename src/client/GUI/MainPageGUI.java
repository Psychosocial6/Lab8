package client.GUI;

import client.Client;
import client.ClientRequestSender;
import client.ClientResponseReceiver;
import client.custom_gui_elements.MovieTableModel;
import client.dataStorage.CurrentMovie;
import client.eventHandlers.*;
import client.other.TableElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *The {@code GUI.MainPageGui} describes GUI of main page in Collection Viewer from {@code ProgrammingLab8}.
 * Creates standard configuration with elements table, showing area and various filters.
 * @author Andrey
 * */
public class MainPageGUI {
    public static final Dimension WINDOW_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final String TITLE = "Collection Viewer";

    public static final Dimension LABEL_SIZE = new Dimension(400, 40);

    public static final Dimension TEXT_FIELD_SIZE = new Dimension(150, 30);

    public static final Dimension MENU_PANEL_SIZE = new Dimension(200, 200);

    public static final String SORTING_MENU_LABEL = "Sorting menu";
    public static final String FILTERS_MENU_LABEL = "Filters menu";
    public static final Dimension SMALL_LABEL_SIZE = new Dimension(200, 20);
    public static final String[] SORTING_AND_FILTERING_PARAMETERS = {
            "id",
            "name",
            "coordinates",
            "creation date",
            "oscars count",
            "total box office",
            "usa box office",
            "genre",
            "operator"};

    public static final Dimension MENU_SIZE = new Dimension(200, 100);

    public static final Dimension TABLE_SIZE = new Dimension((int)WINDOW_SIZE.getWidth()/5 * 2, (int)WINDOW_SIZE.getHeight()/3 * 2);

    public static final Dimension BUTTON_SIZE = new Dimension(100, 50);
    public static final String NEXT_PAGE_BUTTON_TITLE = "Next";
    public static final String SORT_BUTTON_TITLE = "Sort";
    public static final String FILTERS_BUTTON_TITLE = "Filter";
    public static final String FILTER_HINT = "><= *value*";

    public static final int VERTICAL_STRUT = 20;
    public static final int SMALL_VERTICAL_STRUT = 5;

    public static final Color BUTTON_COLOR = new Color(0, 169, 255);
    public static final Color TEXT_COLOR = Color.BLACK;
    public static final Color HINT_COLOR = Color.GRAY;

    private JLabel userLabel;
    private JTable elementsTable;
    private JButton nextPageButton;
    private JPanel sortingMenu;
    private JPanel filtersMenu;
    private JFrame window;
    private NextPageHandler nextPageHandler;
    private SortingHandler sortingHandler;
    private FilterHandler filterHandler;
    private ClientRequestSender sender;
    private ClientResponseReceiver receiver;

    public MainPageGUI(String user, ArrayList<TableElement> elements) {
        window = new JFrame(TITLE);
        window.setSize(WINDOW_SIZE);
        window.setLayout(new BorderLayout(10, 10));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userLabel = createLabel("User: ");
        elementsTable = createFilmsTable(elements);
        this.userLabel = createLabel("User: " + user);
        nextPageButton = createNextPageButton();
        sortingMenu = createSortingMenu();
        filtersMenu = createFiltersMenu();
    }

    /**
     * Method which creates main window's label
     * @return  {@code JLabel} - new {@code JLabel} with pre created configuration and label's text
     * */
    private JLabel createLabel(String user) {
        JLabel label = new JLabel();
        label.setPreferredSize(LABEL_SIZE);
        label.setText(user);
        label.setHorizontalAlignment(SwingConstants.LEFT);

        return label;
    }

    /**
     * Method which creates button to show next page.
     * @return  {@code JButton} - new {@code JButton} with pre created configuration
     * */
    private JButton createNextPageButton() {
        JButton button = new JButton();
        button.setText(NEXT_PAGE_BUTTON_TITLE);
        button.setPreferredSize(BUTTON_SIZE);
        button.setBackground(BUTTON_COLOR);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<TableElement> movies = nextPageHandler.getNextPage();
                ((MovieTableModel) elementsTable.getModel()).updateMovies(movies);
            }
        });

        return button;
    }

    private JTable createFilmsTable(ArrayList<TableElement> movies) {
        MovieTableModel tableModel = new MovieTableModel(movies);
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);

                if (row >= 0) {
                    TableElement selectedMovie = ((MovieTableModel) table.getModel()).getMovieAt(row);
                    //CurrentMovie movie = sender.send(new Object[]{"load_next_sorted_page", new Object[]{Client.pageCounter}, Client.currentClient.getUserName(), Client.currentClient.getUserPassword()});
                    //ElementInfoPageGUI elementInfoPageGUI = new ElementInfoPageGUI(se.getName());
                    //DeleteHandler deleteHandler = new DeleteHandler(elementInfoPageGUI);
                    //EditHandler editHandler = new EditHandler(elementInfoPageGUI);
                    //elementInfoPageGUI.setEditHandler(editHandler);
                    //elementInfoPageGUI.setDeleteHandler(deleteHandler);
                    //elementInfoPageGUI.createAndShowWindow();
                }
            }
        });

        return table;
    }

    private JPanel createSortingMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel();
        label.setText(SORTING_MENU_LABEL);
        label.setPreferredSize(SMALL_LABEL_SIZE);

        JComboBox<String> menu = new JComboBox<>(SORTING_AND_FILTERING_PARAMETERS);
        menu.setPreferredSize(MENU_SIZE);

        JButton button = new JButton();
        button.setText(SORT_BUTTON_TITLE);
        button.setPreferredSize(BUTTON_SIZE);
        button.setBackground(BUTTON_COLOR);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<TableElement> elements = sortingHandler.sort((String) menu.getSelectedItem());
                ((MovieTableModel) elementsTable.getModel()).updateMovies(elements);
            }
        });

        panel.add(button);
        panel.add(Box.createVerticalStrut(SMALL_VERTICAL_STRUT));
        panel.add(label);
        panel.add(Box.createVerticalStrut(SMALL_VERTICAL_STRUT));
        panel.add(Box.createRigidArea(new Dimension((int) TEXT_FIELD_SIZE.getWidth(), (int) TEXT_FIELD_SIZE.getHeight())));
        panel.add(Box.createVerticalStrut(SMALL_VERTICAL_STRUT));
        panel.add(menu);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel createFiltersMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel();
        label.setText(FILTERS_MENU_LABEL);
        label.setPreferredSize(SMALL_LABEL_SIZE);

        JTextField textField = new JTextField();
        textField.setPreferredSize(TEXT_FIELD_SIZE);
        textField.setText(FILTER_HINT);
        textField.setForeground(HINT_COLOR);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(FILTER_HINT)) {
                    textField.setText("");
                    textField.setForeground(TEXT_COLOR);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(FILTER_HINT);
                    textField.setForeground(HINT_COLOR);
                }
            }
        });


        JComboBox<String> menu = new JComboBox<>(SORTING_AND_FILTERING_PARAMETERS);
        menu.setPreferredSize(MENU_SIZE);

        JButton button = new JButton();
        button.setText(FILTERS_BUTTON_TITLE);
        button.setPreferredSize(BUTTON_SIZE);
        button.setBackground(BUTTON_COLOR);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<TableElement> elements = filterHandler.filter((String) menu.getSelectedItem(), textField.getText());
                ((MovieTableModel) elementsTable.getModel()).updateMovies(elements);
            }
        });

        panel.add(button);
        panel.add(Box.createVerticalStrut(SMALL_VERTICAL_STRUT));
        panel.add(label);
        panel.add(Box.createVerticalStrut(SMALL_VERTICAL_STRUT));
        panel.add(textField);
        panel.add(Box.createVerticalStrut(SMALL_VERTICAL_STRUT));
        panel.add(menu);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

     public void createAndShowWindow() {
        JPanel leftPanel = new JPanel(new BorderLayout(0, VERTICAL_STRUT));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        userPanel.add(userLabel);
        userPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(userPanel);

         JScrollPane scrollTable = new JScrollPane(elementsTable);
         scrollTable.setPreferredSize(TABLE_SIZE);
        JPanel tp = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        tp.add(scrollTable);
        tp.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(tp);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        buttonPanel.add(nextPageButton);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(buttonPanel);

        leftPanel.add(panel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

        sortingMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
        sortingMenu.setPreferredSize(MENU_PANEL_SIZE);
        filtersMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
        filtersMenu.setPreferredSize(MENU_PANEL_SIZE);

        JPanel menusPanel = new JPanel();
        menusPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        menusPanel.add(filtersMenu);
        menusPanel.add(sortingMenu);
        menusPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        rightPanel.add(menusPanel);
        rightPanel.add(Box.createVerticalGlue());

        window.add(leftPanel, BorderLayout.WEST);
        window.add(rightPanel, BorderLayout.CENTER);
        window.setVisible(true);
     }

    public void setNextPageHandler(NextPageHandler nextPageHandler) {
        this.nextPageHandler = nextPageHandler;
    }

    public void setSortingHandler(SortingHandler sortingHandler) {
        this.sortingHandler = sortingHandler;
    }

    public void setFilterHandler(FilterHandler filterHandler) {
        this.filterHandler = filterHandler;
    }
}
