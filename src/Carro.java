public class Carro {
	
	private static final String REGEX_VALIDA_PLACA = "^[A-Z]{3}\\-\\d{4}$";
	private String placa;

	public Carro(String placa) throws PlacaInvalidaException {
		validaPlacaLancaExcecao(placa);
		this.setPlaca(placa);
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	private void validaPlacaLancaExcecao(String placa) throws PlacaInvalidaException {
		if (placa == null || !placa.matches(REGEX_VALIDA_PLACA)) throw new PlacaInvalidaException();
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		return true;
	}

}
