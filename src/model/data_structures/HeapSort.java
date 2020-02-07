package model.data_structures;

import java.util.Comparator;


public class HeapSort <E extends Comparable<E>>{

	private E[] arreglo;
    private int size;
    private int left;
    private int right;
    private int largest;

    
    public void buildheap(E []a){
        size=a.length-1;
        arreglo=a;
        for(int i=size/2;i>=0;i--){
            maxheap(a,i);
        }
    }
    
    public void maxheap(E[] a, int i ){ 
        left=2*i;
        right=2*i+1;
        if(left <= size && a[left]!=null&& a[i]!=null&&a[left].compareTo(a[i])>0){
            largest=left;
        }
        else{
            largest=i;
        }
        
        if(right <= size && a[right]!=null&& a[largest]!=null&& a[right].compareTo(a[largest])>0){
            largest=right;
        }
        if(largest!=i){
            exchange(i,largest);
            maxheap(a, largest);
        }
    }
    
    public void buildheapComparador(E []a,Comparator<E> comparador){
        size=a.length-1;
        arreglo=a;
        for(int i=size/2;i>=0;i--){
            maxheapComparador(a,i,comparador);
        }
    }
    
    public void maxheapComparador(E[] a, int i,Comparator<E> comparador){ 
        left=2*i;
        right=(2*i)+1;
        if(left <= size && a[left]!=null&& a[i]!=null&&comparador.compare(a[left],a[i])>0){
            largest=left;
        }
        else{
            largest=i;
        }
        
        if(right <= size && a[right]!=null&& a[largest]!=null&& comparador.compare(a[right],a[largest])>0){
            largest=right;
        }
        if(largest!=i){
            exchange(i,largest);
            maxheapComparador(a, largest,comparador);
        }
    }
    
    
    public void exchange(int i, int j){
        E temporal=arreglo[i];
        arreglo[i]=arreglo[j];
        arreglo[j]=temporal; 
        }
    
    public E[] sort(E []a0){
        arreglo=a0;
        buildheap(arreglo);
        
        for(int i=size;i>0;i--){
            exchange(0, i);
            size=size-1;
            maxheap(arreglo, 0);
        }
        return arreglo;
    }
    public E[] sortComparador(E []a0,Comparator<E> comparador){
        arreglo=a0;
        buildheapComparador(arreglo, comparador);
        
        for(int i=size;i>0;i--){
            exchange(0, i);
            size=size-1;
            maxheapComparador(arreglo, 0, comparador);
        }
        return arreglo;
    }

	public E[] getArreglo() {
		return arreglo;
	}

	public void setArreglo(E[] arreglo) {
		this.arreglo = arreglo;
	}
	

}
