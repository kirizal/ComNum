package ExtendedMath;

public class HyperbolicFunctions
{
	// Гиперболический синус
	public static double sh(double rad)
	{
		return (Math.exp(rad) - Math.exp(-rad))/2;
	}

	// Гиперболический косинус
	public static double ch(double rad)
	{
		return (Math.exp(rad) + Math.exp(-rad))/2;
	}

	// Гиперболический тангенс
	public static double th(double rad)
	{
		// ch(rad) не может быть равен нулю => исключение не нужно
		return sh(rad)/ch(rad);
	}

	// Гиперболический котангенс
	public static double сth(double rad) throws Exception
	{
		if(sh(rad) == 0)
			throw new Exception("Received division by zero due to argument: rad = 0 in cth(rad).");

		return ch(rad)/sh(rad);
	}
}