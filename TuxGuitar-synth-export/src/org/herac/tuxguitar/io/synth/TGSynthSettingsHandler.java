package org.herac.tuxguitar.io.synth;

import org.herac.tuxguitar.app.io.persistence.TGPersistenceSettingsHandler;
import org.herac.tuxguitar.app.io.persistence.TGPersistenceSettingsMode;
import org.herac.tuxguitar.io.base.TGFileFormat;
import org.herac.tuxguitar.io.base.TGSongStreamContext;
import org.herac.tuxguitar.util.TGContext;
import org.herac.tuxguitar.util.TGSynchronizer;

public class TGSynthSettingsHandler implements TGPersistenceSettingsHandler {

	private TGContext context;

	public TGSynthSettingsHandler(TGContext context) {
		this.context = context;
	}

	public TGFileFormat getFileFormat() {
		return TGSynthSongWriter.FILE_FORMAT;
	}

	public TGPersistenceSettingsMode getMode() {
		return TGPersistenceSettingsMode.WRITE;
	}

	public void handleSettings(final TGSongStreamContext context, final Runnable callback) {
		TGSynchronizer.getInstance(this.context).executeLater(new Runnable() {
			public void run() {
				final TGSynthAudioSettings midiToAudioSettings = new TGSynthAudioSettings();

				new TGSynthSettingsDialog(TGSynthSettingsHandler.this.context).open(midiToAudioSettings, new Runnable() {
					public void run() {
						context.setAttribute(TGSynthAudioSettings.class.getName(), midiToAudioSettings);
						context.setAttribute(TGFileFormat.class.getName(), new TGSynthAudioFormat(midiToAudioSettings));
						callback.run();
					}
				});
			}
		});
	}
}
