package ConsoleUtils;
import ConsoleUtils.ConColor;

public abstract class ConControl
{
	private static final int MAX_WIDTH = 80;
	private static final int MIN_WIDTH = 10;

	private int Width;
	private String Name;
	private String BackgroundColor;
	private String ForegroundColor;


	// -- Гетеры и сетеры --
	public final String getName() { return this.Name; }
	public final void setName(String name) { this.Name = name; }

	public final int getWidth() { return this.Width; }
	public final void setWidth(int width) throws Exception
	{
		if(width > MAX_WIDTH || width < MIN_WIDTH) 
			throw new Exception(String.format("The size of the control must be in the interval [%d; %d]", MIN_WIDTH, MAX_WIDTH));
		else 
			this.Width = width; 
	}

	public final String getBackgroundColor() { return this.BackgroundColor; }
	public final void setBackgroundColor(String ansi_color) throws Exception
	{ 
		if(ConColor.ColorCheck(ansi_color))
			this.BackgroundColor = ansi_color;
		else
			throw new Exception("Unknown color, try to use ConColor class constants");
	}

	public final String getForegroundColor() { return this.ForegroundColor; }
	public final void setForegroundColor(String ansi_color) throws Exception
	{ 
		if(ConColor.ColorCheck(ansi_color))
			this.ForegroundColor = ansi_color; 
		else
			throw new Exception("Unknown color, try to use ConColor class constants");
	}




	public ConControl(String name, int width)
	{
		this.Name = name;

		if (width > MAX_WIDTH) this.Width = MAX_WIDTH;
		else if (width < MIN_WIDTH) this.Width = MIN_WIDTH;
		else this.Width = width;

		this.BackgroundColor = ConColor.ANSI_RESET;
		this.ForegroundColor = ConColor.ANSI_RESET;
	}

	
	public abstract void printControl();
}