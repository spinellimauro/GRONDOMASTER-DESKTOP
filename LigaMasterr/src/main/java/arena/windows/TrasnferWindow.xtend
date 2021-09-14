package arena.windows

import java.util.List
import master.DT
import master.LigaMaster
import org.eclipse.xtend.lib.annotations.Accessors
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.utils.Observable
import master.Transferencia
import datos.Mercado

class TransferWindow extends Dialog<TransferModel> {

	new(WindowOwner parent, DT model) {
		super(parent, new TransferModel(model))
		title = "Transferencias"
	}

	override createMainTemplate(Panel panel) {
		new Table(panel, Transferencia) => [
			bindItemsToProperty("transferencias")
			numberVisibleRows = 30
			
			new Column(it) => [
				title = "VENDEDOR"
				bindContentsToProperty("dtVende")
				fixedSize = 120
			]

			new Column(it) => [
				title = "COMPRADOR"
				bindContentsToProperty("dtCompra")
				fixedSize = 120
			]
			
			new Column(it) => [
				title = "MONTO"
				bindContentsToProperty("monto")
				fixedSize = 120
			]

			new Column(it) => [
				title = "JUGADOR"
				bindContentsToProperty("jugadorComprado")
				fixedSize = 120
			]
			
			new Column(it) => [
				title = "NIVEL"
				bindContentsToProperty("nivel")
				fixedSize = 120
			]
			
		]
	}

	override protected addActions(Panel actionsPanel) {}

	override protected createFormPanel(Panel mainPanel) {}
}

@Observable
@Accessors
class TransferModel {
	DT dtON
	List<Transferencia> transferencias = newArrayList

	new(DT model){
		dtON = model
		transferencias = LigaMaster.instance.mercado.listaTraspasos.reverse
	}
	

	
}



