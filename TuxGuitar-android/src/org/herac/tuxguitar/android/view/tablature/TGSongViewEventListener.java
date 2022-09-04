package org.herac.tuxguitar.android.view.tablature;

import java.util.List;

import org.herac.tuxguitar.editor.event.TGDestroyEvent;
import org.herac.tuxguitar.editor.event.TGRedrawEvent;
import org.herac.tuxguitar.editor.event.TGUpdateEvent;
import org.herac.tuxguitar.editor.event.TGUpdateMeasuresEvent;
import org.herac.tuxguitar.event.TGEvent;
import org.herac.tuxguitar.event.TGEventListener;
import org.herac.tuxguitar.util.TGAbstractContext;

public class TGSongViewEventListener implements TGEventListener {

	private TGSongViewController songView;

	public TGSongViewEventListener(TGSongViewController songView) {
		this.songView = songView;
	}

	@SuppressWarnings("unchecked")
	public void processUpdateEvent(TGEvent event) {
		int type = ((Integer)event.getAttribute(TGUpdateEvent.PROPERTY_UPDATE_MODE)).intValue();
		if( type == TGUpdateEvent.SELECTION ){
			this.songView.updateSelection();

			TGAbstractContext sourceContext = event.getAttribute(TGEvent.ATTRIBUTE_SOURCE_CONTEXT);
			if( sourceContext != null && Boolean.TRUE.equals(sourceContext.getAttribute(TGSongViewSmartMenu.REQUEST_SMART_MENU))) {
				this.songView.getSmartMenu().openSmartMenu(sourceContext);
			}
		}
		else if( type == TGUpdateEvent.MEASURE_UPDATED ){
			this.songView.updateMeasures((List<Integer>) event.getAttribute(TGUpdateMeasuresEvent.PROPERTY_MEASURE_NUMBERS));
		}
		else if( type == TGUpdateEvent.SONG_UPDATED ){
			this.songView.updateTablature();
		}
		else if( type == TGUpdateEvent.SONG_LOADED ){
			this.songView.updateTablature();
			this.songView.resetScroll();
			this.songView.resetCaret();
		}
	}

	public void processRedrawEvent(TGEvent event) {
		int type = ((Integer)event.getAttribute(TGRedrawEvent.PROPERTY_REDRAW_MODE)).intValue();
		if( type == TGRedrawEvent.NORMAL ){
			this.songView.redraw();
		}else if( type == TGRedrawEvent.PLAYING_NEW_BEAT ){
			this.songView.redrawPlayingMode();
		}
	}

	public void processDestroyEvent(TGEvent event) {
		this.songView.dispose();
	}

	public void processEvent(TGEvent event) {
		if( TGRedrawEvent.EVENT_TYPE.equals(event.getEventType()) ) {
			this.processRedrawEvent(event);
		}
		else if( TGUpdateEvent.EVENT_TYPE.equals(event.getEventType()) ) {
			this.processUpdateEvent(event);
		}
		else if( TGDestroyEvent.EVENT_TYPE.equals(event.getEventType()) ) {
			this.processDestroyEvent(event);
		}
	}
}
