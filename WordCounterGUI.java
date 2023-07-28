import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordCounterGUI extends JFrame {

    private JTextArea textArea;
    private JTextArea resultArea;

    public WordCounterGUI() {
        setTitle("Word Counter");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea(10, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane textScrollPane = new JScrollPane(textArea);

        JButton countButton = new JButton("Count Words");
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countWords();
            }
        });

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(textScrollPane, BorderLayout.CENTER);
        inputPanel.add(countButton, BorderLayout.SOUTH);

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.add(resultScrollPane, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputPanel, outputPanel);
        splitPane.setDividerLocation(0.5);

        add(splitPane);

        setVisible(true);
    }

    private void countWords() {
        String inputText = textArea.getText();
        if (inputText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter text or provide a file to count words.");
            return;
        }

        String[] words = inputText.split("\\s+|\\p{Punct}");

       
        Set<String> commonWords = new HashSet<>(Arrays.asList("a", "an", "the", "in", "on", "and", "is", "are", "it", "to"));

        Map<String, Integer> wordFrequencies = new HashMap<>();

       
        int totalWordCount = 0;

        for (String word : words) {
            word = word.toLowerCase(); 
            if (!word.isEmpty() && !commonWords.contains(word)) {
               
                wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
                totalWordCount++;
            }
        }

        resultArea.setText("Total word count: " + totalWordCount + "\n\n");

       
        resultArea.append("Unique Words and Frequencies:\n");
        for (Map.Entry<String, Integer> entry : wordFrequencies.entrySet()) {
            resultArea.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WordCounterGUI());
    }
}
