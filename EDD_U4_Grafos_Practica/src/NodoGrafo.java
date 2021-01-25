/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chuy4
 */
public class NodoGrafo {
    char valor;
    NodoGrafo ant, sig;
    NodoArista aristas;
    
    public NodoGrafo(char v){
        valor = v;
        ant = sig = null;
        aristas = null;
    }
}
