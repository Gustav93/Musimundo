package com.musimundo.carritos.beans;
/*
 * Totals container
 * */
public class TotalesCarritos {
	private Integer totalCarros = 0;
	private Integer totalCarrosCarsa = 0;
	private Integer totalCarrosEmsa = 0;
	private Integer totalProductos = 0;
	private Integer totalProductosCarsa = 0;
	private Integer totalProductosEmsa = 0;
	private Double totalRecaudado = 0.0;
	private Double totalRecaudadoCarsa = 0.0;
	private Double totalRecaudadoEmsa = 0.0;
	
	public Integer getTotalCarros() {
		return totalCarros;
	}
	
	public void setTotalCarros(Integer totalCarros) {
		this.totalCarros = totalCarros;
	}
	
	public Integer getTotalCarrosCarsa() {
		return totalCarrosCarsa;
	}
	
	public void setTotalCarrosCarsa(Integer totalCarrosCarsa) {
		this.totalCarrosCarsa = totalCarrosCarsa;
	}
	
	public Integer getTotalCarrosEmsa() {
		return totalCarrosEmsa;
	}
	
	public void setTotalCarrosEmsa(Integer totalCarrosEmsa) {
		this.totalCarrosEmsa = totalCarrosEmsa;
	}
	
	public Integer getTotalProductos() {
		return totalProductos;
	}
	
	public void setTotalProductos(Integer totalProductos) {
		this.totalProductos = totalProductos;
	}
	
	public Integer getTotalProductosCarsa() {
		return totalProductosCarsa;
	}
	
	public void setTotalProductosCarsa(Integer totalProductosCarsa) {
		this.totalProductosCarsa = totalProductosCarsa;
	}
	
	public Integer getTotalProductosEmsa() {
		return totalProductosEmsa;
	}
	
	public void setTotalProductosEmsa(Integer totalProductosEmsa) {
		this.totalProductosEmsa = totalProductosEmsa;
	}
	
	public Double getTotalRecaudado() {
		return totalRecaudado;
	}
	
	public void setTotalRecaudado(Double totalRecaudado) {
		this.totalRecaudado = totalRecaudado;
	}
	
	public Double getTotalRecaudadoCarsa() {
		return totalRecaudadoCarsa;
	}
	
	public void setTotalRecaudadoCarsa(Double totalRecaudadoCarsa) {
		this.totalRecaudadoCarsa = totalRecaudadoCarsa;
	}
	
	public Double getTotalRecaudadoEmsa() {
		return totalRecaudadoEmsa;
	}
	
	public void setTotalRecaudadoEmsa(Double totalRecaudadoEmsa) {
		this.totalRecaudadoEmsa = totalRecaudadoEmsa;
	}

	public void limpiarTotales() {
		setTotalCarros(0);
		setTotalCarrosCarsa(0);
		setTotalCarrosEmsa(0);
		setTotalProductos(0);
		setTotalProductosCarsa(0);
		setTotalProductosEmsa(0);
		setTotalRecaudado(0.0);
		setTotalRecaudadoCarsa(0.0);
		setTotalRecaudadoEmsa(0.0);		
	}
	
	

}
