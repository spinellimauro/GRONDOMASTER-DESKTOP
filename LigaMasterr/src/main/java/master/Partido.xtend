package master

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.Collections
import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors
import org.uqbar.commons.utils.Observable
import org.uqbar.commons.model.ObservableUtils

@Observable
@Accessors
class Partido {
	int numeroFecha = 0
	DT dtLocal
	DT dtVisitante
	boolean terminado = false
	@JsonIgnore
	Torneo torneo
	@JsonIgnore
	List<Jugador> golesLocal = newArrayList
	@JsonIgnore
	List<Jugador> golesVisitante = newArrayList
	@JsonIgnore
	List<Jugador> listaAmarillas = newArrayList
	@JsonIgnore
	List<Jugador> listaRojas = newArrayList

	def boolean getJugoPartido(DT dt) {
		dtLocal.equals(dt) || dtVisitante.equals(dt)
	}

	// Goles
	def String getScore() {
		golesLocal.size + " - " + golesVisitante.size
	}

	def int getGoles(Jugador jugador) {
		Collections.frequency((golesLocal + golesVisitante).toList, jugador)
	}

	def void addGol(Jugador jugador) {
		if(dtLocal.getListaJugadores.contains(jugador)) golesLocal.add(jugador) else golesVisitante.add(jugador)
		ObservableUtils.firePropertyChanged(this, "score") 
	}

	def void removeGol(Jugador jugador) {
		if(dtLocal.getListaJugadores.contains(jugador)) golesLocal.remove(jugador) else golesVisitante.remove(jugador)
		ObservableUtils.firePropertyChanged(this, "score") 
	}

	// Amonestaciones
	def void addAmarilla(Jugador jugador) {
		if(getAmarillas(jugador) < 2 && getRojas(jugador) < 1) listaAmarillas.add(jugador)
	}

	def void removeAmarilla(Jugador jugador) {
		listaAmarillas.remove(jugador)
	}

	def void addRoja(Jugador jugador) {
		if(getRojas(jugador) < 1 && getAmarillas(jugador) < 2) listaRojas.add(jugador)
	}

	def void removeRoja(Jugador jugador) {
		listaRojas.remove(jugador)
	}

	def boolean fueAmonestado(Jugador jugador) {
		getAmarillas(jugador) == 1
	}

	def boolean fueExpulsado(Jugador jugador) {
		getAmarillas(jugador) == 2 || getRojas(jugador) == 1
	}

	// Estadisticas - Jugador
	def int getAmarillas(Jugador jugador) {
		Collections.frequency(listaAmarillas, jugador)
	}

	def int getRojas(Jugador jugador) {
		Collections.frequency(listaRojas, jugador)
	}

	// Estadisticas - DT
	def int getPuntos(DT dt) {
		if (dt.equals(dtLocal)) {
			if (golesLocal.size > golesVisitante.size)
				3
			else if(golesLocal.size < golesVisitante.size) 0 else 1
		} else {
			if (golesLocal.size < golesVisitante.size)
				3
			else if(golesLocal.size > golesVisitante.size) 0 else 1
		}
	}

	def getGolesFavor(DT dt) {
		if(dt.equals(dtLocal)) golesLocal.size else golesVisitante.size
	}

	def getGolesContra(DT dt) {
		if(dt.equals(dtLocal)) golesVisitante.size else golesLocal.size
	}

	// Listas
	@JsonIgnore
	def List<Jugador> getSuspendidos() {
		(dtLocal.listaJugadores + dtVisitante.listaJugadores).filter[torneo.estaSuspendido(it, numeroFecha)].toList
	}
	
	@JsonIgnore
	def List<Jugador> getLesionados() {
		(dtLocal.listaJugadores + dtVisitante.listaJugadores).filter[estaLesionado].toList
	}

	// Terminar Partido
	def void terminarPartido() {
		if (terminado)
			throw new Exception("El partido ya terminó")

		terminado = true
		lesionados.forEach[decLesion]
		sumarRanking()
		dtLocal.incPlata(getPremio(dtLocal))
		dtVisitante.incPlata(getPremio(dtVisitante))
	}
	
	def double getPremio(DT dt) {
		if (dt.puntos == 3)
			torneo.premios.getPremioEvento("Victoria") +
				torneo.premios.getPremioEvento("Gol") * ( getGolesFavor(dt) - getGolesContra(dt) )
		else if (dt.puntos == 1)
			torneo.premios.getPremioEvento("Empate")
		else
			0
	}
	
	def boolean ganoLocal(){
		if (golesLocal.size() > golesVisitante.size()) true
		else false
	}
	
	def boolean ganoVisitante(){
		if (golesLocal.size() < golesVisitante.size()) true
		else false
	}
	
	def sumarRanking() {
		if (ganoLocal()){
			rankVictoria(dtLocal, dtVisitante)
		}
		else if (ganoVisitante()){
			rankVictoria(dtVisitante, dtLocal)		
		}
		else if(empataron()){
				empate()
		}
	}
	
	def empataron() {
		if (golesLocal.size() == golesVisitante.size()) true
		else false
	}
	
	
	def void rankVictoria(DT dtGanador, DT dtPerdedor){
		// rankings anteriores
		var PbeforeLocal = dtGanador.rank
		var PbeforeVisitante = dtPerdedor.rank
		
		// importancia del partido 
		val I = 10.0
		
		// E = empate 
		val W = 1.0
		val D = 0.0
		// formula del ranking
		
		var dr = PbeforeLocal - PbeforeVisitante
		var WeG	= 1.0	/ (Math.pow(10.0,(-dr/6.0)) +	1.0)
		var WeP	= 1.0	/ (Math.pow(10.0,(-dr/600.0)) +	1.0)
		var PA = PbeforeLocal + I * (W - WeG)
		var PB = PbeforeVisitante + I * (D - WeP)
			
		// en caso de ser menor o igual a 0 dejarlo en 0
		
		if (PA < 0){
			PA = 0
		}
				
		if (PB < 0){
			PB = 0
		}
		
		dtGanador.setRank(PA)
		dtPerdedor.setRank(PB)
		LigaMaster.instance.guardarBase()
						
	}
	
	def void empate(){
		// rankings anteriores
		var PbeforeLocal = dtLocal.rank
		var PbeforeVisitante = dtVisitante.rank
		
		// importancia del partido 
		val I = 10.0
		
		// E = empate 
		val E = 0.5

		// formula del ranking
		
		var dr = PbeforeLocal - PbeforeVisitante
		var We = 1.0 / 10.0 * ((-dr / 60.0) + 1.0)
		var PA = PbeforeLocal + I * (E - We)
		var PB = PbeforeVisitante + I * (E - We)
			
		// en caso de ser menor o igual a 0 dejarlo en 0
		
		if (PA < 0){
			PA = 0
		}
				
		if (PB < 0){
			PB = 0
		}
		
		dtLocal.setRank(PA)
		dtVisitante.setRank(PB)
		LigaMaster.instance.guardarBase()
	}
	
}
