package group_2;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		System.out.println("--------------------------------------------------------------");
		System.out.println("                WELCOME TO UX GALLERY PROGRAM                 ");
		System.out.println("--------------------------------------------------------------");
		System.out.println("1. CUSTOMER");
		System.out.println("2. ARTIST");
		System.out.println("3. ARTWORK");
		System.out.println("4. EXIT");
		System.out.print("\nPlease enter your choice : ");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		
		switch(choice) {
			case 1:
				customerInformationMenu();
				break;
			case 2:
				artistInformationTest();
				break;
			case 3:
				artworkInformationMenu();
				break;
			case 4:
				System.out.println("Thank you for using our application!!!");
				return;
		}	
	}
	
	public static void customerInformationMenu()
	{
		CustomerInformation customerInfomation = new CustomerInformation();	
		ArrayList <CustomerInformation> customerList = customerInfomation.getList();
		customerList.clear();
		customerInfomation.readCustomerFile(); 
		
		try {
			Scanner input = new Scanner(System.in);
			int choice;
			boolean loop = true;
	
			while(loop)
			{
				
				System.out.println("------------------------------------------------------------------------------------------");
				System.out.printf("%1$55s%n", "CUSTOMER INFORMATION");
				System.out.println("------------------------------------------------------------------------------------------");
	
				System.out.printf("%1$60s%n%n","List of the customer information");
				
				System.out.printf("%1$-5s %2$-20s %3$-15s %4$-20s %5$-20s%n", "ID", "NAME", "CONTACT", "ARTWORK PURCHASED", "ARTIST PREFERENCES");
				System.out.printf("%1$-5s %2$-20s %3$-15s %4$-20s %5$-20s%n", "----", "-----------------", "-----------", "-----------------", "------------------");
				
				for (CustomerInformation customerInformation : customerList)
				{
		            System.out.printf("%1$-5s %2$-20s %3$-15s %4$-20s %5$-20s%n",customerInformation.getCustomerId(), customerInformation.getCustomerName(),  
		            		customerInformation.getCustomerContact(), customerInformation.getCustomerArtworkPurchased(), customerInformation.getCustomerArtistPreferences());            
				}
				
				System.out.println("\n------------------------------------------------------------------------------------------");
				
				System.out.println("Do you want to : ");
				System.out.println("1. Add\n2. Delete\n3. Modify\n4. Search\n5. Exit\n");
				System.out.print("Enter option : ");
				
				choice = input.nextInt();
				input.nextLine();
				
				boolean loop2 = true;
				while(loop2)
				{
					if (choice == 1)
					{
						CustomerInformation.addCustomer();
						loop2 = false;
					}
					else if (choice == 2)
					{
						CustomerInformation.deleteCustomer(); 
						loop2 = false;
					}
					else if (choice == 3)
					{
						CustomerInformation.modifyCustomer();
						loop2 = false;
					}
					else if (choice == 4)
					{
						CustomerInformation.searchCustomer();
						loop2 = false;
					}
					else if (choice == 5)
					{
						loop2 = false;
						loop = false;
						Main.main(null);
					}
					else
					{
						System.out.print("Please enter your choice : ");
						choice = input.nextInt();
	                    input.nextLine(); 
					}
				}				
			}
			input.close();
		}catch (Exception ex){
			ex.getMessage();
		}
	}
	
	public static void artistInformationTest()
	{
		// Create an object of the ArtistInformation class and initialize an ArrayList of ArtistInformation objects
		ArtistInformation artistInformation = new ArtistInformation();
		ArrayList<ArtistInformation> artistList = artistInformation.getList();
		// Read artist information from a file
		artistInformation.readArtistFile();
		
		// Create a Scanner object to read user input
		Scanner input = new Scanner(System.in);
		
		try {
			int opt;
			boolean loop = true;
			
			// Enter a loop that displays the menu until the user chooses to exit
			while (loop) {
				// Display the header and artist information table
				System.out.println("------------------------------------------------------------------------------------------");
				System.out.printf("%1$49s%n", "ARTIST INFORMATION");
				System.out.println("------------------------------------------------------------------------------------------");
				System.out.printf("%1$-5s %2$-20s %3$-15s %4$-20s %5$-20s%n", "ID", "NAME", "SPECIALTY", "STATUS", "PRICE RANGE");
				System.out.printf("%1$-5s %2$-20s %3$-15s %4$-20s %5$-20s%n", "----", "-----------------", "-----------", "-----------------", "------------------");

				for (ArtistInformation artistInfo : artistList) {
		            System.out.printf("%1$-5s %2$-20s %3$-15s %4$-20s %5$-20s%n", artistInfo.getArtistID(), artistInfo.getArtistName(),  
		            		artistInfo.getSpecialty(), artistInfo.getStatus(), artistInfo.getPriceRange());
		        }
				System.out.println("\n------------------------------------------------------------------------------------------");
				
				// Display the menu and prompt the user for input
				System.out.println("Do you want to: ");
				System.out.print("\n1. Add\n2. Delete\n3. Modify\n4. Search\n5. Exit\nEnter option: ");
				
				opt = input.nextInt();
				
				// Enter a loop that executes the chosen operation and repeats until a valid option is chosen
				boolean loop2 = true;
				while(loop2) {
					if(opt == 1) {
						ArtistInformation.addArtist(); // Call the addArtist() method of the ArtistInformation class
						loop2 = false;
					}
					else if(opt == 2) {
						ArtistInformation.deleteArtist(); // Call the deleteArtist() method of the ArtistInformation class
						loop2 = false;
					}
					else if(opt == 3) {
						ArtistInformation.modifyArtist(); // Call the modifyArtist() method of the ArtistInformation class
						loop2 = false;
					}
					else if(opt == 4) {
						ArtistInformation.searchArtist(); // Call the searchArtist() method of the ArtistInformation class
						loop2 = false;
					}
					else if(opt == 5) {
						System.out.println("Exiting to the Main Menu...");
						loop2 = false; 
						loop = false; // Exit the loop that displays the menu
						Main.main(null); // Call the main() method of the Main class to return to the main menu
					}
					else {
						// Display an error message and prompt the user for a valid option
						System.out.println("Invalid option. Only 1 - 5 is allowed.");
						System.out.println("Please enter an option: ");
						opt = input.nextInt();
						input.nextLine(); 
					}
				}
			}
			input.close(); // close the Scanner object
		} catch (Exception ex) {
			ex.getMessage();
		}
	}
	
	public static void artworkInformationMenu()
	{
		// Creating scanner object to get user input
        Scanner scanner = new Scanner(System.in);
        // Creating artwork manager object to access the inventory.txt file
        ArtworkManager artworkManager = new ArtworkManager("inventory.txt");
        
        // Display the header information for the artwork
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%80s\n", "ARTWORK INFORMATION");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%-10s%-30s%-30s%-20s%-20s%-20s%-20s%s", "ID", "Name", "Artist", "DatePurchase", "DateSold", "PurchasePrice", "SellingPrice", "Status"));
        
        // Display the artwork information in a tabular format
        for (Artwork artwork : artworkManager.listArtwork()) {
            System.out.println(String.format("%-10s%-30s%-30s%-20s%-20s%-20s%-20s%s",
                    artwork.getId(),
                    artwork.getTitle(),
                    artwork.getArtist(),
                    artwork.getDatePurchased(),
                    artwork.getDateSold(),
                    artwork.getPurchasePrice(),
                    artwork.getSellingPrice(),
                    artwork.getStatus()));
        }
        // Loop until the user chooses to exit the program
        boolean exit = false;
        while (!exit) {
            // Display the options menu to the user
            System.out.println("\nDo you want to:");
            System.out.println("1. Find");
            System.out.println("2. Add");
            System.out.println("3. Modify");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("\nPlease enter your choice : ");
            // Read the user's choice from the console input
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the new line character left over from scanner.nextInt() call

             // Switch case for menu options
            switch (choice) {
                // Finding an artwork
                case 1:
                    System.out.print("\nEnter the ID of the artwork:");
                    String id = scanner.next();
                    scanner.nextLine();
                    Artwork artwork = artworkManager.findArtwork(id);
                    if (artwork != null) {
                        System.out.println(artwork.toText());
                    } else {
                        System.out.println("Artwork not found.");
                    }
                    break;
                // Adding an artwork
                case 2:
                // Prompt the user to enter a new artwork ID and validate it
                String newId;
                while (true) {
                    System.out.print("\nEnter the ID of the artwork (4 digits):");
                    newId = scanner.next();
                    scanner.nextLine();
                    if (newId.length() != 4 || !newId.matches("\\d+")) {
                        System.out.println("Please enter a valid 4-digit number.");
                    } else if (artworkManager.findArtworkById(newId) != null) {
                        System.out.println("An artwork with that ID already exists.");
                    } else {
                        break;
                    }
                }
            
                // Prompt the user to enter the title of the artwork
                System.out.print("Enter the title of the artwork:");
                String title = scanner.nextLine();
            
                // Prompt the user to enter the artist of the artwork and validate it
                System.out.print("Enter the artist of the artwork:");
                String artist = scanner.nextLine();
                boolean artistFound = false;
                try (Scanner artistFileScanner = new Scanner(new File("artist.txt"))) {
                    while (artistFileScanner.hasNextLine()) {
                        String line = artistFileScanner.nextLine();
                        String[] parts = line.split("\\|");
                        if (parts.length >= 2 && parts[1].equalsIgnoreCase(artist)) {
                            artistFound = true;
                            break;
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Error: artist.txt file not found");
                }
            
                // If the artist is not found in the artist.txt file, return to the main menu
                if (!artistFound) {
                    System.out.println("The artist is not found in the artist.txt file. Please add the artist first.");
                    Main.main(null);
                    return;
                }
            
                // Prompt the user to enter the date purchased of the artwork and validate it
                System.out.print("Enter the date purchased (yyyy-mm-dd) of the artwork:");
                String datePurchased = scanner.nextLine();
                if (!datePurchased.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    System.out.println("Invalid date format. Please enter the date in the format yyyy-mm-dd.");
                    scanner.nextLine(); 
                }
            
                // Prompt the user to enter the date sold of the artwork and validate it
                System.out.print("Enter the date sold (yyyy-mm-dd) of the artwork (press enter if not sold yet):");
                String dateSold = scanner.nextLine();
                if (!dateSold.isEmpty() && !dateSold.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    System.out.println("Invalid date format. Please enter the date in the format yyyy-mm-dd or press enter if not sold yet.");
                    scanner.nextLine(); // clear the buffer
                }
            
                // Prompt the user to enter the purchase price of the artwork and validate it
                System.out.print("Enter the purchase price of the artwork:");
                double purchasePrice;
                while (true) {
                    String purchasePriceStr = scanner.nextLine();
                    if (purchasePriceStr.matches("\\d+")) {
                        purchasePrice = Double.parseDouble(purchasePriceStr);
                        break;
                    }
                    System.out.println("Invalid input. Please enter only digits.");
                }
            
                // Prompt the user to enter the selling price of the artwork and validate it
                System.out.print("Enter the selling price of the artwork (press enter if not sold yet):");
                double sellingPrice = 0.0;
                while (true) {
                    String sellingPriceStr = scanner.nextLine();
                    if (sellingPriceStr.isEmpty()) {
                        break;
                    }
                    if (sellingPriceStr.matches("\\d+")) {
                            sellingPrice = Double.parseDouble(sellingPriceStr);
                            break;
                        }
                        System.out.println("Invalid input. Please enter only digits or press enter if not sold yet.");
                    }


                    System.out.print("Enter the status of the artwork (sold/available):");
                    String status;
                    while (true) {
                        status = scanner.nextLine();
                        if (status.equals("Sold") || status.equals("Available")) {
                            break;
                        }
                        System.out.println("Invalid input. Please enter either 'sold' or 'available'.");
                    }
                    Artwork newArtwork = new Artwork(newId, title, artist, datePurchased, dateSold, purchasePrice, sellingPrice, status);
                    artworkManager.addArtwork(newArtwork);
                    System.out.println("Artwork added successfully.");
                    break;
                // Modifying an artwork
                case 3:
                // Find the artwork to be modified by its ID
                    System.out.print("\nEnter the ID of the artwork:");
                    String modifyId = scanner.next();
                    scanner.nextLine();
                    Artwork artwork1 = artworkManager.findArtwork(modifyId);
                    if (artwork1 != null) {
                            // Print the current information of the artwork
                        System.out.println("\nArtwork information:");
                        System.out.println("ID: " + artwork1.getId());
                        System.out.println("Name: " + artwork1.getTitle());
                        System.out.println("Artist: " + artwork1.getArtist());
                        System.out.println("Purchased Date: " + artwork1.getDatePurchased());
                        System.out.println("Sold Date: " + artwork1.getDateSold());
                        System.out.println("Purchased Price: " + artwork1.getPurchasePrice());
                        System.out.println("Sold Price: " + artwork1.getSellingPrice());
                        System.out.println("Status: " + artwork1.getStatus());

                        String newTitle = "";
                        while (newTitle.equals("")) {
                                    // Prompt the user to enter the new title of the artwork
                            System.out.print("\nEnter the new title of the artwork:");
                            newTitle = scanner.nextLine();
                        }
                        artwork1.setTitle(newTitle);
                        // Prompt the user to enter the new artist of the artwork
                        System.out.print("\nEnter the new artist of the artwork:");
                        String newArtist = scanner.nextLine();

                        boolean artistFound1 = false;
                        try (Scanner artistFileScanner = new Scanner(new File("artist.txt"))) {
                                // Check if the new artist is in the artist.txt file
                            while (artistFileScanner.hasNextLine()) {
                                String line = artistFileScanner.nextLine();
                                String[] parts = line.split("\\|");
                                if (parts.length >= 2 && parts[1].equalsIgnoreCase(newArtist)) {
                                    artistFound1 = true;
                                    break;
                                }
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("Error: artist.txt file not found");
                        }

                        //If the new artist is not in the artist.txt file, prompt the user to add the artist and return to the main menu
                        if (!artistFound1) {
                            System.out.println("The artist is not found in the artist.txt file. Please add the artist first.");
                            // Return to the main menu
                            Main.main(null);
                            return;
                        }

                        artwork1.setArtist(newArtist);


                        String newPurchasedDate = "";
                        while (true) {
                            // Prompt the user to enter the new purchased date of the artwork
                            System.out.print("\nEnter the new purchased date of the artwork (yyyy-MM-dd): ");
                            newPurchasedDate = scanner.nextLine();
                                // Check if the input date is valid
                            if (newPurchasedDate.equals("N/A") || newPurchasedDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                break;
                            } else {
                                System.out.println("Error: Invalid input format. Please enter a valid date in the format yyyy-MM-dd or N/A.");
                            }
                        }
                        artwork1.setDatePurchased(newPurchasedDate);

                        String newSoldDate = "";
                        while (true) {
                            // Prompt the user to enter the new sold date of the artwork
                            System.out.print("\nEnter the new sold date of the artwork (yyyy-MM-dd or N/A, press Enter to skip): ");
                            newSoldDate = scanner.nextLine();
                            if (newSoldDate.equals("") || newSoldDate.equals("N/A") || newSoldDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                break;
                            } else {
                                System.out.println("Error: Invalid input format. Please enter a valid date in the format yyyy-MM-dd or N/A, or press Enter to skip.");
                            }
                        }
                        artwork1.setDateSold(newSoldDate);


                        double newPurchasePrice = 0.0;
                        while (newPurchasePrice == 0.0) {
                            System.out.print("\nEnter the new purchase price of the artwork:");
                            try {
                                newPurchasePrice = Double.parseDouble(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid number.");
                            }
                        }
                        artwork1.setPurchasePrice(newPurchasePrice);

                        double newSellingPrice = -1.0;
                        while (newSellingPrice < 0) {
                            System.out.print("\nEnter the new selling price of the artwork (Press enter to skip):");
                            String input = scanner.nextLine();
                            if (input.equals("")) {
                                newSellingPrice = 0.0;
                            } else {
                                try {
                                    newSellingPrice = Double.parseDouble(input);
                                    if (newSellingPrice < 0) {
                                        System.out.println("Error: Selling price cannot be negative.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                }
                            }
                        }
                        artwork1.setSellingPrice(newSellingPrice);


                        String newStatus = "";
                        while (!newStatus.equalsIgnoreCase("Sold") && !newStatus.equalsIgnoreCase("Available")) {
                            System.out.print("\nEnter the new status of the artwork (Sold or Available):");
                            newStatus = scanner.nextLine();
                            if (!newStatus.equalsIgnoreCase("Sold") && !newStatus.equalsIgnoreCase("Available")) {
                                System.out.println("Error: Invalid input. Please enter either Sold or Available.");
                            }
                        }
                        artwork1.setStatus(newStatus);

                        artworkManager.modifyArtwork(artwork1);
                        System.out.println("Artwork modified successfully.");
                    } else {
                        System.out.println("Artwork not found.");
                    }
                    break;

                // Deleting an artwork
                case 4:
                    System.out.print("\nEnter the ID of the artwork to delete:");
                    String deleteId = scanner.next();
                    scanner.nextLine();
                    if (artworkManager.deleteArtwork(deleteId)) {
                        System.out.println("Artwork deleted successfully.");
                    } else {
                        System.out.println("Artwork not found.");
                    }
                    break;
                // Exiting the program
                case 5:
                    System.out.println("\nExiting the artwork program...");
                    Main.main(null);
                    break;
                default:
                    System.out.println("wrong option. Only 1-6 is allowed");
                    }
        }     
	}
}

