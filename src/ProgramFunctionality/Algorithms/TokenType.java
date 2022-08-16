package ProgramFunctionality.Algorithms;

public enum TokenType {
    Unnecessary("^[\\s\\n\\t\\r\\v]+"),
    ComLiteralRectangular("^\\d+\\.\\d+[\\+\\-]i\\*\\d+\\.\\d+"),
    ComLiteralPolar("^\\d+\\.\\d+\\*\\(cos\\(\\d+\\.\\d+\\)\\+i\\*sin\\(\\d+\\.\\d+\\)\\)"),
    ComLiteralExponential("^\\d+\\.\\d+\\*exp\\(i\\*\\d+\\.\\d+\\)"),
    NumLiteral("^\\d+\\.\\d+"),
    Sin("^sin"),
    Cos("^cos"),
    Tg("^tg"),
    Ctg("^ctg"),
    Pown("^pown"),
    Sqrtn("^sqrtn"),
    Print("^print"),
    PrintAllForms("^paf"),
    Add("^\\+"),
    Sub("^-"),
    Mul("^\\*"),
    Div("^\\/"),
    Assignment("^="),
    OpeningBracket("^\\("),
    ClosingBracket("^\\)"),
    Comma("^,"),
    Variable("^[a-zA-Z]+\\w*");


    private String RegEx;
    public String getRegEx(){ return this.RegEx; }
    TokenType(String regex){
        this.RegEx = regex;
    }
}
