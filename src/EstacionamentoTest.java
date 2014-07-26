import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EstacionamentoTest {
	
	private Estacionamento estacionamento;
	
	@Before
	public void criaEstacionamento() {
		estacionamento = new Estacionamento(500);
	}
	
	@Test
	public void deveTer500VagasDisponiveis() throws EstacionamentoLotadoException {
		int numeroDeVagas = estacionamento.getVagasDisponiveis();
		assertEquals(500, numeroDeVagas);
	}
	
	@Test
	public void deveEntrarUmCarro() throws CarroDuplicadoException, EstacionamentoLotadoException, PlacaInvalidaException {
		estacionamento.entraUmCarro("MBC-1009");
		int numeroDeVagas = estacionamento.getVagasDisponiveis();
		assertEquals(499, numeroDeVagas);
	}
	
	@Test
	public void deveSairUmCarro() throws CarroDuplicadoException, EstacionamentoLotadoException, CarroInexistenteException, PlacaInvalidaException {
		estacionamento.entraUmCarro("MBC-1009");
		int numeroDeVagas = estacionamento.getVagasDisponiveis();
		assertEquals(499, numeroDeVagas);
		
		estacionamento.sairUmCarro("MBC-1009");
		numeroDeVagas = estacionamento.getVagasDisponiveis();
		assertEquals(500, numeroDeVagas);
	}
	
	@Test(expected = CarroDuplicadoException.class)
	public void naoPodeCarroDuplicado() throws CarroDuplicadoException, EstacionamentoLotadoException, PlacaInvalidaException {
		estacionamento.entraUmCarro("MBC-1009");
		estacionamento.entraUmCarro("MBC-1009");
	}

	@Test(expected = EstacionamentoLotadoException.class)
	public void estacionamentoLotado() throws CarroDuplicadoException, EstacionamentoLotadoException, PlacaInvalidaException {
		estacionamento = new Estacionamento(0);
		estacionamento.entraUmCarro("MBC-1009");		
	}
	
	@Test(expected = CarroInexistenteException.class)
	public void naoPodeSairCarroQueNaoExiste() throws CarroInexistenteException, PlacaInvalidaException {
		estacionamento.sairUmCarro("MBC-1009");
	}
	
	@Test(expected = PlacaInvalidaException.class)
	public void naoPodeEntrarCarroComPlacaInvalida() throws CarroDuplicadoException, EstacionamentoLotadoException, PlacaInvalidaException {
		estacionamento.entraUmCarro("MBC1009");
	}
	
}