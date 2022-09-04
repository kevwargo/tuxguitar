package org.herac.tuxguitar.app.action.impl.measure;

import org.herac.tuxguitar.action.TGActionContext;
import org.herac.tuxguitar.app.transport.TGTransport;
import org.herac.tuxguitar.app.view.component.tab.Caret;
import org.herac.tuxguitar.app.view.component.tab.TablatureEditor;
import org.herac.tuxguitar.editor.action.TGActionBase;
import org.herac.tuxguitar.player.base.MidiPlayer;
import org.herac.tuxguitar.song.models.TGMeasure;
import org.herac.tuxguitar.song.models.TGTrack;
import org.herac.tuxguitar.util.TGContext;

public class TGGoPreviousMeasureAction extends TGActionBase{

	public static final String NAME = "action.measure.go-previous";

	public TGGoPreviousMeasureAction(TGContext context) {
		super(context, NAME);
	}

	protected void processAction(TGActionContext context){
		if( MidiPlayer.getInstance(getContext()).isRunning() ){
			TGTransport.getInstance(getContext()).gotoPrevious();
		}
		else{
			Caret caret = TablatureEditor.getInstance(getContext()).getTablature().getCaret();
			TGTrack track = caret.getTrack();
			TGMeasure measure = getSongManager(context).getTrackManager().getPrevMeasure(caret.getMeasure());
			if( track != null && measure != null ){
				caret.update(track.getNumber(), measure.getStart(), caret.getSelectedString().getNumber());
			}
		}
	}
}
