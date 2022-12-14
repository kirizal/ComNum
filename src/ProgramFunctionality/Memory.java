package ProgramFunctionality;
import ExtendedMath.ComplexNumber;
import ProgramFunctionality.Variables.*;
import java.util.ArrayList;


public class Memory {
    private ArrayList<Variable> VariableList;
    private ArrayList<String> ProgramCode;
    private StringBuilder ConsoleOutput;
    private StringBuilder ErrorLog;


    public Memory(){
        this.VariableList = new ArrayList<>();
        this.ConsoleOutput = new StringBuilder("");
        this.ProgramCode = new ArrayList<>();
        this.ErrorLog = new StringBuilder("");
    }



    public void setProgramCode(ArrayList<String> code) { this.ProgramCode = code; }
    public ArrayList<String> getProgramCode() { return this.ProgramCode; }



    public boolean containsVariable(String name){
        boolean result = false;
        for (Variable var: this.VariableList){
            if(var.getName().equals(name)) result = true;
            break;
        }
        return result;
    }



    public ComplexNumber getValueOfVariable(String name) throws Exception{
        for(Variable var: this.VariableList){
            if(var.getName().equals(name)){
                if(var instanceof ComplexNumberVar){
                    ComplexNumberVar temp = (ComplexNumberVar)var;
                    return temp.getValue();
                }
            }
        }
        throw new Exception("The variable \"" + name + "\" is not initialized.");
    }



    public void createNewVariable(Variable var) {
        this.VariableList.add(var);
    }
    public void setNewVariableValue(String varName, ComplexNumber value) {
        for(Variable var: this.VariableList){
            if(var.getName().equals(varName)){
                var.setValue(value);
                return;
            }
        }
        ComplexNumberVar newVar = new ComplexNumberVar(varName, value);
        this.createNewVariable(newVar);
    }



    public String getConsoleOutput(){
        return this.ConsoleOutput.toString();
    }
    public String getErrorLog(){
        return this.ErrorLog.toString();
    }
    public void clearConsoleOutput(){
        this.ConsoleOutput = new StringBuilder("");
    }
    public void clearErrorLog(){
        this.ErrorLog = new StringBuilder("");
    }
    public void addTextToConsoleOutput(String text){
        this.ConsoleOutput.append(text);
    }
    public void addTextToErrorLog(String text) {
        this.ErrorLog.append(text);
    }
}