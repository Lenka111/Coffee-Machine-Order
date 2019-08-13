//Elena Voinu

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.text.DecimalFormat;
import javax.swing.*;


public class CoffeeMachine extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    //created extra JLabels to display gifs and text
    JLabel l1, l2, l3, l4, l5, l6, l7, l8;

    // created extra button "ok" to return to main  app after the message summary is displayed to the user
    JButton b1, b2,b3;
    JTextField t1, t2, t3;
    JCheckBox cream, raw, espresso;
    JMenuBar menuBar;
    JMenu mnFile, mnHelp;
    JMenuItem mntmExit, mntmAbout;

    private JRadioButton small;
    private JRadioButton large;
    private JRadioButton medium;
    private ButtonGroup group;

    //Get the currency used by this decimal format when formatting currency values
    DecimalFormat decform = new DecimalFormat("$##,###.00");

    // Constructor
    CoffeeMachine() {
        l1 = new JLabel(" Customer Name");
        l2 = new JLabel(" Amount to pay");
        l3 = new JLabel("  ");
        l4 = new JLabel("  ");
        l5 = new JLabel("  ");
        l6 = new JLabel("  ");

        menuBar = new JMenuBar();
        mnFile = new JMenu("File");
        mntmExit = new JMenuItem("Exit");
        mnHelp = new JMenu("Help");
        mntmAbout = new JMenuItem("About");


        b1 = new JButton("COMPUTE");
        b2 = new JButton("EXIT");
        b3 = new JButton("OK");

        t1 = new JTextField(10);
        t2 = new JTextField(10);
        t3 = new JTextField(10);

        small = new JRadioButton("Small", true);
        large = new JRadioButton("Large", false);
        medium = new JRadioButton("Medium", false);
        group = new ButtonGroup();
        group.add(small);
        group.add(large);
        group.add(medium);

        cream = new JCheckBox("Cream", false);
        raw = new JCheckBox("Raw Sugar", false);
        espresso = new JCheckBox("Espresso Shot", false);

        add(l1);
        add(t1);
        add(small);
        add(cream);
        add(medium);
        add(raw);
        add(large);
        add(espresso);
        add(l2);
        add(t2);
        add(l5);
        add(l6);
        add(b1);
        add(b2);

        add(menuBar);
        menuBar.add(mnFile);
        mnFile.add(mntmExit);
        menuBar.add(mnHelp);
        mnHelp.add(mntmAbout);

        b1.addActionListener(this);
        b2.addActionListener(e -> System.exit(0));
        b3.addActionListener(e -> System.exit(0));

        setSize(500, 400);
        setLayout(new GridLayout(7, 2));
        setTitle("Coffee Machine");
        setJMenuBar(menuBar);
        mntmExit.addActionListener(e -> System.exit(0));
    }
    //Perform a certain task when an event occurs(clicking a button, selecting a checkbox etc.)
    public void actionPerformed(ActionEvent ae) {

        double price = 0;
        String a = " ", message = "";

        if (small.isSelected() == true) {
            // if small coffee is selected then compute the price and display the message
            System.out.println("Small");
            price += 1.25;
            message += "Small Coffee\n";
        }
        if (medium.isSelected() == true) {
            // if medium coffee is selected then compute the price and display the message
            System.out.println("Medium");
            price += 1.75;
            message += "Medium Coffee\n";
        }

        if (large.isSelected() == true) {
            // if large coffee is selected then compute the price and display message
            System.out.println("large");
            price += 2.30;
            message += "Large Coffee\n";
        }
        if (cream.isSelected() == true) {
            // if cream checkbox is selected then computer the price and display message
            price = price + 0.75;
            message += "\nWith Cream\n";
        }
        if (raw.isSelected() == true) {
            // // if raw sugar checkbox is selected then compute the price and display message
            price = price + 0.75;
            message += "\nWith Raw Sugar\n";
        }
        if (espresso.isSelected() == true) {
            // if espresso checkbox is selected then compute the price and display message
            price = price + 0.75;
            message += "\nAnd Espresso Shot";
        }

        if (ae.getSource() == b1) {

            try {
                a = t1.getText();

                //if the input is not a String then throw exception
                if (!a.matches("^[ a-z A-Z ]+$"))

                    throw new Exception();

                    //otherwise display the order summary to the user
                else {

                    t2.setText(decform.format(price));

                    //frame attributes
                    JFrame aFrame = new JFrame("Order Summary");
                    aFrame.setLayout(new GridLayout());


                    l7 = new JLabel(("<html>Summary:<BR>Hello " + a+",")+ ("<BR> \tEnjoy Your Beverage!") +
                    ("<BR>\tYour Order: " + message)+("<BR> \tGrand Total: " + decform.format(price))+
                    "</html>");

                    Font headlineFont = new Font("Arial", Font.BOLD + Font.ITALIC, 26);
                    l7.setFont(headlineFont);

                    // set summary text color
                    Color customColor = new Color( 125, 5, 82) ;
                    l7.setForeground(customColor);

                    ClassLoader ldr = this.getClass().getClassLoader();
                    ImageIcon coffee = new ImageIcon( ldr.getResource("coffee.gif") );

                    l8 = new JLabel("Coffee",coffee,JLabel.CENTER);
                    l8.setHorizontalTextPosition(JLabel.CENTER);
                    l8.setVerticalTextPosition(JLabel.BOTTOM);

                    aFrame.add(l7);
                    aFrame.add(l8);
                    aFrame.add(b3);
                    // needed to have multiple lines of text in the JLabel l7
                    aFrame.pack();

                    JPanel pnl = new JPanel();
                    pnl.setLayout(null);

                    //position the button
                    b3.setBounds(100,300, 70, 40);
                    pnl.add(b3);
                    //put the button under the coffee cup(JLabel l8)
                    l7.add(b3);

                    aFrame.setDefaultCloseOperation(1);
                    aFrame.setSize(600, 400);
                    aFrame.setVisible(true);
                }
            }
            // handle exception and ask the user to re-enter a non numeric, non-character name
            catch (Exception e) {
                String msg = "Error: \nPlease enter a valid non-numeric and non-character name ";
               // String msg = "Error️!\nPlease Enter a valid non-Numeric and non-Character name!!!";
                JOptionPane.showMessageDialog(null, msg + JOptionPane.INFORMATION_MESSAGE);
            }

        }

        mntmAbout.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                //gif path
                URL url = this.getClass().getResource("giphy.gif");
                Icon icon = new ImageIcon(url);
                String msg = "";

                msg +="I never laugh until I've had my coffee...\n" + "\n" + "This app was created " +
                        "by Elena Voinu\nFor CIS 144 Class, Lab12\n©Copyright 2019.\nAll rights reserved";

                //the message that is displayed when the user clicks on "about" in the message box.
                JOptionPane.showMessageDialog(null, msg,
                        "About", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        });
    }

    //main class
    public static void main (String [] avg)
    {
        //create object of CoffeeMachine class
        CoffeeMachine a = new CoffeeMachine();
        a.setVisible(true);
        a.setLocation(200, 200);

    }// end main

}// end class




