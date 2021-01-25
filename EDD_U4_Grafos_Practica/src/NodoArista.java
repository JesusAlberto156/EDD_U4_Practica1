/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chuy4
 */
class NodoArista {
    NodoGrafo direccion;
    NodoArista ant, sig;
    
    public NodoArista(NodoGrafo d){
        direccion = d;
        ant = sig = null;
    }

}
