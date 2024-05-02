package src;

// Import the required classes from the java.awt and java.awt.event packages
import java.awt.*;
import java.awt.event.*;

public class AWTExample {
    // Declare the components of the GUI
    private Frame mainFrame;
    private Label headerLabel;
    private Label statusLabel;
    private Panel controlPanel;

    public AWTExample() {
        // Call the prepareGUI method to set up the GUI
        prepareGUI();
    }

    public static void main(String[] args) {
        // Create an instance of the AWTExample class and call the showEventDemo method
        AWTExample awtExample = new AWTExample();
        awtExample.showEventDemo();
    }

    private void prepareGUI() {
        // Create the main frame and set its size and layout
        mainFrame = new Frame("Java AWT Examples");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new GridLayout(3, 1));

        // Add a window listener to the main frame to close the application when the close button is clicked
        mainFrame.addWindowListener(
            new WindowAdapter() {

                public void windowClosing(WindowEvent windowEvent) {
                    System.exit(0);
                }
            }
        );

        // Create the header label and set its alignment
        headerLabel = new Label();
        headerLabel.setAlignment(Label.CENTER);

        // Create the status label and set its alignment and size
        statusLabel = new Label();
        statusLabel.setAlignment(Label.CENTER);
        statusLabel.setSize(350, 100);

        // Create the control panel and set its layout
        controlPanel = new Panel();
        controlPanel.setLayout(new FlowLayout());

        // Add the header label, control panel, and status label to the main frame
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);

        // Make the main frame visible
        mainFrame.setVisible(true);
    }

    private void showEventDemo() {
        // Set the text of the header label
        headerLabel.setText("Control in action: Button");

        // Create the buttons and add action listeners to them
        Button okButton = new Button("OK");
        Button submitButton = new Button("Submit");
        Button cancelButton = new Button("Cancel");

        okButton.addActionListener(
            new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    // Set the text of the status label when the OK button is clicked
                    statusLabel.setText("Ok Button clicked.");
                }
            }
        );
        submitButton.addActionListener(
            new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    // Set the text of the status label when the Submit button is clicked
                    statusLabel.setText("Submit Button clicked.");
                }
            }
        );
        cancelButton.addActionListener(
            new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    // Set the text of the status label when the Cancel button is clicked
                    statusLabel.setText("Cancel Button clicked.");
                }
            }
        );
        // Add buttons to the control panel
        controlPanel.add(okButton);
        controlPanel.add(submitButton);
        controlPanel.add(cancelButton);

        // Set the visibility of the main frame to true
        mainFrame.setVisible(true);
    }
}