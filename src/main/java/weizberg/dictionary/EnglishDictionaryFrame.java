package weizberg.dictionary;

import com.opencsv.exceptions.CsvValidationException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class EnglishDictionaryFrame extends JFrame {
    private JTextField wordField = new JTextField();
    private JTextArea definitionArea = new JTextArea();
    private EnglishDictionary dictionary = new EnglishDictionary();

    public EnglishDictionaryFrame() throws CsvValidationException, IOException {
        setSize(400, 400);
        setTitle("English Dictionary Lookup");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        definitionArea.setEditable(false);
        definitionArea.setLineWrap(true);
        definitionArea.setWrapStyleWord(true);
        Font font = new Font("Calibri", Font.PLAIN, 16);
        definitionArea.setFont(font);

        JPanel north = new JPanel();
        north.setLayout(new BorderLayout());
        north.add(wordField, BorderLayout.CENTER);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        main.add(north, BorderLayout.NORTH);
        main.add(new JScrollPane(definitionArea), BorderLayout.CENTER);

        add(main);


        wordField.getDocument().addDocumentListener(new SimpleDocumentListener() {
            @Override
            public void update(DocumentEvent e) {
                updateTextArea();
            }
        });
    }

    private void updateTextArea() {
        definitionArea.setText("");
        List<String> definitionForWord = dictionary.getDefinition(wordField.getText());

        for (String s : definitionForWord) {
            definitionArea.append(s);
            definitionArea.append("\n");
        }
    }

    public static void main(String[] args) throws CsvValidationException, IOException {
        EnglishDictionaryFrame frame = new EnglishDictionaryFrame();
        frame.setVisible(true);
    }
}
