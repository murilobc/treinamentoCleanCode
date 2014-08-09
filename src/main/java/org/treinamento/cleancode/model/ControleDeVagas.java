package org.treinamento.cleancode.model;

import org.treinamento.cleancode.exception.CarroDuplicadoException;
import org.treinamento.cleancode.exception.CarroInexistenteException;
import org.treinamento.cleancode.exception.EstacionamentoException;
import org.treinamento.cleancode.exception.EstacionamentoLotadoException;
import org.treinamento.cleancode.exception.PlacaInvalidaException;

public class ControleDeVagas {
    private CarroEstacionado[] carros;
    private Integer numeroDeVagas;

    public ControleDeVagas(int numeroDeVagas) {
    	this.numeroDeVagas = numeroDeVagas;
    	this.carros = new CarroEstacionado[numeroDeVagas];
	}
    
    public int getVagasDisponiveis() throws EstacionamentoLotadoException {
        return numeroDeVagas - getPosicaoVaga();
    }
    
    private int getPosicaoVaga() throws EstacionamentoLotadoException {
        for (int i = 0; i < carros.length; i++) {
            if (carros[i] == null) {
                return i;
            }
        }
        throw new EstacionamentoLotadoException();
    }

	public void estacionaCarro(CarroEstacionado carro) throws EstacionamentoLotadoException, CarroDuplicadoException {
		carros[getPosicaoVaga()] = carro;
	}

	private int getPosicaoCarro(String placa) throws PlacaInvalidaException, CarroInexistenteException {
        CarroEstacionado carro = new CarroEstacionado(placa, null);
        for (int i = 0; i < carros.length; i++) {
            if (carros[i] == null) break;
            if (carro.equals(carros[i])) return i;
        }
        throw new CarroInexistenteException();
    }
	
	public boolean isCarroEstacionado(CarroEstacionado carro) {
        try {
            getPosicaoCarro(carro.getPlaca());
            return true;
        } catch (EstacionamentoException e) {
            return false;
        }
    }
	
	public CarroEstacionado getCarroEstacionado(String placa) throws PlacaInvalidaException, CarroInexistenteException {
        return carros[getPosicaoCarro(placa)];
	}
	
	public void liberaVaga(String placa) throws PlacaInvalidaException, CarroInexistenteException {
		carros[getPosicaoCarro(placa)] = null;
	}
}
