package GUI;

import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import Listeners.*;

public class MainWindowUser {
    private final JFrame mainWindowUserFrame = new JFrame("AirPort Control " + Main.Main.getVersionApp());
    private final JPanel mainAboutPanel = new JPanel();
    private final JLabel mainAboutLabelText = new JLabel(
                Main.Main.getFirstName() + " " + 
                Main.Main.getLastName() + " | Access Level: " + 
                Main.Main.getAccessLevel());
    
    private final JMenuBar mainWindowMenuBar = new JMenuBar();
    private final JMenu informationMenu = new JMenu("Information");
    private final JMenu searchMenu = new JMenu("Search");
    private final JMenu helpMenu = new JMenu("Help");
    
    private final JMenuItem flightInformationMenuItem = new JMenuItem("Flights");
    private final JMenuItem priceInformationMenuItem = new JMenuItem("Prices");

    private final JMenuItem searchPriceMenuItem = new JMenuItem("Search Price");
    private final JMenuItem searchFlightNumberMenuItem = new JMenuItem("Search Flight Number");
    private final JMenuItem searchPortMenuItem = new JMenuItem("Search Arrival/Departure Port");
    
    private final JMenuItem updatePriceMenuItem = new JMenuItem("Pricelist");
    private final JMenuItem updateFlightsMenuItem = new JMenuItem("Flights Information");

    private final JMenuItem heplAboutMenuItem = new JMenuItem("About");
    private final JMenuItem newVersionMenuItem = new JMenuItem("Look New Version...");
    
    public void mainWindow () {
        mainWindowUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindowUserFrame.setBounds(100, 100, 450, 100);
        mainWindowUserFrame.setLayout(new GridBagLayout());
        
        mainWindowMenuBar.add(informationMenu);
        mainWindowMenuBar.add(searchMenu);
        mainWindowMenuBar.add(helpMenu);
        
        informationMenu.add(flightInformationMenuItem);
        flightInformationMenuItem.addActionListener(new ViewFligthsListener());
        informationMenu.add(priceInformationMenuItem);
        priceInformationMenuItem.addActionListener(new ViewPricesListener());
        
        searchMenu.add(searchPriceMenuItem);
        searchPriceMenuItem.addActionListener(new SearchPricesListener());
        searchMenu.add(searchFlightNumberMenuItem);
        searchFlightNumberMenuItem.addActionListener(new SearchFlightsListener());
        searchMenu.add(searchPortMenuItem);
        searchPortMenuItem.addActionListener(new SearchPortListener());
        
        helpMenu.add(newVersionMenuItem);
        newVersionMenuItem.addActionListener(new NotHaveUpdateListener());
        helpMenu.add(heplAboutMenuItem);
        heplAboutMenuItem.addActionListener(new AboutMenuListener());
        
        mainWindowUserFrame.add(mainAboutPanel);
        mainAboutPanel.add(mainAboutLabelText);
        
        mainWindowUserFrame.setJMenuBar(mainWindowMenuBar);
        mainWindowUserFrame.setVisible(true);
    }
}