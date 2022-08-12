package ProgramFunctionality.Variables;
import ExtendedMath.ComplexNumber;
import ExtendedMath.ComplexNumberForm;


public class NumberVar implements Variable<Double>{

    private final String Name;
    private Double Value;



    public NumberVar(String name, Double num) {
        this.Name = name;
        this.Value = num;
    }
    public ComplexNumber ConvertToComplexNumber(){
        return new ComplexNumber(ComplexNumberForm.Rectangular, this.Value, 0.0);
    }



    @Override
    public Double getValue() {
        return this.Value;
    }

    @Override
    public void setValue(Double value) {
        this.Value = value;
    }

    @Override
    public String getName() {
        return this.Name;
    }

    @Override
    public String getType() {
        return "num";
    }
}
