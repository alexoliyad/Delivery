import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;

public class Main {
    private static boolean isUserLoggedIn = false; // Track login state

    public static void main(String[] args) {
        JComboBox<String> menuDropdown;
        HashMap<String, Integer> menu;
        JButton addButton, calculateButton, payButton, clearCartButton;
        JLabel totalLabel, userInfoLabel = new JLabel("", SwingConstants.CENTER);
        JTextArea orderArea = new JTextArea(10, 30);
        orderArea.setEditable(false);
        AtomicInteger totalPrice = new AtomicInteger();

        JFrame frame = new JFrame("KT CAFE ORDERING SYSTEM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // ----------------- Menu Bar -----------------
        JMenuBar menuBar = new JMenuBar();
        JMenu navigationMenu = new JMenu("Navigation");

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton loginNavButton = new JButton("Login Page");
        JButton homeNavButton = new JButton("Menu Page");
        JButton paymentNavButton = new JButton("Payment Page");

        loginNavButton.addActionListener(e -> cardLayout.show(cardPanel, "LoginPage"));

        homeNavButton.addActionListener(e -> {
            if (!isUserLoggedIn) {
                JOptionPane.showMessageDialog(frame, "Please login first!", "Access Denied", JOptionPane.WARNING_MESSAGE);
            } else {
                cardLayout.show(cardPanel, "HomePage");
            }
        });

        paymentNavButton.addActionListener(e -> {
            if (!isUserLoggedIn) {
                JOptionPane.showMessageDialog(frame, "Please login first!", "Access Denied", JOptionPane.WARNING_MESSAGE);
            } else {
                cardLayout.show(cardPanel, "PaymentPage");
            }
        });

        navPanel.add(loginNavButton);
        navPanel.add(homeNavButton);
        navPanel.add(paymentNavButton);
        menuBar.add(navPanel);
        frame.setJMenuBar(menuBar);

        // ----------------- Login Page -----------------
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(Color.YELLOW);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("KT CAFE ORDERING SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(new Color(227, 39, 95));

        JLabel username = new JLabel("UserName:", SwingConstants.RIGHT);
        JTextField tf = new JTextField(15);
        JLabel phone = new JLabel("Phone No:", SwingConstants.RIGHT);
        JTextField phot = new JTextField(15);
        JLabel password = new JLabel("Password:", SwingConstants.RIGHT);
        JPasswordField passwordField = new JPasswordField(15);
        JLabel addresses = new JLabel("Address:", SwingConstants.RIGHT);
        String[] address = {"WING1A", "WING1B", "BUILDING1", "BUILDING2", "BUILDING3", "SAT1A", "SAT1B", "AMEL1A", "AMELB", "AMEL2A", "AMEL2B", "AMEL3A", "AMEL3B"};
        JComboBox<String> comboBox = new JComboBox<>(address);
        JLabel dorm = new JLabel("Dorm No:");
        String[] dormNumbers = new String[455];
        for (int i = 0; i < 455; i++) dormNumbers[i] = String.valueOf(i + 1);
        JComboBox<String> dormBox = new JComboBox<>(dormNumbers);
        JButton btnLogin = new JButton("Login");

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; loginPanel.add(title, gbc);
        gbc.gridy = 1; gbc.gridwidth = 1; loginPanel.add(username, gbc);
        gbc.gridx = 1; loginPanel.add(tf, gbc);
        gbc.gridx = 0; gbc.gridy = 2; loginPanel.add(phone, gbc);
        gbc.gridx = 1; loginPanel.add(phot, gbc);
        gbc.gridx = 0; gbc.gridy = 3; loginPanel.add(password, gbc);
        gbc.gridx = 1; loginPanel.add(passwordField, gbc);
        gbc.gridx = 0; gbc.gridy = 4; loginPanel.add(addresses, gbc);
        gbc.gridx = 1; loginPanel.add(comboBox, gbc);
        gbc.gridx = 0; gbc.gridy = 5; loginPanel.add(dorm, gbc);
        gbc.gridx = 1; loginPanel.add(dormBox, gbc);
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2; loginPanel.add(btnLogin, gbc);

        // ----------------- Home Page -----------------
        JPanel homePanel = new JPanel(new GridBagLayout());
        homePanel.setBackground(new Color(91, 243, 139));
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(5, 5, 5, 5);
        gbc2.fill = GridBagConstraints.HORIZONTAL;

        JLabel welcomeMessage = new JLabel("Welcome to KT Café!", SwingConstants.CENTER);
        welcomeMessage.setFont(new Font("Arial", Font.BOLD, 18));

        menu = new HashMap<>();
        menu.put("Shiro -70", 70); menu.put("Beyaynet-70", 70);  menu.put("Mahiberawi-80", 80);
        menu.put("Pasta 70", 70); menu.put("Ruz-70", 70); menu.put("Firfir-60", 60);
        menu.put("Mokoroni-70", 70); menu.put("Minchet-80", 80); menu.put("Tuna-100", 100);
        menu.put("Keywot-150", 150); menu.put("Kikil-100", 100);  menu.put("Tibs-150", 150);
        menu.put("Inkulal-90", 90); menu.put("Pepsi-35", 35); menu.put("Mirinda-35", 35);
        menu.put("Fanta-35", 35); menu.put("Cocacola-35", 35);  menu.put("Irgo-50", 50);
        menu.put("Coffee-15", 15); menu.put("Tea-10", 10); menu.put("Spris-15", 15); menu.put("Power-40", 40);

        menuDropdown = new JComboBox<>(menu.keySet().toArray(new String[0]));
        addButton = new JButton("Add to Order");
        clearCartButton = new JButton("Clear Cart");
        JScrollPane scrollPane = new JScrollPane(orderArea);
        totalLabel = new JLabel("Total: 0 ETB", SwingConstants.CENTER);
        calculateButton = new JButton("Calculate Total");
        JLabel deliveryTimeLabel = new JLabel("Enter Delivery Time (HH:mm):");
        JTextField deliveryTimeField = new JTextField(10);
        payButton = new JButton("Proceed to Payment");

        gbc2.gridx = 0; gbc2.gridy = 0; gbc2.gridwidth = 2; homePanel.add(userInfoLabel, gbc2);
        gbc2.gridy = 1; homePanel.add(welcomeMessage, gbc2);
        gbc2.gridy = 2; gbc2.gridwidth = 1; homePanel.add(menuDropdown, gbc2);
        gbc2.gridy = 3; homePanel.add(addButton, gbc2);
        gbc2.gridy = 4; homePanel.add(clearCartButton, gbc2);
        gbc2.gridy = 5; gbc2.gridwidth = 2; homePanel.add(scrollPane, gbc2);
        gbc2.gridy = 6; homePanel.add(totalLabel, gbc2);
        gbc2.gridy = 7; homePanel.add(calculateButton, gbc2);
        gbc2.gridy = 8; gbc2.gridwidth = 1; homePanel.add(deliveryTimeLabel, gbc2);
        gbc2.gridy = 9; homePanel.add(deliveryTimeField, gbc2);
        gbc2.gridy = 10; gbc2.gridwidth = 2; homePanel.add(payButton, gbc2);

        addButton.addActionListener(e -> {
            String selectedItem = (String) menuDropdown.getSelectedItem();
            int price = menu.get(selectedItem);
            orderArea.append(selectedItem + " - " + price + " ETB\n");
            totalPrice.addAndGet(price);
        });

        clearCartButton.addActionListener(e -> {
            orderArea.setText("");
            totalPrice.set(0);
            totalLabel.setText("Total: 0 ETB");
        });

        calculateButton.addActionListener(e -> {
            totalLabel.setText("Total: " + totalPrice.get() + " ETB");
        });

        payButton.addActionListener(e -> {
            if (deliveryTimeField.getText().matches("^(?:[01]\\d|2[0-3]):[0-5]\\d$")) {
                cardLayout.show(cardPanel, "PaymentPage");
            } else {
                JOptionPane.showMessageDialog(frame, "Enter valid delivery time (HH:mm)", "Input Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        // ----------------- Payment Page -----------------
        JPanel paymentPanel = new JPanel(new GridBagLayout());
        paymentPanel.setBackground(new Color(91, 243, 139));
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.insets = new Insets(5, 5, 5, 5);
        gbc3.fill = GridBagConstraints.HORIZONTAL;

        JLabel payforhead = new JLabel("PAYMENT FORM", SwingConstants.CENTER);
        payforhead.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel payLabel = new JLabel("Select Payment Method:");
        JComboBox<String> paymentOptions = new JComboBox<>(new String[]{"CBE", "Telebirr", "Abyssinia"});
        JLabel accno1 = new JLabel("CBE-100025157175", SwingConstants.CENTER);
        JLabel accno2 = new JLabel("TELEBIRR-2662712", SwingConstants.CENTER);
        JLabel accno3 = new JLabel("ABY-12651256", SwingConstants.CENTER);
        JLabel amountLabel = new JLabel("Amount: 0 ETB", SwingConstants.CENTER);
        JButton confirmButton = new JButton("Confirm Payment");
        JButton exitButton = new JButton("EXIT");

        gbc3.gridx = 0; gbc3.gridy = 0; gbc3.gridwidth = 2; paymentPanel.add(payforhead, gbc3);
        gbc3.gridy = 1; gbc3.gridwidth = 1; paymentPanel.add(payLabel, gbc3);
        gbc3.gridx = 1; paymentPanel.add(paymentOptions, gbc3);
        gbc3.gridx = 0; gbc3.gridy = 2; gbc3.gridwidth = 2; paymentPanel.add(accno1, gbc3);
        gbc3.gridy = 3; paymentPanel.add(accno2, gbc3);
        gbc3.gridy = 4; paymentPanel.add(accno3, gbc3);
        gbc3.gridy = 5; paymentPanel.add(amountLabel, gbc3);
        gbc3.gridy = 6; paymentPanel.add(confirmButton, gbc3);
        gbc3.gridy = 7; paymentPanel.add(exitButton, gbc3);

        payButton.addActionListener(e -> {
            amountLabel.setText("Amount: " + totalPrice.get() + " ETB");
            cardLayout.show(cardPanel, "PaymentPage");
        });

        confirmButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Payment Confirmed! Thank you.");
            frame.dispose();
        });

        exitButton.addActionListener(e -> frame.dispose());

        // ----------------- Card Panel -----------------
        cardPanel.add(loginPanel, "LoginPage");
        cardPanel.add(homePanel, "HomePage");
        cardPanel.add(paymentPanel, "PaymentPage");

        cardLayout.show(cardPanel, "LoginPage");

        frame.add(cardPanel);
        frame.setVisible(true);

        // ----------------- Login Action -----------------
        btnLogin.addActionListener(e -> {
            String userName = tf.getText();
            String phoneNumber = phot.getText();
            String addressSelected = (String) comboBox.getSelectedItem();
            String dormNumber = (String) dormBox.getSelectedItem();

            if (userName.isEmpty() || phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your name and phone number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            userInfoLabel.setText("<html>User: <b>" + userName + "</b> | Phone: <b>" + phoneNumber + "</b> | Address: <b>" +
                    addressSelected + "</b> | Dorm: <b>" + dormNumber + "</b></html>");

            isUserLoggedIn = true; // Update login state
            cardLayout.show(cardPanel, "HomePage");
        });
    }
}