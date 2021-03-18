/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import lexical.Token;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import lexical.LexicalAnalyzer;

/**
 *
 * @author chars_mc
 */
public class LexicalController {

  private lexical.LexicalAnalyzer la = null;
  private BufferedReader buffer = null;
  private boolean file = false;

  public LexicalController() {
  }

  /**
   * Returns a map with the result of the lexical analysis
   *
   * @return Map with the lexeme and the token
   */
  public Map<Integer, String[]> getResults() {
    if (this.buffer == null)
      return null;

    Map<Integer, String[]> results = new HashMap<>();
    int i = 0;
    try {
      while (true) {
        Token tokenResult = la.yylex();
        if (!la.existsToken())
          break;

        String[] result = {tokenResult.getLexeme(), tokenResult.getToken()};
        results.put(i++, result);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return results;
  }

  /**
   * Loads a file into the buffer reader
   */
  public void LoadFile(String filePath) {
    try {
      this.buffer = new BufferedReader(new FileReader(filePath));
      this.la = new LexicalAnalyzer(this.buffer);
      this.file = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Loads a file into the buffer reader
   */
  public void LoadString(String text) {
    Reader inputString = new StringReader(text);
    this.buffer = new BufferedReader(inputString);
    this.la = new LexicalAnalyzer(this.buffer);
    this.file = false;
  }

  /**
   * Checks if there is a file loaded
   *
   * @return Retuns true if there is a file loaded
   */
  public boolean isFileLoaded() {
    return this.file;
  }

  /**
   * Generates the class from .flex file
   */
  public static void generateLexerClass() {
    String lexerFilePath = "src/lexical/LexicalAnalyzer.flex";
    File file = new File(lexerFilePath);
    JFlex.Main.generate(file);
  }
}
