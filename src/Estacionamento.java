public class Estacionamento {

	private Carro[] carros;
	private int numeroVagas;
	
	public Estacionamento(int numeroVagas) {
		this.numeroVagas = numeroVagas;
		this.carros = new Carro[numeroVagas];
	}

	public int getVagasDisponiveis() throws EstacionamentoLotadoException {
		return numeroVagas - getPosicaoVaga();
	}

	public void entraUmCarro(String placa) throws CarroDuplicadoException, EstacionamentoLotadoException, PlacaInvalidaException {
		Carro carro = new Carro(placa);
		if (isCarroEstacionado(carro))	throw new CarroDuplicadoException();
		carros[getPosicaoVaga()] = carro;
	}
	
	private boolean isCarroEstacionado(Carro carro) {
		try {
			getPosicaoCarro(carro.getPlaca());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void sairUmCarro(String placa) throws CarroInexistenteException, PlacaInvalidaException {
		int posicaoCarro = getPosicaoCarro(placa);
		carros[posicaoCarro] = null;
	}
	
	private int getPosicaoVaga() throws EstacionamentoLotadoException {
		for (int i = 0; i < carros.length; i++) {
			if (carros[i] == null) {
				return i;
			}
		}
		throw new EstacionamentoLotadoException();
	}
	
	private int getPosicaoCarro(String placa) throws PlacaInvalidaException, CarroInexistenteException {
		Carro carro = new Carro(placa);
		for (int i = 0; i < carros.length; i++) {
			if (carros[i] == null) break;
			if (carro.equals(carros[i])) return i;
		}
		throw new CarroInexistenteException();
	}
}