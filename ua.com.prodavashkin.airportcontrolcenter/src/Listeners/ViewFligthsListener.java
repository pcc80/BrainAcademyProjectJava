package Listeners;

import GUI.ViewFlights;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;

import javax.swing.JOptionPane;



public class ViewFligthsListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {

        ViewFlights vf = new ViewFlights();

        try {
            vf.ViewFlighhts();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Something wrong !", "WRONG", JOptionPane.ERROR_MESSAGE);
        }

    }

}