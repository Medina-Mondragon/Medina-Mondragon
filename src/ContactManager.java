import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactManager {
    private List<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
        loadContactsFromFile();
    }

    private void loadContactsFromFile() {
        try {
            File file = new File("contacts.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length == 2) {
                    String name = parts[0].trim();
                    String phoneNumber = parts[1].trim();
                    Contact contact = new Contact(name, phoneNumber);
                    contacts.add(contact);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: contacts.txt");
        }
        contacts.add(new Contact("John Doer", "123-453-2345"));
        contacts.add(new Contact("Bob Baste", "132-034-5432"));
        contacts.add(new Contact("Dan Raste", "555-423-2444"));

    }
    public void viewContacts() {
        System.out.println("Name" + "      | " + "Phone number" );
        System.out.println("------------------------");
        for (Contact contact : contacts) {
            System.out.println(contact.getName() + " | " + contact.getPhoneNumber());
        }
    }

    public void addContact(String name, String phoneNumber) {
        Contact contact = new Contact(name, phoneNumber);
        contacts.add(contact);
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    public void saveContactsToFile() {
        try {
            FileWriter writer = new FileWriter("contacts.txt");

            for (Contact contact : contacts) {
                writer.write(contact.getName() + " | " + contact.getPhoneNumber() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: contacts.txt");
        }
    }

    public void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMain Menu");
            System.out.println("1. View contacts");
            System.out.println("2. Add a new contact");
            System.out.println("3. Search a contact by name");
            System.out.println("4. Delete an existing contact");
            System.out.println("5. Exit");
            System.out.print("Enter an option (1, 2, 3, 4, or 5): ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    viewContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("Exiting the contact manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (choice != 5);

        scanner.close();
    }

    private void searchContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
    }

    private void deleteContact() {

    }

    private void addContact() {

    }

}
