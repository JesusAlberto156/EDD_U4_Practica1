/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chuy4
 */
public class Grafo {
    NodoGrafo ini, fin;
    
    public Grafo(){
        ini = fin = null;
    }
    
    public boolean insertarNodo(char v){
        NodoGrafo nuevo = new NodoGrafo(v);
        
        if(nuevo == null){
            return false;
        }
        
        if(ini == null && fin == null){
            ini = fin = nuevo;
            return true;
        }
        
        fin.sig = nuevo;
        nuevo.ant = fin;
        fin = nuevo;
        
        return true;
    }
    
    public boolean insertarArista(char orig,char dest){
        NodoGrafo origen = buscarNodoGrafo(orig);
        
        if(origen == null){
            return false;
        }
        
        NodoGrafo destino = buscarNodoGrafo(dest);
        
        if(destino == null){
            return false;
        }
        
        NodoArista temp = new NodoArista(destino);
        
        if(temp ==  null){
            return false;
        }
        
        if(origen.aristas == null){
            origen.aristas = temp;
            return true;
        }
        
        //Este ciclo pongo a t2 en aristas (es decir) al inicio de la lista de aristas
        NodoArista t2 = origen.aristas;
        
        //Este ciclo avanza a t2, hasta que llega al ultimo nodo de lista aristas
        while(t2.sig != null){
            t2 = t2.sig;
        }
        
        t2.sig = temp;
        temp.ant = t2;
        
        return true;
    }

    private NodoGrafo buscarNodoGrafo(char valorBuscado) {
        NodoGrafo temp = ini;
        
        do{
            if(temp.valor == valorBuscado){
                return temp;
            }
            temp = temp.sig;
        }while(temp != null);
        
        return temp;
    }
    
    public boolean eliminarArista(char orig, char dest){
        NodoGrafo origen = buscarNodoGrafo(orig);
        
        if(origen == null){
            return false;
        }
        
        if(origen.aristas == null){
            return false;
        }
        
        NodoArista temp = origen.aristas;
        
        do{
            if(temp.direccion.valor == dest){
                if(temp == origen.aristas){
                    //revisando si la arista A ELIMINAR esta como primer nodo
                    origen.aristas = temp.sig;
                    temp.direccion = null;
                    temp.sig = null;
                    origen.aristas.ant = null;
                    return true;
                }else{
                    //Revisar si la arista a ELIMINAR esta como ULTIMO nodo
                    if(temp.sig == null){
                        temp.ant.sig = null;
                        temp.direccion = null;
                        temp.ant = null;
                        return true;
                    }
                    //desconectar la arista a eliminar estando en medio;
                    temp.ant.sig = temp.sig; //NODO SE ARRIBA DE TEMP, es decir, el puntero SIG de nodo ARRIBA (temp.ant) lo enlazo con nodo de abajo (temp.sig)
                    temp.sig.ant = temp.ant; //NODO DEBAJO DE TEMP, es decir, el puntero ANT de nodo ABAJO ( temp.sig) lo enlazo con nodo ARRIBA (temp.ant)
                    temp.sig = temp.ant = null;
                    temp.direccion = null;
                    return true;
                }
            }
            temp = temp.sig;
        }while(temp != null);
        
        return false;
    }
    
    public boolean eliminarNodo(char v){
        if(ini == null && fin == null){
            return false;
        }
        
        NodoGrafo nodoAEliminar = buscarNodoGrafo(v);
        if(nodoAEliminar == null){
            return false;
        }
        //VERIFICAR SI ES ISLA EL NODO A ELIMINAR
        //caso 1 (que el no tenga aristas)
        if(nodoAEliminar.aristas != null){
            return false;
        }
        //caso 2 (que otro nodo no tenga aristas a nodo_Eliminar)
        for(NodoGrafo temp = ini; temp != null; temp = temp.sig){
            if(encontarAristas(temp, nodoAEliminar) == true){
                return false;
            }
        }
        
        if(ini == fin && ini == nodoAEliminar){
            ini = fin = null;
            return true;
        }
        //eliminando si el NodoEliminar esta en INI
        if(ini == nodoAEliminar){
            NodoGrafo temp = ini.sig;
            ini.sig = null;
            temp.ant = null;
            ini = temp;
            return true;
        }
        //eliminando si el NodoEliminar esta en FIN
        if(fin == nodoAEliminar){
            NodoGrafo temp = fin.ant;
            temp.sig = null;
            fin.ant = null;
            fin = temp;
            return true;
        }
        //eliminando si el NodoEliminar esta en MEDIO
        nodoAEliminar.ant.sig = nodoAEliminar.sig;
        nodoAEliminar.sig.ant = nodoAEliminar.ant;
        nodoAEliminar.sig = nodoAEliminar.ant = null;
        return true;
    }

    private boolean encontarAristas(NodoGrafo temp, NodoGrafo nodoAEliminar) {
       for(NodoArista temp2 = temp.aristas; temp2 != null; temp2 = temp2.sig){
           if(temp2.direccion == nodoAEliminar){
               return true;
           }
       }
       return false;
    }
    
    public boolean encontrarArista(char orig,char dest){
        NodoGrafo origen = buscarNodoGrafo(orig);
        if(origen == null){
            return false;
        }
        if(origen.aristas == null){
            return false;
        }
        NodoArista temp = origen.aristas;
        if(temp.direccion.valor == dest){
            return true;
        }
        return false;
    }
    
    public String mostrarNodo(){
        String cad = "";
        for(NodoGrafo temp = ini;temp != null; temp = temp.sig){
            cad += temp.valor + "\n";
        }
        return cad;
    }
    
}
