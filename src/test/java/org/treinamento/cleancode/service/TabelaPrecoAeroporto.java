package org.treinamento.cleancode.service;

public class TabelaPrecoAeroporto implements TabelaPreco {

	@Override
	public int calcularValor(int tempoDePermanencia) {
		if (tempoDePermanencia <= 5) return 5;
		return ((tempoDePermanencia - 5) * 3) + 5;
	}

}
