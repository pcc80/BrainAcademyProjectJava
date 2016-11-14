package GUI;

import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Listeners.*;


public class MainWindow {
    
    private final JFrame mainWindowFrame = new JFrame("AirPort Control " + Main.Main.getVersionApp());
    private final JPanel mainAboutPanel = new JPanel();
    
    private final JLabel mainAboutLabelText = new JLabel(
                Main.Main.getFirstName() + " " + 
                Main.Main.getLastName() + " | Access Level: " + 
                Main.Main.getAccessLevel());
    
    private final JMenuBar mainWindowMenuBar = new JMenuBar();
    private final JMenu informationMenu = new JMenu("Information");
    private final JMenu searchMenu = new JMenu("Search");
    private final JMenu updateMenu = new JMenu("Update");
    private final JMenu helpMenu = new JMenu("Help");
    
    private final JMenuItem passangersInformationMenuItem = new JMenuItem("Passangers");
    private final JMenuItem flightInformationMenuItem = new JMenuItem("Flights");
    private final JMenuItem priceInformationMenuItem = new JMenuItem("Prices");

    private final JMenuItem searchPassangerMenuItem = new JMenuItem("Search Passanger");
    private final JMenuItem searchPriceMenuItem = new JMenuItem("Search Price");
    private final JMenuItem searchFlightNumberMenuItem = new JMenuItem("Search Flight Number");
    private final JMenuItem searchPortMenuItem = new JMenuItem("Search Arrival/Departure Port");
    
    private final JMenuItem updatePassangerMenuItem = new JMenuItem("Passanger Information");
    private final JMenuItem updatePriceMenuItem = new JMenuItem("Pricelist");
    private final JMenuItem updateFlightsMenuItem = new JMenuItem("Flights Information");

    private final JMenuItem heplAboutMenuItem = new JMenuItem("About");
    private final JMenuItem newVersionMenuItem = new JMenuItem("Look New Version...");
    
    public void mainWindow () {
        mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindowFrame.setBounds(100, 100, 450, 100);
        mainWindowFrame.setLayout(new GridBagLayout());
        
        mainWindowMenuBar.add(informationMenu);
        mainWindowMenuBar.add(searchMenu);
        mainWindowMenuBar.add(updateMenu);
        mainWindowMenuBar.add(helpMenu);
        
        informationMenu.add(passangersInformationMenuItem);
        passangersInformationMenuItem.addActionListener(new NothingListener());
        informationMenu.add(flightInformationMenuItem);
        flightInformationMenuItem.addActionListener(new NothingListener());
        informationMenu.add(priceInformationMenuItem);
        priceInformationMenuItem.addActionListener(new NothingListener());
        
        searchMenu.add(searchPassangerMenuItem);
        searchPassangerMenuItem.addActionListener(new SearchPassengerListener());
        searchMenu.add(searchPriceMenuItem);
        searchPriceMenuItem.addActionListener(new NothingListener());
        searchMenu.add(searchFlightNumberMenuItem);
        searchFlightNumberMenuItem.addActionListener(new NothingListener());
        searchMenu.add(searchPortMenuItem);
        searchPortMenuItem.addActionListener(new NothingListener());
        
        updateMenu.add(updatePassangerMenuItem);
        updatePassangerMenuItem.addActionListener(new NothingListener());
        updateMenu.add(updatePriceMenuItem);
        updatePriceMenuItem.addActionListener(new NothingListener());
        updateMenu.add(updateFlightsMenuItem);
        updateFlightsMenuItem.addActionListener(new NothingListener());
        
        helpMenu.add(newVersionMenuItem);
        newVersionMenuItem.addActionListener(new NotHaveUpdateListener());
        helpMenu.add(heplAboutMenuItem);
        heplAboutMenuItem.addActionListener(new AboutMenuListener());
        
        mainWindowFrame.add(mainAboutPanel);
        mainAboutPanel.add(mainAboutLabelText);
        
        mainWindowFrame.setJMenuBar(mainWindowMenuBar);
        mainWindowFrame.setVisible(true);
    }
}
