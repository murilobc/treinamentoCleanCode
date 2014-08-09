package org.treinamento.cleancode.service;

public class TabelaPrecoPraia implements TabelaPreco {

	@Override
	public int calcularValor(int tempoDePermanencia) {
		return 5;
	}

}
