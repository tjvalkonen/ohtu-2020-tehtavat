package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KonsoliIO implements IO {
    private static Scanner scanner;
    
    public KonsoliIO() {
        scanner = new Scanner(System.in);
    }
    
    public String nextLine() {
        return scanner.nextLine();
    }
    
    public void println(String m) {
        System.out.println(m);
    }
}
