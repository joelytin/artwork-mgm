package group_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;

//This class manages a collection of artworks
public class ArtworkManager {
	private ArrayList<Artwork> artworks; // the collection of artworks
    private String filename; // the name of the file to read/write artworks from/to

     // Constructor for ArtworkManager
    public ArtworkManager(String filename) {
        artworks = new ArrayList<>(); // Initialize the artworks array list
        this.filename = filename;
        readFromFile();// Read artworks from file
    }

     // Adds an artwork to the collection
    public void addArtwork(Artwork artwork) {
        artworks.add(artwork);
        writeToFile();
    }

    // Deletes an artwork from the collection by ID
    public boolean deleteArtwork(String id) {
        for (Artwork artwork : artworks) {
            if (artwork.getId().equals(id)) {
                artworks.remove(artwork);
                writeToFile();
                return true;
            }
        }
        return false;
    }

    // Modifies an artwork in the collection
    public void modifyArtwork(Artwork artwork) {
        for (int i = 0; i < artworks.size(); i++) {
            if (artworks.get(i).getId() == artwork.getId()) {
                artworks.set(i, artwork);
                writeToFile();
                return;
            }
        }
    }

    public Artwork findArtwork(String id) {
        for (Artwork artwork : artworks) {
            if (artwork.getId().equals(id)) {
                return artwork;
            }
        }
        return null;
    }

    // Finds an artwork in the collection by ID
    public Artwork findArtworkById(String id) {
        for (Artwork artwork : listArtwork()) {
            if (artwork.getId().equalsIgnoreCase(id)) {
                return artwork;
            }
        }
        return null; // Return null if the artwork is not found
    }

    // Returns the entire collection of artworks
    public ArrayList<Artwork> listArtwork() {
        return artworks;
    }

    // Reads the artworks from the file
    private void readFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                if (!line.isEmpty()) {
                    Artwork artwork = Artwork.fromText(line);
                    artworks.add(artwork);
                } else {
                    System.out.println("Skipping empty line in input file");
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Error parsing input data in file: " + filename);
            e.printStackTrace();
        }
    }


    private void writeToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Artwork artwork : artworks) {
                writer.write(artwork.toText() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Artwork {
    private String id;
    private String title;
    private String artist;
    private String datePurchased;
    private String dateSold;
    private double purchasePrice;
    private double sellingPrice;
    private String status;

    public Artwork(String id, String title, String artist, String datePurchased, String dateSold, double purchasePrice,
    		double sellingPrice, String status) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.datePurchased = datePurchased;
        this.dateSold = dateSold;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.status = status;
    }

    //mutator & getter
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getDatePurchased() {
        return datePurchased;
    }

    public String getDateSold() {
        return dateSold;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setID(String id) {
    	this.id = id;
    }
    
    public void setStatus(String status) {
    	this.status = status;
    }
    
    public void setTitle(String title) {
    	this.title = title;
    }
    
    public void setArtist(String artist) {
    	this.artist = artist;
    }
    
    public void setDatePurchased(String datePurchased) {
    	this.datePurchased = datePurchased;
    }
    
    public void setDateSold(String dateSold) {
    	this.dateSold = dateSold;
    }
    
    public void setPurchasePrice(double purchasePrice) {
    	this.purchasePrice = purchasePrice;
    }
    
    public void setSellingPrice(double sellingPrice) {
    	this.sellingPrice = sellingPrice;
    }

    public static Artwork fromText(String line) {
        if (line.isEmpty()) {
            throw new IllegalArgumentException("Input string is empty");
        }
        String[] tokens = line.split("\\|");
        if (tokens.length < 8) {
            throw new IllegalArgumentException("Input string does not contain the expected number of tokens: " + line);
        }
        String id = tokens[0];
        String title = tokens[1];
        String artist = tokens[2];
        String datePurchased = tokens[3];
        String dateSold = tokens[4];
        double purchasePrice = 0.0;
        double sellingPrice = 0.0;
        if (!tokens[5].isEmpty()) {
            try {
                purchasePrice = Double.parseDouble(tokens[5]);
            } catch (NumberFormatException e) {
            }
        }
        if (!tokens[6].isEmpty()) {
            try {
                sellingPrice = Double.parseDouble(tokens[6]);
            } catch (NumberFormatException e) {
            }
        }
        String status = tokens[7];
        return new Artwork(id, title, artist, datePurchased, dateSold, purchasePrice, sellingPrice, status);
    }



    public String toText() {
        return String.format("%s|%s|%s|%s|%s|%.2f|%.2f|%s", id, title, artist, datePurchased, dateSold, purchasePrice,
                sellingPrice, status);
}
}
