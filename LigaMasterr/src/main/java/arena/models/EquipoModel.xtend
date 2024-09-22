package arena.models

import datos.Precios
import datos.SoFifa
import java.util.List
import master.DT
import master.Jugador
import master.LigaMaster
import master.Torneo
import org.eclipse.xtend.lib.annotations.Accessors
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.uqbar.commons.model.ObservableUtils
import org.uqbar.commons.utils.Dependencies
import master.Transferencia

@Observable
@Accessors
class EquipoModel {
	Torneo torneoON
	DT dtActivo
	DT dtON
	Jugador jugadorON = new Jugador

	String textoBusqueda = ""
	List<Jugador> listaMaquina = newArrayList

	new(TorneoModel model) {
		torneoON = model.torneoON
		dtActivo = model.dtON
		dtON = model.dtON
	}

	def void validar() {
		if (jugadorON == null)
			throw new UserException("Debe seleccionar un jugador")
	}

	def void validarOfertar() {
		validar

		if (dtActivo.listaJugadores.contains(jugadorON))
			throw new UserException("Ese jugador es tuyo")

		if (listaMaquina.contains(jugadorON))
			throw new UserException("Ese jugador es de la Máquina")
	}

	def List<DT> getListaDT() {
		LigaMaster.instance.listaDT.sortBy[nombreDT]
	}

	@Dependencies("jugadorON")
	def boolean getJugadorPropio() {
		dtActivo.listaJugadores.contains(jugadorON)
	}

	def void comprarSlot() {
		try{
			dtActivo.comprarSlot
			LigaMaster.instance.guardarBase
		}catch (Exception e)
			throw new UserException(e.message)
	}

	def void venderAMaquina() {
		
			val jugador = jugadorON
			dtActivo.venderJugador(jugador, (jugador.precioMaquina / Precios.instance.getPrecio("PrecioMaquina")))
			
			var compraMaquina = new Transferencia => [
			
				dtVende = dtActivo.nombreDT
				dtCompra = "Libre"
				monto = (jugador.precioMaquina / 4.0)
				jugadorComprado = jugador.nombre
				nivel = jugador.nivel
			]
			
			LigaMaster.instance.mercado.agregarTransferencia(compraMaquina)
			LigaMaster.instance.guardarBase()
	
	}

	// Armado de Equipo
	def void transferIn() {
		validar
		
		if(dtON.listaJugadores.contains(jugadorON)){
			throw new UserException("Ya tiene ese jugador")	
		}
		
		jugadorON.propietario.removeJugador(jugadorON)
		dtON.addJugador(jugadorON)
		listaMaquina.remove(jugadorON)
		LigaMaster.instance.guardarBase

		ObservableUtils.firePropertyChanged(this, "listaMaquina")
		ObservableUtils.firePropertyChanged(this, "dtON")
	}

	def void transferOut() {
		validar
		
		listaMaquina.add(jugadorON)
		dtON.removeJugador(jugadorON)
		LigaMaster.instance.guardarBase

		ObservableUtils.firePropertyChanged(this, "listaMaquina")
		ObservableUtils.firePropertyChanged(this, "dtON")
	}

	def boolean esMaster() {
		dtON.equals(LigaMaster.instance.master)
	}

	// Maquina
	def void buscar() {
		listaMaquina.clear
		listaMaquina.addAll(SoFifa.instance.getJugadores(textoBusqueda))
	}

	def void comprarAMaquina() {
		validar
		
		if (!listaMaquina.contains(jugadorON))
			throw new UserException("Ese jugador no es de la Máquina")

		if (!jugadorON.propietario.nombreDT.equals("Libre"))
			throw new UserException("Ese jugador no está Libre")

		val jugadorMaquina = jugadorON

		try{
			dtON.comprarJugador(jugadorMaquina, jugadorMaquina.precioMaquina)
			LigaMaster.instance.guardarBase
		}catch (Exception e)
			throw new UserException(e.message)
			
		listaMaquina.remove(jugadorMaquina)
		LigaMaster.instance.guardarBase()
	}
	
	def updatePlantel() {
		try{
			dtON.listaJugadores.forEach[
				try{update}catch (Exception e){}
			]
			LigaMaster.instance.guardarBase
			
		}catch (Exception e)
			throw new UserException("No se completo el update. 1:Fijarse si hay jugadores que no estan en sofifa 2:Internet")
		
	}
	
}
