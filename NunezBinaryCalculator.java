/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.nunezbinarycalculator;

/**
 *
 * @author kitty
 */

import java.awt.EventQueue;
public class NunezBinaryCalculator {
    
    
public static void main(String[] args) {
    BinaryCalculator calc = new BinaryCalculator();
     EventQueue.invokeLater(() -> {calc.setVisible(true);});
    }
}
