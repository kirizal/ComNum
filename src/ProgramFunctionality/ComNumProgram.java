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
            if(this.memory.getProgramCode().isEmpty()){
                this.memory.addTextToErrorLog(createErrorMessage("The file named \"" + fileName + "\" either could not be found, or it is empty."));
            }

            //Попытка выполнения всех строк кода с фиксацией ошибок
            this.progressBar.printControl();
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
                this.progressBar.printControl();
            }

            //Вывод в консоль результата работы программы
            if(this.memory.getErrorLog().isEmpty()){
                System.out.println("\n"+this.memory.getConsoleOutput());
            }
            else{
                System.out.println("\n"+this.memory.getErrorLog());
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
            this.memory.addTextToErrorLog("line #" + lineNumber + ":\t " + createErrorMessage(ex.getMessage()));
        }
    }



    private static String createFileNameMessage(String text){
        return ConColor.ANSI_FOREGROUND_CYAN.getANSI_Color() +
               "\n" + text + ":\n" + ConColor.ANSI_RESET.getANSI_Color();
    }
    private static String createErrorMessage(String text){
        return ConColor.ANSI_FOREGROUND_RED.getANSI_Color() +
               text + "\n" + ConColor.ANSI_RESET.getANSI_Color();
    }
}
