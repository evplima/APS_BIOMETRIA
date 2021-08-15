/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import com.machinezoo.sourceafis.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *
 * @author evert
 */
public class AutenticacaoBiometrica {
    
    public static boolean autenticar(byte[] imagemBanco, byte[] imagemAutenticacao) throws IOException{
    FingerprintTemplate probe = new FingerprintTemplate(
    new FingerprintImage()
        .dpi(500)
        .decode(imagemBanco));
FingerprintTemplate candidate = new FingerprintTemplate(
    new FingerprintImage()
        .dpi(500)
        .decode(imagemAutenticacao));
double score = new FingerprintMatcher()
    .index(probe)
    .match(candidate);
boolean matches = score >= 40;
        if(score>=40){
            return true;
        }else{
            return false;
        }  
    }
    
    
     public static boolean autenticar2(String caminho) throws IOException{
    FingerprintTemplate probe = new FingerprintTemplate(
    new FingerprintImage()
        .dpi(500)
        .decode(Files.readAllBytes(Paths.get(caminho))));
FingerprintTemplate candidate = new FingerprintTemplate(
    new FingerprintImage()
        .dpi(500)
        .decode(Files.readAllBytes(Paths.get(caminho))));
double score = new FingerprintMatcher()
    .index(probe)
    .match(candidate);
boolean matches = score >= 40;
        if(score>=40){
            return true;
        }else{
            return false;
        }  
    }
}
