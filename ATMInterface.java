import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;
    private String date;

    public Transaction(String type, double amount, String date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public String toString() {
        return type + ": $" + amount + " on " + date;
    }
}

class BankAccount {
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public BankAccount() {
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount, "today"));
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount, "today"));
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void transfer(double amount, BankAccount recipient) {
        if (amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add(new Transaction("Transfer to " + recipient, amount, "today"));
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}

class User {
    private String userId;
    private String userPin;
    private BankAccount bankAccount;

    public User(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.bankAccount = new BankAccount();
    }

    public boolean authenticate(String enteredPin) {
        return userPin.equals(enteredPin);
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    // Getter method for userId
    public String getUserId() {
        return userId;
    }
}

class ATMSystem {
    private ArrayList<User> users;

    public ATMSystem() {
        this.users = new ArrayList<>();
        // Initialize users with their IDs and PINs
        users.add(new User("user1", "1234"));
        users.add(new User("user2", "5678"));
    }

    public User authenticateUser(String userId, String userPin) {
        for (User user : users) {
            if (user.authenticate(userPin) && user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ATMSystem atmSystem = new ATMSystem();

            System.out.print("Enter User ID: ");
            String userId = scanner.next();
            System.out.print("Enter PIN: ");
            String userPin = scanner.next();

            User currentUser = atmSystem.authenticateUser(userId, userPin);

            if (currentUser != null) {
                System.out.println("Authentication successful!");

                int choice;
                do {
                    System.out.println("\nChoose an option:");
                    System.out.println("1. Transactions History");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Deposit");
                    System.out.println("4. Transfer");
                    System.out.println("5. Quit");

                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            displayTransactionHistory(currentUser.getBankAccount().getTransactionHistory());
                            break;
                        case 2:
                            System.out.print("Enter withdrawal amount: $");
                            double withdrawalAmount = scanner.nextDouble();
                            currentUser.getBankAccount().withdraw(withdrawalAmount);
                            break;
                        case 3:
                            System.out.print("Enter deposit amount: $");
                            double depositAmount = scanner.nextDouble();
                            currentUser.getBankAccount().deposit(depositAmount);
                            break;
                        case 4:
                            System.out.print("Enter recipient's User ID: ");
                            String recipientId = scanner.next();
                            User recipient = atmSystem.authenticateUser(recipientId, "");
                            if (recipient != null) {
                                System.out.print("Enter transfer amount: $");
                                double transferAmount = scanner.nextDouble();
                                currentUser.getBankAccount().transfer(transferAmount, recipient.getBankAccount());
                            } else {
                                System.out.println("Recipient not found.");
                            }
                            break;
                        case 5:
                            System.out.println("Thank you for using the ATM. Goodbye!");
                            break;
                        default:
                            System.out.println("Invalid choice. Please choose again.");
                    }

                } while (choice != 5);
            } else {
                System.out.println("Authentication failed. Please try again.");
            }
        }
    }

    private static void displayTransactionHistory(ArrayList<Transaction> transactions) {
        System.out.println("\nTransaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}
