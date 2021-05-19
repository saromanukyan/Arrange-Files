import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ArrangerForm extends JFrame {
    Container container = getContentPane();

    ImageIcon mainIcon = new ImageIcon("images\\up103.png");
    JLabel imageLabel = new JLabel("", mainIcon, SwingConstants.CENTER);

    JLabel nameLabel = new JLabel("Arrange Files");
    JTextField nameTextView = new JTextField("   Enter directory to arrange");

    JCheckBox includeSubFolders = new JCheckBox("   include subfolders");

    ToggleBtn toggleButton = new ToggleBtn();
    JLabel toggleText = new JLabel("Open folder when done");

    JButton arrangeBtn = new JButton("Press to arrange");

    static boolean firstClick = true;

    ArrangerForm() {
        setVisible(true);
        setBounds(0, 0, 600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        container.setLayout(null);
        setLocationAndSize();
        setStyle();
        addComponentsToContainer();
        addActionEvent();
    }

    private void setLocationAndSize() {
        imageLabel.setBounds(250, 20, 103, 103);
        nameLabel.setBounds(195, 140, 300, 50);
        nameTextView.setBounds(40, 200, 500, 30);
        includeSubFolders.setBounds(55, 255, 200, 30);
        toggleButton.setLocation(300, 255);
        toggleText.setBounds(370, 255, 200, 30);
        arrangeBtn.setBounds(90, 320, 400, 40);

    }

    private void setStyle() {
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.ITALIC, 34f));
        nameTextView.setFont(nameLabel.getFont().deriveFont(Font.ITALIC, 14f));
        nameTextView.setForeground(Color.lightGray);
    }

    private void addComponentsToContainer() {
        container.add(imageLabel);
        container.add(nameLabel);
        container.add(nameTextView);
        container.add(includeSubFolders);
        container.add(toggleButton);
        container.add(toggleText);
        container.add(arrangeBtn);

    }

    private void addActionEvent() {
        nameTextView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (firstClick) {
                    nameTextView.setText("");
                    nameTextView.setForeground(Color.BLACK);
                    firstClick = false;
                }
            }
        });
        arrangeBtn.addActionListener(e -> {
            String root = nameTextView.getText();
            Arranger arranger = new Arranger(root);
            if (includeSubFolders.isSelected()) {
                arranger.arrangeIncludeSubFolders();
            } else {
                arranger.arrange();
            }
            if (!Arranger.isCorrectInput) {
                JOptionPane.showMessageDialog(this, "Plese, enter correct directory");
                return;
            }
            if (toggleButton.isOn()) {
                arranger.openFolder();
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(this, "Done!");
            }
        });
    }
}
