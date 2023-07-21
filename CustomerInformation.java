package group_2;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerInformation {
	// Instance variables
	private String customerId;
	private String customerName;
	private String customerContact;
	private String customerArtworkPurchased;
	private String customerArtistPreferences;
	private static ArrayList<CustomerInformation> customerList = new ArrayList<>();
	
	// Accessor methods
	public String getCustomerId() {
		return customerId;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public String getCustomerContact() {
		return customerContact;
	}
	
	public String getCustomerArtworkPurchased() {
		return customerArtworkPurchased;
	}
	
	public String getCustomerArtistPreferences() {
		return customerArtistPreferences;
	}
	
	public ArrayList<CustomerInformation> getList() {
		return customerList;
	}
	
	// Mutator methods
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public void setCustomerName(String name) {
		this.customerName = name;
	}
	
	public void setCustomerContact(String contact) {
		this.customerContact = contact;
	}
	
	public void setCustomerArtworkPurchased(String artworkPurchased) {
		this.customerArtworkPurchased = artworkPurchased;
	}
	
	public void setCustomerArtistPreferences(String artistPreferences) {
		this.customerArtistPreferences = artistPreferences;
	}
	
	// Constructors
	public CustomerInformation(String id, String name, String contact, String artworkPurchased, String artistPreferences) {
		this.customerId = id;
		this.customerName = name;
		this.customerContact = contact;
		this.customerArtworkPurchased = artworkPurchased;
		this.customerArtistPreferences = artistPreferences;	
	}
	public CustomerInformation() {
		
	}
	
	// Read customer data text file method
	public void readCustomerFile() {
		try {
			// Create a new file object
			FileReader fileReader = new FileReader ("customer.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			// Read each line of the file and add it to the ArrayList
			String line;
	        while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split("\\|");
                String customerId = fields[0];
                String customerName = fields[1];
                String customerContact = fields[2];
                String customerArtworkPurchased = fields[3];
                String customerArtistPreferences = fields[4];
                
                CustomerInformation customerInfo = new CustomerInformation(customerId, customerName, customerContact, customerArtworkPurchased, customerArtistPreferences);
                customerList.add(customerInfo);
	        }
	        
	        // Close the BufferedWriter and FileWriter
	        bufferedReader.close();
	        fileReader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// Write customer data text file method
	public void writeFile() {		
		 try {
	        // Create a new FileWriter object with the file path as parameter
	        FileWriter fileWriter = new FileWriter("customer.txt");
	
	        // Create a new BufferedWriter object to write data to the file
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	
	        // Write data to the file
            for (CustomerInformation customerInformation : customerList) {
            	String line = String.format("%s|%s|%s|%s|%s",
            		    customerInformation.getCustomerId(),
            		    customerInformation.getCustomerName(),
            		    customerInformation.getCustomerContact(),
            		    customerInformation.getCustomerArtworkPurchased(),
            		    customerInformation.getCustomerArtistPreferences());
            	bufferedWriter.write(line);
            	bufferedWriter.newLine(); // add new line character
            }
	
	        // Close the BufferedWriter and FileWriter
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
            }
	}

	// Create add customer method
	public static void addCustomer() {
		Scanner input = new Scanner(System.in);
		// Print the user interface
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.printf("%1$50s%n", "ADD CUSTOMER");
		System.out.println("--------------------------------------------------------------------------------------");
		
		// Create array for storing only customer ID
		String[] idList = new String[customerList.size()];
		for (int i = 0; i < customerList.size(); i++) 
		{
		    idList[i] = customerList.get(i).getCustomerId();
		}
		// Prompt user to input customer ID
		System.out.print("Enter customer ID			: ");
		String id = input.next();
		// Check the id is in 4 digit number
		while (true)
		{
			if (id.matches("\\d{4}")) {
	            break;
	        } else {
	            System.out.println("Invalid input. Please enter a 4 digit number.");
	            System.out.print("Enter customer ID			: ");
				id = input.next();
	        }
		}
		
		// Validate the customer ID
		boolean idExists = true;
        while (idExists) {
            for (int j = 0; j < idList.length; j++) {
                if (idList[j].equals(id)) {
                    System.out.println("The ID already exists. Please use another ID.");
                    System.out.print("Enter customer ID			: ");
                    id = input.next();
                    while (true)
        			{
        				if (id.matches("\\d{4}")) {
        		            break;
        		        } else {
        		            System.out.println("Invalid input. Please enter a 4 digit number.");
        		            System.out.print("Enter customer ID			: ");
        					id = input.next();
        		        }
        			}
                    break; // Break out of the for loop so we don't keep checking the same ID
                }
                if (j == idList.length - 1) {
                    idExists = false; // Exit the while loop because the ID doesn't exist in the array
                }
            }
        }

		// Prompt user to input customer name, contact
		System.out.print("Enter customer name			: ");
		input.nextLine();
		String name = input.nextLine();	
		System.out.print("Enter customer contact			: ");
		String contact = input.next();
		
		// Prompt user to input customer artwork purchased ID
		System.out.print("Enter artwork purchased ID		: ");
		String artworkPurchased = input.next();

		// Validate the customer artwork purchased ID
		ArtworkManager artworkManager = new ArtworkManager("inventory.txt");
        ArrayList<Artwork> artworks = artworkManager.listArtwork();
        String[] artworkIdList = new String[artworks.size()];
        String[] artworkStatusList = new String[artworks.size()];
        for (int i = 0; i < artworks.size(); i++) {
        	artworkIdList[i] = artworks.get(i).getId();
        	artworkStatusList[i] = artworks.get(i).getStatus();
        }
        boolean artworkIdExists = true;
		boolean artworkAvailable = true;
        while (artworkIdExists && artworkAvailable) {
        	boolean found = false;
            for (int j = 0; j < artworkIdList.length; j++) {    	
                if (artworkIdList[j].equals(artworkPurchased)) {    	 
                	found = true;
                	if (artworkStatusList[j].equals("Sold")) {
                        System.out.println("The artwork already sold. Please enter another ID.");
                        System.out.print("Enter artwork ID			: ");
                        artworkPurchased = input.next();
                        break;	
                    }
                    else if (artworkStatusList[j].equals("Available")) {
                    	artworkIdExists = false;
                    	artworkAvailable = false;
                    	break;	
	                }
                }   
            } 
            if (!found) {
                System.out.println("Artwork ID not found. Exiting to Main Menu.");
                System.out.println("Press any key to continue...");
        		input.nextLine();
        		input.nextLine();
        		artworkIdExists = false;
            	artworkAvailable = false;
            	
        		Main.main(null);
        		
            }
	    }
       
        // Prompt user to input customer artist preferences ID
		System.out.print("Enter artist preferences ID		: ");
		String artistPreferences = input.next();

		// Validate the customer artist preferences ID
		ArtistInformation artistInformation = new ArtistInformation();
		artistInformation.readArtistFile();
        ArrayList<ArtistInformation> artists = artistInformation.getList();
        String[] artistIdList = new String[artists.size()];
        for (int i = 0; i < artists.size(); i++) {
        	artistIdList[i] = artists.get(i).getArtistID();
        }
		
		boolean artistExists = true;
        while (artistExists) {
        	boolean found = false;
            for (int j = 0; j < artistIdList.length; j++) {    	
            	
            	if (artistIdList[j].equals(artistPreferences)) {
            		found = true;
                    System.out.println("The artist ID is exists.");
                    artistExists = false;
                    break; // Break out of the for loop so we don't keep checking the same ID
                }                   
            } 
            if (!found) {
                System.out.println("Artist ID not found. Exiting method to Main Menu.");
                System.out.println("Press any key to continue...");
        		input.nextLine();
        		input.nextLine();
        		artistExists = false;
        		Main.main(null);
            }
	    }
		
        // Double confirm on user input
		while (true) {
        	System.out.println("\nPlease check your newly added customer data.");
        	System.out.println("The customer ID				: " + id);
        	System.out.println("The customer name			: " + name);
        	System.out.println("The customer contact			: " + contact);
        	System.out.println("The customer artwork purchased ID	: " + artworkPurchased);
        	System.out.println("The customer artist preferences ID	: " + artistPreferences);
        	
            System.out.println("\nAre you sure you want to add?");
	        System.out.print("Yes(Y)     No(N)      : ");
	        char option = input.next().charAt(0);
	        if (option == 'Y' || option == 'y') {
                CustomerInformation customerInformation = new CustomerInformation(id, name, contact, artworkPurchased, artistPreferences);
                customerList.add(customerInformation);
               
                customerInformation.writeFile();
	            System.out.println("\nThe customer " + id + " has been added successfully.");
	            break;
	        } 
	        else if (option == 'N' || option == 'n') {
	            System.out.println("\nThe add customer action is cancelled.");
	            break;
	        } 
	        else {
	            System.out.println("Wrong option. Only Y and N is allowed.");
	            
	        }	        
        }
		System.out.println("Press any key to continue...");
		input.nextLine();      
		input.nextLine();
	}
	
	// Create delete customer method
	public static void deleteCustomer() {
		Scanner input = new Scanner(System.in);
		// Print the user interface
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.printf("%1$50s%n", "DELETE CUSTOMER");
		System.out.println("--------------------------------------------------------------------------------------");
		
		// Create array for storing only customer ID
		String[] idList = new String[customerList.size()];
		for (int i = 0; i < customerList.size(); i++) {
		    idList[i] = customerList.get(i).getCustomerId();
		}
		// Prompt user to input customer ID
		System.out.print("Enter customer ID		: ");
		String id = input.next();
		// Validate the customer ID and double confirm on user input
		boolean found = true;
		boolean idExists = true;
        while (idExists) {
            for (int j = 0; j < idList.length; j++) {
                if (idList[j].equals(id)) {
                	System.out.println("\nThe ID " + id + " is in the customer list.");
                	System.out.println("The customer ID			: " + id );
                	System.out.println("The customer name		: " + customerList.get(j).getCustomerName());
                	System.out.println("The customer contact		: " + customerList.get(j).getCustomerContact());
                	System.out.println("The artwork puchased ID		: " + customerList.get(j).getCustomerArtworkPurchased());
                	System.out.println("The artist preferences ID	: " + customerList.get(j).getCustomerArtistPreferences()); 
            
                	System.out.println("\nAre you sure you want to delete?");
            		System.out.print("Yes(Y)     No(N)      : ");
            		char option = input.next().charAt(0);
            		if(option == 'Y' || option == 'y' || option == 'N' || option == 'n') {
	            		while(found) { 			
	            			if (option == 'Y' || option == 'y') {
	            				customerList.remove(j);	            					            				
	                            CustomerInformation customerInfomation = new CustomerInformation();
	                            customerInfomation.writeFile();
	                            	
	            				System.out.println("\nThe customer information of " + id + " was deleted successfully.");
	            				idExists = false; 
	            				break;
	                		}
	                		else if (option == 'N' || option == 'n') {
	                			System.out.println("\nThe delete customer action is cancelled");
	                			idExists = false; 
	                			break;
	                		}
	            		}
            		}
            		else {
            			System.out.println("Wrong option. Only Y and N is allowed.");
            			System.out.print("Yes(Y)     No(N)      : ");
            			option = input.next().charAt(0);
            		}
                    break; // Break out of the for loop so we don't keep checking the same ID
                }
                if (j == idList.length - 1) {
                    System.out.println("The ID does not exist. Please use another ID.");
                    System.out.print("Enter customer ID			: ");
                    id = input.next();
                }
            }
        }  
        System.out.println("Press any key to continue...");
		input.nextLine();
		input.nextLine();
	}
		
	// Create modify customer method
	public static void modifyCustomer() {
		Scanner input = new Scanner (System.in);
		// Print the user interface
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.printf("%1$50s%n", "MODIFY CUSTOMER");
		System.out.println("--------------------------------------------------------------------------------------");
		
		// Prompt user to input customer ID
		System.out.print("Enter customer ID			: ");
		String id = input.next();
		// Validate the customer ID into 4 digit
		while (true)
		{
			if (id.matches("\\d{4}")) {
	            break;
	        } else {
	            System.out.println("Invalid input. Please enter a 4 digit number.");
	            System.out.print("Enter customer ID			: ");
				id = input.next();
	        }
		}
		
		// Validate the customer ID
		boolean idExists = true;
		while (idExists) {
			for (int i = 0; i < customerList.size(); i++) {
			    if (customerList.get(i).getCustomerId().equals(id)) {
			        idExists = false;
			        System.out.println("\nThe ID " + id + " is in the customer list.");
			        System.out.println("The customer ID			: " + id );
	            	System.out.println("The customer name		: " + customerList.get(i).getCustomerName());
	            	System.out.println("The customer contact		: " + customerList.get(i).getCustomerContact());
	            	System.out.println("The artwork puchased ID		: " + customerList.get(i).getCustomerArtworkPurchased());
	            	System.out.println("The artist preferences ID	: " + customerList.get(i).getCustomerArtistPreferences());
	            	
	            	// Prompt user to input new customer info and validate the new customer info
	            	System.out.println("\nPlease enter new customer info.");
		            System.out.print("Enter new ID			: ");
		            String newID = input.next();
		            // Check the new ID is 4 digit number
		            while (true)
					{
						if (newID.matches("\\d{4}")) {
				            break;
				        } else {
				            System.out.println("Invalid input. Please enter a 4 digit number.");
				            System.out.print("Enter customer ID			: ");
				            newID = input.next();
				        }
					}
		            
		            int index = 0;
		            String[] idList = new String[customerList.size()];
		            String[] artworkPurchasedList = new String[customerList.size()];
		    		for (int j = 0; j < customerList.size(); j++) {
		    		    idList[j] = customerList.get(j).getCustomerId();
		    		    artworkPurchasedList[j] = customerList.get(j).getCustomerArtworkPurchased();
		    		}
		    		
		    		// Validate the customer ID
		    		boolean idNewExists = true;
		            while (idNewExists) {
		            	if(id.equals(newID)) {		 
		            		for (int l = 0; l < idList.length; l++) {
		            		    if (idList[l].equals(newID)) {
		            		        index = l;  
		            		        break;
		            		    }
		            		}
			    			break;
			    		}
		                for (int k = 0; k < idList.length; k++) {
		                    if (idList[k].equals(newID) ) {
		                        System.out.println("The ID already exists. Please use another ID.");
		                        System.out.print("Enter new customer ID			: ");
		                        newID = input.next();
		                        
		                        break; // Break out of the for loop so we don't keep checking the same ID
		                    }
		                    if (k == idList.length - 1) {
		                    	idNewExists = false; // Exit the while loop because the ID doesn't exist in the array
		                    }
		                }
		            }
		            // Prompt use to input the name, contact and artwork purchased ID
		            System.out.print("Enter new name			: ");
		            input.nextLine();
		            String newName = input.nextLine();
		            System.out.print("Enter new contact		: ");
		            String newContact = input.next();
		            
		            System.out.print("Enter new artwork purchased	: ");
		            String newArtworkPurchased = input.next();
		            
		            // Validate the artwork purchased ID 
		            ArtworkManager artworkManager = new ArtworkManager("inventory.txt");
		            ArrayList<Artwork> artworks = artworkManager.listArtwork();
		            String[] artworkIdList = new String[artworks.size()];
		            String[] artworkStatusList = new String[artworks.size()];
		            for (int m = 0; m < artworks.size(); m++) {
		            	artworkIdList[m] = artworks.get(m).getId();
		            	artworkStatusList[m] = artworks.get(m).getStatus();
		            }
		            
		            boolean artworkIdExists = true;
		    		boolean artworkAvailable = true;
		            while (artworkIdExists == true && artworkAvailable == true) {
		            	if (artworkPurchasedList[index].equals(newArtworkPurchased))	{
			    			break;
			    		}
		            	
		            	boolean found = false;
		                for (int j = 0; j < artworkIdList.length; j++) {    	
		                    if (artworkIdList[j].equals(newArtworkPurchased)) {    	 
		                    	found = true;
		                    	if (artworkStatusList[j].equals("Sold")) {
		                            System.out.println("The artwork is already sold. Please enter another ID.");
		                            System.out.print("Enter artwork ID		: ");
		                            newArtworkPurchased = input.next();
		                            break;	
		                        }
		                        else if (artworkStatusList[j].equals("Available")) {
		                        	artworkIdExists = false;
		                        	artworkAvailable = false;
		                        	break;	
		    	                }
		                    }   
		                } 
		                if (!found) {
		                	break;
		                }
		    	    }    
		            
		            // Prompt the user to input the artist preferences ID
		            System.out.print("Enter new artist preferences	: ");
		            String newArtistPreferences = input.next();
		            
		            // Validate the artist preferences
		            ArtistInformation artistInformation = new ArtistInformation();
		    		artistInformation.readArtistFile();
		            ArrayList<ArtistInformation> artists = artistInformation.getList();
		            String[] artistIdList = new String[artists.size()];
		            for (int m = 0; m < artists.size(); m++) {
		            	artistIdList[m] = artists.get(m).getArtistID();
		            }
		    		
		    		boolean artistExists = true;
		            while (artistExists) {
		            	boolean found = false;
		                for (int j = 0; j < artistIdList.length; j++) {    	
		                	if (artistIdList[j].equals(newArtistPreferences)) {
		                		found = true;
		                        System.out.println("The artist ID is exists.");
		                        artistExists = false;
		                        break; // Break out of the for loop so we don't keep checking the same ID
		                    }                   
		                } 
		                if (!found) {
		                    System.out.println("Artist ID not found. Exiting to Main Menu.");
		                    System.out.println("Press any key to continue...");
		            		input.nextLine();
		            		input.nextLine();
		            		artistExists = false;
		            		Main.main(null);
		                }
		    	    }
		            
		            // Double confirm on user input
		            while (true) {
		            	System.out.println("\nPlease check your newly modified customer data.");
		            	System.out.println("The new ID			: " + newID);
		            	System.out.println("The new name			: " + newName);
		            	System.out.println("The new contact			: " + newContact);
		            	System.out.println("The new artwork purchased	: " + newArtworkPurchased);
		            	System.out.println("The new artist preferences	: " + newArtistPreferences);
		            	
			            System.out.println("\nAre you sure you want to modify?");
				        System.out.print("Yes(Y)     No(N)      : ");
				        char option = input.next().charAt(0);
				        if (option == 'Y' || option == 'y') {
				        	customerList.get(i).setCustomerId(newID);
				            customerList.get(i).setCustomerName(newName);
				            customerList.get(i).setCustomerContact(newContact);
				            customerList.get(i).setCustomerArtworkPurchased(newArtworkPurchased);
				            customerList.get(i).setCustomerArtistPreferences(newArtistPreferences);
				            CustomerInformation customerInfomation = new CustomerInformation();
		                    customerInfomation.writeFile();   
				            System.out.println("\nThe customer data has been modified successfully.");
				            break;
				        } 
				        else if (option == 'N' || option == 'n') {
				            System.out.println("\nThe modify customer action is cancelled.");
				            break;
				        } 
				        else {
				            System.out.println("Wrong option. Only Y and N is allowed."); 
				        }
		            }
		            break;
			    }
			}
			if (idExists == true) {
				// Prompt the user for a valid customer id
			    System.out.println("The ID does not exist. Please enter a valid ID.");
			    System.out.print("Enter customer ID			: ");
	            id = input.next();
			}
		}
		System.out.println("Press any key to continue.....");
		input.nextLine();
	}
		
	// Create search customer method
	public static void searchCustomer() {
		Scanner input = new Scanner (System.in);
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.printf("%1$50s%n", "SEARCH CUSTOMER");
		System.out.println("--------------------------------------------------------------------------------------");
		
		// Prompt user to input customer ID
		System.out.print("Enter customer ID			: ");
		String id = input.next();

		// Print the customer info user search
		boolean idExists = true;
		boolean found = false;
		while (idExists) {
			for (int i = 0; i < customerList.size(); i++) {
			    if (customerList.get(i).getCustomerId().equals(id)) { 
			        System.out.println("\nThe ID " + id + " is in the customer list.");
			        System.out.println("The customer ID			: " + id );
	            	System.out.println("The customer name		: " + customerList.get(i).getCustomerName());
	            	System.out.println("The customer contact		: " + customerList.get(i).getCustomerContact());
	            	System.out.println("The artwork puchased ID		: " + customerList.get(i).getCustomerArtworkPurchased());
	            	System.out.println("The artist preferences ID	: " + customerList.get(i).getCustomerArtistPreferences());
	            	
	            	found = true;
	            	idExists = false;
	            	break;			// Exit the loop while customer info is found
			    }
			}
			if (!found) {
				// Prompt the user for a valid customer ID if the ID doesn't exist in the database
			    System.out.println("\nThe ID does not exist. Please enter a valid ID.");
			    System.out.print("Enter customer ID			: ");
	            id = input.next();
			}
		}
		System.out.println("Press any key to continue...");
		input.nextLine();
		input.nextLine();
	}	
}
