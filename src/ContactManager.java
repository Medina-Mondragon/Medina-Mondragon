import java.io.*;
import java.util.*;

public class ContactManager {
	private List<Contact> contacts;
	private Scanner scanner;

	public ContactManager() {
		contacts = new ArrayList<>();
		scanner = new Scanner(System.in);
		loadContactsFromFile();
	}

	private void loadContactsFromFile(){
		File file = new File("contacts.txt");

		if (!file.exists()){
			try{file.createNewFile();
				PrintWriter writer = new PrintWriter(file);
				writer.println("John Doer|123-453-2345");
				writer.println("Bob Baste|132-034-5432");
				writer.println("Dan Raste|555-423-2444");
				writer.close();
			}
			catch (IOException e){
				System.out.println("Error creating contacts.txt file.");
			}
		}
		try {Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()){
				String line = scanner.nextLine();
				String[] parts = line.split("\\|");

				if (parts.length == 2){
					String name = parts[0].trim();
					String phoneNumber = parts[1].trim();
					Contact contact = new Contact(name, phoneNumber);
					contacts.add(contact);
				}
			}
			scanner.close();
		}
		catch (FileNotFoundException exception){
			System.out.println("File not found: contacts.txt");
		}
	}
	public void viewContacts() {
		System.out.println("Name" + "      | " + "Phone number" );
		System.out.println("------------------------");
		for (Contact contact : contacts) {
			System.out.println(contact.getName() + " | " + contact.getPhoneNumber());
		}
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
		int choice;

		do {
			System.out.println("\n+-----------------------------------------------+\n" +
					"|                   Main Menu                   |\n" +
					"+-----------------------------------------------+\n" +
					"|                                               |\n" +
					"| 1. View contacts                              |\n" +
					"| 2. Add a new contact                          |\n" +
					"| 3. Search a contact by name                   |\n" +
					"| 4. Delete an existing contact                 |\n" +
					"| 5. Exit                                       |\n" +
					"|                                               |\n" +
					"+-----------------------------------------------+");
			System.out.print("Enter an option (1, 2, 3, 4, or 5): \n");


			choice = scanner.nextInt();
			scanner.nextLine();

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
					System.out.println("\n+-----------------------------------------------+\n" +
							"|  oooo  oooo  oooo  ooo   oooo  oo  oo  oooo   |\n" +
							"|  o  o  o  o  o  o  o  o  o  o   o  o   o      |\n" +
							"|  oooo  o  o  o  o  o  o  oo      o     ooo    |\n" +
							"|     o  o  o  o  o  o  o  o  o    o     o      |\n" +
							"|  oooo  oooo  oooo  ooo   oooo    o     oooo   |\n" +
							"+-----------------------------------------------+\n" );
					break;
				default:
					System.out.println("Invalid choice. Please enter a valid option.");
					break;
			}
		} while (choice != 5);
	}
	public void searchContact() {
		System.out.print("Enter the name of the contact to search: ");
		String name = scanner.nextLine();

		boolean found = false;
		for (Contact contact : contacts) {
			if (contact.getName().equalsIgnoreCase(name)) {
				System.out.println("Contact found: " + contact.getName() + " | " + contact.getPhoneNumber());

				int maxLength = Math.max(contact.getName().length(), contact.getPhoneNumber().length());

				String line = "+----------------------------+\n" +
						String.format("| Name: %-" + (maxLength + 10) + "s |\n", contact.getName()) +
						String.format("| Number: %-" + (maxLength + 8) + "s |\n", contact.getPhoneNumber()) +
						"+----------------------------+";

				System.out.println(line);

				found = true;
				break;
			}
		}

		if (found = false) {
			System.out.println("Sorry, the name you entered was not found.");
		}
	}

	public void addContact(){

		System.out.print("Enter the name of the contact: ");
		String name = scanner.nextLine();

		System.out.print("Enter the phone number of the contact: ");
		String phoneNumber = scanner.nextLine();

		Contact contact = new Contact(name, phoneNumber);
		contacts.add(contact);

		saveContactsToFile();

		System.out.println("Contact added successfully.");
	}

	public void deleteContact(){

		System.out.print("Enter the name of the contact to delete: ");
		String name = scanner.nextLine();

		contacts.removeIf(contact -> contact.getName().equalsIgnoreCase(name));

		saveContactsToFile();

		System.out.println("Contact deleted successfully.");
	}
}