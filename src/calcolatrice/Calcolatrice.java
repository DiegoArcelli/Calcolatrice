/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcolatrice;

import javax.swing.JFrame;

/**
 *
 * @author diego
 */
public class Calcolatrice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Finestra f = new Finestra("Calcolatrice");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(250,300);
        f.setResizable(false);
        f.setVisible(true);
    }
    
}
