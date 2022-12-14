package ConsoleUtils;

public enum ConColor {
	ANSI_RESET("\u001B[0m"),

    ANSI_FOREGROUND_BLACK("\u001B[30m"),
    ANSI_FOREGROUND_RED("\u001B[31m"),
    ANSI_FOREGROUND_GREEN("\u001B[32m"),
    ANSI_FOREGROUND_YELLOW("\u001B[33m"),
    ANSI_FOREGROUND_BLUE("\u001B[34m"),
    ANSI_FOREGROUND_PURPLE("\u001B[35m"),
    ANSI_FOREGROUND_CYAN("\u001B[36m"),
    ANSI_FOREGROUND_WHITE("\u001B[37m"),

    ANSI_BACKGROUND_BLACK("\u001B[40m"),
	ANSI_BACKGROUND_RED("\u001B[41m"),
	ANSI_BACKGROUND_GREEN("\u001B[42m"),
	ANSI_BACKGROUND_YELLOW("\u001B[43m"),
	ANSI_BACKGROUND_BLUE("\u001B[44m"),
	ANSI_BACKGROUND_PURPLE("\u001B[45m"),
	ANSI_BACKGROUND_CYAN("\u001B[46m"),
	ANSI_BACKGROUND_WHITE("\u001B[47m");



	private String ANSI_Color;
	public String getANSI_Color() {
		return this.ANSI_Color;
	}
	public void setANSI_Color(String ansi_color) {
			this.ANSI_Color = ansi_color;
	}
	ConColor(String ansi_color) {
		this.ANSI_Color = ansi_color;
	}
}