package org.herac.tuxguitar.editor.action.measure;

import org.herac.tuxguitar.action.TGActionContext;
import org.herac.tuxguitar.document.TGDocumentContextAttributes;
import org.herac.tuxguitar.editor.action.TGActionBase;
import org.herac.tuxguitar.song.managers.TGSongManager;
import org.herac.tuxguitar.song.models.TGSong;
import org.herac.tuxguitar.util.TGContext;

public class TGAddMeasureAction extends TGActionBase {

	public static final String NAME = "action.measure.add";

	public static final String ATTRIBUTE_MEASURE_NUMBER = "measureNumber";

	public TGAddMeasureAction(TGContext context) {
		super(context, NAME);
	}

	protected void processAction(TGActionContext context){
		TGSong song = ((TGSong) context.getAttribute(TGDocumentContextAttributes.ATTRIBUTE_SONG));
		int number = ((Integer) context.getAttribute(ATTRIBUTE_MEASURE_NUMBER)).intValue();

		if( number > 0 && number <=  (song.countMeasureHeaders() + 1)){
			TGSongManager tgSongManager = getSongManager(context);
			tgSongManager.addNewMeasure(song, number);
		}
	}
}
