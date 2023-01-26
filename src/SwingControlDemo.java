import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.*;

public class SwingControlDemo implements ActionListener {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;    //Don't actually use this
    private JPanel searchPanel;     //Where you put the link
    private JPanel printPanel;      //Where all of the hyper links get printed
    private JMenuBar mb;
    private JMenu file, edit, help;     //Don't actually use this
    private JMenuItem cut, copy, paste, selectAll;  //Don't use this LOL
    private JTextField ta;  //Don't use this either !
    private JTextField la;  //The Textfield where you put the link in
    private JTextField keySearch;
    private JTextArea pa = new JTextArea(20,20); //"Print Area", where the hyper links actually get added
    private JScrollPane scrollLinks = new JScrollPane(pa);  //Makes pa scrollable
    // Sets frame dimensions
    private int WIDTH=800;
    private int HEIGHT=700;

    //for the keyword search
    private String searchTerm;

    //url thingy
    private String urlString;




    public SwingControlDemo() {
        prepareGUI();
    }

    public static void main(String[] args) {
        SwingControlDemo swingControlDemo = new SwingControlDemo();
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI() {

        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(2,1));
        mainFrame.getContentPane().setBackground(Color.black);

        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        mb.add(file);
        mb.add(edit);
        mb.add(help);


        // link thingy
        la = new JTextField("Paste Link Here",20);
        la.setBounds(50,5,WIDTH-300, HEIGHT-50);

        // text links area print area
        pa = new JTextArea();
        pa.setBounds(100,100,WIDTH-100,HEIGHT-100);

        //keyword search thing
        keySearch = new JTextField("Enter Keyword");


        //making pa scrollable
        scrollLinks = new JScrollPane(pa);
        scrollLinks.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



        mainFrame.add(mb);
        mainFrame.setJMenuBar(mb);

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        //This is where the search button is
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        printPanel = new JPanel();
        printPanel.setLayout(new GridLayout(1,1));

        searchPanel.add(controlPanel);
        searchPanel.add(keySearch);

        mainFrame.add(searchPanel);
      //  mainFrame.add(controlPanel);
        mainFrame.add(printPanel);
        mainFrame.setVisible(true);
    }

    private void showEventDemo() {
        headerLabel.setText("Control in action: Button");

        JButton okButton = new JButton("Search");
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        okButton.setActionCommand("Go");
        submitButton.setActionCommand("Submit");
        cancelButton.setActionCommand("Cancel");
        okButton.addActionListener(new ButtonClickListener());
        submitButton.addActionListener(new ButtonClickListener());
        cancelButton.addActionListener(new ButtonClickListener());

        okButton.setForeground(Color.blue);


        searchPanel.add(la);
        controlPanel.add(okButton);
        printPanel.add(scrollLinks);
        mainFrame.setVisible(true);
    }

    @Override
    //Doesn't actually do anything because I got rid of this textfield but if I take it out my code gets mad at me so I'm leaving it in
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
    }

    public void LinkPullerTest2() { // Should this be void or not???

        try {
            System.out.println();
           // URL url = new URL("https://www.milton.edu/");
            URL url = new URL(urlString);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {

                if (line.contains("href")) {

                    try {

                        int start = line.indexOf("https");
                        int enddouble = line.indexOf("\"", start);
                        int endsingle = line.indexOf("'", start);
                        int end;
                        if(endsingle< enddouble && endsingle != -1)
                        {
                            end = endsingle;
                        }else if (enddouble != -1){
                            end = enddouble;
                        }
                        else{
                            end = 0;
                        }

                        String link = line.substring(start, end);
                        if(link.contains(searchTerm)) {
                            System.out.println(link);

                            pa.append(link + "\n");
                        }
                    } catch(Exception e){

                    }



//
                }

            }

            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }


    }
//


    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Go")) {
             pa.setText("");
             searchTerm = keySearch.getText();
             urlString = la.getText();
               LinkPullerTest2();
               statusLabel.setText("Go Button clicked.");

            } else if (command.equals("Submit")) {
                statusLabel.setText("Submit Button clicked.");
            } else {
                statusLabel.setText("Cancel Button clicked.");
            }
        }
    }


}