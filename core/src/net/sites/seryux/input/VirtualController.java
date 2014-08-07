package net.sites.seryux.input;

public class VirtualController {
	public boolean moverIzquierda;
	public boolean moverDerecha;
	public boolean moverArriba;
	public boolean moverAbajo;
	public boolean moverArribaDerecha;
	public boolean moverArribaIzquierda;
	public boolean moverAbajoDerecha;
	public boolean moverAbajoIzquierda;
	
	public void clean(){
		moverIzquierda = false;
		moverDerecha = false;
		moverArriba = false;
		moverAbajo = false;
		moverArribaDerecha = false;
		moverArribaIzquierda = false;
		moverAbajoDerecha = false;
		moverAbajoIzquierda = false;
	}
	
	
	
}
