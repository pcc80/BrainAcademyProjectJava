package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class AboutMenuListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "AirPort Control Center Application ("+Main.Main.getVersionApp()+")\nProject by Brain Academy\nCreated by PheNiX\n2016\n", "About", JOptionPane.PLAIN_MESSAGE);
    }

}