import ConsoleUtils.ConCursorMove;
import ConsoleUtils.ConProgressBar;
import ConsoleUtils.ConColor;
import ProgramFunctionality.FileManager;



public class Program {
	public static void main(String[] args) {
		try {
			ConCursorMove.Right(20);
			System.out.println(args[0]);
			FileManager fm = new FileManager(args[0]);
			fm.getDataFromFile();



            // Пример использования класса ConProgressBar
            ConProgressBar prBar = new ConProgressBar("myProgressBar", 10, 37);
            prBar.setBackgroundColor(ConColor.ANSI_BACKGROUND_WHITE);
            prBar.setForegroundColor(ConColor.ANSI_FOREGROUND_PURPLE);
            System.out.println("\nПример #1:");
            prBar.printControl();
            System.out.println("\nПример #2:");
            prBar = new ConProgressBar("myProgressBar", 30, 73);
            prBar.setBackgroundColor(ConColor.ANSI_BACKGROUND_BLACK);
            prBar.setForegroundColor(ConColor.ANSI_FOREGROUND_YELLOW);
            prBar.printControl();
            System.out.println("\n");


            // Реализация худшего случая для сортировки пузырьком 
            // для тестирования класса ConProgressBar
			ConProgressBar pb = new ConProgressBar("myProgressBar", 60, 0);
			pb.setBackgroundColor(ConColor.ANSI_BACKGROUND_BLACK);
			pb.setForegroundColor(ConColor.ANSI_FOREGROUND_GREEN);
			int perc = 0;

			int elemQuantity = 10000;
			int [] arr = new int[elemQuantity];
        	boolean isSorted = false;
        	int temp;


        	System.out.printf("\nКоличество элементов в массиве: %d\n", elemQuantity);
        	System.out.println("Прогресс пузырьковой сортировки:");

        	// Создание худщего случая для пузырьковой сортировки
        	temp = elemQuantity;
        	for(int i = 0; i < arr.length; i++) {
        		arr[i] = temp--;
        	}

        	// Сортировка пузырьком
        	while(!isSorted) {
       		    isSorted = true;
      	 	    for (int i = 0; i < arr.length-1; i++) {
                	if(arr[i] > arr[i+1]) {
                  		isSorted = false;
 
                    	temp = arr[i];
                    	arr[i] = arr[i+1];
                    	arr[i+1] = temp;


                	}
            	}
            	// Настройка ConProgressBar'a и его вывод в консоль
            	perc++;
                pb.setPercentage((int)((float)perc/(float)elemQuantity*100.0f));
                System.out.print("\r");
                pb.printControl();
        	}
		}
		catch(Exception ex) {
			System.out.println("\n" + ex.getMessage());
		}
	}
}