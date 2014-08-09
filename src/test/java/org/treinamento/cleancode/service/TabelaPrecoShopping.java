package org.treinamento.cleancode.service;

public class TabelaPrecoShopping implements TabelaPreco {

	@Override
	public int calcularValor(int tempoDePermanencia) {
		if (tempoDePermanencia <= 3) return 6;
		return ((tempoDePermanencia - 3) * 2) + 6;
	}

}
