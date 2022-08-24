package ConsoleUtils;
import java.lang.StringBuilder;


public class ConProgressBar extends ConControl {

	private static final int MAX_PERCENTAGE = 100;
	private static final int MIN_PERCENTAGE = 0;
	private static final char Completed_block = '#';
	private static final char Empty_block = '.';
	private int Percentage;


	public int getPercentage() { return this.Percentage; }
	public void setPercentage(int perc) {
		if(perc > MAX_PERCENTAGE) this.Percentage = MAX_PERCENTAGE;
		else if(perc < MIN_PERCENTAGE) this.Percentage = MIN_PERCENTAGE;
		else this.Percentage = perc;
	}




	public ConProgressBar(String name, int width, int startPercentage) {
		super(name, width);
		if(startPercentage > MAX_PERCENTAGE) {
			this.Percentage = MAX_PERCENTAGE;
		}
		else if(startPercentage < MIN_PERCENTAGE) {
			this.Percentage = MIN_PERCENTAGE;
		}
		else {
			this.Percentage = startPercentage;
		}
	}
	public ConProgressBar(String name, int width, int startPercentage,
						  ConColor background, ConColor foreground) {
		super(name, width, background, foreground);
		if(startPercentage > MAX_PERCENTAGE) {
			this.Percentage = MAX_PERCENTAGE;
		}
		else if(startPercentage < MIN_PERCENTAGE) {
			this.Percentage = MIN_PERCENTAGE;
		}
		else {
			this.Percentage = startPercentage;
		}
	}




	@Override
	public void printControl() {
		int freeBlocks;
		int blockCount;
		String output;
		StringBuilder sb;

		if(this.getWidth() >= 15) {
			output = String.format("%3d%% [%s%s",
					this.Percentage,
					this.getBackgroundColor().getANSI_Color(),
					this.getForegroundColor().getANSI_Color());
			sb = new StringBuilder(output);
			freeBlocks = this.getWidth() - 1 - 7;		// 7 - кол-во символов на вывод процентажа
														// загрузки и границ ProgressBar'a
			blockCount = (int)((double)this.Percentage/(double)MAX_PERCENTAGE * (double)freeBlocks);

			// Добавление символов, характеризующих загрузку
			for(int i = 0; i <= blockCount; i++) {
				sb.insert(sb.length(), Completed_block);
			}
			// Добавление символов пробела (для обозначения незаполненности ProgressBar'a)
			for(int j = 7 + blockCount; j < this.getWidth() - 1; j++) {
				sb.insert(sb.length(), Empty_block);
			}
			sb.insert(sb.length(), ConColor.ANSI_RESET.getANSI_Color() + "]");
		}
		else {
			output = String.format("%3d%%%s",
					this.Percentage,
					ConColor.ANSI_RESET.getANSI_Color());
			sb = new StringBuilder(output);
		}


		System.out.print(sb);
	}


	@Override
	public void printControlWithoutColors(){
		int freeBlocks;
		int blockCount;
		String output;
		StringBuilder sb;

		if(this.getWidth() >= 15) {
			output = String.format("%3d%% [", this.Percentage);
			sb = new StringBuilder(output);
			freeBlocks = this.getWidth() - 1 - 7;		// 7 - кол-во символов на вывод процентажа
			// загрузки и границ ProgressBar'a
			blockCount = (int)((double)this.Percentage/(double)MAX_PERCENTAGE * (double)freeBlocks);

			// Добавление символов, характеризующих загрузку
			for(int i = 0; i <= blockCount; i++) {
				sb.insert(sb.length(), Completed_block);
			}
			// Добавление символов пробела (для обозначения незаполненности ProgressBar'a)
			for(int j = 7 + blockCount; j < this.getWidth() - 1; j++) {
				sb.insert(sb.length(), Empty_block);
			}
			sb.insert(sb.length(), "]");
		}
		else {
			output = String.format("%3d%%", this.Percentage);
			sb = new StringBuilder(output);
		}


		System.out.print(sb);
	}
}