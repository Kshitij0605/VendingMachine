import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class VendingMachineGUI extends JFrame {
    private JLabel display;
    private double balance;
    private DecimalFormat df = new DecimalFormat("#.##");
    private JTextArea purchaseCart;

    public VendingMachineGUI() {
        super("Vending Machine");
        balance = 0.0;

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("one006e-88-e01-mainpreview-13298797e24ab5fe7d57ceedba00375da81c706c9220f9f1deed9ccd8db20200.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setBackground(Color.LIGHT_GRAY); // Set background color to light grey

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton cokeButton = new JButton("Coca Cola (Rs. 25)");
        JButton pepsiButton = new JButton("Pepsi (Rs. 30)");
        JButton spriteButton = new JButton("Sprite (Rs. 20)");
        JButton kitkatButton = new JButton("KitKat (Rs. 10)");
        JButton dairyMilkButton = new JButton("Dairy Milk (Rs. 15)");
        JButton laysButton = new JButton("Lays (Rs. 10)");
        JButton kurkureButton = new JButton("Kurkure (Rs. 15)");
        JButton coinButton = new JButton("Enter Coin");
        JButton resetButton = new JButton("Reset");
        JButton changeButton = new JButton("Get Change");

        display = new JLabel("Balance: Rs. " + df.format(balance));
        display.setForeground(Color.WHITE); // Set color to white
        purchaseCart = new JTextArea();
        purchaseCart.setEditable(false);
        purchaseCart.setBounds(250, 50, 200, 150); // Adjusted size and position
        purchaseCart.setBackground(panel.getBackground()); // Set background color

        panel.add(purchaseCart, gbc);
        gbc.gridy++;

        panel.add(cokeButton, gbc);
        gbc.gridy++;
        panel.add(pepsiButton, gbc);
        gbc.gridy++;
        panel.add(spriteButton, gbc);
        gbc.gridy++;
        panel.add(kitkatButton, gbc);
        gbc.gridy++;
        panel.add(dairyMilkButton, gbc);
        gbc.gridy++;
        panel.add(laysButton, gbc);
        gbc.gridy++;
        panel.add(kurkureButton, gbc);
        gbc.gridy++;
        panel.add(coinButton, gbc);
        gbc.gridy++;
        panel.add(resetButton, gbc);
        gbc.gridy++;
        panel.add(changeButton, gbc);
        gbc.gridy++;
        panel.add(display, gbc);

        cokeButton.addActionListener(new ProductListener(25.0, "Coca Cola"));
        pepsiButton.addActionListener(new ProductListener(30.0, "Pepsi"));
        spriteButton.addActionListener(new ProductListener(20.0, "Sprite"));
        kitkatButton.addActionListener(new ProductListener(10.0, "KitKat"));
        dairyMilkButton.addActionListener(new ProductListener(15.0, "Dairy Milk"));
        laysButton.addActionListener(new ProductListener(10.0, "Lays"));
        kurkureButton.addActionListener(new ProductListener(15.0, "Kurkure"));
        coinButton.addActionListener(new CoinListener());
        resetButton.addActionListener(new ResetListener());
        changeButton.addActionListener(new ChangeListener());

        add(panel);
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class ProductListener implements ActionListener {
        private double price;
        private String product;

        public ProductListener(double price, String product) {
            this.price = price;
            this.product = product;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            balance -= price;
            display.setText("Balance: Rs. " + df.format(balance));
            purchaseCart.append(product + " - Rs. " + price + "\n");
        }
    }

    private class CoinListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(null, "Enter coin amount (Rs. 1, Rs. 2, Rs. 5, Rs. 10)");
            if (input != null && !input.isEmpty()) {
                try {
                    double coin = Double.parseDouble(input);
                    balance += coin;
                    display.setText("Balance: Rs. " + df.format(balance));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid coin amount entered!");
                }
            }
        }
    }

    private class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            balance = 0;
            display.setText("Balance: Rs. " + df.format(balance));
            purchaseCart.setText("");
        }
    }

    private class ChangeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (balance > 0) {
                JOptionPane.showMessageDialog(null, "Change: Rs. " + df.format(balance));
                balance = 0;
                display.setText("Balance: Rs. " + df.format(balance));
                purchaseCart.setText("");
                JOptionPane.showMessageDialog(null, "Purchase successful!");
            } else {
                JOptionPane.showMessageDialog(null, "No change available.");
            }
        }
    }

    public static void main(String[] args) {
        new VendingMachineGUI();
    }
}
