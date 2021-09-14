package master

import org.eclipse.xtend.lib.annotations.Accessors
import org.uqbar.commons.utils.Observable
import java.util.Date

@Observable
@Accessors
class Transferencia {
	String dtCompra
	String dtVende
	double monto
	String jugadorComprado
	int nivel
}