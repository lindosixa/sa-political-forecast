package com.mylindwana.opiniontracker;

import javax.swing.SwingUtilities;


public class OpinionTracker {

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> { 
            OpinionApp app = new OpinionApp();
            app.setVisible(true);
            
        });
        
    }
}
