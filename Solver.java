import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class Solver {
    public static void main(String[] args) {

        // Outputing the name of the program
        System.out.println("~~~~~~~~~~~~~~~~~~~WORDLE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~SOLVER~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // Outputing to the user the instructions on how to use the program
        System.out.println("This program will help you navigate through the game Wordle");
        System.out.println("For each task (CL)for Chosen Letter,(S)for Specific Letter,(V)for Vowel or (C) for Consonant?");
        // Calling the readList method
        List<String> word_List = readList(checkFile());
        // Calling the menu method
        List<String> out_List = menu(word_List);
        // Calling the out_List method
        outpuToFile(out_List);
        // Output thanks to the user
        System.out.println("~~~~~~~~~~~~~~~~~~THANKS FOR USING MY PROGRAM~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    // This is the checkFile method
    public static String checkFile() {
        // Ask the user what is the name of the file
        System.out.println("What is the name of the file?");
        // Gathers the object based on the input of the user
        Scanner valid = new Scanner(System.in);
        String fileName = valid.nextLine();

        // Use a while loop to iterate over and over again until I get a valid file name
        while (!new File(fileName).exists()) {
            // Ask the user for the file name
            System.out.println("Please input a valid file name:");
            fileName = valid.nextLine();
        }
        // Returns the file name after it is proven to be valid.
        return fileName;
    }


    // This is the readList method
    public static List<String> readList(String n_File) {
        // Establish the list of strings called o_List
        List<String> o_List = new ArrayList<>();
        // This try and catch exception is here to make sure that the data that is in the file
        // Is applicable to be used in the list that I have initiated.
        // If the file is not applicable then the catch will let user know that there was an
        // Error when reading the file.
        try (BufferedReader reader = new BufferedReader(new FileReader(n_File))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                o_List.add(line);
            }
        } catch (IOException e) {
            System.out.println("There was an error while this file was read. " + e.getMessage());
        }
        // Output the that this is the starter cod efor the o_List and return it.
        System.out.println("This is your starter code for o_List!");
        System.out.println(o_List);
        return o_List;
    }

    // This is my menu method
    public static List<String> menu(List<String> w_List) {
        label:

        // This is a forever while loop to keep asking which task the user would like to use.
        while (true) {

            // Output to the user which task they would like to do.
            Scanner choice = new Scanner(System.in);
            System.out.println("(CL)for Chosen Letter,(S)for Specific Letter,(V)for Vowel or (C) for Consonant(N) For Quit?");
            String ask_Task = choice.nextLine();

            // I have switch cases instead of like 50 if statements based on the input of the user.
            // The switch cases are more organized and more effecient for my code!
            switch (ask_Task) {
                case "CL":
                    // This is the chosenLetter case and it calls the chosenLetter method
                    w_List = chosenLetter(w_List);
                    break;
                case "S":
                    // This is the specifiedLetter case and it calls the specificLetter method
                    w_List = specificLetter(w_List);
                    break;
                case "V":
                    // This is the vowelCheck case and it called the vowelCheck method
                    w_List = vowelCheck(w_List);
                    break;
                case "C":
                    // This is the consonantCheck case and it called the consonantCheck method
                    w_List = consonantCheck(w_List);
                    break;
                case "N":
                    break label;
                default:
                    System.out.println("Invalid input, please try again!");
                    break;
            }
        }
        // Based on the input of the user and the task they will perform after the task is performed
        // I will return the list known as w_List
        return w_List;
    }


    // This is the chosenLetter method called from the menu.
    public static List<String> chosenLetter(List<String> w_List){
        // Ask the user which letter they would like to search for in the word list
        Scanner chosen = new Scanner(System.in);
        System.out.println("What letter would you like to search for in the word list?");
        String c_Letter = chosen.nextLine();
        c_Letter.toLowerCase();
        String c_looker = c_Letter;
        // Based on the input letter from the user cut the list down to only words that contain the inputed character
        List<String> c_List = w_List.stream().filter(i -> i.contains(c_looker)).collect(Collectors.toList());
        // Outpit the list of the these words that contain the inputed character
        System.out.println("Here is the list of words that include your chosen letter: " +
                c_List);
        return c_List;
    }

    // This is the specificLetter method
    public static List<String>  specificLetter(List<String> w_List) {
        // Ask the user which letter they would like to search for in the word list
        Scanner specific = new Scanner(System.in);
        System.out.println("What letter would you like to search for in the word list?");
        String s_Letter = specific.nextLine();
        // Ask the specific position of where they would lke there letter to be at
        System.out.println("For beginning(0,1), for middle(2,3), at the end (4)");
        System.out.println("Where in the word would you desire the letter to be at?");
        int s_Location = specific.nextInt();
        char s_Looker = s_Letter.charAt(0);
        List<String> s_List = new ArrayList<>();

        // These are the if and elif statements based on the user's input above
        if (s_Location == 0) {
            s_List = charPosition(w_List, s_Looker, 0);
        }
        else if (s_Location == 1) {
            s_List = charPosition(w_List,s_Looker,1);
        }
        else if (s_Location == 2){
            s_List = charPosition(w_List,s_Looker,2);
        }
        else if (s_Location == 3){
            s_List = charPosition(w_List,s_Looker,3);
        }
        else if (s_Location == 4){
            s_List = charPosition(w_List,s_Looker,4);
        }
        // Output the list of words that include the specific letter
        System.out.println("Here is the list of words that include your specific letter: " + s_List);
        return s_List;
    }

    // This is my vowelCheck method
    public static List<String> vowelCheck(List<String> w_List){
        // Ask the user how many vowels they would like in their word
        Scanner vowel = new Scanner(System.in);
        System.out.println("How many vowels would you like in your word?");
        int v = vowel.nextInt();
        List<Character> vowels = List.of('a','e','i','o','u');
        List<String> v_List = new ArrayList<>();


        // This is nested for loops to find all of the needed words with the user inputed
        // amount of vowels.
        for (String word : w_List){
            int v_Count = 0;
            for (Character letter : word.toCharArray()){
                if (vowels.contains(letter)){
                    v_Count++;

                    if (v_Count == v){
                        v_List.add(word);
                    }
                }
            }
        }
        // Output the vowel list and return it
        System.out.println("Vowel List: " + v_List);
        return v_List;
    }

    // This is the consonant method
    public static List<String> consonantCheck(List<String> w_List){
        // Ask the user how many consonants they would like in their word
        Scanner consonant = new Scanner(System.in);
        System.out.println("How many consonants would you like in your word?");
        int c = consonant.nextInt();
        List<Character> consonants = List.of('b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t'
                ,'v','w','x','y','z');
        List<String> c_List = new ArrayList<>();
        // Nested for loops to find the needed amount of consonants based on the input from the user.
        for (String word: w_List){
            int c_Count = 0;

            for (Character letter : word.toCharArray()){
                if (consonants.contains(letter)){
                    c_Count++;
                }
            }
            if (c_Count == c){
                c_List.add(word);
            }
        }
        // Output the Consonant List
        System.out.println("Consonant List: " + c_List);
        return c_List;
    }

    // This is the outputToFile method
    public static void outpuToFile(List<String> og_List) {
        // Ask the user what the name of the file they would like to write to is
        Scanner w = new Scanner(System.in);
        System.out.println("What is the name of the file?");
        String fileName = w.nextLine();
        // Try and catch exceptions to make sure that the file is valid and can be used
        // If the file is not applicable it will output an error message
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String line : og_List) {
                writer.write(line);
            }
        } catch (IOException e) {
            System.out.println("There was an error with this file.");
        }
    }
    // This is the charPosition method
    public static List<String> charPosition(List<String> w_List,char looker, int location) {
        // Initialize the char_List
        List<String> char_List = new ArrayList<>();
        // For loop with nested if statement that gathers all of the words with the needed position to get all of the
        // found words and add them to the list
        for (String word : w_List) {
            if (word.length() > location && word.charAt(location) == looker) {
                char_List.add(word);
            }
        }
        return char_List;
    }
}
