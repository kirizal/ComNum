import ConsoleUtils.ConCursorMove;
import ConsoleUtils.ConProgressBar;
import ConsoleUtils.ConColor;
import ExtendedMath.*;


public class Program {
	public static void main(String[] args) {
		try {
			ConCursorMove.Right(20);
			System.out.println("smth");
			ConCursorMove.Right(40);
			System.out.println("smth");


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




			// Тестирование комплексных чисел
			// Решение задач из Н.В.Деменева "Комплексные числа"
			// -------------------------------------------------
			System.out.println("\n\nН.В.Деменева \"Комплексные числа\", стр.55, Пример №2.12 под пунктом 1:");
			ComplexNumber num1 = new ComplexNumber(ComplexNumberForm.Rectangular, -2, 2);
			System.out.println("num1:\t " + num1.ToString(ComplexNumberForm.Polar));
			System.out.printf("pow9:\t" + ComplexNumberOperation.pown(num1, 2).ToString(ComplexNumberForm.Polar) + "\n\n\n");



			System.out.println("Н.В.Деменева \"Комплексные числа\", стр.57, Пример №2.13 под пунктом 3:");
			ComplexNumber num2 = new ComplexNumber(ComplexNumberForm.Rectangular, -3, -Math.sqrt(3.0));
			ComplexNumber[] temp_sqrt = ComplexNumberOperation.sqrtn(num2, 2);
			System.out.println("num2: " + num2.ToString(ComplexNumberForm.Rectangular));
			System.out.printf("sqrt7:\n");
			for(ComplexNumber cn: temp_sqrt)
			{
				System.out.printf("\t" + cn.ToString(ComplexNumberForm.Polar) + "\n");
			}
			System.out.println("\n");



			System.out.println("Н.В.Деменева \"Комплексные числа\", стр. 37, Пример №2.7 под пунктами 1 и 8:");
			ComplexNumber num3 = new ComplexNumber(ComplexNumberForm.Rectangular, -1, -Math.sqrt(3.0));
			System.out.println("num3: " + num3.ToString(ComplexNumberForm.Rectangular));
			ComplexNumber.printAllForms(num3);
			ComplexNumber num4 = new ComplexNumber(ComplexNumberForm.Rectangular, 0, 0.5);
			System.out.println("num4: " + num4.ToString(ComplexNumberForm.Rectangular));
			ComplexNumber.printAllForms(num4);
			System.out.println();



			System.out.println("Н.В.Деменева \"Комплексные числа\", стр. 44, Пример №2.8 под пунктами 1, 8, 10:");
			ComplexNumber num5 = new ComplexNumber(ComplexNumberForm.Rectangular, 1, 0.0);
			System.out.println("num5: " + num5.ToString(ComplexNumberForm.Rectangular));
			ComplexNumber.printAllForms(num5);
			ComplexNumber num6 = new ComplexNumber(ComplexNumberForm.Rectangular, 0, -5.0);
			System.out.println("num6: " + num6.ToString(ComplexNumberForm.Rectangular));
			ComplexNumber.printAllForms(num6);
			ComplexNumber num7 = new ComplexNumber(ComplexNumberForm.Rectangular, -3, -2);
			System.out.println("num7: " + num7.ToString(ComplexNumberForm.Rectangular));
			ComplexNumber.printAllForms(num7);
			System.out.println();



			System.out.println("Н.В.Деменева \"Комплексные числа\", стр. 49, Пример №2.9 под пунктом 2:");
			ComplexNumber num8 = new ComplexNumber(ComplexNumberForm.Rectangular, -5.0, 0.0);
			System.out.println("num8: " + num8.ToString(ComplexNumberForm.Rectangular));
			ComplexNumber num9 = new ComplexNumber(ComplexNumberForm.Rectangular, 3.0, -7.0);
			System.out.println("num9: " + num9.ToString(ComplexNumberForm.Rectangular));
			System.out.println("sum:\t" + ComplexNumberOperation.sum(num8, num9).ToString(ComplexNumberForm.Rectangular));
			System.out.println("sub:\t" + ComplexNumberOperation.sub(num8, num9).ToString(ComplexNumberForm.Rectangular));
			System.out.println("mul:\t" + ComplexNumberOperation.mul(num8, num9).ToString(ComplexNumberForm.Rectangular));
			System.out.println("div:\t" + ComplexNumberOperation.div(num8, num9).ToString(ComplexNumberForm.Rectangular));
			System.out.println("\n");



			System.out.println("Тестирование sin, cos, tg, ctg с помощью сайта:");
			System.out.println("https://programforyou.ru/calculators/complex-calculator");
			ComplexNumber num10 = new ComplexNumber(ComplexNumberForm.Rectangular, 1.0, -2.0);
			System.out.println("num10: " + num10.ToString(ComplexNumberForm.Rectangular));
			System.out.println("sin(num10):\t" + ComplexNumberOperation.sin(num10).ToString(ComplexNumberForm.Rectangular));
			System.out.println("cos(num10):\t" + ComplexNumberOperation.cos(num10).ToString(ComplexNumberForm.Rectangular));
			System.out.println("tg(num10): \t" + ComplexNumberOperation.tg(num10).ToString(ComplexNumberForm.Rectangular));
			System.out.println("ctg(num10):\t" + ComplexNumberOperation.ctg(num10).ToString(ComplexNumberForm.Rectangular));
			System.out.println();
			ComplexNumber num11 = new ComplexNumber(ComplexNumberForm.Rectangular, 0.0, -4.0);
			System.out.println("num11: " + num11.ToString(ComplexNumberForm.Rectangular));
			System.out.println("sin(num11):\t" + ComplexNumberOperation.sin(num11).ToString(ComplexNumberForm.Rectangular));
			System.out.println("cos(num11):\t" + ComplexNumberOperation.cos(num11).ToString(ComplexNumberForm.Rectangular));
			System.out.println("tg(num11): \t" + ComplexNumberOperation.tg(num11).ToString(ComplexNumberForm.Rectangular));
			System.out.println("ctg(num11):\t" + ComplexNumberOperation.ctg(num11).ToString(ComplexNumberForm.Rectangular));
			System.out.println("\n");
		}
		catch(Exception ex) {
			System.out.println("\n" + ex.getMessage());
		}
	}
}