import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class OnlineExaminationSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static String username;
    private static String password;
    private static int score = 0;
    private static Timer timer;

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Update Profile and Password");
            System.out.println("3. Take Exam");
            System.out.println("4. View Score");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    updateProfileAndPassword();
                    break;
                case 3:
                    takeExam();
                    break;
                case 4:
                    viewScore();
                    break;
                case 5:
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login() {
        System.out.print("Enter your username: ");
        username = scanner.next();
        System.out.print("Enter your password: ");
        password = scanner.next();

        // Perform authentication logic here (not implemented in this example)
        System.out.println("Login successful!");
    }

    private static void updateProfileAndPassword() {
        if (username == null || password == null) {
            System.out.println("Please login first.");
            return;
        }

        System.out.print("Enter your new password: ");
        password = scanner.next();

        // Update profile information in the database (not implemented in this example)
        System.out.println("Profile and password updated successfully!");
    }

    private static void takeExam() {
        if (username == null || password == null) {
            System.out.println("Please login first.");
            return;
        }

        // Array to store questions, options, and correct answers
        String[] questions = {
            "What does JVM stand for?",
            "Which programming language is known as the 'mother of all languages'?",
            "What is the main purpose of the 'finally' block in Java?",
            "What is the output of 'System.out.println(2 + 2 + \" = \" + (2 + 2));'?",
            "In object-oriented programming, what is an 'interface'?",
            "What is the purpose of the 'super' keyword in Java?",
            "Which data structure uses the Last In, First Out (LIFO) order?",
            "What is the default value of a boolean variable in Java?",
            "What is the full form of API?",
            "What is the role of 'break' statement in a switch-case construct?",
            "Which sorting algorithm has the worst-case time complexity of O(n^2)?",
            "What is the purpose of the 'this' keyword in Java?",
            "What is a 'NullPointerException' in Java?",
            "What is the difference between '== and '.equals()' in Java?",
            "What is the purpose of the 'static' keyword in Java?",
            "What is the significance of the 'volatile' keyword in Java?",
            "What is polymorphism in object-oriented programming?",
            "What is the purpose of 'try', 'catch', and 'finally' blocks in exception handling?",
            "What is the purpose of the 'abstract' keyword in Java?",
            "How is memory managed in Java?",
            "What is the difference between 'int' and 'Integer' in Java?",
            "What is the use of 'StringBuilder' in Java?",
            "How does the 'foreach' loop differ from a regular 'for' loop?",
            "What is the difference between 'HashMap' and 'HashTable' in Java?",
            "What is multithreading in Java?",
            "What is the purpose of the 'extends' keyword in Java?",
            "What is recursion in programming?",
            "What is the significance of the 'interface' in Java?",
            "What is the purpose of 'finalize' method in Java?",
            "What is the difference between 'Stack' and 'Queue' data structures?",
            "What is the role of 'new' keyword in Java?"
        };
        String[][] options = {
            {"Java Virtual Machine", "Java Visual Machine", "Java Virtual Memory", "Java Visual Memory"},
            {"C", "Fortran", "Assembly", "COBOL"},
            {"To handle exceptions", "To execute code irrespective of exceptions", "To handle errors", "To indicate the end of a program"},
            {"4 = 4", "4 = 5", "2 + 2 = 4", "2 + 2 = 22"},
            {"A class that can be instantiated", "A way to achieve multiple inheritance in Java", "A template for a class", "A keyword to create objects"},
            {"To refer to the immediate parent class object", "To invoke a static method", "To access instance variables", "To break out of a loop"},
            {"Queue", "Stack", "List", "Tree"},
            {"true", "false", "0", "1"},
            {"Application Programming Interface", "Advanced Programming Interface", "Application Program Interface", "Advanced Program Interface"},
            {"To terminate the loop", "To skip the current iteration", "To exit the program", "To transfer control to the next case"},
            {"Bubble Sort", "Quick Sort", "Merge Sort", "Insertion Sort"},
            {"To refer to the current instance of the class", "To invoke a static method", "To access instance variables", "To refer to the immediate parent class object"},
            {"A runtime exception", "A checked exception", "A type of error", "An unchecked exception"},
            {"'==' compares object references, '.equals()' compares content", "'==' is used for primitive types, '.equals()' for objects", "'==' compares content, '.equals()' compares object references", "They are entirely interchangeable"},
            {"To create objects of a class", "To access non-static members of a class", "To declare a class constant", "To denote a class as final"},
            {"It ensures visibility of changes across threads", "It prevents the object from being garbage collected", "It allows multiple threads to access the variable concurrently", "It improves the performance of the program"},
            {"It allows a class to have multiple methods with the same name", "It allows a class to have multiple methods with different names", "It allows a class to have multiple methods with the same name but different parameters", "It allows a class to have multiple constructors"},
            {"To handle different types of exceptions", "To indicate the end of a program", "To execute code irrespective of whether an exception occurs or not", "To handle only checked exceptions"},
            {"To create an instance of a class", "To provide an implementation for an interface", "To denote a class as abstract", "To create an object of a class"},
            {"Java uses automatic garbage collection", "Java uses manual memory allocation and deallocation", "Java uses reference counting for memory management", "Java uses malloc() and free() functions for memory management"},
            {"'int' is a primitive data type, 'Integer' is a wrapper class", "'int' is a wrapper class, 'Integer' is a primitive data type", "'int' is used for integers, 'Integer' is used for decimals", "'int' is a class, 'Integer' is an interface"},
            {"To concatenate strings efficiently", "To create a mutable sequence of characters", "To format strings", "To parse strings"},
            {"It can only iterate over arrays", "It is more efficient than a regular 'for' loop", "It can only iterate backward", "It automatically handles the loop variable"},
            {"'HashMap' is synchronized, 'HashTable' is not", "'HashTable' allows null keys, 'HashMap' does not", "'HashMap' is part of the Java Collections Framework, 'HashTable' is not", "They are functionally equivalent"},
            {"A process of executing multiple threads simultaneously", "A process of executing a single thread", "A process of executing a thread multiple times", "A process of executing a thread sequentially"},
            {"To create a subclass", "To implement an interface", "To denote a class as final", "To access non-static members of a class"},
            {"A programming technique where a function calls itself", "A process of dividing a problem into smaller sub-problems", "A programming technique where functions are arranged in a stack", "A process of converting high-level code into machine code"},
            {"It allows multiple inheritance in Java", "It defines a contract for classes that implement it", "It is used for achieving polymorphism", "It is used to create an instance of a class"},
            {"It is called when the object is about to be garbage collected", "It is used to explicitly deallocate memory", "It is used to finalize the implementation of a class", "It is used to handle exceptions"},
            {"Stack uses Last In, First Out (LIFO) order", "Queue uses Last In, First Out (LIFO) order", "Stack uses First In, First Out (FIFO) order", "Queue uses First In, First Out (FIFO) order"},
            {"To create an instance of a class", "To denote a class as abstract", "To refer to the current instance of the class", "To access static members of a class"}

        };
        char[] correctAnswers = {'A', 'B', 'C', 'A', 'B', 'D', 'C', 'A', 'D', 'B', 'A', 'B', 'C', 'D', 'A', 'C', 'B', 'D', 'C', 'A', 'A', 'B', 'C', 'D', 'A', 'B', 'C', 'D', 'A', 'B'};

        // Display and process each question
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Q" + (i + 1) + ". " + questions[i]);
            for (int j = 0; j < options[i].length; j++) {
                System.out.println((char) ('A' + j) + ") " + options[i][j]);
            }

            // Set up timer for 30 seconds
            setTimer(30);

            // Prompt user for the answer
            System.out.print("Your answer (A, B, C, or D): ");
            char selectedAnswer = scanner.next().toUpperCase().charAt(0);

            // Cancel the timer after the user provides an answer
            timer.cancel();

            // Check the answer
            if (selectedAnswer == correctAnswers[i]) {
                System.out.println("Correct! You earned 1 point.");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer is " + correctAnswers[i] + ".");
            }
        }

        System.out.println("Exam completed! You scored " + score + " out of " + questions.length + " points.");
        score = 0;  // Reset the score for the next exam
    }

    private static void viewScore() {
        System.out.println("Your current score is: " + score);
    }

    private static void logout() {
        System.out.println("Logging out. Goodbye!");
        System.exit(0);
    }

    private static void setTimer(int seconds) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Auto-submitting your answer.");
                scanner.nextLine();  // Consume the newline character
                timer.cancel();
            }
        }, seconds * 1000);
    }
}
