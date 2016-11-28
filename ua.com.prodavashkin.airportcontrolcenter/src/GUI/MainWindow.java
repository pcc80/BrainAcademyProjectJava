package GUI;

import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import Listeners.*;
import java.awt.event.ActionEvent;


public class MainWindow {

    private final JFrame mainWindowFrame = new JFrame("AirPort Control " + Main.Main.getVersionApp());
    private final JPanel mainAboutPanel = new JPanel();
    private final JLabel mainAboutLabelText = new JLabel(
                Main.Main.getFirstName() + " " + 
                Main.Main.getLastName() + " | Access Level: " + 
                Main.Main.getAccessLevel());

    private final JMenuBar mainWindowMenuBar = new JMenuBar();
    private final JMenu fileMenu = new JMenu("File");
    private final JMenu informationMenu = new JMenu("Information");
    private final JMenu searchMenu = new JMenu("Search");
    private final JMenu updateMenu = new JMenu("Update");
    private final JMenu updatePassangerMenu = new JMenu("Passanger Information");
    private final JMenu helpMenu = new JMenu("Help");

    private final JMenuItem exportPassangersInformationMenuItem = new JMenuItem("Export Passanger Information");
    private final JMenuItem exportFlightsInformationMenuItem = new JMenuItem("Export Flights Information");
    private final JMenuItem logoutMenuItem = new JMenuItem("Log Out ...");
    private final JMenuItem exitMenuItem = new JMenuItem("Exit");
    
    private final JMenuItem passangersInformationMenuItem = new JMenuItem("Passangers");
    private final JMenuItem flightInformationMenuItem = new JMenuItem("Flights");
    private final JMenuItem priceInformationMenuItem = new JMenuItem("Prices");

    private final JMenuItem searchPassangerMenuItem = new JMenuItem("Search Passanger");
    private final JMenuItem searchPriceMenuItem = new JMenuItem("Search Price");
    private final JMenuItem searchFlightNumberMenuItem = new JMenuItem("Search Flight Number");
    private final JMenuItem searchPortMenuItem = new JMenuItem("Search Arrival/Departure Port");
    
    private final JMenuItem addNewPassangerUpdateMenuItem = new JMenuItem("New Passanger");
    private final JMenuItem deletePassangerUpdateMenuItem = new JMenuItem("Delete Passanger");
    
    private final JMenuItem heplAboutMenuItem = new JMenuItem("About");
    private final JMenuItem newVersionMenuItem = new JMenuItem("Look New Version...");
    

    public void MainWindow () {

        mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindowFrame.setBounds(100, 100, 450, 100);
        mainWindowFrame.setResizable(false);
        mainWindowFrame.setLayout(new GridBagLayout());
        
        mainWindowMenuBar.add(fileMenu);
        mainWindowMenuBar.add(informationMenu);
        mainWindowMenuBar.add(searchMenu);
        if (Main.Main.getAccessLevel() != 3) {
            mainWindowMenuBar.add(updateMenu);
        }
        mainWindowMenuBar.add(helpMenu);
        
        if (Main.Main.getAccessLevel() != 3) {
            fileMenu.add(exportPassangersInformationMenuItem);
            exportPassangersInformationMenuItem.addActionListener(new ExportPassangersListToExcelListener());
        }
        fileMenu.add(exportFlightsInformationMenuItem);
        exportFlightsInformationMenuItem.addActionListener(new ExportFlightsListToExcelListener());
        fileMenu.addSeparator();
        fileMenu.add(logoutMenuItem);
        logoutMenuItem.addActionListener((ActionEvent e) -> {
            Main.Main.setAccessLevel(0);
            Main.Main.setFirstName("");
            Main.Main.setLastName("");
            mainWindowFrame.dispose();
            LoginWindow loginWindow = new GUI.LoginWindow();
            loginWindow.loginingWindows();
        });
        fileMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ExitListener());
        
        if (Main.Main.getAccessLevel() != 3) {
            informationMenu.add(passangersInformationMenuItem);
            passangersInformationMenuItem.addActionListener(new ViewPassangerListener());
        }
        informationMenu.add(flightInformationMenuItem);
        flightInformationMenuItem.addActionListener(new ViewFligthsListener());
        informationMenu.add(priceInformationMenuItem);
        priceInformationMenuItem.addActionListener(new ViewPricesListener());
        
        if (Main.Main.getAccessLevel() != 3) {
            searchMenu.add(searchPassangerMenuItem);
            searchPassangerMenuItem.addActionListener(new SearchPassengerListener());
        }
        searchMenu.add(searchPriceMenuItem);
        searchPriceMenuItem.addActionListener(new SearchPricesListener());
        searchMenu.add(searchFlightNumberMenuItem);
        searchFlightNumberMenuItem.addActionListener(new SearchFlightsListener());
        searchMenu.add(searchPortMenuItem);
        searchPortMenuItem.addActionListener(new SearchPortListener());
        
        if (Main.Main.getAccessLevel() != 3) {
            updateMenu.add(updatePassangerMenu);
            updatePassangerMenu.add(addNewPassangerUpdateMenuItem);
            addNewPassangerUpdateMenuItem.addActionListener(new AddNewPassangerListener());
            updatePassangerMenu.add(deletePassangerUpdateMenuItem);
            deletePassangerUpdateMenuItem.addActionListener(new DeletePassangerListener());
        }
        
        helpMenu.add(newVersionMenuItem);
        newVersionMenuItem.addActionListener(new SearchUpdateListener());
        helpMenu.add(heplAboutMenuItem);
        heplAboutMenuItem.addActionListener(new AboutMenuListener());
        
        mainWindowFrame.add(mainAboutPanel);
        mainAboutPanel.add(mainAboutLabelText);
        
        mainWindowFrame.setJMenuBar(mainWindowMenuBar);
        mainWindowFrame.setVisible(true);
    }

}