import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CandyShop extends JFrame {
    private JTextField[] quantityFields;
    private JButton orderButton;
    private JLabel[] priceLabels;
    private JLabel totalLabel;

    public CandyShop() {
        setTitle("Candy Shop");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 3));

        String[] candies = {"cookie", "arrozdoce", "chocolate"};
        double[] prices = {1.50, 2.00, 1.75};

        quantityFields = new JTextField[3];
        priceLabels = new JLabel[3];

        for (int i = 0; i < candies.length; i++) {
      
            ImageIcon candyImage = new ImageIcon("arrozdoce.jpg"); 
            JLabel imageLabel = new JLabel(candyImage);
            panel.add(imageLabel);

     
            priceLabels[i] = new JLabel("Preço: $" + String.format("%.2f", prices[i]));
            panel.add(priceLabels[i]);

            JLabel quantityLabel = new JLabel("Quantidade:");
            panel.add(quantityLabel);

            quantityFields[i] = new JTextField();
            panel.add(quantityFields[i]);
        }

        orderButton = new JButton("Pedir");
        orderButton.addActionListener(new OrderButtonHandler());
        panel.add(orderButton);

        totalLabel = new JLabel("Total: $0.00");
        panel.add(totalLabel);

        add(panel);
    }

    private class OrderButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double total = 0.0;

            try {
                for (int i = 0; i < quantityFields.length; i++) {
                    int quantity = Integer.parseInt(quantityFields[i].getText());
                    double price = Double.parseDouble(priceLabels[i].getText().substring(8)); // Obtém o preço a partir do texto do JLabel
                    total += quantity * price;
                }

                totalLabel.setText("Total: $" + String.format("%.2f", total));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(CandyShop.this, "Por favor, insira uma quantidade válida para cada doce.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CandyShop().setVisible(true);
            }
        });
    }
}
