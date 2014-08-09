package org.treinamento.cleancode.model;

import java.util.Calendar;
import java.util.Date;

import org.treinamento.cleancode.exception.HoraDeSaidaException;

public class Periodo {

	private Date periodoInicial; 
	private Date periodoFinal;
	
	public Periodo(int horaInicio, int horaFim) throws HoraDeSaidaException {
		this(as(horaInicio) , as(horaFim));
	}
	
	public Periodo(Date periodoInicial, Date periodoFinal) throws HoraDeSaidaException {
		if (periodoInicial == null || periodoFinal == null) throw new IllegalArgumentException();
		if (periodoFinal.before(periodoInicial)) throw new HoraDeSaidaException();
		this.periodoInicial = periodoInicial;
		this.periodoFinal = periodoFinal;
	}

    public boolean isPeriodoForaDeFuncionamento(Date hora) {
    	return hora.before(periodoInicial)  || hora.after(periodoFinal);
	}
    
	public Integer getHorasPermanencia() {
		Calendar entrada = Calendar.getInstance();
		entrada.setTime(periodoInicial);
		Calendar saida = Calendar.getInstance();
		saida.setTime(periodoFinal);
		return saida.get(Calendar.HOUR_OF_DAY) - entrada.get(Calendar.HOUR_OF_DAY);
	}
	
    public static Date as(int horaDeEntrada) {
    	Calendar calendario = Calendar.getInstance();
    	calendario.set(Calendar.HOUR_OF_DAY, horaDeEntrada);
    	return calendario.getTime();    	
    }
	
}
