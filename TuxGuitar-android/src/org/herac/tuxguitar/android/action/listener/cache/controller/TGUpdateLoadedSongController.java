package org.herac.tuxguitar.android.action.listener.cache.controller;

import org.herac.tuxguitar.action.TGActionContext;
import org.herac.tuxguitar.editor.undo.TGUndoableManager;
import org.herac.tuxguitar.player.base.MidiPlayer;
import org.herac.tuxguitar.util.TGContext;

public class TGUpdateLoadedSongController extends TGUpdateItemsController {

	public TGUpdateLoadedSongController() {
		super();
	}

	@Override
	public void update(TGContext context, TGActionContext actionContext) {
		MidiPlayer midiPlayer = MidiPlayer.getInstance(context);
		midiPlayer.reset();
		midiPlayer.getMode().clear();
		midiPlayer.resetChannels();

		TGUndoableManager.getInstance(context).discardAllEdits();

		this.findUpdateBuffer(context).requestUpdateLoadedSong();

		super.update(context, actionContext);
	}
}
