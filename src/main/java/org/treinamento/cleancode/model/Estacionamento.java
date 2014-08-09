package org.treinamento.cleancode.model;

import java.util.Date;

import org.treinamento.cleancode.exception.CarroDuplicadoException;
import org.treinamento.cleancode.exception.EstacionamentoException;
import org.treinamento.cleancode.exception.EstacionamentoFechadoException;
import org.treinamento.cleancode.exception.EstacionamentoLotadoException;

public class Estacionamento {
	private ControleDeVagas controleDeVagas;
    private Periodo periodoFuncionamento;
    
    public Estacionamento(int numeroVagas, Periodo periodoFuncionamento) {
    	this.controleDeVagas = new ControleDeVagas(numeroVagas);
        this.periodoFuncionamento = periodoFuncionamento;
    }

    public void entraUmCarro(String placa, Date horaDeEntrada) throws EstacionamentoException {
    	CarroEstacionado carro = new CarroEstacionado(placa, horaDeEntrada);
        if (periodoFuncionamento.isPeriodoForaDeFuncionamento(horaDeEntrada)) throw new EstacionamentoFechadoException(); 
        if (this.controleDeVagas.isCarroEstacionado(carro))  throw new CarroDuplicadoException();
        controleDeVagas.estacionaCarro(carro);
    }
    
    public Integer sairUmCarro(String placa, Date horaDeSaida) throws EstacionamentoException {
    	if (periodoFuncionamento.isPeriodoForaDeFuncionamento(horaDeSaida)) throw new EstacionamentoFechadoException();
        Date horaDeEntrada = controleDeVagas.getCarroEstacionado(placa).getHoraDeEntrada();
        Periodo periodoPermanencia = new Periodo(horaDeEntrada, horaDeSaida);
		controleDeVagas.liberaVaga(placa);
		return periodoPermanencia.getHorasPermanencia();
    }

	public int getVagasDisponiveis() throws EstacionamentoLotadoException {
		return controleDeVagas.getVagasDisponiveis();
	}
}