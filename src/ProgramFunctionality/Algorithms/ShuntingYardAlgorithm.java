package ProgramFunctionality.Algorithms;
import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
    Статический класс реализующий:
    1) Токенизацию входящих выражений;
    2) Алгоритм сортирововчной станции для приведения сложных выражений к постфиксной форме записи;
 */
public class ShuntingYardAlgorithm {
    private ShuntingYardAlgorithm() {}


    public static ArrayList<Token> Tokenization(String str) {
        TokenType[] tokenTypeArr = TokenType.values();
        ArrayList<Token> resultTokenList = new ArrayList<Token>();
        String currentToken;
        Pattern pattern;
        Matcher matcher;
        int index;

        while (!str.isEmpty()){
            for (TokenType tokenType : tokenTypeArr) {
                pattern = Pattern.compile(tokenType.getRegEx());
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
}
