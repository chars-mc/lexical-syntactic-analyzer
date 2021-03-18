/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical;

/**
 *
 * @author chars_mc
 */
public class Token {

  String lexeme;
  String token;

  Token(String lexeme, String token) {
    this.lexeme = lexeme;
    this.token = token;
  }

  public String toString() {
    return "Lexeme: " + this.lexeme + "\tToken: " + this.token;
  }

  public String getLexeme() {
    return this.lexeme;
  }

  public String getToken() {
    return this.token;
  }
}
