package ConsoleUtils;
import java.lang.StringBuilder;


public class ConCursorMove {

    private static final String ANSI_CURSOR_LEFT = "\u001B[D";
    private static final String ANSI_CURSOR_RIGHT = "\u001B[C";
    private static final String ANSI_CURSOR_UP = "\u001B[A";
    private static final String ANSI_CURSOR_DOWN = "\u001B[B";



    public static void Left(int steps){
        StringBuilder sb = new StringBuilder(ANSI_CURSOR_LEFT);
        sb.insert(2, steps);
        System.out.print(sb);
    }

    public static void Right(int steps){
        StringBuilder sb = new StringBuilder(ANSI_CURSOR_RIGHT);
        sb.insert(2, steps);
        System.out.print(sb);
    }

    public static void Up(int steps){
        StringBuilder sb = new StringBuilder(ANSI_CURSOR_UP);
        sb.insert(2, steps);
        System.out.print(sb);
    }

    public static void Down(int steps){
        StringBuilder sb = new StringBuilder(ANSI_CURSOR_DOWN);
        sb.insert(2, steps);
        System.out.print(sb);
    }
}