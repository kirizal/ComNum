package ExtendedMath;

public class ComplexNumberOperation {
    private ComplexNumberOperation() {}




    public static ComplexNumber getComplexConjugate(ComplexNumber num) {
        return new ComplexNumber(ComplexNumberForm.Rectangular, num.Re(), -num.Im());
    }




    public static ComplexNumber add(ComplexNumber num1, ComplexNumber num2) {
        return new ComplexNumber(ComplexNumberForm.Rectangular,
                num1.Re() + num2.Re(),
                num1.Im() + num2.Im());
    }
    public static ComplexNumber sub(ComplexNumber num1, ComplexNumber num2) {
        return new ComplexNumber(ComplexNumberForm.Rectangular,
                num1.Re() - num2.Re(),
                num1.Im() - num2.Im());
    }
    public static ComplexNumber mul(ComplexNumber num1, ComplexNumber num2) {
        return new ComplexNumber(ComplexNumberForm.Rectangular,
                num1.Re() * num2.Re() - num1.Im() * num2.Im(),
                num1.Re() * num2.Im() + num2.Re() * num1.Im());
    }
    public static ComplexNumber div(ComplexNumber num1, ComplexNumber num2) throws Exception {
        if(num2.Re() == 0 && num2.Im() == 0) {
            throw new Exception("The divisor must have a non-zero real part or a non-zero\n\t\t imaginary part.\n");
        }
        else {
            return new ComplexNumber(ComplexNumberForm.Rectangular,
                    (num1.Re() * num2.Re() + num1.Im() * num2.Im())/(num2.Re() * num2.Re() + num2.Im() * num2.Im()),
                    (num1.Im() * num2.Re() - num1.Re() * num2.Im())/(num2.Re() * num2.Re() + num2.Im() * num2.Im()));
        }
    }




    public static ComplexNumber sin(ComplexNumber num) {
        return new ComplexNumber(ComplexNumberForm.Rectangular,
                Math.sin(num.Re()) * HyperbolicFunctions.ch(num.Im()),
                Math.cos(num.Re()) * HyperbolicFunctions.sh(num.Im()));
    }
    public static ComplexNumber cos(ComplexNumber num) {
        return new ComplexNumber(ComplexNumberForm.Rectangular,
                Math.cos(num.Re()) * HyperbolicFunctions.ch(num.Im()),
                -Math.sin(num.Re()) * HyperbolicFunctions.sh(num.Im()));
    }
    public static ComplexNumber tg(ComplexNumber num) throws Exception {
        return ComplexNumberOperation.div(ComplexNumberOperation.sin(num),
                                        ComplexNumberOperation.cos(num));
    }
    public static ComplexNumber ctg(ComplexNumber num) throws Exception {
        return ComplexNumberOperation.div(ComplexNumberOperation.cos(num),
                                        ComplexNumberOperation.sin(num));
    }




    // Возведение комплексного числа в n-степень по формуле Муавра
    public static ComplexNumber pown(ComplexNumber num, int n) throws Exception {
        if(n < 2) 	// Показатель степени должен быть натуральным числом
            throw new Exception("The exponent of a complex number must be a natural number greater than 2.");


        return new ComplexNumber(ComplexNumberForm.Polar,
                Math.pow(num.AbsValue(), n),
                num.Arg_deg() * n);
    }
    // Извлечение корня n-степени из комплексного числа
    // Результат - несколько комплексных чисел
    public static ComplexNumber[] sqrtn(ComplexNumber num, int n) throws Exception {
        if(n < 2) 	// Показатель степени должен быть натуральным числом
            throw new Exception("The exponent of the root of a complex number must be a natural number greater than 2.");


        // Для всех n-1  комплексных чисел, являющихся ответами
        ComplexNumber[] result = new ComplexNumber[n];

        for(int k = 1; k <= n; k++)
        {
            result[k - 1] = new ComplexNumber(ComplexNumberForm.Polar,
                    Math.pow(num.AbsValue(), 1.0/n),
                    (num.Arg_deg() + 360 * k)/n);
        }
        return result;
    }
}
