package ConsoleUtils;

public class ConColor
{
	public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_FOREGROUND_BLACK = "\u001B[30m";
    public static final String ANSI_FOREGROUND_RED = "\u001B[31m";
    public static final String ANSI_FOREGROUND_GREEN = "\u001B[32m";
    public static final String ANSI_FOREGROUND_YELLOW = "\u001B[33m";
    public static final String ANSI_FOREGROUND_BLUE = "\u001B[34m";
    public static final String ANSI_FOREGROUND_PURPLE = "\u001B[35m";
    public static final String ANSI_FOREGROUND_CYAN = "\u001B[36m";
    public static final String ANSI_FOREGROUND_WHITE = "\u001B[37m";

    public static final String ANSI_BACKGROUND_BLACK = "\u001B[40m";
	public static final String ANSI_BACKGROUND_RED = "\u001B[41m";
	public static final String ANSI_BACKGROUND_GREEN = "\u001B[42m";
	public static final String ANSI_BACKGROUND_YELLOW = "\u001B[43m";
	public static final String ANSI_BACKGROUND_BLUE = "\u001B[44m";
	public static final String ANSI_BACKGROUND_PURPLE = "\u001B[45m";
	public static final String ANSI_BACKGROUND_CYAN = "\u001B[46m";
	public static final String ANSI_BACKGROUND_WHITE = "\u001B[47m";




	public static boolean ColorCheck(String ansi_color)
	{
		return ansi_color == ANSI_RESET ||
				ansi_color == ANSI_FOREGROUND_BLACK ||
				ansi_color == ANSI_FOREGROUND_RED ||
				ansi_color == ANSI_FOREGROUND_GREEN ||
				ansi_color == ANSI_FOREGROUND_YELLOW ||
				ansi_color == ANSI_FOREGROUND_BLUE ||
				ansi_color == ANSI_FOREGROUND_PURPLE ||
				ansi_color == ANSI_FOREGROUND_CYAN ||
				ansi_color == ANSI_FOREGROUND_WHITE ||
				ansi_color == ANSI_BACKGROUND_BLACK ||
				ansi_color == ANSI_BACKGROUND_RED ||
				ansi_color == ANSI_BACKGROUND_GREEN ||
				ansi_color == ANSI_BACKGROUND_YELLOW ||
				ansi_color == ANSI_BACKGROUND_BLUE ||
				ansi_color == ANSI_BACKGROUND_PURPLE ||
				ansi_color == ANSI_BACKGROUND_CYAN ||
				ansi_color == ANSI_BACKGROUND_WHITE;
	}
}