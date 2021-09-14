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
import org.uqbar.arena.widgets.Button

class RankWindow extends Dialog<RankModel> {

	new(WindowOwner parent, DT model) {
		super(parent, new RankModel(model))
		title = "GrondoRanking"
	}

	override createMainTemplate(Panel panel) {
		new Table(panel, DT) => [
			bindItemsToProperty("dts")
			numberVisibleRows = 20
			
			new Column(it) => [
				title = "DT"
				bindContentsToProperty("nombreDT")
				fixedSize = 120
			]

			new Column(it) => [
				title = "PUNTOS"
				bindContentsToProperty("rank")
				fixedSize = 120
			]
			
			new Button(panel) => [
				caption = "Iniciar Rank"
				onClick[modelObject.iniciarRank()]
				fontSize = 10
			]
		]
	}

	override protected addActions(Panel actionsPanel) {}

	override protected createFormPanel(Panel mainPanel) {}
}

@Observable
@Accessors
class RankModel {
	DT dtON
	List<DT> dts = newArrayList
	
	new(DT model){
		dtON = model
		dts = LigaMaster.instance.listaDT.sortBy[rank].reverse
	}
	
	def iniciarRank() {
		dts.forEach[rank = 1.0]
	}
	

	
}



