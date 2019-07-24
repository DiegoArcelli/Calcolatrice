/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcolatrice;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author diego
 */
public class Finestra extends JFrame implements ActionListener {
    
    JButton[] tasti;
    JTextField testo;
    double num;
    char op;
    boolean dot;
    JMenuItem howto;
    JMenuItem inform;
    JMenuItem url;
    
    public Finestra(String title){
        super(title);
        dot = false;
        url = new JMenuItem("GitHub");
        url.addActionListener(this);
        JMenuBar barramenu = new JMenuBar();
        JMenu aiuto = new JMenu("Help");
        JMenu info = new JMenu("Info");
        barramenu.add(aiuto);
        barramenu.add(info);
        howto = new JMenuItem("How to use");
        howto.addActionListener(this);
        aiuto.add(howto);
        inform = new JMenuItem("Information");
        inform.addActionListener(this);
        info.add(inform);
        url = new JMenuItem("GitHub");
        url.addActionListener(this);
        info.add(url);
        JPanel tastiera = new JPanel();
        JPanel schermo = new JPanel();
        tastiera.setLayout(new GridLayout(5,4,10,10));
        schermo.setLayout(new GridLayout(1,0,10,10));
        schermo.setPreferredSize(new Dimension(150,40));
        this.setLayout(new BorderLayout());
        this.repaint();
        tasti = new JButton[20];
        tasti[0] = new JButton("7");
        tasti[1] = new JButton("8");
        tasti[2] = new JButton("9");
        tasti[3] = new JButton("+");
        tasti[4] = new JButton("4");
        tasti[5] = new JButton("5");
        tasti[6] = new JButton("6");
        tasti[7] = new JButton("-");
        tasti[8] = new JButton("1");
        tasti[9] = new JButton("2");
        tasti[10] = new JButton("3");
        tasti[11] = new JButton("x");
        tasti[12] = new JButton("0");
        tasti[13] = new JButton("C");
        tasti[14] = new JButton("CA");
        tasti[15] = new JButton("/");
        tasti[16] = new JButton(".");
        tasti[17] = new JButton("^");
        tasti[18] = new JButton("√");
        tasti[19] = new JButton("=");
        for(int i=0;i<tasti.length;i++){
            tastiera.add(tasti[i]);
            tasti[i].addActionListener(this);
            tasti[i].setActionCommand(tasti[i].getText());
        }
        testo = new JTextField(20);
        testo.setText("0.0");
        testo.setEditable(false);
        schermo.add(testo);
        schermo.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        tastiera.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(schermo, BorderLayout.NORTH);
        add(tastiera, BorderLayout.CENTER);
        this.setJMenuBar(barramenu);
    }

    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //verifica se è stato premuto un tasto
        if(ae.getSource() instanceof JButton){
            //verifica se è stato premuto un numero altrimenti lancia un'eccezione di numero mal formattato
            try {
                Integer.parseInt(ae.getActionCommand());
                if(dot==false){
                    if(testo.getText().equals("0.0")){
                        testo.setText(ae.getActionCommand()+".0");
                    } else {
                        String[] parts = testo.getText().split("\\.");
                        testo.setText(parts[0] + ae.getActionCommand() + "." + parts[1]);
                    }
                } else {
                    String[] parts = testo.getText().split("\\.");
                    if(parts.length==1){
                        testo.setText(parts[0] + "." + ae.getActionCommand());
                    } else {
                        testo.setText(parts[0] + "." + parts[1] + ae.getActionCommand());
                    }
                }
            } catch (NumberFormatException ex){     //eccezione che viene lanciata quando si preme un teasto che non è un numero
                if(ae.getActionCommand().equalsIgnoreCase("C")){
                    if(dot==false){
                        String[] parts = testo.getText().split("\\.");
                        testo.setText(parts[0].substring(0,parts[0].length()-1) + "." + parts[1]);
                        if(testo.getText().charAt(0)=='.'){
                            testo.setText("0.0");
                        }
                    } else {
                        String[] parts = testo.getText().split("\\.");
                        testo.setText(parts[0] + "." + parts[1].substring(0,parts[1].length()-1));
                        if(testo.getText().charAt(testo.getText().length()-1)=='.'){
                            testo.setText(parts[0] + "." + "0");
                        }
                    }
                } else if(ae.getActionCommand().equals("+") || ae.getActionCommand().equals("-") || ae.getActionCommand().equals("x") || ae.getActionCommand().equals("/") || ae.getActionCommand().equals("^")){
                    num=Double.parseDouble(testo.getText());
                    op=ae.getActionCommand().charAt(0);
                    testo.setText("0.0");
                    dot=false;
                    tasti[3].setEnabled(false);
                    tasti[7].setEnabled(false);
                    tasti[11].setEnabled(false);
                    tasti[15].setEnabled(false);
                    tasti[17].setEnabled(false);
                } else if(ae.getActionCommand().equals("CA")){
                    testo.setText("0.0");
                } else if(ae.getActionCommand().equals("√")) {
                    //num=Math.sqrt(Double.parseDouble(testo.getText()));
                    testo.setText(Double.toString(Math.sqrt(Double.parseDouble(testo.getText()))));
                } else if(ae.getActionCommand().equals(".")){
                    dot=true;
                    String[] parts = testo.getText().split("\\.");
                    testo.setText(parts[0] + ".");
                } else if(ae.getActionCommand().equals("=")){
                    if(op=='+'){
                        num+=Double.parseDouble(testo.getText());
                    } else if(op=='-'){
                        num-=Double.parseDouble(testo.getText());
                    } else if(op=='x'){
                        num*=Double.parseDouble(testo.getText());
                    } else if(op=='/'){
                        num/=Double.parseDouble(testo.getText());
                    } else if(op=='^'){
                        num=Math.pow(num,Double.parseDouble(testo.getText()));
                    }
                    tasti[3].setEnabled(true);
                    tasti[7].setEnabled(true);
                    tasti[11].setEnabled(true);
                    tasti[15].setEnabled(true);
                    tasti[17].setEnabled(true);
                    dot=false;
                    testo.setText(Double.toString(num));
                }
            }
        }
        
        if(ae.getSource() instanceof JMenuItem){
            if(ae.getSource() == howto){
                String msg = readFile("howto.txt");
                JOptionPane.showMessageDialog(this,msg,"How to use the calulator",JOptionPane.INFORMATION_MESSAGE);
            } else if(ae.getSource() == inform){
                String msg = readFile("info.txt");
                JOptionPane.showMessageDialog(this,msg,"Information",JOptionPane.INFORMATION_MESSAGE);
            } else if(ae.getSource() == url){
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/DiegoArcelli/Calcolatrice"));
                } catch (URISyntaxException ex) {
                    Logger.getLogger(Finestra.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Finestra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    public String readFile(String nome){
        String msg = "";
        try(FileInputStream fis = new FileInputStream(nome)){
            String scan;
            while(fis.available()>0){
                msg+=((char) fis.read());
            }
        } catch(FileNotFoundException ex){
            System.err.println("File non trovato");
        } catch(IOException ex){
            System.err.println("Errore");
        } finally {
            return msg;
        }
    }

    
}
