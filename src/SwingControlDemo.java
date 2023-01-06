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
    private JPanel controlPanel;
    private JPanel searchPanel;
    private JPanel printPanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextField ta;
    private JTextField la;
    private JTextArea pa;
    private int WIDTH=800;
    private int HEIGHT=700;






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
        mainFrame.setLayout(new GridLayout(4,1));
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

        //    ta = new JTextField(20);
        //   ta.setBounds(50, 30, WIDTH-400, HEIGHT-50);
        // link thingy
        la = new JTextField("Paste Link Here",20);
        la.setBounds(50,5,WIDTH-300, HEIGHT-50);
        // text links area print area
        pa = new JTextArea();
        pa.setBounds(100,100,WIDTH-100,HEIGHT-100);



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
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        printPanel = new JPanel();
        printPanel.setLayout(new GridLayout(2,1));



        // mainFrame.add(headerLabel);
        //        mainFrame.add(la);
        mainFrame.add(searchPanel);
        mainFrame.add(controlPanel);

        //        statusLabel.setText("this is the status label");
        // mainFrame.add(statusLabel);
        //        mainFrame.add(ta);
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
        printPanel.add(pa);
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
    }

    public void HtmlRead(){
        try {
            System.out.println();
            URL url = new URL(la.getText());
            //url = new URL("https://www.milton.edu/");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {


                if (line.contains("https")) {
                    int start = line.indexOf("https");
                    String line1 = line.substring(start);
                   System.out.println("not chopped " +line1);

                    int end = line1.indexOf("https", start + 1);

                    while (end >= 1) {

                        start = line1.indexOf("https");

                       // if (end == -1) {

                            if(line1.indexOf(" ", start + 1)>0){
                                end = line1.indexOf(" ", start + 1);
                            }
                            else if (line1.indexOf(" *", start + 1)>0){
                                end = line1.indexOf(" *", start + 1);
                            }


                        System.out.println(start);

                        System.out.println(end);
                        System.out.println(line1);

                        // }

                        String indexLine = line1.substring(0, end);
                        System.out.println(indexLine);
                        pa.append(line);


                        // moved this to the end from the beginning of while loop bc we want to change where we are searching
                        start = end;
                        end = line1.indexOf("https", start + 1);
                        line1 = line1.substring(end);
                    }
                    if (end == -1) {
                        end = line1.indexOf("\" ", start + 1);

                    }

                    String indexLine = line1.substring(0, end);
                    System.out.println(indexLine);
                    pa.append(line);
                }
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }


    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Go")) {
                HtmlRead();
                statusLabel.setText("Go Button clicked.");

            } else if (command.equals("Submit")) {
                statusLabel.setText("Submit Button clicked.");
            } else {
                statusLabel.setText("Cancel Button clicked.");
            }
        }
    }


}