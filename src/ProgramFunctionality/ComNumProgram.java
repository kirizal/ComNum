package ProgramFunctionality;
import ConsoleUtils.ConColor;
import ConsoleUtils.ConProgressBar;
import ProgramFunctionality.Algorithms.ComNumAlgorithm;
import ProgramFunctionality.Algorithms.Token;
import java.util.ArrayList;


public class ComNumProgram {
    private Memory memory;
    private FileManager fileManager;
    private String[] arguments;
    private ConProgressBar progressBar;


    public ComNumProgram(String[] args) {
        this.arguments = args;

    }



    public void Run(){
        for(String fileName: this.arguments){
            //Подготовка программы к работе
            this.memory = new Memory();
            this.fileManager = new FileManager(fileName);
            this.progressBar = new ConProgressBar("pb", 60, 0, ConColor.ANSI_RESET, ConColor.ANSI_FOREGROUND_GREEN);
            this.memory.setProgramCode(this.fileManager.getTextFromCurrentFile());
            System.out.println("\n---------------| " + fileName + " |---------------");
            System.out.println("Processing file contents:");
            if(this.memory.getProgramCode().isEmpty()){
                this.memory.addTextToErrorLog("The file named \"" + fileName + "\" either could not be found, or it is empty.");
            }

            //Попытка выполнения всех строк кода с фиксацией ошибок
            this.progressBar.printControlWithoutColors();
            double codeLength = this.memory.getProgramCode().size();
            double codeExecuted = 1;
            int percantage = 0;
            for(String line: this.memory.getProgramCode()){
                if(!line.isEmpty()){
                    this.tryToExecuteCodeLine(line, (int)codeExecuted);
                }
                codeExecuted++;
                percantage = (int)((codeExecuted - 1) / codeLength * 100.0);
                this.progressBar.setPercentage(percantage);
                System.out.print("\r");
                this.progressBar.printControlWithoutColors();
            }

            //Вывод в консоль результата работы программы
            if(this.memory.getErrorLog().isEmpty()){
                System.out.println("\n"+this.memory.getConsoleOutput());
            }
            else{
                System.out.println("\n --= ERRORS =--");
                System.out.println(""+this.memory.getErrorLog());
            }
        }
    }



    private void tryToExecuteCodeLine(String line, int lineNumber){
        try{
            ArrayList<Token> tokenList = ComNumAlgorithm.Tokenization(line, this.memory);
            tokenList = ComNumAlgorithm.ShuntingYardAlgorithm(tokenList);
            ComNumAlgorithm.CalculateExpressionInRPN(tokenList, this.memory);
        }
        catch(Exception ex){
            String exMessage = ex.getMessage();
            if(exMessage.equals("The variable \"i\" is not initialized.")){
                exMessage = "An error in the polar or exponential form of complex number.\n\t\t\t Check that the first multiplier is positive, and the arguments of the sin() and cos() in polar form match.\n";
            }
            this.memory.addTextToErrorLog("line #" + lineNumber + ":\t " + exMessage);
        }
    }
}
