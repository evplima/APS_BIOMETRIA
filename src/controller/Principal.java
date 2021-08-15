/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.io.IOException;
import model.AutenticacaoBiometrica;
import view.TelaLogin;
/**
 *
 * @author evert
 */
public class Principal {
    
    
    public static void main(String args[]) throws IOException{
    //   System.out.println(AutenticacaoBiometrica.autenticar2("C:\\Users\\evert\\Desktop\\1.png"));
        new TelaLogin().setVisible(true);
    }
}
