package org.herac.tuxguitar.editor.action.effect;

import org.herac.tuxguitar.action.TGActionContext;
import org.herac.tuxguitar.document.TGDocumentContextAttributes;
import org.herac.tuxguitar.editor.action.TGActionBase;
import org.herac.tuxguitar.song.models.TGBeat;
import org.herac.tuxguitar.song.models.TGMeasure;
import org.herac.tuxguitar.song.models.TGString;
import org.herac.tuxguitar.song.models.effects.TGEffectGrace;
import org.herac.tuxguitar.util.TGContext;

public class TGChangeGraceNoteAction extends TGActionBase {

	public static final String NAME = "action.note.effect.change-grace";

	public static final String ATTRIBUTE_EFFECT = TGEffectGrace.class.getName();

	public TGChangeGraceNoteAction(TGContext context) {
		super(context, NAME);
	}

	protected void processAction(TGActionContext context){
		TGMeasure measure = ((TGMeasure) context.getAttribute(TGDocumentContextAttributes.ATTRIBUTE_MEASURE));
		TGBeat beat = ((TGBeat) context.getAttribute(TGDocumentContextAttributes.ATTRIBUTE_BEAT));
		TGString string = ((TGString) context.getAttribute(TGDocumentContextAttributes.ATTRIBUTE_STRING));
		TGEffectGrace effect = ((TGEffectGrace) context.getAttribute(ATTRIBUTE_EFFECT));

		getSongManager(context).getMeasureManager().changeGraceNote(measure, beat.getStart(), string.getNumber(), effect);
	}
}
