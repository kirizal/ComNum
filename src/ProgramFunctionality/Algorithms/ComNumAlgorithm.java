package ProgramFunctionality.Algorithms;
import ExtendedMath.ComplexNumberForm;
import ExtendedMath.ComplexNumberOperation;
import ExtendedMath.ComplexNumber;
import ProgramFunctionality.Memory;
import ProgramFunctionality.Variables.ComplexNumberVar;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
    Статический класс реализующий:
    1) Токенизацию строк кода, полученных из файлы;
    2) Алгоритм сортирововчной станции для приведения сложных выражений к постфиксной форме записи;
    3) Вычисление выражений, записанных в постфиксной форме
 */
public class ComNumAlgorithm {
    private ComNumAlgorithm() {}


    public static ArrayList<Token> Tokenization(String str, Memory memory) throws Exception {
        ArrayList<Token> resultTokenList = new ArrayList<>();
        TokenType[] tokenTypeArr = TokenType.values();
        String currentTokenContent;
        Pattern pattern;
        Matcher matcher;

        while (!str.isEmpty()){
            for (TokenType tokenType : tokenTypeArr) {
                pattern = Pattern.compile('^' + tokenType.getRegEx());
                matcher = pattern.matcher(str);
                if (matcher.find()) {
                    currentTokenContent = matcher.group();
                    str = matcher.replaceAll("");
                    if(tokenType == TokenType.Comment){
                        return resultTokenList;
                    }
                    else if(tokenType == TokenType.Unknown){
                        throw new Exception("An unknown string token \"" + currentTokenContent + "\" was encountered.");
                    }
                    else if(tokenType != TokenType.Unnecessary) {
                        resultTokenList.add(new Token(tokenType, currentTokenContent));
                    }
                    break;
                }
            }
        }
        return resultTokenList;
    }




    public static ArrayList<Token> ShuntingYardAlgorithm(ArrayList<Token> inputList) throws Exception{
        ArrayList<Token> outputList = new ArrayList<>();
        Stack<Token> stack = new Stack<>();

        for(Token token: inputList){
            if(token.isVariable() || token.isLiteral()){
                outputList.add(token);
            }
            else if(token.isFunction()){
                stack.push(token);
            }
            else if(token.isComma()){
                if(stack.isEmpty()){
                    //Синтаксическая ошибка при написании параметров функции
                    throw new Exception("Syntax error when writing function parameters.");
                }
                while(!stack.peek().isOpeningBracket()){
                    outputList.add(stack.pop());
                    if(stack.isEmpty()){
                        //Пропущена открывающая скобка или запятая (разделитель аргументов функции)
                        throw new Exception("The function argument separator (comma) is omitted in the expression, or the opening bracket is omitted.");
                    }
                }
            }
            else if(token.isOperation()){
                if(!stack.isEmpty()){
                    while((stack.peek().getType().getPriority() > token.getType().getPriority()) ||
                            (stack.peek().getType().getPriority() == token.getType().getPriority() &&
                            stack.peek().isLeftAssociativeOperation())){
                        outputList.add(stack.pop());
                    }
                }
                stack.push(token);
            }
            else if(token.isOpeningBracket()){
                stack.push(token);
            }
            else if(token.isClosingBracket()){
                if(stack.isEmpty()){
                    //Количества открывающих и закрывающих скобок не соответствуют друг другу
                    throw new Exception("In the expression, the number of opening brackets does not match the number of closing brackets.");
                }
                while(stack.peek().getType() != TokenType.OpeningBracket){
                    outputList.add(stack.pop());
                    if(stack.isEmpty()){
                        //Количества открывающих и закрывающих скобок не соответствуют друг другу
                        throw new Exception("In the expression, the number of opening brackets does not match the number of closing brackets.");
                    }
                }
                stack.pop();
                if(!stack.isEmpty()){
                    if(stack.peek().isFunction()){
                        outputList.add(stack.pop());
                    }
                }
            }
        }
        while(!stack.isEmpty()){
            if(stack.peek().getType() == TokenType.OpeningBracket){
                //Количества открывающих и закрывающих скобок не соответствуют друг другу
                throw new Exception("In the expression, the number of opening brackets does not match the number of closing brackets.");
            }
            outputList.add(stack.pop());
        }
        return outputList;
    }



    //Вычисление на стеке выражения в обратной польской записи
    public static void CalculateExpressionInRPN(ArrayList<Token> list, Memory memory) throws Exception{
        Stack<Token> stack = new Stack<>();
        Token temp;

        for(Token token: list){
            if(token.isVariable() || token.isLiteral()){
                stack.push(token);
            }
            else if(token.getType() == TokenType.Add){
                if(stack.size() > 1){
                    temp = new Token(TokenType.ComLiteralRectangular,
                            ComplexNumberOperation.add(getVariableOrLiteralValueFromToken(stack.pop(), memory),
                            getVariableOrLiteralValueFromToken(stack.pop(), memory)).ToString());
                    stack.push(temp);
                }
                else throw  new Exception("The number of operands does not match to the number of operations.");
            }
            else if(token.getType() == TokenType.Sub){
                if(stack.size() > 1){
                    Token op2 = stack.pop();
                    Token op1 = stack.pop();
                    temp = new Token(TokenType.ComLiteralRectangular,
                            ComplexNumberOperation.sub(getVariableOrLiteralValueFromToken(op1, memory),
                            getVariableOrLiteralValueFromToken(op2, memory)).ToString());
                    stack.push(temp);
                }
                else throw  new Exception("The number of operands does not match to the number of operations.");
            }
            else if(token.getType() == TokenType.Mul){
                if(stack.size() > 1){
                    temp = new Token(TokenType.ComLiteralRectangular,
                            ComplexNumberOperation.mul(getVariableOrLiteralValueFromToken(stack.pop(), memory),
                            getVariableOrLiteralValueFromToken(stack.pop(), memory)).ToString());
                    stack.push(temp);
                }
                else throw  new Exception("The number of operands does not match to the number of operations.");
            }
            else if(token.getType() == TokenType.Div){
                if(stack.size() > 1){
                    Token op2 = stack.pop();
                    Token op1 = stack.pop();
                    temp = new Token(TokenType.ComLiteralRectangular,
                            ComplexNumberOperation.div(getVariableOrLiteralValueFromToken(op1, memory),
                            getVariableOrLiteralValueFromToken(op2, memory)).ToString());
                    stack.push(temp);
                }
                else throw  new Exception("The number of operands does not match to the number of operations.");
            }
            else if(token.getType() == TokenType.Assignment){
                if(stack.size() > 1){
                    Token op2 = stack.pop();
                    Token op1 = stack.pop();
                    if(op1.isVariable()){
                        ComplexNumberVar var = new ComplexNumberVar(op1.getContent(), getVariableOrLiteralValueFromToken(op2, memory));
                        memory.setNewVariableValue(var.getName(), var.getValue());
                    }
                    else throw new Exception("It is not possible to assign a value to a non-variable.");
                }
                else throw  new Exception("The number of operands does not match to the number of operations.");
            }
            else if(token.getType() == TokenType.Sin){
                if(stack.size() > 0){
                    ComplexNumber operand = getVariableOrLiteralValueFromToken(stack.pop(), memory);
                    if(operand.Im() == 0.0){
                        temp = new Token(TokenType.ComLiteralRectangular,
                                new ComplexNumber(ComplexNumberForm.Rectangular,
                                        Math.sin(Math.toRadians(operand.Re())), 0.0).ToString());
                    }
                    else {
                        temp = new Token(TokenType.ComLiteralRectangular,
                                ComplexNumberOperation.sin(operand).ToString());
                    }
                    stack.push(temp);
                }
                else throw  new Exception("The number of operands does not match to the number of operations.");
            }
            else if(token.getType() == TokenType.Cos){
                if(stack.size() > 0){
                    ComplexNumber operand = getVariableOrLiteralValueFromToken(stack.pop(), memory);
                    if(operand.Im() == 0.0){
                        temp = new Token(TokenType.ComLiteralRectangular,
                                new ComplexNumber(ComplexNumberForm.Rectangular,
                                        Math.cos(Math.toRadians(operand.Re())), 0.0).ToString());
                    }
                    else {
                        temp = new Token(TokenType.ComLiteralRectangular,
                                ComplexNumberOperation.cos(operand).ToString());
                    }
                    stack.push(temp);
                }
                else throw  new Exception("The number of operands does not match to the number of operations.");
            }
            else if(token.getType() == TokenType.Tg){
                if(stack.size() > 0){
                    ComplexNumber operand = getVariableOrLiteralValueFromToken(stack.pop(), memory);
                    if(operand.Im() == 0.0){
                        temp = new Token(TokenType.ComLiteralRectangular,
                                new ComplexNumber(ComplexNumberForm.Rectangular,
                                        Math.tan(Math.toRadians(operand.Re())), 0.0).ToString());
                    }
                    else {
                        temp = new Token(TokenType.ComLiteralRectangular,
                                ComplexNumberOperation.tg(operand).ToString());
                    }
                    stack.push(temp);
                }
                else throw  new Exception("The number of operands does not match to the number of operations.");
            }
            else if(token.getType() == TokenType.Ctg){
                if(stack.size() > 0){
                    ComplexNumber operand = getVariableOrLiteralValueFromToken(stack.pop(), memory);
                    if(operand.Im() == 0.0){
                        temp = new Token(TokenType.ComLiteralRectangular,
                                new ComplexNumber(ComplexNumberForm.Rectangular,
                                        1.0 / Math.tan(Math.toRadians(operand.Re())), 0.0).ToString());
                    }
                    else {
                        temp = new Token(TokenType.ComLiteralRectangular,
                                ComplexNumberOperation.ctg(operand).ToString());
                    }
                    stack.push(temp);
                }
                else throw  new Exception("The number of operands does not match to the number of operations.");
            }
            else if (token.getType() == TokenType.Pown){
                if(stack.size() > 1){
                    int op2 = getIntegerFromComplexNumber(getVariableOrLiteralValueFromToken(stack.pop(), memory), "pown");
                    ComplexNumber op1 = getVariableOrLiteralValueFromToken(stack.pop(), memory);
                    stack.push(new Token(TokenType.ComLiteralRectangular,
                            ComplexNumberOperation.pown(op1, op2).ToString()));
                }
                else throw new Exception("The number of operands does not match to the number of operations.");
            }
            else if(token.getType() == TokenType.PrintAllForms){
                if(stack.size() > 0){
                    temp = stack.pop();
                    memory.addTextToConsoleOutput(
                            "\npaf(" + temp.getContent() + "):\n" + ComplexNumber.getAllForms(
                                    getVariableOrLiteralValueFromToken(temp, memory)));
                }
                else throw  new Exception("The number of operands does not match to the number of operations.");
            }
            else if(token.getType() == TokenType.Print) {
                if(stack.size() > 0){
                    temp = stack.pop();
                    memory.addTextToConsoleOutput(
                            "\nprint(" + temp.getContent() + "):\n\t" + (getVariableOrLiteralValueFromToken(temp, memory)).ToString() + "\n");
                }
                else throw  new Exception("The number of operands does not match to the number of operations.");
            }
            else if(token.getType() == TokenType.PrintSqrtn){
                if(stack.size() > 1) {
                    int op2 = getIntegerFromComplexNumber(getVariableOrLiteralValueFromToken(stack.pop(), memory), "printSqrtn");
                    ComplexNumber op1 = getVariableOrLiteralValueFromToken(stack.pop(), memory);
                    memory.addTextToConsoleOutput("\nprintSqrtn(" + op1.ToString(ComplexNumberForm.Polar) + ", " + op2 + "):\n");
                    for (ComplexNumber num : ComplexNumberOperation.sqrtn(op1, op2)) {
                        memory.addTextToConsoleOutput("\t" + num.ToString() + "\n");
                    }
                }
                else throw  new Exception("The number of operands does not match to the number of operations.");
            }
            else if(token.getType() == TokenType.ComplexConjugate){
                if(stack.size() > 0) {
                    temp = stack.pop();
                    switch (temp.getType()){
                        // Для комплексных литералов условно выбранный знак '$'
                        // будет значить, преобразование к комплексно сопряжённому числу
                        case ComLiteralRectangular, ComLiteralExponential1,
                        ComLiteralExponential2, ComLiteralPolar1, ComLiteralPolar2 -> {
                            stack.push(new Token(
                                    TokenType.ComLiteralRectangular,
                                    ComplexNumberOperation.getComplexConjugate(
                                            getVariableOrLiteralValueFromToken(temp, memory)).ToString()));
                        }
                        case Variable -> {
                            ComplexNumber cn = memory.getValueOfVariable(temp.getContent());
                            stack.push(new Token(TokenType.ComLiteralRectangular,
                                  (new ComplexNumber(ComplexNumberForm.Rectangular, cn.Re(), cn.Im()))
                                          .ToString(ComplexNumberForm.Rectangular)));
                        }
                        default -> {
                            throw new Exception("The operation of obtaining a complex conjugate number \"$\" cannot be applied to the operand \"" + temp.getContent() + "\"");
                        }
                    }
                }
                else throw  new Exception("The number of operands does not match to the number of operations.");
            }
        }
    }




    private static ComplexNumber getVariableOrLiteralValueFromToken(Token token, Memory memory) throws Exception{
        if(token.isVariable()){
            return memory.getValueOfVariable(token.getContent());
        }
        else if(token.isLiteral()){
            return token.getComplexNumberFromToken();
        }
        throw new Exception("The \"getTokenValue()\" function is not applicable to a token with the " + token.getType() + " type.");
    }



    private static int getIntegerFromComplexNumber(ComplexNumber cn, String funcName) throws Exception{
        if(cn.Im() == 0.0){
            return (int)cn.Re();
        }
        else throw  new Exception("The complex number \"" + cn.ToString() + "\" cannot be converted to an integer for the function \"" + funcName + "\".");
    }
    private static double getDoubleFromComplexNumber(ComplexNumber cn, String funcName) throws Exception{
        if(cn.Im() == 0.0){
            return cn.Re();
        }
        else throw new Exception("\"The complex number \"" + cn.ToString() + "\" cannot be converted to an fractional number for the function \"" + funcName + "\".");
    }
}