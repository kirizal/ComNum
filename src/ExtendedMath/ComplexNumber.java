package ExtendedMath;
import java.lang.Math;

public class ComplexNumber {

	private final ComplexNumberForm initial_form;
	private double a_сoef;		//  \  Для алгебраической формы:
	private double b_coef;		//  /  z = a + b * i

	private double r_coef;		//  \  Для тригонометрической и показательной форм:
	private double fi_angl_deg;	//  |  z = r * (cos(fi) + i * sin(fi))
	private double fi_angl_rad;	//  /  z = r * exp(fi * i)



	public double Re() { return this.a_сoef; }
	public double Im() {return this.b_coef; }
	public double Arg_deg() { return this.fi_angl_deg; }
	public double Arg_rad() { return this.fi_angl_rad; }
	public double AbsValue() { return this.r_coef; }
	public ComplexNumber ComplexConjugate() { return new ComplexNumber(ComplexNumberForm.Rectangular, this.a_сoef, -this.b_coef); }



	public ComplexNumber(ComplexNumberForm f, double coef1, double coef2) {
		this.initial_form = f;
		switch (this.initial_form) {
			case Rectangular -> {
				this.a_сoef = coef1;
				this.b_coef = coef2;
				this.r_coef = Math.sqrt(this.a_сoef * this.a_сoef + this.b_coef * this.b_coef);

				// Определение угла fi в зависимости от четверти на комплексной плоскости
				// Первая и четвёртая четверти
				if (this.a_сoef > 0) {
					this.fi_angl_rad = Math.atan(this.b_coef / this.a_сoef);
				}
				// Вторая четверть
				else if (this.a_сoef < 0 && this.b_coef > 0) {
					this.fi_angl_rad = Math.PI + Math.atan(this.b_coef / this.a_сoef);
				}
				// Третья четверть
				else if (this.a_сoef < 0 && this.b_coef < 0) {
					this.fi_angl_rad = -Math.PI + Math.atan(this.b_coef / this.a_сoef);
				}
				// Положительная часть мнимой оси
				else if (this.a_сoef == 0 && this.b_coef > 0) {
					this.fi_angl_rad = Math.PI / 2.0;
				}
				// Отрицательная часть мнимой оси
				else if (this.a_сoef == 0 && this.b_coef < 0) {
					this.fi_angl_rad = 3.0 * Math.PI / 2.0;
				}
				// Положительная часть реальной оси
				else if (this.b_coef == 0 && this.a_сoef > 0) {
					this.fi_angl_rad = 0.0;
				}
				// Отрицательная часть реальной оси
				else if (this.b_coef == 0 && this.a_сoef < 0) {
					this.fi_angl_rad = Math.PI;
				}
				this.fi_angl_deg = Math.toDegrees(this.fi_angl_rad) % 360;
				// Поправка fi_angl_rad после удаления лишних периодов 2*PI*k
				this.fi_angl_rad = Math.toRadians(this.fi_angl_deg);
			}
			case Polar, Exponential -> {
				this.r_coef = coef1;
				this.fi_angl_deg = coef2 % 360;
				this.fi_angl_rad = Math.toRadians(this.fi_angl_deg);
				this.a_сoef = this.r_coef * Math.sin(this.fi_angl_rad);
				this.b_coef = this.r_coef * Math.cos(this.fi_angl_rad);
			}
		}
	}



	public String ToString() {
		return this.ToString(this.initial_form);
	}
	public String ToString(ComplexNumberForm form) {
		switch(form) {
			case Rectangular:
				if(this.Im() > 0)
					return String.format("%.3f + %.3f * i", this.a_сoef, this.b_coef);
				else
					return String.format("%.3f - %.3f * i", this.a_сoef, Math.abs(this.b_coef));

			case Polar:
				return String.format("%.3f * (cos(%.3f°) + i * sin(%.3f°))", r_coef, this.fi_angl_deg, this.fi_angl_deg);

			case Exponential:
				return String.format("%.3f * exp(i * %.3f°)", this.r_coef, this.fi_angl_deg);

			default:
				return "Complex number not defined";
		}
	}




	public static void printAllForms(ComplexNumber num)
	{
		try
		{
			System.out.printf("Rectangular:\t" + num.ToString(ComplexNumberForm.Rectangular) + "\n");
			System.out.printf("Polar       \t" + num.ToString(ComplexNumberForm.Polar) + "\n");
			System.out.printf("Exponential:\t" + num.ToString(ComplexNumberForm.Exponential) + "\n\n");
		}
		catch(Exception ex)
		{
			System.out.println("\n" + ex.getMessage());
		}
	}
}