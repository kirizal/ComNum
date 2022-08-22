package ProgramFunctionality.Algorithms;

public enum TokenType {
    Unnecessary("[\\s\\n\\t\\r\\v]+", (byte)0),
    ComLiteralRectangular("-?\\d+\\.\\d+[\\+\\-]\\d+\\.\\d+\\*i", (byte)0),
    ComLiteralPolar1("\\d+\\.\\d+\\*\\(cos\\(\\d+\\.\\d+\\)\\+i\\*sin\\(\\d+\\.\\d+\\)\\)", (byte)0),
    ComLiteralPolar2("\\d+\\.\\d+\\*\\(cos\\(-\\d+\\.\\d+\\)\\+i\\*sin\\(-\\d+\\.\\d+\\)\\)", (byte)0),
    ComLiteralExponential1("\\d+\\.\\d+\\*exp\\(i\\*\\d+\\.\\d+\\)", (byte)0),
    ComLiteralExponential2("\\d+\\.\\d+\\*exp\\(i\\*\\(-\\d+\\.\\d+\\)\\)", (byte)0),
    NumLiteral1("\\d+\\.\\d+", (byte)0),
    NumLiteral2("-?\\d+\\.\\d+", (byte)0),    //Используется только для перевода строковой формы записи
                                                    //комлпексного числа в объект класса ComplexNumber
    Sin("sin", (byte)0),
    Cos("cos", (byte)0),
    Tg("tg", (byte)0),
    Ctg("ctg", (byte)0),
    Pown("pown", (byte)0),
    PrintSqrtn("printSqrtn", (byte)0),
    Print("print", (byte)0),
    PrintAllForms("paf", (byte)0),
    Add("\\+", (byte)3),
    Sub("-", (byte)3),
    Mul("\\*", (byte)4),
    Div("\\/", (byte)4),
    ComplexConjugate("\\$", (byte)5),
    Assignment("=", (byte)2),
    OpeningBracket("\\(", (byte)1),
    ClosingBracket("\\)", (byte)1),
    Comma(",", (byte)0),
    Variable("[a-zA-Z]+\\w*", (byte)0),
    Unknown(".+", (byte)0);


    private String RegEx;
    private byte Priority;  // Только у операций и скобок приоритет > 0
    public String getRegEx(){ return this.RegEx; }
    public byte getPriority() { return this.Priority; }
    TokenType(String regex, byte priority){
        this.RegEx = regex;
        this.Priority = priority;
    }

}