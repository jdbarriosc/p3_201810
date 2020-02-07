package model.data_structures;


/**
 * Clase que representa un nodo de la lista doblemente encadenada.
 * @param <E> Tipo del objeto que se almacenar� en el nodo.
 */
public class NodoListaDoble<E> 
{


	protected E elemento;
	
	/**
	 * Siguiente nodo.
	 */
	protected NodoListaDoble<E> siguiente;
	
	/**
	 * Constructor del nodo.
	 * @param elemento El elemento que se almacenar� en el nodo. elemento != null
	 */
	/**
	 * Nodo anterior.
	 */
	private NodoListaDoble<E> anterior;
	
	/**
	 * M�todo constructor del nodo doblemente encadenado
	 * @param elemento elemento que se almacenar� en el nodo.
	 */
	public NodoListaDoble(E elemento) 
	{
		this.elemento = elemento;
		anterior=null;
		siguiente=null;
	}
	

	public void cambiarSiguiente(NodoListaDoble<E> siguiente)
	{
		this.siguiente = siguiente;
	}
	
	/**
	 * M�todo que retorna el elemento almacenado en el nodo.
	 * @return El elemento almacenado en el nodo.
	 */
	public E darElemento()
	{
		return elemento;
	}
	
	/**
	 * Cambia el elemento almacenado en el nodo.
	 * @param elemento El nuevo elemento que se almacenar� en el nodo.
	 */
	public void cambiarElemento(E elemento)
	{
		this.elemento = elemento;
	}
	
	/**
	 * M�todo que retorna el siguiente nodo.
	 * @return Siguiente nodo
	 */
	public NodoListaDoble<E> darSiguiente()
	{
		return siguiente;
	}
	
	/**
	 * M�todo que retorna el nodo anterior.
	 * @return Nodo anterior.
	 */
	public NodoListaDoble<E> darAnterior()
	{
		return anterior;
	}
	
	/**
	 * M�todo que cambia el nodo anterior por el que llega como par�metro.
	 * @param anterior Nuevo nodo anterior.
	 */
	public void cambiarAnterior(NodoListaDoble<E> anterior)
	{
		this.anterior = anterior;
	}
}
