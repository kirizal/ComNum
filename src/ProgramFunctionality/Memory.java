package ProgramFunctionality;
import ProgramFunctionality.Variables.Variable;

import java.util.ArrayList;

public class Memory {
    protected ArrayList<Variable> Var_list;
    private StringBuilder Output;


    public Memory(){
        Var_list = new ArrayList<>();
        Output = new StringBuilder("");
    }
}
