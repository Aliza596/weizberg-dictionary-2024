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
    private ArrayList<String> words = new ArrayList<>();
    private ArrayList<String> definitions = new ArrayList<>();

    public EnglishDictionary() throws CsvValidationException, IOException {
        //gets the file from the resources directory
        InputStream in = EnglishDictionary.class.getResourceAsStream(
                "/englishDictionary.csv");

        CSVReader reader = new CSVReader(new InputStreamReader(in));
        String[] record = null;

        while ((record = reader.readNext()) != null) {
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
        List<String> defsOfWord = new ArrayList<>();

        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).equalsIgnoreCase(word)) {
                defsOfWord.add(definitions.get(i));
            }
        }
        return defsOfWord;

    }
}
