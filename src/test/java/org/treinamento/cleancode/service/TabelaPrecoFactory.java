package org.treinamento.cleancode.service;

public enum TabelaPrecoFactory {
	
	PRAIA {
		@Override
		public TabelaPreco criaTabelaPreco() {
			return new TabelaPrecoPraia();
		}
	}, 
	CENTRO {
		@Override
		public TabelaPreco criaTabelaPreco() {
			return new TabelaPrecoCentro();
		}
	}, 
	SHOPPING {
		@Override
		public TabelaPreco criaTabelaPreco() {
			return new TabelaPrecoShopping();
		}
	}, 
	AEROPORTO {
		@Override
		public TabelaPreco criaTabelaPreco() {
			return new TabelaPrecoAeroporto();
		}
	};
	
	public abstract TabelaPreco criaTabelaPreco();

}
