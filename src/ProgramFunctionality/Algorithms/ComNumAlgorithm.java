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
 */
public class ComNumAlgorithm {
    private ComNumAlgorithm() {}


    public static ArrayList<Token> Tokenization(String str) {
        ArrayList<Token> resultTokenList = new ArrayList<Token>();
        TokenType[] tokenTypeArr = TokenType.values();
        String currentToken;
        Pattern pattern;
        Matcher matcher;

        while (!str.isEmpty()){
            for (TokenType tokenType : tokenTypeArr) {
                pattern = Pattern.compile('^' + tokenType.getRegEx());
                matcher = pattern.matcher(str);
                if (matcher.find()) {
                    currentToken = matcher.group();
                    str = matcher.replaceAll("");
                    if(tokenType != TokenType.Unnecessary) {
                        resultTokenList.add(new Token(tokenType, currentToken));
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
        String exceptionMessage = "The number of operands does not match to the number of operations.";
        Token temp;

        for(Token token: list){
            if(token.isVariable() || token.isLiteral()){
                stack.push(token);
            }
            else if(token.getType() == TokenType.Add){
                if(stack.size() > 1){
                    temp = new Token(TokenType.ComLiteralRectangular,
                            ComplexNumberOperation.add(getVariableOrLiteralValue(stack.pop(), memory),
                            getVariableOrLiteralValue(stack.pop(), memory)).ToString());
                    stack.push(temp);
                }
                else throw  new Exception(exceptionMessage);
            }
            else if(token.getType() == TokenType.Sub){
                if(stack.size() > 1){
                    Token op2 = stack.pop();
                    Token op1 = stack.pop();
                    temp = new Token(TokenType.ComLiteralRectangular,
                            ComplexNumberOperation.sub(getVariableOrLiteralValue(op1, memory),
                            getVariableOrLiteralValue(op2, memory)).ToString());
                    stack.push(temp);
                }
                else throw  new Exception(exceptionMessage);
            }
            else if(token.getType() == TokenType.Mul){
                if(stack.size() > 1){
                    temp = new Token(TokenType.ComLiteralRectangular,
                            ComplexNumberOperation.mul(getVariableOrLiteralValue(stack.pop(), memory),
                            getVariableOrLiteralValue(stack.pop(), memory)).ToString());
                    stack.push(temp);
                }
                else throw  new Exception(exceptionMessage);
            }
            else if(token.getType() == TokenType.Div){
                if(stack.size() > 1){
                    Token op2 = stack.pop();
                    Token op1 = stack.pop();
                    temp = new Token(TokenType.ComLiteralRectangular,
                            ComplexNumberOperation.div(getVariableOrLiteralValue(op1, memory),
                            getVariableOrLiteralValue(op2, memory)).ToString());
                    stack.push(temp);
                }
                else throw  new Exception(exceptionMessage);
            }
            else if(token.getType() == TokenType.Assignment){
                if(stack.size() > 1){
                    Token op2 = stack.pop();
                    Token op1 = stack.pop();
                    if(op1.isVariable()){
                        ComplexNumberVar var = new ComplexNumberVar(op1.getContent(), getVariableOrLiteralValue(op2, memory));
                        if(memory.containsVariable(op1.getContent())){
                            memory.setNewVariableValue(var.getName(), var.getValue());
                        }
                        else {
                            memory.createNewVariable(var);
                        }
                    }
                    else throw new Exception("It is not possible to assign a value to a non-variable.");
                }
                else throw  new Exception(exceptionMessage);
            }
            else if(token.getType() == TokenType.Sin){
                if(stack.size() > 0){
                    temp = new Token(TokenType.ComLiteralRectangular,
                            ComplexNumberOperation.sin(getVariableOrLiteralValue(stack.pop(), memory)).ToString());
                    stack.push(temp);
                }
                else throw  new Exception(exceptionMessage);
            }
            else if(token.getType() == TokenType.Cos){
                if(stack.size() > 0){
                    temp = new Token(TokenType.ComLiteralRectangular,
                            ComplexNumberOperation.cos(getVariableOrLiteralValue(stack.pop(), memory)).ToString());
                    stack.push(temp);
                }
                else throw  new Exception(exceptionMessage);
            }
            else if(token.getType() == TokenType.Tg){
                if(stack.size() > 0){
                    temp = new Token(TokenType.ComLiteralRectangular,
                            ComplexNumberOperation.tg(getVariableOrLiteralValue(stack.pop(), memory)).ToString());
                    stack.push(temp);
                }
                else throw  new Exception(exceptionMessage);
            }
            else if(token.getType() == TokenType.Ctg){
                if(stack.size() > 0){
                    temp = new Token(TokenType.ComLiteralRectangular,
                            ComplexNumberOperation.ctg(getVariableOrLiteralValue(stack.pop(), memory)).ToString());
                    stack.push(temp);
                }
                else throw  new Exception(exceptionMessage);
            }
            // ?????????????
            else if (token.getType() == TokenType.Pown){
                Token op2 = stack.pop();
                Token op1 = stack.pop();
                stack.push(new Token(TokenType.ComLiteralRectangular,
                        ComplexNumberOperation.pown(op1.getComplexNumberFromToken(), )));

            }
            else if(token.getType() == TokenType.PrintAllForms){
                if(stack.size() > 0){
                    Token operand = stack.pop();
                    memory.addTextToConsoleOutput(
                            "paf(" + operand.getContent() + "):\n" + ComplexNumber.getAllForms(
                                    getVariableOrLiteralValue(operand, memory)));
                }
                else throw  new Exception(exceptionMessage);
            }
            else if(token.getType() == TokenType.Print) {
                if(stack.size() > 0){
                    Token operand = stack.pop();
                    memory.addTextToConsoleOutput(
                            "print(" + operand.getContent() + "):\n\t" + (getVariableOrLiteralValue(operand, memory)).ToString());
                }
                else throw  new Exception(exceptionMessage);
            }
            else if(token.getType() == TokenType.PrintSqrtn){
                if(stack.size() > 1) {
                    Token op2 = stack.pop();
                    Token op1 = stack.pop();
                    memory.addTextToConsoleOutput("printSqrtn(" + op1.getContent() + ", " + op2.getContent() + "):\n");
                    if (op2.getType() == TokenType.NumLiteral1) {
                        for (ComplexNumber num : ComplexNumberOperation.sqrtn(getVariableOrLiteralValue(op1, memory), (int) Double.parseDouble(op2.getContent()))) {
                            memory.addTextToConsoleOutput("\t" + num.ToString() + "\n");
                        }
                    }
                    else throw new Exception("The second parameter in the \"printSqrtn\" function must be a positive integer literal.");
                }
                else throw  new Exception(exceptionMessage);
            }
            else if(token.getType() == TokenType.ComplexConjugate){
                if(stack.size() > 0) {
                    Token operand = stack.pop();
                    switch (operand.getType()){
                        // Для комплексных литералов условно выбранный знак '$'
                        // будет значить, преобразование к комплексно сопряжённому числу
                        case ComLiteralRectangular, ComLiteralExponential1,
                        ComLiteralExponential2, ComLiteralPolar1, ComLiteralPolar2 -> {
                            stack.push(new Token(
                                    TokenType.ComLiteralRectangular,
                                    ComplexNumberOperation.getComplexConjugate(
                                            getVariableOrLiteralValue(operand, memory)).ToString()));
                        }
                        case Variable -> {
                            ComplexNumber cn = memory.getValueOfVariable(operand.getContent());
                            stack.push(new Token(TokenType.ComLiteralRectangular,
                                  (new ComplexNumber(ComplexNumberForm.Rectangular, cn.Re(), cn.Im()))
                                          .ToString(ComplexNumberForm.Rectangular)));
                        }
                        default -> {
                            throw new Exception("The operation of obtaining a complex conjugate number \"$\" cannot be applied to the operand \"" + operand.getContent() + "\"");
                        }
                    }
                }
                else throw  new Exception(exceptionMessage);
            }
        }
    }




    private static ComplexNumber getVariableOrLiteralValue(Token token, Memory memory) throws Exception{
        if(token.isVariable()){
            return memory.getValueOfVariable(token.getContent());
        }
        else if(token.isLiteral()){
            return token.getComplexNumberFromToken();
        }
        throw new Exception("The \"getTokenValue()\" function is not applicable to a token with the " + token.getType() + " type.");
    }
}