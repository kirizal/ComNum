package ProgramFunctionality.Algorithms;

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
}

