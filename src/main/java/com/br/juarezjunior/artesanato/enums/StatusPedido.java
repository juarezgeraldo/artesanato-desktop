package com.br.juarezjunior.artesanato.enums;

public enum StatusPedido {

	PAGAMENTO_PENDENTE(1),
	PAGO(2),
	CONSIGNACAO(3),
	CONSIGNACAO_ACERTADO(4);	
	
	private int codigoStatus;
	
	private StatusPedido(int codigoStatus) {
		this.codigoStatus = codigoStatus;
	}
	
	public int getCode() {
		return codigoStatus;
	}
	
	public static StatusPedido valueOf(int codigoStatus) {
		for(StatusPedido value : StatusPedido.values()) {
			if(value.getCode() == codigoStatus) {
				return value;
			}
		}
		throw new IllegalArgumentException("Status do pedido inválido.");
	}
}
