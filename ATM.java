import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class ATM {
    private static Scanner input = new Scanner(System.in);
    private static double balance = 1000.0;
    private static ArrayList<String> history = new ArrayList<>();
    private static String pin;
    private static String accNo;
    private static String name;

    public static void main(String[] args) {
        createAccount();

        while (true) {
            System.out.print("\nEnter your PIN: ");
            String enteredPin = input.nextLine();

            if (enteredPin.equals(pin)) {
                System.out.println("\nWelcome to SBI, " + name);
                System.out.println("Your Account Number: " + accNo);

                int option;
                do {
                    showMenu();
                    System.out.print("Choose an option: ");
                    while (!input.hasNextInt()) {
                        System.out.print("Invalid input. Enter a number: ");
                        input.next(); // discard invalid input
                    }
                    option = input.nextInt();
                    input.nextLine(); // clear buffer

                    switch (option) {
                        case 1 -> showHistory();
                        case 2 -> showBalance();
                        case 3 -> withdraw();
                        case 4 -> deposit();
                        case 5 -> transfer();
                        case 6 -> System.out.println("Thank you for banking with SBI. Visit again!");
                        default -> System.out.println("Invalid option! Try again...");
                    }
                } while (option != 6);

                break;
            } else {
                System.out.println("Incorrect PIN! Please try again...");
            }
        }
    }

    private static void createAccount() {
        System.out.println("SBI Welcomes You!");
        System.out.print("Enter your name: ");
        name = input.nextLine();

        accNo = generateAccountNumber();
        System.out.println("Your Account Number: " + accNo);

        System.out.print("Set a 4-digit PIN: ");
        pin = input.nextLine();
        while (pin.length() != 4 || !pin.matches("\\d{4}")) {
            System.out.print("Invalid PIN. Enter a 4-digit number: ");
            pin = input.nextLine();
        }

        System.out.println("Account created successfully!");
    }

    private static String generateAccountNumber() {
        return String.valueOf(100000 + new Random().nextInt(900000)); // 6-digit account number
    }

    private static void showMenu() {
        System.out.println("\n*** Menu ***");
        System.out.println("1. Transaction History");
        System.out.println("2. Check Balance");
        System.out.println("3. Withdraw");
        System.out.println("4. Deposit");
        System.out.println("5. Transfer");
        System.out.println("6. Quit");
    }

    private static void showHistory() {
        System.out.println("\nTransaction History:");
        if (history.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            history.forEach(System.out::println);
        }
    }

    private static void showBalance() {
        System.out.println("\nCurrent Balance: $" + balance);
    }

    private static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        while (!input.hasNextDouble()) {
            System.out.print("Invalid input. Enter a valid amount: ");
            input.next();
        }
        double amount = input.nextDouble();
        input.nextLine(); // clear buffer

        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            history.add("Withdrawn: $" + amount);
            System.out.println("Withdrawal successful. New Balance: $" + balance);
        }
    }

    private static void deposit() {
        System.out.print("Enter amount to deposit: ");
        while (!input.hasNextDouble()) {
            System.out.print("Invalid input. Enter a valid amount: ");
            input.next();
        }
        double amount = input.nextDouble();
        input.nextLine(); // clear buffer

        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else {
            balance += amount;
            history.add("Deposited: $" + amount);
            System.out.println("Deposit successful. New Balance: $" + balance);
        }
    }

    private static void transfer() {
        System.out.print("Enter recipient account number: ");
        String recipientAcc = input.nextLine();

        System.out.print("Enter amount to transfer: ");
        while (!input.hasNextDouble()) {
            System.out.print("Invalid input. Enter a valid amount: ");
            input.next();
        }
        double amount = input.nextDouble();
        input.nextLine(); // clear buffer

        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            history.add("Transferred: $" + amount + " to Account: " + recipientAcc);
            System.out.println("Transfer successful. New Balance: $" + balance);
        }
    }
}
