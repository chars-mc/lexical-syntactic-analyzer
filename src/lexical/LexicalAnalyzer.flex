package lexical;

// DECLARATION SECTION
%%
%public
%class LexicalAnalyzer
%type Token

%init{
  /* constructor */
%init}


// REGULAR EXPRESSIONS

LETTER = [a-zA-Z_]
WORD = {LETTER}{LETTER}*
DIGIT = [0-9]
NUMBER = {DIGIT}{DIGIT}*

SYMBOL = "+" | "-" | "*" | "/" | "=" | ";" | "{" | "}" | "(" | ")" | "==" | "!=" | "[" | "]"
SPACE = " "
BREAK_LINE = \n | \r | \r\n


%{
  private boolean nextToken = true;

  public Boolean existsToken() {
    return this.nextToken;
  }
%}
%init{
  /* constructor*/
%init}

%eof{
  this.nextToken = false;
%eof}

// RULES SECTION
%%
int |
main |
class |
while |
for |
if |
else {

  return new Token(yytext(), "RESERVED");
}

// symbols to ignore
{SPACE} {}
{BREAK_LINE} {}
"//".* {}

// symbols
"+" {
  return new Token(yytext(), "ADDITION");
}
"-" {
  return new Token(yytext(), "SUBTRACTION");
}
"/" {
  return new Token(yytext(), "DIVISION");
}
"*" {
  return new Token(yytext(), "MULTIPLY");
}
"=" {
  return new Token(yytext(), "EQUALS");
}
";" {
  return new Token(yytext(), "SEMICOLON");
}
"{" | "}" {
  return new Token(yytext(), "CURLY BRACES");
}
"(" | ")" {
  return new Token(yytext(), "BRACKETS");
}
"==" {
  return new Token(yytext(), "EQUAL TO");
}
"!=" {
  return new Token(yytext(), "NOT EQUAL");
}
"[" | "]" {
  return new Token(yytext(), "SQUARE BRACKETS");
}

// number
("(-"{DIGIT}+")")|{DIGIT} {
  return new Token(yytext(), "NUMBER");
}

// identifiers
{LETTER}({LETTER}|{DIGIT})* {
  return new Token(yytext(), "IDENTIFIER");
}
