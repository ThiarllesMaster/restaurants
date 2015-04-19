package br.com.db.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class DataUtils {
	
	
	
	/**
	 * Método desenvolvido para a verificacao de diferenca entre datas
	 * @param dataUltimoVoto
	 * @param dataVoto
	 * @return
	 */
	public static final int verificarPeriodo(Date dataUltimoVoto, Date dataVoto){
	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String ultimoVoto = dateFormat.format(dataUltimoVoto);
		String dataVotoInf = dateFormat.format(dataVoto);
		String[]u = ultimoVoto.split("/");
		String[]d = dataVotoInf.split("/");
		
		int uA = Integer.parseInt(u[2]);
		int uM = Integer.parseInt(u[1]);
		int uD = Integer.parseInt(u[0]);
		
		int dA = Integer.parseInt(d[2]);
		int dM = Integer.parseInt(d[1]);
		int dD = Integer.parseInt(d[0]);
		
		DateTime dataInicial = new DateTime(uA, uM, uD, 0, 0);
		DateTime dataFinal = new DateTime(dA, dM, dD, 0, 0);
		
		return Days.daysBetween(dataInicial, dataFinal).getDays();
		
				
	}

	
}
