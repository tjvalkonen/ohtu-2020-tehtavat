/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.Scanner;

/**
 *
 * @author Tomas
 */

public class KonsoliIO implements IO {
    private static Scanner scanner;
    
    public KonsoliIO() {
        scanner = new Scanner(System.in);
    }
    
    public String nextLine() {
        return scanner.nextLine();
    }
    
    public void print(String m) {
        System.out.println(m);
    }
}
