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
		try {
			getPosicaoCarro(placa);
			throw new CarroDuplicadoException();
		} catch (CarroInexistenteException e) {
			Carro carro = new Carro(placa);
			carros[getPosicaoVaga()] = carro;
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