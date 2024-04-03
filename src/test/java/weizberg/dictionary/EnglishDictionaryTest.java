package weizberg.dictionary;

import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;
import weizberg.dictionary.EnglishDictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnglishDictionaryTest {

    @Test
    public void getDefinition() throws CsvValidationException, IOException {
        //given
        EnglishDictionary dictionary = new EnglishDictionary();

        //when
        List<String> defs = dictionary.getDefinition("huntsman");

        //then
        List<String> expected = new ArrayList<>();
        expected.add("One who hunts, or who practices hunting.");
        expected.add("The person whose office it is to manage the chase or to look after the hounds.");

        assertEquals(expected, defs);
    }

    @Test
    public void getDefinitionOfFakeWord() throws CsvValidationException, IOException {
        //given
        EnglishDictionary dictionary = new EnglishDictionary();

        //when
        List<String> defs = dictionary.getDefinition("supercal");

        //then
        assertTrue(defs.isEmpty());
    }
}
