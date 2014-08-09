package org.treinamento.cleancode.service;

public class TabelaPrecoCentro implements TabelaPreco {

	@Override
	public int calcularValor(int tempoDePermanencia) {
		return tempoDePermanencia * 10;
	}

}
