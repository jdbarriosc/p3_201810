package model.data_structures;


import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;






public class ListaDoblementeEncadenada<E extends Comparable<E>> implements Iterable<E>,ILinkedList <E> 
{
	private NodoListaDoble<E> header; // header sentinel
	private NodoListaDoble<E> trailer; // trailer sentinel
	private int size = 0; // number of elements in the list





	/**
	 * Construye una lista vacia
	 * <b>post:< /b> se ha inicializado el primer nodo en null
	 */
	public ListaDoblementeEncadenada() 
	{
		header = null;
		trailer=null;
		size = 0;
	}

	public NodoListaDoble<E> darHeader()
	{
		return header;
	}

	public NodoListaDoble<E> darTrailer()
	{
		return trailer;
	}


	public E getFirst( )
	{
		if(header!=null)
			return header.darElemento();
		return null;
	}

	public E getLast( )
	{
		if(trailer!=null)
			return trailer.darElemento();
		return null;

	}




	public void addAtStart(E nuevo) {
		NodoListaDoble<E> nNodo= new NodoListaDoble<E>(nuevo);
		nNodo.cambiarSiguiente(header);
		if(header!=null)
			header.cambiarAnterior(nNodo);


	}




	public E dar(int posicion)
	{
		return darNodo(posicion).darElemento();
	}

	/**
	 * Se construye una nueva lista cuyo primer nodo  guardará al elemento que llega por parámentro
	 * @param nPrimero el elemento a guardar en el primer nodo
	 * @throws NullPointerException si el elemento recibido es nulo
	 */
	public ListaDoblementeEncadenada(E nPrimero)
	{
		if(nPrimero == null)
		{
			throw new NullPointerException("Se recibe un elemento nulo");
		}
		header = new NodoListaDoble<E>(nPrimero);
		trailer=header;
		size = 1;
	}

	/**
	 * Agrega un elemento al final de la lista
	 * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id.
	 * Se actualiza la cantidad de elementos.
	 * @param e el elemento que se desea agregar.
	 * @return true en caso que se agregue el elemento o false en caso contrario. 
	 * @throws NullPointerException si el elemento es nulo
	 */
	public void add(E e) 
	{
		// TODO Completar según la documentación
		if(e==null)
			System.out.println("El elemento que se desea agregar a la lista es nulo");

		NodoListaDoble<E> nNodo= new NodoListaDoble<E>(e);

		if(isEmpty()) 
		{
			header=nNodo;
			trailer=header;
			size++;
		}
		else
		{
			if(trailer == null)
				System.out.println("EL trailer es nulo por alguna razon");
			trailer.cambiarSiguiente(nNodo);
			nNodo.cambiarAnterior(trailer);
			trailer=nNodo;
			size++;
		}


	}

	/**
	 * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
	 * @param elemento el elemento que se desea agregar.
	 * @return true en caso que se agregue el elemento o false en caso contrario. 
	 * @throws NullPointerException si el elemento es nulo
	 */
	public void addIndex(int index, E elemento) 
	{
		// TODO Completar según la documentación
		if(elemento==null||index>size)
			throw new NullPointerException("El elemento que se desea agregar a la lista es nulo");



		NodoListaDoble<E> nNodo=new NodoListaDoble<E>(elemento);
		if(index==0&&isEmpty())
		{
			header=nNodo;
			trailer=header;
			size++;
		}
		if(index==size)
		{
			trailer.cambiarSiguiente(nNodo);
			nNodo.cambiarAnterior(trailer);
			trailer=nNodo;
			size++;

		}


		if (index==0)
		{

			nNodo.cambiarSiguiente(header);
			header.cambiarAnterior(nNodo);
			header=nNodo;

			size++;
		}
		else
		{
			NodoListaDoble<E> siguiente=darNodo(index);
			NodoListaDoble<E> anterior= darNodo(index-1);
			nNodo.cambiarSiguiente(siguiente);
			nNodo.cambiarAnterior(anterior);
			anterior.cambiarSiguiente(nNodo);
			siguiente.cambiarAnterior(nNodo);
			size++;
		}



	}

	/**
	 * Elimina el nodo en la posición por parámetro.
	 * Actualiza la cantidad de elementos.
	 * @param pos la posición que se desea eliminar
	 * @return el elemento eliminado
	 * @throws IndexOutOfBoundsException si index < 0 o index >= size()
	 */
	public E remove(int index) throws IndexOutOfBoundsException
	{
		// TODO Completar según la documentación



		NodoListaDoble<E> nodo=darNodo(index);
		if(index==0)
		{

			header=header.darSiguiente();
			header.cambiarAnterior(null);
			size--;
		}
		else if(index==size-1)
		{
			trailer=trailer.darAnterior();
			trailer.cambiarSiguiente(null);
			size--;
		}
		else
		{
			NodoListaDoble<E> anterior=(NodoListaDoble<E>)darNodo(index-1);
			NodoListaDoble<E> siguiente=null;
			if(index+1<size)
				siguiente=darNodo(index+1);
			anterior.cambiarSiguiente(siguiente);
			if(siguiente!=null)
				siguiente.cambiarAnterior(anterior);
			size--;

		}
		return nodo.darElemento();
	}

	/**
	 * Deja en la lista solo los elementos que están en la colección que llega por parámetro.
	 * Actualiza la cantidad de elementos
	 * @param coleccion la colección de elementos a mantener. coleccion != null
	 * @return true en caso que se modifique (eliminación) la lista o false en caso contrario
	 */
	public boolean retainAll(Collection<?> c) 
	{
		// TODO Completar según la documentación
		boolean modificado = false;

		NodoListaDoble<E> actual= header;
		while (actual!=null)
		{
			if(!c.contains(actual)){
				remove(actual);
				modificado=true;
				size--;
			}
		}
		return modificado;


	}

	/**
	 * Crea una lista con los elementos de la lista entre las posiciones dadas
	 * @param inicio la posición del primer elemento de la sublista. Se incluye en la sublista
	 * @param fin la posición del útlimo elemento de la sublista. Se excluye en la sublista
	 * @return una lista con los elementos entre las posiciones dadas
	 * @throws IndexOutOfBoundsException Si inicio < 0 o fin >= size() o fin < inicio
	 */
	public ListaDoblementeEncadenada<E> subList(int inicio, int fin) throws IndexOutOfBoundsException
	{
		// TODO Completar según la documentación
		ListaDoblementeEncadenada<E> lista= new ListaDoblementeEncadenada<E>();
		if(fin<inicio||fin>=size())
			throw new IndexOutOfBoundsException("El fin esta fuera del tamaño de la lista o el fin es menor al inicio");
		NodoListaDoble<E> actual=darNodo(inicio);
		while(inicio<=fin&&actual!=null){
			lista.add(actual.darElemento());
			inicio++;
			actual= actual.darSiguiente();
		}
		return lista;
	}
	public void removeAll()
	{
		header=null;
		header.cambiarSiguiente(null);
		trailer=null;
		trailer.cambiarAnterior(null);
	}

	/**
	 * Devuelve el nodo de la posición dada
	 * @param pos la posición  buscada
	 * @return el nodo en la posición dada 
	 * @throws IndexOutOfBoundsException si index < 0 o index >= size()
	 */
	public NodoListaDoble<E> darNodo(int index)throws IndexOutOfBoundsException
	{
		if(index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("Se está pidiendo el indice: " + index + " y el tamaño de la lista es de " + size);
		}

		NodoListaDoble<E> actual=null;




		if(index<=(size/2))
		{
			actual = header;
			int posActual = 0;
			while(actual != null && posActual < index)
			{
				actual =  actual.darSiguiente();
				posActual++;
			}
		}
		else
		{

			actual = trailer;
			int posActual = size-1;
			while(actual != null && posActual > index)
			{
				actual = actual.darAnterior();
				posActual--;
			}
		}

		return actual;
	}




	public NodoListaDoble<E> addFirst(E e) {

		NodoListaDoble<E> nNodo= new NodoListaDoble<E>(e);

		if(isEmpty()) 
		{
			header=nNodo;
			trailer=header;
			size++;
		}
		else 
		{
			nNodo.cambiarSiguiente(header);		
			header.cambiarAnterior(nNodo);
			header=nNodo;
			size++;
		}

		return nNodo;

	}



	public int size() 
	{
		return size; 
	}

	public boolean isEmpty() 
	{ 
		return size == 0; 
	}

	public NodoListaDoble<E> first( )
	{
		return header;
	}

	public NodoListaDoble<E> last( ) 
	{
		return trailer;
	}


	private NodoListaDoble<E> validate(NodoListaDoble p) throws IllegalArgumentException 
	{
		if (!(p instanceof NodoListaDoble)) throw new IllegalArgumentException("Invalid p");
		NodoListaDoble<E> NodoListaDoble = (NodoListaDoble<E>) p; // safe cast
		if (NodoListaDoble.darElemento()==null) // convention for defunct NodoListaDoble
			throw new IllegalArgumentException("p no tiene ningun elemento");
		return NodoListaDoble;
	}




	public void addInOrderBinario(Comparator<E> comparador, E elementoAUbicar) 
	{
		// TODO Completar según la documentación


		System.out.println(elementoAUbicar.toString());

		NodoListaDoble<E> nNodo=new NodoListaDoble<E>(elementoAUbicar);
		NodoListaDoble<E> nodoSiguiente = null;


		if(isEmpty())
			addFirst(elementoAUbicar);
		else
		{


			int inicio=0;
			int fin= size()-1;

			boolean ya=false;
			while(inicio<=fin&&!ya)
			{
				int medio=(inicio+fin)/2;

				//					System.out.println(medio);

				NodoListaDoble<E> nodoMitad=darNodo(medio);

				if(comparador.compare(nodoMitad.darElemento(),elementoAUbicar)==0)
				{
					System.out.println("==0");

					nodoSiguiente=(NodoListaDoble<E>) nodoMitad.darSiguiente();
					nNodo.cambiarAnterior(nodoMitad);
					nNodo.cambiarSiguiente(nodoSiguiente);
					nodoMitad.cambiarSiguiente(nNodo);
					if(nodoSiguiente!=null)	
						nodoSiguiente.cambiarAnterior(nNodo);
					size++;
					ya=true;
				}	


				else if(comparador.compare(nodoMitad.darElemento(),elementoAUbicar)>0)
					fin=medio-1;

				else
				{

					if(comparador.compare(nodoMitad.darSiguiente().darElemento(),elementoAUbicar)>0)
					{
						System.out.println(">0");
						nodoSiguiente=(NodoListaDoble<E>) nodoMitad.darSiguiente();
						nNodo.cambiarAnterior(nodoMitad);
						nNodo.cambiarSiguiente(nodoSiguiente);
						nodoMitad.cambiarSiguiente(nNodo);
						if(nodoSiguiente!=null)		
							nodoSiguiente.cambiarAnterior(nNodo);
						size++;
						ya=true;
					}
					else
						inicio=medio+1;

				}
			}
		}

		System.out.println(size);
	}


	public void addInOrderComparador(Comparator<E> comparador, E elementoAUbicar) 
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
				if(comparador.compare(actual.darElemento(),elementoAUbicar)<0)
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





	public void addInOrder( E elementoAUbicar) 
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
				if(actual.darElemento().compareTo(elementoAUbicar)<0)
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
	
	


	public ILinkedList<E> subLista(Comparator<E> comparador, E elementoAIgualar)
	{

		NodoListaDoble<E> nodoInicial=buscarNodo(comparador, elementoAIgualar);
		NodoListaDoble<E> nodoIn2=nodoInicial;


		ListaDoblementeEncadenada<E> lista= new ListaDoblementeEncadenada<E>(nodoInicial.darElemento());

		while(true)
		{
			if(comparador.compare(nodoInicial.darSiguiente().darElemento(),elementoAIgualar)==0)
			{
				lista.add(nodoInicial.darSiguiente().darElemento());
				nodoInicial=(NodoListaDoble<E>) nodoInicial.darSiguiente();
			}
			else
				break;
		}
		while(true)
		{
			if(comparador.compare(nodoIn2.darAnterior().darElemento(),elementoAIgualar)==0)
			{
				lista.add(nodoIn2.darAnterior().darElemento());
				nodoIn2=(NodoListaDoble<E>) nodoIn2.darAnterior();
			}
			else
				break;
		}





		return (ILinkedList<E>) lista;
	}


	public NodoListaDoble<E> buscarNodo(Comparator<E> comparador, E elementoAUbicar)
	{

		NodoListaDoble<E> nodoBuscado=null;
		if(isEmpty())
			return nodoBuscado;


		int inicio=0;
		int fin= size-1;
		boolean ya=false;
		while(inicio<=fin&&!ya)
		{
			int medio=(inicio+fin)/2;

			E elemMit=darNodo(medio).darElemento();

			if(comparador.compare(elemMit,elementoAUbicar)==0)
			{
				nodoBuscado=(NodoListaDoble<E>) elemMit;
				ya=true;
			}	


			else if(comparador.compare(elemMit,elementoAUbicar)>0)
				fin=medio-1;

			else
				inicio=medio+1;

		}
		return nodoBuscado;


	}



	public void limpiar()
	{
		header=null;
		trailer=null;
		size=0;
	}

	public void enlazar(ListaDoblementeEncadenada<E> nLista)
	{
		if(trailer!=null)
		{
			trailer.cambiarSiguiente(nLista.darHeader());
			if(trailer.darSiguiente()!=null)
			{
				trailer.darSiguiente().cambiarAnterior(trailer);
				trailer=nLista.darTrailer();
			}
		}
		else 
		{
			header=nLista.darHeader();
			trailer=nLista.darTrailer();
		}
	
		size+=nLista.size();

	}


	public boolean contains(E elem)
	{
		boolean esta=false;
		ListIterator<E> iterador= new ListIterator<E>(header);
		while(iterador.hasNext()&&!esta)
		{
			if(iterador.next().compareTo(elem)==0)
				esta=true;
		}
		return esta;
	}
//	public E getByElement(E elem)
//	{
//		
//		E devolver=null;
//		ListIterator<E> iterador= new ListIterator<E>(header);
//		
//		while(iterador.hasNext())
//		{
//			E actual=iterador.next();
//			RangoDistancia e=(RangoDistancia)elem;
//			System.out.println(e.getLimineInferior());
//			e=(RangoDistancia)actual;
//			System.out.println(e.getLimineInferior());
//			if(actual.compareTo(elem)==0)
//			{
//				devolver=actual;
//				break;
//			}
//		}
//		return devolver;
//	}


	public E set(NodoListaDoble<E> p, E e) throws IllegalArgumentException {
		NodoListaDoble<E> NodoListaDoble = validate(p);
		E answer = NodoListaDoble.darElemento();
		NodoListaDoble.cambiarElemento(e);;
		return answer;
	}

	public NodoListaDoble<E> merge_sort(NodoListaDoble<E> head) {
		if(head == null || head.darSiguiente() == null) 
		{ 
			return head; 
		}
		NodoListaDoble<E> middle = getMiddle(head);      //get the middle of the list
		NodoListaDoble<E> sHalf = middle.darSiguiente(); 
		middle.cambiarSiguiente(null);   //split the list into two halfs

		return merge(merge_sort(head),merge_sort(sHalf));  //recurse on that
	}

	//Merge subroutine to merge two sorted lists
	public NodoListaDoble<E> merge(NodoListaDoble<E> a, NodoListaDoble<E> b) {
		NodoListaDoble<E> dummyHead, curr; 
		dummyHead = new NodoListaDoble<E>(null); 
		curr = dummyHead;
		while(a !=null && b!= null) {
			if(a.darElemento().compareTo(b.darElemento()) <= 0) 
			{ 
				curr.cambiarSiguiente(a);
				a.darAnterior().cambiarSiguiente(a.darSiguiente()); 
			}
			else 
			{ 
				curr.cambiarSiguiente(b);
				b.darAnterior().cambiarSiguiente(b.darSiguiente());
			}
			curr = curr.darSiguiente();
		}
		curr.cambiarSiguiente((a == null) ? b : a) ;
		return dummyHead.darSiguiente();
	}

	//Finding the middle element of the list for splitting
	public NodoListaDoble<E> getMiddle(NodoListaDoble<E> head) {
		if(head == null) 
		{ 
			return head; 
		}
		NodoListaDoble<E> slow, fast; slow = fast = head;
		while(fast.darSiguiente() != null && fast.darSiguiente().darSiguiente() != null) 
		{
			slow = slow.darSiguiente(); 
			fast = fast.darSiguiente().darSiguiente();
		}
		return slow;
	}



	public E remove(NodoListaDoble<E> p) throws IllegalArgumentException {
		NodoListaDoble<E> NodoListaDoble = validate(p);
		NodoListaDoble<E> predecessor = NodoListaDoble.darAnterior( );
		NodoListaDoble<E> successor = (NodoListaDoble)NodoListaDoble.darSiguiente( );
		predecessor.cambiarSiguiente(successor);
		successor.cambiarAnterior(predecessor);
		size--;
		E answer = NodoListaDoble.darElemento( );
		NodoListaDoble.cambiarElemento(null); // help with garbage collection
		NodoListaDoble.cambiarSiguiente(null); // and convention for defunct NodoListaDoble
		NodoListaDoble.cambiarAnterior(null);
		return answer;
	}



	public Iterator<E> iterator()
	{
		return new ListIterator<E>(header);
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator<E> implements Iterator<E> {
		private NodoListaDoble<E> current;

		public ListIterator(NodoListaDoble<E> first) 
		{
			current = first;
		}

		public boolean hasNext()
		{
			return current != null;
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}

		public E next() 
		{
			if (!hasNext()) throw new NoSuchElementException();
			E item = current.darElemento();
			current = current.darSiguiente(); 
			return item;
		}
	}
		

}



//public NodoListaDoble<E> addBefore(NodoListaDoble<E> p, E e)
//throws IllegalArgumentException {
//NodoListaDoble<E> NodoListaDoble = validate(p);
//return addBetween(e, NodoListaDoble.darAnterior(), NodoListaDoble);
//}
//
//
//public NodoListaDoble<E> addAfter(NodoListaDoble<E> p, E e)
//throws IllegalArgumentException {
//NodoListaDoble<E> NodoListaDoble = validate(p);
//return addBetween(e, NodoListaDoble,(NodoListaDoble) NodoListaDoble.darSiguiente());
//}
//
//
//public ListaDoblementeEncadenada( ) {
//
//	header = new NodoListaDoble<>(null, null, null); // create header
//	trailer = new NodoListaDoble<>(null, header, null); // trailer is preceded by header
//	header.cambiarSiguiente(trailer); // header is followed by trailer
//}



//private NodoListaDoble position(NodoListaDoble<E> NodoListaDoble) {
//	if (NodoListaDoble == header || NodoListaDoble == trailer)
//		return null; // do not expose user to the sentinels
//	return NodoListaDoble;
//}
//
//

//public NodoListaDoble<E> before(NodoListaDoble p) throws IllegalArgumentException {
//	NodoListaDoble<E> NodoListaDoble = validate(p);
//	return position(NodoListaDoble.darAnterior());
//}


//public NodoListaDoble after(NodoListaDoble<E> p) throws IllegalArgumentException {
//	NodoListaDoble<E> NodoListaDoble = validate(p);
//	return position((NodoListaDoble)
//			NodoListaDoble.darSiguiente());
//}
///**
//* Elimina el nodo que contiene al objeto que llega por parámetro.
//* Actualiza la cantidad de elementos.
//* @param objeto el objeto que se desea eliminar. objeto != null
//* @return true en caso que exista el objeto y se pueda eliminar o false en caso contrario
//*/
//public boolean remove(Object o) 
//{
//	// TODO Completar según la documentación
//	boolean removido=false;
//	int indice=indexOf(o);
//	if(indice!=-1)
//	{
//		if(indice==0)
//		{
//			NodoListaDoble<E> prim=(NodoListaDoble<E>)header;
//			prim=(NodoListaDoble<E>)header.darSiguiente();
//			prim.cambiarAnterior(null);
//			header=prim;
//			removido = true;
//			size--;
//
//		}
//		else
//		{
//			NodoListaDoble<E> anterior=(NodoListaDoble<E>)darNodo(indice-1);
//			NodoListaDoble<E> siguiente=null;
//			if(indice+1<size)
//				siguiente=(NodoListaDoble<E>)darNodo(indice+1);
//			anterior.cambiarSiguiente(siguiente);
//			if(siguiente!=null)
//				siguiente.cambiarAnterior(anterior);
//			removido = true;
//			size--;
//
//		}
//	}
//	return removido;
//	
//}


//public int indexOf(Object o) 
//{
//	// TODO Completar según la documentación
//	E elemento=(E)o;
//	NodoListaDoble<E> actual=header;
//	int index=-1;
//	int ind=0;
//	boolean ya=false;
//	while(actual!=null&&!ya)
//	{
//		if(actual.darElemento().darIdentificador().equalsIgnoreCase(elemento.darIdentificador())) 
//		{
//			index=ind;
//			ya=true;
//		}
//		ind++;
//		actual= actual.darSiguiente();
//	}
//	
//	return index;
//	
//}