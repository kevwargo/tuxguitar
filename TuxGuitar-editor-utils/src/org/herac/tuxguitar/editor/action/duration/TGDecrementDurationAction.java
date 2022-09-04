package org.herac.tuxguitar.editor.action.duration;

import org.herac.tuxguitar.action.TGActionContext;
import org.herac.tuxguitar.action.TGActionManager;
import org.herac.tuxguitar.document.TGDocumentContextAttributes;
import org.herac.tuxguitar.editor.action.TGActionBase;
import org.herac.tuxguitar.song.models.TGDuration;
import org.herac.tuxguitar.util.TGContext;

public class TGDecrementDurationAction extends TGActionBase {

	public static final String NAME = "action.note.duration.decrement-duration";

	public TGDecrementDurationAction(TGContext context) {
		super(context, NAME);
	}

	protected void processAction(TGActionContext context) {
		TGDuration duration = ((TGDuration) context.getAttribute(TGDocumentContextAttributes.ATTRIBUTE_DURATION));

		if( duration.getValue() > TGDuration.WHOLE ){
			duration.setValue(duration.getValue() / 2);
			duration.setDotted(false);
			duration.setDoubleDotted(false);

			TGActionManager tgActionManager = TGActionManager.getInstance(getContext());
			tgActionManager.execute(TGSetDurationAction.NAME, context);
		}
	}
}
