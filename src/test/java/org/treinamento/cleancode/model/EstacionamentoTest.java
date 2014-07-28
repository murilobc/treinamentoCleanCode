package org.treinamento.cleancode.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.treinamento.cleancode.exception.CarroDuplicadoException;
import org.treinamento.cleancode.exception.CarroInexistenteException;
import org.treinamento.cleancode.exception.EstacionamentoException;
import org.treinamento.cleancode.exception.EstacionamentoLotadoException;
import org.treinamento.cleancode.exception.PlacaInvalidaException;

public class EstacionamentoTest {
    
    private Estacionamento estacionamento;
    
    @Before
    public void criaEstacionamento() {
        estacionamento = new Estacionamento(500);
    }
    
    @Test
    public void deveTer500VagasDisponiveis() throws EstacionamentoException {
        int numeroDeVagas = estacionamento.getVagasDisponiveis();
        assertEquals(500, numeroDeVagas);
    }
    
    @Test
    public void deveEntrarUmCarro() throws EstacionamentoException {
        estacionamento.entraUmCarro("MBC-1009");
        int numeroDeVagas = estacionamento.getVagasDisponiveis();
        assertEquals(499, numeroDeVagas);
    }
    
    @Test
    public void deveSairUmCarro() throws CarroDuplicadoException, EstacionamentoException {
        estacionamento.entraUmCarro("MBC-1009");
        int numeroDeVagas = estacionamento.getVagasDisponiveis();
        assertEquals(499, numeroDeVagas);
        
        estacionamento.sairUmCarro("MBC-1009");
        numeroDeVagas = estacionamento.getVagasDisponiveis();
        assertEquals(500, numeroDeVagas);
    }
    
    @Test(expected = CarroDuplicadoException.class)
    public void naoPodeCarroDuplicado() throws EstacionamentoException {
        estacionamento.entraUmCarro("MBC-1009");
        estacionamento.entraUmCarro("MBC-1009");
    }

    @Test(expected = EstacionamentoLotadoException.class)
    public void estacionamentoLotado() throws EstacionamentoException {
        estacionamento = new Estacionamento(0);
        estacionamento.entraUmCarro("MBC-1009");        
    }
    
    @Test(expected = CarroInexistenteException.class)
    public void naoPodeSairCarroQueNaoExiste() throws EstacionamentoException {
        estacionamento.sairUmCarro("MBC-1009");
    }
    
    @Test(expected = PlacaInvalidaException.class)
    public void naoPodeEntrarCarroComPlacaInvalida() throws EstacionamentoException {
        estacionamento.entraUmCarro("MBC1009");
    }
}