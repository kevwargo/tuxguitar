package org.herac.tuxguitar.app.action.impl.view;

import org.herac.tuxguitar.action.TGActionContext;
import org.herac.tuxguitar.action.TGActionManager;
import org.herac.tuxguitar.app.view.dialog.matrix.TGMatrixEditorController;
import org.herac.tuxguitar.editor.action.TGActionBase;
import org.herac.tuxguitar.util.TGContext;

public class TGToggleMatrixEditorAction extends TGActionBase{

	public static final String NAME = "action.gui.toggle-matrix-editor";

	public TGToggleMatrixEditorAction(TGContext context) {
		super(context, NAME);
	}

	protected void processAction(TGActionContext tgActionContext){
		tgActionContext.setAttribute(TGToggleViewAction.ATTRIBUTE_CONTROLLER, new TGMatrixEditorController());
		TGActionManager.getInstance(getContext()).execute(TGToggleViewAction.NAME, tgActionContext);
	}
}
