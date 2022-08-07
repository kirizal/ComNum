package ConsoleUtils;
import ConsoleUtils.ConColor;

public abstract class ConControl
{
	private static final int MAX_WIDTH = 80;
	private static final int MIN_WIDTH = 10;



	private int Width;
	private String Name;
	private ConColor BackgroundColor;
	private ConColor ForegroundColor;




	// -- Гетеры и сетеры --
	public String getName() { return this.Name; }
	public int getWidth() { return this.Width; }
	public ConColor getBackgroundColor() { return this.BackgroundColor; }
	public String getBackgroundColorANSI() { return this.BackgroundColor.getANSI_Color(); }
	public ConColor getForegroundColor() { return this.ForegroundColor; }
	public String getForegroundColorANSI() { return this.ForegroundColor.getANSI_Color(); }


	public void setName(String name) { this.Name = name; }
	public void setWidth(int width) throws Exception {
		if(width > MAX_WIDTH || width < MIN_WIDTH) {
			throw new Exception(String.format("The size of the control must be in the interval [%d; %d].",
					MIN_WIDTH, MAX_WIDTH));
		}
		else {
			this.Width = width;
		}
	}
	public void setBackgroundColor(ConColor ansi_color) throws Exception {
		if(ConColor.ColorCheck(ansi_color.getANSI_Color()))
			this.BackgroundColor = ansi_color;
		else
			throw new Exception("Unknown ansi_color, try using ConColor enumeration colors.");
	}
	public void setForegroundColor(ConColor ansi_color) throws Exception {
		if(ConColor.ColorCheck(ansi_color.getANSI_Color()))
			this.ForegroundColor = ansi_color;
		else
			throw new Exception("Unknown ansi_color, try using ConColor enumeration colors.");
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
	public ConControl(String name, int width, ConColor background, ConColor foreground)
	{
		this.Name = name;

		if (width > MAX_WIDTH) this.Width = MAX_WIDTH;
		else if (width < MIN_WIDTH) this.Width = MIN_WIDTH;
		else this.Width = width;

		this.BackgroundColor = background;
		this.ForegroundColor = foreground;
	}

	
	public abstract void printControl();
}