import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class Solver {
    public static void main(String[] args) {
        System.out.println("This program will help you navigate through the game Wordle");
        System.out.println("For each task (CL)for Chosen Letter,(S)for Specific Letter,(V)for Vowel or (C) for Consonant?");
        List<String> word_List = readList(checkFile());
        List<String> out_List = menu(word_List);
        outpuToFile(out_List);
    }

    public static String checkFile() {
        System.out.println("WHat is the name of the file?");
        Scanner valid = new Scanner(System.in);
        String fileName = valid.nextLine();

        while (!new File(fileName).exists()) {
            System.out.println("Please input a valid file name:");
            fileName = valid.nextLine();
        }
        return fileName;
    }

    public static List<String> readList(String n_File) {
        List<String> o_List = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(n_File))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                o_List.add(line);
            }
        } catch (IOException e) {
            System.out.println("There was an error while this file was read. " + e.getMessage());
        }

        System.out.println("This is your starter code for o_List!" + o_List);
        return o_List;
    }

    public static List<String> menu(List<String> w_List) {
        label:
        while (true) {
            Scanner choice = new Scanner(System.in);
            System.out.println("(CL)for Chosen Letter,(S)for Specific Letter,(V)for Vowel or (C) for Consonant(N) For Quit?");
            String ask_Task = choice.nextLine();

            switch (ask_Task) {
                case "CL":
                    w_List = chosenLetter(w_List);
                    break;
                case "S":
                    w_List = specificLetter(w_List);
                    break;
                case "V":
                    w_List = vowelCheck(w_List);
                    break;
                case "C":
                    w_List = consonantCheck(w_List);
                    break;
                case "N":
                    break label;
                default:
                    System.out.println("Invalid input, please try again!");
                    break;
            }
        }
        return w_List;
    }

    public static List<String> chosenLetter(List<String> w_List){
        Scanner chosen = new Scanner(System.in);
        System.out.println("What letter would you like to search for in the word list?");
        String c_Letter = chosen.nextLine();
        c_Letter.toLowerCase();
        String c_looker = c_Letter;
        List<String> c_List = w_List.stream().filter(i -> i.contains(c_looker)).collect(Collectors.toList());
        System.out.println("Here is the list of words that include your chosen letter: " +
                c_List);
        return c_List;
    }

    public static List<String>  specificLetter(List<String> w_List) {
        Scanner specific = new Scanner(System.in);
        System.out.println("What letter would you like to search for in the word list?");
        String s_Letter = specific.nextLine();
        System.out.println("For beginning(0,1), for middle(2,3), at the end (4)");

        System.out.println("Where in the word would you desire the letter to be at?");
        int s_Location = specific.nextInt();
        char s_Looker = s_Letter.charAt(0);
        List<String> s_List = new ArrayList<>();


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
        System.out.println("Here is the list of words that include your specific letter: " + s_List);
        return s_List;
    }

    public static List<String> vowelCheck(List<String> w_List){
        Scanner vowel = new Scanner(System.in);
        System.out.println("How many vowels would you like in your word?");
        int v = vowel.nextInt();
        List<Character> vowels = List.of('a','e','i','o','u');
        List<String> v_List = new ArrayList<>();

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
        System.out.println("Vowel List: " + v_List);
        return v_List;
    }

    public static List<String> consonantCheck(List<String> w_List){
        Scanner consonant = new Scanner(System.in);
        System.out.println("How many consonants would you like in your word?");
        int c = consonant.nextInt();
        List<Character> consonants = List.of('b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t'
                ,'v','w','x','y','z');
        List<String> c_List = new ArrayList<>();

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
        System.out.println("Consonant List: " + c_List);
        return c_List;
    }

    public static void outpuToFile(List<String> og_List) {
        Scanner w = new Scanner(System.in);
        System.out.println("What is the name of the file?");
        String fileName = w.nextLine();

        try (FileWriter writer = new FileWriter(fileName)) {
            for (String line : og_List) {
                writer.write(line);
            }
        } catch (IOException e) {
            System.out.println("There was an error with this file.");
        }
    }

    public static List<String> charPosition(List<String> w_List,char looker, int location) {
        List<String> char_List = new ArrayList<>();

        for (String word : w_List) {
            if (word.length() > location && word.charAt(location) == looker) {
                char_List.add(word);
            }
        }
        return char_List;
    }
}
