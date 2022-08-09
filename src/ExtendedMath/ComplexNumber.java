package ExtendedMath;
import java.lang.Math;

public class ComplexNumber {

	private final ComplexNumberForm initial_form;
	private double a_coef;		//  \  Для алгебраической формы:
	private double b_coef;		//  /  z = a + b * i

	private double r_coef;		//  \  Для тригонометрической и показательной форм:
	private double fi_angl_deg;	//  |  z = r * (cos(fi) + i * sin(fi))
	private double fi_angl_rad;	//  /  z = r * exp(fi * i)



	public double Re() { return this.a_coef; }
	public double Im() {return this.b_coef; }
	public double Arg_deg() { return this.fi_angl_deg; }
	public double Arg_rad() { return this.fi_angl_rad; }
	public double AbsValue() { return this.r_coef; }
	public ComplexNumber ComplexConjugate() { return new ComplexNumber(ComplexNumberForm.Rectangular, this.a_coef, -this.b_coef); }



	public ComplexNumber(ComplexNumberForm f, double coef1, double coef2) {
		this.initial_form = f;
		switch (this.initial_form) {
			case Rectangular -> {
				this.a_coef = coef1;
				this.b_coef = coef2;
				this.r_coef = Math.sqrt(this.a_coef * this.a_coef + this.b_coef * this.b_coef);
				this.fi_angl_rad = CalculateAngleInRadians(coef1, coef2);
				this.fi_angl_deg = Math.toDegrees(this.fi_angl_rad);
			}
			case Polar, Exponential -> {
				this.r_coef = coef1;
				this.fi_angl_deg = coef2 % 360;
				this.fi_angl_rad = Math.toRadians(this.fi_angl_deg);
				this.a_coef = this.r_coef * Math.cos(this.fi_angl_rad);
				this.b_coef = this.r_coef * Math.sin(this.fi_angl_rad);
			}
		}
	}



	private static Double CalculateAngleInRadians(double coef_re, double coef_im){
		double result;

		// Определение угла fi в зависимости от четверти на комплексной плоскости
		// Первая и четвёртая четверти
		if (coef_re > 0) {
			result = Math.atan(coef_im / coef_re);
		}
		// Вторая четверть
		else if (coef_re < 0 && coef_im > 0) {
			result = Math.PI + Math.atan(coef_im / coef_re);
		}
		// Третья четверть
		else if (coef_re < 0 && coef_im < 0) {
			result = -Math.PI + Math.atan(coef_im / coef_re);
		}
		// Положительная часть мнимой оси
		else if (coef_re == 0 && coef_im > 0) {
			result = Math.PI / 2.0;
		}
		// Отрицательная часть мнимой оси
		else if (coef_re == 0 && coef_im < 0) {
			result = 3.0 * Math.PI / 2.0;
		}
		// Положительная часть реальной оси
		else if (coef_im == 0 && coef_re > 0) {
			result = 0.0;
		}
		// Отрицательная часть реальной оси
		else {
			result = Math.PI;
		}
		return result;
	}



	public String ToString() {
		return this.ToString(this.initial_form);
	}
	public String ToString(ComplexNumberForm form) {
		switch(form) {
			case Rectangular:
				if(this.Im() >= 0)
					return String.format("%.3f + %.3f * i", this.a_coef, this.b_coef);
				else
					return String.format("%.3f - %.3f * i", this.a_coef, Math.abs(this.b_coef));

			case Polar:
				return String.format("%.3f * (cos(%.3f°) + i * sin(%.3f°))", r_coef, this.fi_angl_deg, this.fi_angl_deg);

			case Exponential:
				return String.format("%.3f * exp(i * %.3f°)", this.r_coef, this.fi_angl_deg);

			default:
				return "Complex number not defined";
		}
	}




	public static String getAllForms(ComplexNumber num)
	{
		StringBuilder sb = new StringBuilder("");
		sb.insert(sb.length(), String.format("Rectangular:\t" + num.ToString(ComplexNumberForm.Rectangular) + "\n"));
		sb.insert(sb.length(), String.format("Polar       \t" + num.ToString(ComplexNumberForm.Polar) + "\n"));
		sb.insert(sb.length(), String.format("Exponential:\t" + num.ToString(ComplexNumberForm.Exponential) + "\n\n"));
		return sb.toString();
	}



	@Override
	public boolean equals(Object obj){
		if(obj instanceof ComplexNumber){
			ComplexNumber temp = (ComplexNumber)obj;
			return this.fi_angl_deg == temp.fi_angl_deg &&
					this.r_coef == temp.r_coef;
		}
		else if(obj instanceof Double){
			Double temp = (Double) obj;
			return this.a_coef == temp && this.b_coef == 0;
		}
		else{
			return false;
		}
	}

}