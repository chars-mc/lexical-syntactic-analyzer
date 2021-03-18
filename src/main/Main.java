/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controllers.LexicalController;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import javax.swing.JTextArea;
import views.AnalyzerView;

/**
 *
 * @author chars_mc
 */
public class Main {

  // path of the .flex file
  public static void main(String[] args) {
    // creates the class from the .flex file
    LexicalController.generateLexerClass();

    // initialize the app
    init();
  }

  public static void init() {
    AnalyzerView view = new AnalyzerView();
    LexicalController lexController = new LexicalController();

    JTextArea resultOutput = view.getResultOutput();  // text area output
    JTextArea inputText = view.getTextInput();        // text area input
    view.setLocationRelativeTo(null);                 // center the window

    // analyze
    view.getAnalyzeBtn().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        StringBuilder sb = new StringBuilder();
        lexController.LoadString(inputText.getText());

        Map<Integer, String[]> results = lexController.getResults();
        if (results == null)
          return;

        results.forEach((k, v) -> {
          sb.append(v[0]).append("\t").append(v[1]).append("\n");
          resultOutput.setText(sb.toString());
        });
      }
    });

    // load file
    view.getLoadFileBtn().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        FileDialog fd = new FileDialog(view, "Choose a file", FileDialog.LOAD);
        fd.setDirectory("C:\\");
        fd.setVisible(true);

        if (fd.getFile() != null) {
          String file = fd.getDirectory() + fd.getFile();
          lexController.LoadFile(file);

          try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();

            for (String line = br.readLine(); line != null; line = br.readLine()) {
              sb.append(line).append("\n");
            }

            inputText.setText(sb.toString());
            br.close();
          } catch (FileNotFoundException ex) {
            ex.printStackTrace();
          } catch (IOException ex) {
            ex.printStackTrace();
          }
        }

      }
    });
  }
}
