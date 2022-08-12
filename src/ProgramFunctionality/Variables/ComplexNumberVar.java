package ProgramFunctionality.Variables;
import ExtendedMath.ComplexNumber;


public class ComplexNumberVar implements Variable<ComplexNumber> {
    private final String Name;
    private ComplexNumber Value;


    public ComplexNumberVar(String name, ComplexNumber num) {
        this.Name = name;
        this.Value = num;
    }


    @Override
    public ComplexNumber getValue() {
        return this.Value;
    }

    @Override
    public void setValue(ComplexNumber value) { this.Value = value; }

    @Override
    public String getName() { return this.Name; }

    @Override
    public String getType() {
        return "cnum";
    }
}
