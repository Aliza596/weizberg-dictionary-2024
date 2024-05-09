package weizberg.dictionary;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.collections.map.HashedMap;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * reads the english dictionary file once (put it in the constructor)
 */
public class EnglishDictionary {
    private Map<String, List<String>> words = new HashMap();

    public EnglishDictionary() throws CsvValidationException, IOException {
        //gets the file from the resources directory
        InputStream in = EnglishDictionary.class.getResourceAsStream(
                "/englishDictionary.csv");

        CSVReader reader = new CSVReader(new InputStreamReader(in));
        String[] record = null;

        while ((record = reader.readNext()) != null) {
            /*
            this returns a list of the definitions, if there is more than one definition
            if there is only one definition then it just makes a new arrayList
            we are essentially searching for the key which is record[0] and returning an arraylist
            of definitions of that word, if there is only 1 definition, then it creates a new ArrayList
             */
            List<String> list = words.getOrDefault(record[0], new ArrayList<>());
            list.add(record[2]);
            //record is one line of the csv
            words.put(record[0], list);
        }
        reader.close();
    }

    /**
     *
     * @param word to look up
     * @return a list of definitions or an empty list if the word doesn't exist
     */
    public List<String> getDefinition(String word) {
        if (words.get(word) == null)
        {
            return new ArrayList<>();
        }
        return words.get(word);
    }
}

/*
a map is O(1) instead of O(n) which is a HUGE DIFFERENCE!!!
 */
