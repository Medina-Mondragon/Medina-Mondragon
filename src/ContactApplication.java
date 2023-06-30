import java.util.Scanner;

public class ContactApplication {
    private static ContactManager contactManager;

    public static void main(String[] args) {
        contactManager = new ContactManager();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            printMainMenu();
            int choice = getInput(scanner);

            switch (choice) {
                case 1:
                    contactManager.viewContacts();
                    break;
                case 2:
                    addContact(scanner);
                    break;
                case 3:
                    searchContact(scanner);
                    break;
                case 4:
                    deleteContact(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        contactManager.saveContactsToFile();
        scanner.close();
        System.out.println("Application exited successfully.");
    }

    private static void printMainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. View contacts");
        System.out.println("2. Add a new contact");
        System.out.println("3. Search a contact by name");
        System.out.println("4. Delete an existing contact");
        System.out.println("5. Exit");
    }

    private static int getInput(Scanner scanner) {
        System.out.print("Enter an option (1, 2, 3, 4, or 5): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    private static void addContact(Scanner scanner) {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter the contact's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the contact's phone number: ");
        String phoneNumber = scanner.nextLine();
        contactManager.addContact(name, phoneNumber);
        System.out.println("Contact added successfully.");
    }

    private static void searchContact(Scanner scanner) {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter the name of the contact to search: ");
        String name = scanner.nextLine();
        Contact contact = contactManager.searchContact(name);
        if (contact != null) {
            System.out.println("Contact found:");
            System.out.println(contact.getName() + " | " + contact.getPhoneNumber());
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void deleteContact(Scanner scanner) {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter the name of the contact to delete: ");
        String name = scanner.nextLine();
        Contact contact = contactManager.searchContact(name);
        if (contact != null) {
            contactManager.deleteContact(contact);
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }
}