package ProgramFunctionality.Algorithms;
import ExtendedMath.ComplexNumber;
import ExtendedMath.ComplexNumberForm;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Token {

    private String Content;
    private TokenType Type;


    public String getContent() { return this.Content; }
    public TokenType getType() { return this.Type; }


    public Token(TokenType type, String content){
        this.Content = content;
        this.Type = type;
    }


    @Override
    public String toString(){
        return "Token:\n\tType:\t\t" + this.Type + "\n\tContent:\t" + this.Content;
    }



    public ComplexNumber getComplexNumberFromToken() throws Exception {
        //Для поиска коэффициентов комплексного числа в поле Content токена
        Pattern pattern = Pattern.compile(TokenType.NumLiteral2.getRegEx());
        Matcher matcher = pattern.matcher(this.Content);
        double[] coefficients = new double[2];


        switch (this.Type) {
            case ComLiteralRectangular -> {
                matcher.find();                                             //
                coefficients[0] = Double.parseDouble(matcher.group());      // Поиск
                matcher.find();                                             // коэффциентов
                coefficients[1] = Double.parseDouble(matcher.group());      //
                return new ComplexNumber(ComplexNumberForm.Rectangular,
                        coefficients[0],
                        coefficients[1]);
            }
            case ComLiteralPolar1, ComLiteralPolar2 -> {
                matcher.find();                                             //
                coefficients[0] = Double.parseDouble(matcher.group());      // Поиск
                matcher.find();                                             // коэффциентов
                coefficients[1] = Double.parseDouble(matcher.group());      //
                return new ComplexNumber(ComplexNumberForm.Polar,
                        coefficients[0],
                        coefficients[1]);
            }
            case ComLiteralExponential1, ComLiteralExponential2 -> {
                matcher.find();                                             //
                coefficients[0] = Double.parseDouble(matcher.group());      // Поиск
                matcher.find();                                             // коэффциентов
                coefficients[1] = Double.parseDouble(matcher.group());      //
                return new ComplexNumber(ComplexNumberForm.Exponential,
                        coefficients[0],
                        coefficients[1]);
            }
            case NumLiteral1, NumLiteral2 -> {
                matcher.find();                                             // Поиск
                coefficients[0] = Double.parseDouble(matcher.group());      // коэффициента
                return new ComplexNumber(ComplexNumberForm.Rectangular,
                        coefficients[0],
                        0.0);
            }
            default -> {
                throw new Exception("The \"getComplexNumberFromToken()\" function is not applicable to a token with the " + this.Type + " type.");
            }
        }
    }



    //Функция, возвращающая TRUE, если операция является левоассоциатовной и FALSE - если не является
    public boolean isLeftAssociativeOperation(){
        return this.Type == TokenType.Add || this.Type == TokenType.Sub ||
                this.Type == TokenType.Mul || this.Type == TokenType.Div;
    }

    public boolean isVariable(){
        return this.Type == TokenType.Variable;
    }

    public boolean isLiteral(){
        return this.Type == TokenType.NumLiteral1 || this.Type == TokenType.NumLiteral2 ||
                this.Type == TokenType.ComLiteralExponential1 || this.Type == TokenType.ComLiteralExponential2 ||
                this.Type == TokenType.ComLiteralRectangular || this.Type == TokenType.ComLiteralPolar1 ||
                this.Type == TokenType.ComLiteralPolar2;
    }

    public boolean isOperation(){
        return this.Type == TokenType.Add || this.Type == TokenType.Sub ||
                this.Type == TokenType.Mul || this.Type == TokenType.Div ||
                this.Type == TokenType.ComplexConjugate || this.Type == TokenType.Assignment;
    }

    public boolean isFunction(){
        return this.Type == TokenType.Sin || this.Type == TokenType.Cos ||
                this.Type == TokenType.Tg || this.Type == TokenType.Ctg ||
                this.Type == TokenType.Pown || this.Type == TokenType.PrintSqrtn ||
                this.Type == TokenType.Print || this.Type == TokenType.PrintAllForms;
    }

    public boolean isOpeningBracket(){
        return this.Type == TokenType.OpeningBracket;
    }

    public  boolean isClosingBracket(){
        return this.Type == TokenType.ClosingBracket;
    }

    public boolean isComma(){
        return this.Type == TokenType.Comma;
    }
}