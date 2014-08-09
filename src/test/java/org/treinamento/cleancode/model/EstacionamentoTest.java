package org.treinamento.cleancode.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.treinamento.cleancode.exception.CarroDuplicadoException;
import org.treinamento.cleancode.exception.CarroInexistenteException;
import org.treinamento.cleancode.exception.EstacionamentoException;
import org.treinamento.cleancode.exception.EstacionamentoFechadoException;
import org.treinamento.cleancode.exception.EstacionamentoLotadoException;
import org.treinamento.cleancode.exception.HoraDeSaidaException;
import org.treinamento.cleancode.exception.PlacaInvalidaException;
import org.treinamento.cleancode.service.TabelaPrecoFactory;

public class EstacionamentoTest {
    
    private Estacionamento estacionamento;
    private Periodo periodoFuncionamento;
    
    private static final int PERIODO_INICIAL = 8;
    private static final int PERIODO_FINAL = 22;
    
    @Before
    public void criaEstacionamento() throws HoraDeSaidaException {
    	periodoFuncionamento = new Periodo(PERIODO_INICIAL, PERIODO_FINAL);
        estacionamento = new Estacionamento(500, periodoFuncionamento);
    }
    
    @Test
    public void deveTer500VagasDisponiveis() throws EstacionamentoException {
        int numeroDeVagas = estacionamento.getVagasDisponiveis();
        assertEquals(500, numeroDeVagas);
    }
    
    @Test
    public void deveEntrarUmCarro() throws EstacionamentoException {
        estacionamento.entraUmCarro("MBC-1009", Periodo.as(8));
        int numeroDeVagas = estacionamento.getVagasDisponiveis();
        assertEquals(499, numeroDeVagas);
    }
    
    @Test
    public void deveSairUmCarro() throws CarroDuplicadoException, EstacionamentoException {
        estacionamento.entraUmCarro("MBC-1009", Periodo.as(8));
        int numeroDeVagas = estacionamento.getVagasDisponiveis();
        assertEquals(499, numeroDeVagas);
        
        estacionamento.sairUmCarro("MBC-1009", Periodo.as(10));
        numeroDeVagas = estacionamento.getVagasDisponiveis();
        assertEquals(500, numeroDeVagas);
    }
    
    @Test(expected = CarroDuplicadoException.class)
    public void naoPodeCarroDuplicado() throws EstacionamentoException {
        estacionamento.entraUmCarro("MBC-1009", Periodo.as(8));
        estacionamento.entraUmCarro("MBC-1009", Periodo.as(8));
    }

    @Test(expected = EstacionamentoLotadoException.class)
    public void estacionamentoLotado() throws EstacionamentoException {
        estacionamento = new Estacionamento(0, periodoFuncionamento);
        estacionamento.entraUmCarro("MBC-1009", Periodo.as(8));        
    }
    
    @Test(expected = CarroInexistenteException.class)
    public void naoPodeSairCarroQueNaoExiste() throws EstacionamentoException {
        estacionamento.sairUmCarro("MBC-1009", Periodo.as(10));
    }
    
    @Test(expected = PlacaInvalidaException.class)
    public void naoPodeEntrarCarroComPlacaInvalida() throws EstacionamentoException {
        estacionamento.entraUmCarro("MBC1009", Periodo.as(8));
    }
    
    @Test(expected = EstacionamentoFechadoException.class)
    public void tentouEntrarUmCarroForaDoHorairo() throws EstacionamentoException {
    	estacionamento.entraUmCarro("MBC-1009", Periodo.as(7));
    }
    
    @Test(expected = EstacionamentoFechadoException.class)
    public void tentouSairUmCarroForaDoHorario() throws EstacionamentoException {
        estacionamento.entraUmCarro("MBC-1009", Periodo.as(8));
        estacionamento.sairUmCarro("MBC-1009", Periodo.as(23));
    }
    
    @Test(expected = HoraDeSaidaException.class)
    public void umCarroNaoPodeSairAntesDeTerEntrado() throws EstacionamentoException {
    	estacionamento.entraUmCarro("MBC-1009", Periodo.as(10));
    	estacionamento.sairUmCarro("MBC-1009", Periodo.as(9));
    }
    
    @Test
    public void carroPermaneceu3horasNoEstacionamento() throws EstacionamentoException {
    	estacionamento.entraUmCarro("MBC-1009", Periodo.as(8));
    	assertEquals(Integer.valueOf(3), estacionamento.sairUmCarro("MBC-1009", Periodo.as(11)));
    }
    
    @Test
    public void umCarroParouNaPraiaEDevePagar5Reais() throws EstacionamentoException {
    	estacionamento.entraUmCarro("MBC-1009", Periodo.as(8));
    	Integer tempoDePermanencia = estacionamento.sairUmCarro("MBC-1009", Periodo.as(9));
    	int valorTotalEstacionamento = TabelaPrecoFactory.PRAIA.criaTabelaPreco().calcularValor(tempoDePermanencia);
    	assertEquals(5, valorTotalEstacionamento);
    }
    
    @Test
    public void umCarroParouNoCentroPor4HorasEDevePagar40Reais() throws EstacionamentoException {
    	estacionamento.entraUmCarro("MBC-1009", Periodo.as(8));
    	int tempoDePermanencia = estacionamento.sairUmCarro("MBC-1009", Periodo.as(12));
    	int valorTotalEstacionamento = TabelaPrecoFactory.CENTRO.criaTabelaPreco().calcularValor(tempoDePermanencia);
    	assertEquals(40, valorTotalEstacionamento);
    }
    
    @Test
    public void umCarroParouNoShoppingPor2HorasEDevePagar6Reais() throws EstacionamentoException {
    	estacionamento.entraUmCarro("MBC-1009", Periodo.as(8));
    	int tempoDePermanencia = estacionamento.sairUmCarro("MBC-1009", Periodo.as(10));
    	int valorTotalEstacionamento = TabelaPrecoFactory.SHOPPING.criaTabelaPreco().calcularValor(tempoDePermanencia);
    	assertEquals(6, valorTotalEstacionamento);
    }
    
    @Test
    public void umCarroParouNoAeroportoPor3HorasEDevePagar5Reais() throws EstacionamentoException {
    	estacionamento.entraUmCarro("MBC-1009", Periodo.as(8));
    	int tempoDePermanencia = estacionamento.sairUmCarro("MBC-1009", Periodo.as(10));
    	int valorTotalEstacionamento = TabelaPrecoFactory.AEROPORTO.criaTabelaPreco().calcularValor(tempoDePermanencia);
    	assertEquals(5, valorTotalEstacionamento);
    }
    
    
}