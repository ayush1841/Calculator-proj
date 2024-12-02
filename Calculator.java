import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {
    private JFrame frame;
    private JTextField display;
    private StringBuilder currentInput;
    private String operator;
    private double firstNumber;

    public Calculator() {
        frame = new JFrame("Calculator");
        display = new JTextField();
        currentInput = new StringBuilder();
        operator = "";

        // Set up the display
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        
        // Create buttons
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(this);
            panel.add(button);
        }

        // Set up the frame
        frame.setLayout(new BorderLayout(10, 10));
        frame.add(display, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "C":
                currentInput.setLength(0);
                display.setText("");
                break;
            case "=":
                if (!operator.isEmpty()) {
                    double secondNumber = Double.parseDouble(currentInput.toString());
                    double result = calculate(firstNumber, secondNumber, operator);
                    display.setText(String.valueOf(result));
                    currentInput.setLength(0);
                    operator = "";
                }
                break;
            case "/":
            case "*":
            case "-":
            case "+":
                if (currentInput.length() > 0) {
                    firstNumber = Double.parseDouble(currentInput.toString());
                    operator = command;
                    currentInput.setLength(0);
                }
                break;
            default:
                currentInput.append(command);
                display.setText(currentInput.toString());
                break;
        }
    }

    private double calculate(double first, double second, String operator) {
        switch (operator) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            case "/":
                return second != 0 ? first / second : 0; // Prevent division by zero
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
