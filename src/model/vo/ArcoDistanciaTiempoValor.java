package model.vo;

import model.data_structures.grafo.IArco;

public class ArcoDistanciaTiempoValor implements IArco
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
    /**
	 * Constante para la serialización 
	 */
	private static final long serialVersionUID = 1L;
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
     * Peso del arco.
     */
    private double distancia;
    private double tiempo;
    private double valor;
    private double cantidadPagaronPeajes;
    private double cantidad;
 

	private String idVerticeOrigen;
    private String idVerticeDestino;


    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Constructor por parámetros.
     * @param peso Peso del arco.
     */
    public ArcoDistanciaTiempoValor(String idVerticeOrigen,String idVerticeDestino, double distancia,double tiempo, double valor,double peajes)
    {
        this.distancia = distancia;
        this.tiempo = tiempo;
        this.valor = valor;
        this.idVerticeOrigen=idVerticeOrigen;
        this.idVerticeDestino=idVerticeDestino;
        cantidadPagaronPeajes=peajes>0?1:0;
        cantidad=1;

    }
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    public double darPromedioDistancia( )
    {
        return distancia/cantidad;
    }
    public double darPromedioTiempo( )
    {
        return tiempo/cantidad;
    }
    public double darPromedioValor( )
    {
        return valor/cantidad;
    }
    public double darCantidadPagaronPeaje( )
    {
        return cantidadPagaronPeajes;
    }
    public String getIdVerticeOrigen() {
 		return idVerticeOrigen;
 	}

 	public void setIdVerticeOrigen(String idVerticeOrigen) {
 		this.idVerticeOrigen = idVerticeOrigen;
 	}

 	public String getIdVerticeDestino() {
 		return idVerticeDestino;
 	}

 	public void setIdVerticeDestino(String idVerticeDestino) {
 		this.idVerticeDestino = idVerticeDestino;
 	}
    public void agregarValores(double pDistancia,double pTiempo,double pValor,double peajes)
    {
         distancia+=pDistancia;
         tiempo+=pTiempo;
         valor+=pValor;
         cantidadPagaronPeajes+=peajes>0?1:0;
         cantidad++;
    }

	@Override
	public double darPeso() {
		return darPromedioDistancia();
	}

	/**
	 * Devuelve el peso del arco que se quiere, diferenciandolos con enteros 
	 * @param referencia la referencia al peso que quiere que se devuelva 1=distancia
	 * 																	  2=tiempo
	 * 																	  3=valor
	 * 																	  4=cantidadQuePagaronPeaje
	 * 																	 de lo contrario devuelve 0
	 * @param referencia del peso que se quiere
	 * @return El peso
	 */
	@Override
	public double darPeso(int referencia) {
		if(referencia==1)
			return darPromedioDistancia();
		if(referencia==2)
			return darPromedioTiempo();
		if(referencia==3)
			return darPromedioValor();
		if(referencia==4)
			return darCantidadPagaronPeaje();
		return 0;
	}
 

}
