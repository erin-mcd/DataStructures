import javax.xml.bind.SchemaOutputResolver;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Hangman {

    public static List<String> formFamilies(List<String> words, Set<Character> guesses) {

        HashMap<String, List<String>> families = new HashMap<>();
        //take the hashmap
        //create keys based off of the possible word families
        //possible word families are every possible way the guessed letter could be put into the answer

        //for every word in original
        for (String word : words) {
            String family =  "";
            for(char letter : word.toCharArray()) {
                if(guesses.contains(letter)){
                    family+= letter;
                } else {
                    family += "_";
                }
            }

                //if a letter in the word equals the guess
                    if (!families.containsKey(family)) { //if the key doesn't already exist
                       List<String> listWords = new ArrayList(); //make a new Arraylist to be the new value
                        listWords.add(word); // add the word to the arraylist
                        families.put(family, listWords); //put the new arraylist in the key
                }
                    else{
                      families.get(family).add(word);
                    }
        }

        String biggestFamily = "";
        int biggestFamilySize = 0;
        for (String family: families.keySet()) {
            List<String> list = families.get(family);//for every family in families, get the list attached to the family
            if(list.size() > biggestFamilySize){
                biggestFamily = family;
                biggestFamilySize = list.size();
            }
        }

        System.out.println(biggestFamily);

        List<String> finalFamily = families.get(biggestFamily);


        if(finished(biggestFamily)){
          System.out.println("You win the word was: " + biggestFamily);
          System.exit(0);
        }
    return finalFamily;
    }



    public static boolean finished(String biggestFamily){
        boolean finished = false;
        Set<Character> blanks = new HashSet<>();

        for (int i = 0; i < biggestFamily.length(); i++) {
            if (biggestFamily.charAt(i) == '_') {
                blanks.add('_');
            }
        }
        if(blanks.size() == 0){
            finished = true;
        }
        return finished;
    }


    public static void main(String[] args) {
        String fileName = "words.txt";
        HashMap<Integer, ArrayList<String>> lengthMap = new HashMap();
        Set<Character> guesses = new HashSet<>();
        List<String>  hiddenWords = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String firstPrint = "";

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = word.toLowerCase();
                    for (int i = 1; i <= 30; i++) {
                        if(word.length() == i) {
                            if (!lengthMap.containsKey(i)) {
                                ArrayList<String> listWords = new ArrayList();
                                listWords.add(word);
                                lengthMap.put(i, listWords);
                            }
                            else{
                                lengthMap.get(i).add(word);
                            }
                        }
                    }
                    }
                }
            scanner.close();
            }

         catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        System.out.println("How many letters should the hidden word have?");
        int letters = Integer.parseInt(sc.nextLine());
        hiddenWords  = lengthMap.get(letters);

        System.out.println("How many guesses would you like to have?");
        int guessesLeft = Integer.parseInt(sc.nextLine());
        int first = 1;

        while( guessesLeft > 0 ) {
            for (int i = 0; i < letters; i++) {
                firstPrint += '_';
            }

            // print out the revealed words
            // print out guessed letters
            if(first == 1){
                System.out.println(firstPrint);
            }
            System.out.println("Your guesses:" + guesses);
            System.out.println("You have " + guessesLeft + " guesses left");
            System.out.println("Input your guess:");


            String guessLet = sc.nextLine();
            char guessedLetter = guessLet.charAt(0);
            guesses.add(guessLet.charAt(0));


            guessesLeft --;
            first --;


            if(first == 1) {
                hiddenWords = formFamilies(lengthMap.get(letters), guesses);
            }
            else{
                hiddenWords = formFamilies(hiddenWords, guesses);
            }

            for (int i = 0; i < letters; i++) {
                if(hiddenWords.get(0).charAt(i) == guessedLetter && guesses.contains(guessedLetter)){
                    char x = hiddenWords.get(0).charAt(i);
                    guessesLeft++;
                    break;
                }
            }



        }

    System.out.println("You are out of guesses. The word was " + hiddenWords.get(0));

    }

}