package model.data_structures;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class PriorityQueueListaDoble <E extends Comparable<E>>implements IQueue<E>, Iterable<E>  {
	
	private NodoListaDoble<E> header; // header sentinel
	private NodoListaDoble<E> trailer; // trailer sentinel
	private int size = 0; // number of elements in the list





	/**
	 * Construye una lista vacia
	 * <b>post:< /b> se ha inicializado el primer nodo en null
	 */
	public PriorityQueueListaDoble() 
	{
		header = null;
		trailer=null;
		size = 0;
	}
	
	
	public PriorityQueueListaDoble(E[] arreglo, Comparator<E> comparador,boolean aMayor) 
	{
		HeapSort<E> heap=new HeapSort<E>();
		arreglo=heap.sortComparador(arreglo, comparador);
		if(aMayor)
		{
			for(int i=arreglo.length-1;i>=0;i--)
			{
				if(arreglo[i]!=null)
				{
					heapEnqueue(arreglo[i]);
				}
			}
			
		}
		else
		{
			for(int i=0;i<arreglo.length;i++)
			{
				if(arreglo[i]!=null)
				{
					heapEnqueue(arreglo[i]);
				}
			}
		}

		
	}
	
	
	public PriorityQueueListaDoble(E nPrimero)
	{
		if(nPrimero == null)
		{
			throw new NullPointerException("Se recibe un elemento nulo");
		}
		header = new NodoListaDoble<E>(nPrimero);
		trailer=header;
		size = 1;
	}

	public void enqueueComparador(Comparator<E> comparador, E elementoAUbicar) 
	{
		if(elementoAUbicar==null)
			throw new NullPointerException("El elemento que se desea agregar a la lista es nulo");



		NodoListaDoble<E> nNodo=new NodoListaDoble<E>(elementoAUbicar);

		if(isEmpty())
		{
			header=nNodo;
			trailer=header;
			size++;
		}
		else
		{


			NodoListaDoble<E> actual=header;
			boolean agregado=false;

			while(!agregado&&actual!=null)
			{
				if(comparador.compare(actual.darElemento(),elementoAUbicar)>0)
				{
					if(actual.darSiguiente()!=null)
						actual=actual.darSiguiente();
					else
					{
						actual.cambiarSiguiente(nNodo);
						nNodo.cambiarAnterior(actual);
						nNodo.cambiarSiguiente(null);
						trailer=nNodo;
						size++;
						agregado=true;
					}
				}
				else if(comparador.compare(actual.darElemento(),elementoAUbicar)==0)
				{
					nNodo.cambiarAnterior(actual);
					nNodo.cambiarSiguiente(actual.darSiguiente());
					if(trailer==actual)
						trailer=nNodo;
					if(actual.darSiguiente()!=null)	
						actual.darSiguiente().cambiarAnterior(nNodo);
					actual.cambiarSiguiente(nNodo);
					size++;

					agregado=true;
				}
				else
				{
					nNodo.cambiarAnterior(actual.darAnterior());
					nNodo.cambiarSiguiente(actual);
					if(actual.darAnterior()!=null)	
						actual.darAnterior().cambiarSiguiente(nNodo); 
					actual.cambiarAnterior(nNodo);
					if(actual==header)
						header=nNodo;
					size++;
					agregado=true;
				}
			}
		}

	}

	public boolean isEmpty() 
	{ 
		return size == 0; 
	}

	private void heapEnqueue(E element) {
		if (element==null)
			return;
		NodoListaDoble<E> nNodo = new NodoListaDoble<E>(element);
		if(isEmpty()) 
		{
			header=nNodo;
			trailer=header;
			size++;
		}
		else if (trailer!=null) 
		{
			trailer.cambiarSiguiente(nNodo);
			nNodo.cambiarAnterior(trailer);
			trailer = nNodo;
			size++;

		}

	}

	@Override
	public void enqueue( E elementoAUbicar) 
	{
		if(elementoAUbicar==null)
			throw new NullPointerException("El elemento que se desea agregar a la lista es nulo");



		NodoListaDoble<E> nNodo=new NodoListaDoble<E>(elementoAUbicar);

		if(isEmpty())
		{
			header=nNodo;
			trailer=header;
			size++;
		}
		else
		{


			NodoListaDoble<E> actual=header;
			boolean agregado=false;

			while(!agregado&&actual!=null)
			{
				if(actual.darElemento().compareTo(elementoAUbicar)>0)
				{
					if(actual.darSiguiente()!=null)
						actual=actual.darSiguiente();
					else
					{
						actual.cambiarSiguiente(nNodo);
						nNodo.cambiarAnterior(actual);
						nNodo.cambiarSiguiente(null);
						trailer=nNodo;
						size++;
						agregado=true;
					}
				}
				else if(actual.darElemento().compareTo(elementoAUbicar)==0)
				{
					nNodo.cambiarAnterior(actual);
					nNodo.cambiarSiguiente(actual.darSiguiente());
					if(trailer==actual)
						trailer=nNodo;
					if(actual.darSiguiente()!=null)	
						actual.darSiguiente().cambiarAnterior(nNodo);
					actual.cambiarSiguiente(nNodo);
					size++;

					agregado=true;
				}
				else
				{
					nNodo.cambiarAnterior(actual.darAnterior());
					nNodo.cambiarSiguiente(actual);
					if(actual.darAnterior()!=null)	
						actual.darAnterior().cambiarSiguiente(nNodo); 
					actual.cambiarAnterior(nNodo);
					if(actual==header)
						header=nNodo;
					size++;
					agregado=true;
				}
			}
		}
	}
	
	
	

	

	@Override
	public E dequeue() throws NoSuchElementException {
		if (header == null) {
			throw new NoSuchElementException("Queue does not contain any items.");
		}

		E output = header.darElemento();
		header = header.darSiguiente();
		size--;
		return output;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public E element() throws NoSuchElementException {
		if (header == null) {
			throw new NoSuchElementException("Queue does not contain any items.");
		}

		return header.darElemento();
	}

	@Override
	public E peek() {
		return header == null ? null : header.darElemento();
	}
	
	public Iterator<E> iterator() {
		return new ListIterator<E>(header);
	}


	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator<E> implements Iterator<E> {
		private NodoListaDoble<E> current;

		public ListIterator(NodoListaDoble<E> first) {
			current = first;
		}

		@Override
		public boolean hasNext() 
		{ 
			return current != null;                     
		}

		public void remove()      
		{
			throw new UnsupportedOperationException();  
		}

		@Override
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			E item = current.darElemento();
			current = current.darAnterior(); 
			return item;
		}
	}

}
