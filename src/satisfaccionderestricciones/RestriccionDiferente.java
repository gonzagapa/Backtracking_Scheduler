package satisfaccionderestricciones;

import java.util.Arrays;
import java.util.List;

public class RestriccionDiferente implements Restriccion {

	protected final String variable1;
	protected final String variable2;

	public RestriccionDiferente(String var1, String var2) {
		this.variable1 = var1;
		this.variable2 = var2;
	}

	@Override
	public List<String> getVariables() {
    String[] variables ={variable1,variable2};
		return Arrays.asList(variables);
	}

	@Override
	public boolean esSatisfecha(Estado solucion) {
	  Object valor1 = solucion.getAsignacion(variable1);
		Object valor2 = solucion.getAsignacion(variable2);
    return valor1==null || valor2==null || !valor1.equals(valor2);
	}
}

