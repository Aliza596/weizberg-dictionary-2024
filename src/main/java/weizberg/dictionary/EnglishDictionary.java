package weizberg.dictionary;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * reads the english dictionary file once (put it in the constructor)
 */
public class EnglishDictionary {
    ArrayList<String> words = new ArrayList<>();
    ArrayList<String> definitions = new ArrayList<>();

    public EnglishDictionary() throws CsvValidationException, IOException {
        //gets the file from the resources directory
        InputStream in = EnglishDictionary.class.getResourceAsStream(
                "/englishDictionary.csv");

        CSVReader reader = new CSVReader(new InputStreamReader(in));
        String[] record = null;

        while((record = reader.readNext()) != null) {
            //record is one line of the csv
            words.add(record[0]);
            definitions.add(record[2]);
        }
        reader.close();
    }

    /**
     *
     * @param word to look up
     * @return a list of definitions or an empty list if the word doesn't exist
     */
    public List<String> getDefinition(String word) {
        ArrayList<Integer> indexes = new ArrayList<>();
        List<String> definitionsOfSearchedWords = new ArrayList<>();

        //check where the word is in the dictionary
        for (int i = 0; i < words.size(); i++) {
            if(words.get(i).equalsIgnoreCase(word)) {
                indexes.add(i);
            }
        }

        //retrieve the definition form those indexes
        int index;
        for (int i = 0; i < indexes.size(); i++) {
            index = indexes.get(i);
            definitionsOfSearchedWords.add(definitions.get(index));
        }

        return definitionsOfSearchedWords;

    }


    public static void main(String[] args) throws CsvValidationException, IOException {
        EnglishDictionary dictionary = new EnglishDictionary();

        List<String> defs = dictionary.getDefinition("supercal");

        System.out.println(defs);
    }


}
