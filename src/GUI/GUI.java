package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static javax.swing.JFileChooser.*;

/**
 * Created by acer on 09-Jan-18.
 */
public class GUI extends JFrame {
    CustomPannel customPannel = new CustomPannel();
    private JPanel pnBack,pnStatus,pnOption,pnProg;
    private JLabel lblDbCount,lblTbCount,lblStatus,lblCurrentDb,lblCurrentTable,lblName;
    private JButton btChangePath,btStart,btExit;
    private JTextField txPath;

    private JMenuBar menuBar;
    private JMenu file,settings;
    private JMenuItem path,exit,font,background;

    static double[] count = new double[2];
    static java.io.File fileName;

    GridBagConstraints g=new GridBagConstraints();

    public GUI(){

        dbData dbData = new dbData();
        dbData.createConnection();
        count = dbData.getCount();

        Font fontLable = new Font(null,Font.PLAIN,15);
        Color fontColor = Color.CYAN;
        Color bgColor = Color.black;

        pnBack = new JPanel(new GridBagLayout());
        pnBack.setBackground(Color.black);
        add(pnBack);

        menuBar = new JMenuBar();
        file = new JMenu("File");
        path = new JMenuItem("Path");
        exit = new JMenuItem("Exit");

        settings = new JMenu("Settings");
        font = new JMenuItem("Font");
        background = new JMenuItem("BackGround");

        file.add(path);
        file.add(settings);
        file.add(exit);

        settings.add(font);
        settings.add(background);

        menuBar.add(file);
        menuBar.add(settings);

        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 4;
        g.gridheight =1;
        g.weightx = 0;
        g.weighty = 0;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnBack.add(menuBar,g);

        pnStatus = new JPanel(new GridBagLayout());
        pnStatus.setVisible(true);
        pnStatus.setBackground(bgColor);
        pnStatus.setForeground(fontColor);
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 4;
        g.gridheight =1;
        g.weightx = 0;
        g.weighty = 0;
        g.fill=GridBagConstraints.BOTH;
        pnBack.add(pnStatus,g);

        pnOption = new JPanel(new GridBagLayout());
        pnOption.setVisible(true);
        pnOption.setBackground(bgColor);
        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth = 1;
        g.gridheight =1;
        g.weightx = 0;
        g.weighty = 0;
        g.fill=GridBagConstraints.BOTH;
        pnBack.add(pnOption,g);

        pnProg = new JPanel(new GridBagLayout());
        pnProg.setVisible(true);
        pnProg.setBackground(bgColor);
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 3;
        g.gridheight =1;
        g.weighty = 3;
        g.weightx = 3;
        g.fill=GridBagConstraints.BOTH;
        pnBack.add(pnProg,g);

        lblDbCount = new JLabel(count[0]+" Databases Found");
        lblDbCount.setVisible(true);
        lblDbCount.setFont(fontLable);
        lblDbCount.setBackground(bgColor);
        lblDbCount.setForeground(fontColor);
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 3;
        g.gridheight =1;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnStatus.add(lblDbCount,g);

        lblTbCount = new JLabel(count[1]+" Tables Found");
        lblTbCount.setVisible(true);
        lblTbCount.setFont(fontLable);
        lblTbCount.setBackground(bgColor);
        lblTbCount.setForeground(fontColor);
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 3;
        g.gridheight =1;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnStatus.add(lblTbCount,g);

        txPath = new JTextField("");
        txPath.setFont(fontLable);
        txPath.setForeground(fontColor);
        txPath.setBackground(bgColor);
        txPath.setEnabled(false);
        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 2;
        g.gridheight =1;
        g.weighty = 0;
        g.weightx = 1;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnStatus.add(txPath,g);

        btChangePath = new JButton("Set Path");
        btChangePath.setForeground(fontColor);
        btChangePath.setBackground(bgColor);
        g.gridx = 2;
        g.gridy = 3;
        g.gridwidth = 1;
        g.gridheight =1;
        g.weighty = 0;
        g.weightx = 1;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnStatus.add(btChangePath,g);

        lblStatus = new JLabel("Ready to copy");
        lblStatus.setFont(fontLable);
        lblStatus.setBackground(bgColor);
        lblStatus.setForeground(fontColor);
        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth = 2;
        g.gridheight =1;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnStatus.add(lblStatus,g);

        btStart = new JButton("Start");
        btStart.setFont(fontLable);
        btStart.setEnabled(false);
        btStart.setForeground(fontColor);
        btStart.setBackground(bgColor);
        g.gridx = 2;
        g.gridy = 4;
        g.gridwidth = 1;
        g.gridheight =1;
        g.weighty = 0;
        g.weightx = 1;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnStatus.add(btStart,g);

        customPannel.setVisible(true);
        customPannel.setEnabled(true);
        customPannel.setBackground(bgColor);
        customPannel.radius = 50;
        customPannel.barColor = fontColor;
        customPannel.fontColor = fontColor;
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 3;
        g.gridheight =1;
        g.weighty = 1;
        g.weightx = 3;
        g.fill=GridBagConstraints.BOTH;
        pnProg.add(customPannel,g);

        lblCurrentDb = new JLabel("Current Database");
        lblCurrentDb.setFont(fontLable);
        lblCurrentDb.setForeground(fontColor);
        lblCurrentDb.setBackground(bgColor);
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.gridheight =1;
        g.weighty = 0;
        g.weightx = 1;
        g.fill=GridBagConstraints.BOTH;
        pnOption.add(lblCurrentDb,g);

        lblCurrentTable = new JLabel("Current Table");
        lblCurrentTable.setFont(fontLable);
        lblCurrentTable.setForeground(fontColor);
        lblCurrentTable.setBackground(bgColor);
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 1;
        g.gridheight =1;
        g.weighty = 0;
        g.weightx = 1;
        g.fill=GridBagConstraints.BOTH;
        pnOption.add(lblCurrentTable,g);

        btExit = new JButton("Exit");
        btExit.setFont(fontLable);
        btExit.setForeground(fontColor);
        btExit.setBackground(bgColor);
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 1;
        g.gridheight =0;
        g.weighty = 0;
        g.weightx = 0;
        g.fill=GridBagConstraints.BOTH;
        pnOption.add(btExit,g);

        lblName = new JLabel("Created by Darshana Ariyarathna | darshana.uop@gmail.com | +94774901245");
        lblName.setFont(new Font(null,Font.ITALIC,12));
        lblName.setForeground( Color.green);
        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 1;
        g.gridheight =1;
        g.weighty = 0;
        g.weightx = 0;
        g.fill=GridBagConstraints.BOTH;
        pnBack.add(lblName,g);

        btExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int exit = JOptionPane.showOptionDialog(null, "Do you want to Exit?", "Exit Confirmation...",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null,null);
                if (exit==0){
                    System.exit(0);
                }
            }
        });

        btChangePath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(DIRECTORIES_ONLY);
                int response = fc.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION){
                    txPath.setText(fc.getSelectedFile().toString());
                    fileName = fc.getSelectedFile();
                    btStart.setEnabled(true);
                }else {
                    JOptionPane.showMessageDialog(null,"File selection canceled","Canceled",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        btStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int numOfCollumns=0;String word;
                        int countTab=0;

                        lblStatus.setText("Copping to :"+fileName);

                        try{
                            try{
                                dbData.rsDb = dbData.psDb.executeQuery("SHOW DATABASES");
                                while (dbData.rsDb.next()){
                                    try {
                                        String dbName = dbData.rsDb.getString(1);
                                        lblCurrentDb.setText(dbName);
                                        FileWriter streem = new FileWriter(fileName + "\\" + dbName+".txt");
                                        BufferedWriter out = new BufferedWriter(streem);

                                        dbData.psDbOpen.execute("USE "+dbName);

                                        dbData.rstable = dbData.psDbOpen.executeQuery("SHOW TABLES");

                                        while (dbData.rstable.next()){
                                            try {
                                                String tableName = dbData.rstable.getString(1);
                                                countTab++;

                                                double percentage= (countTab/count[1])*100;
                                                int percentageVal = (int) percentage;
                                                //System.out.println(countTab+" , "+percentage+" , "+ (int) (countTab/count[1])*100+" , "+((int) percentage));

                                                customPannel.Update(percentageVal);
                                                customPannel.repaint();

                                                lblCurrentTable.setText(tableName);
                                                out.newLine();
                                                out.write("__________________Begin_Table__________________");
                                                out.newLine();
                                                out.write("Database :"+dbName +",\tTable name : "+tableName);
                                                out.newLine();
                                                //System.out.println("34 : "+tableName);

                                                dbData.rsColl = dbData.psColl.executeQuery("show columns from "+tableName);
                                                //System.out.println("36");
                                                while (dbData.rsColl.next()) {
                                                    try {
                                                        String columnName=dbData.rsColl.getString(1);
                                                        out.write(columnName+"\t");
                                                        //System.out.println(dbData.rsColl.getString(1));
                                                        numOfCollumns++;
                                                    }catch (Exception e1){
                                                        JOptionPane.showMessageDialog(null,"Error in calculating columns","Error",JOptionPane.PLAIN_MESSAGE);
                                                    }
                                                }
                                                out.newLine();
                                                //System.out.println("Columns :"+numOfCollumns);

                                                dbData.rsData = dbData.psData.executeQuery("SELECT * FROM "+tableName);
                                                //System.out.println("37");
                                                while (dbData.rsData.next()){
                                                    try{
                                                        for (int i=1;i<=numOfCollumns;i++){
                                                            word = dbData.rsData.getString(i);
                                                            out.write(word+"\t");
                                                            //System.out.print(word+"\t\t");
                                                        }
                                                        out.newLine();
                                                        //System.out.println();
                                                    }catch (Exception e1){
                                                        //JOptionPane.showMessageDialog(null,"Error getting data","Error",JOptionPane.PLAIN_MESSAGE);
                                                    }
                                                }
                                                out.write("__________________End_Table__________________");
                                                out.newLine();
                                                numOfCollumns=0;
                                            }catch (Exception e1){
                                                //JOptionPane.showMessageDialog(null,"Error opening tables","Error",JOptionPane.PLAIN_MESSAGE);
                                            }
                                        }
                                        out.close();

                                    }catch (Exception e1){
                                    }
                                }
                                lblStatus.setText("Done");
                            }catch (Exception e2){
                                e2.printStackTrace();
                                JOptionPane.showMessageDialog(null,e2.getMessage() + " : Error in Reading dataBase","Error",JOptionPane.PLAIN_MESSAGE);
                            }
                            /**
                             String word = txPath.getText()+"Darshana Ariyarathna";
                             FileWriter streem = new FileWriter(fileNane + "\\" + "a.txt");
                             BufferedWriter out = new BufferedWriter(streem);
                             out.write(word);
                             out.close();
                             */
                        }catch (Exception e1){
                            JOptionPane.showMessageDialog(null,e1.getMessage() + " : Error in writing file","Error",JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }).start();

            }
        });
    }



}
