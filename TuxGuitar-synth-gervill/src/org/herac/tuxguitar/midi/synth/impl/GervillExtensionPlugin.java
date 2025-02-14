package org.herac.tuxguitar.midi.synth.impl;

import java.util.ArrayList;
import java.util.List;

import org.herac.tuxguitar.midi.synth.TGMidiProcessorFactory;
import org.herac.tuxguitar.midi.synth.TGSynthExtension;
import org.herac.tuxguitar.midi.synth.TGSynthExtensionPlugin;
import org.herac.tuxguitar.midi.synth.ui.TGAudioProcessorUIFactory;
import org.herac.tuxguitar.util.TGContext;
import org.herac.tuxguitar.util.plugin.TGPluginException;

public class GervillExtensionPlugin extends TGSynthExtensionPlugin {

	public static final String MODULE_ID = "tuxguitar-synth-gervill";

	public String getModuleId() {
		return MODULE_ID;
	}

	@Override
	public List<TGSynthExtension<?>> createExtensions(TGContext context) throws TGPluginException {
		List<TGSynthExtension<?>> extensions = new ArrayList<TGSynthExtension<?>>();
		extensions.add(new TGSynthExtension<TGMidiProcessorFactory>(TGMidiProcessorFactory.class, new GervillProcessorFactory(context)));
		extensions.add(new TGSynthExtension<TGAudioProcessorUIFactory>(TGAudioProcessorUIFactory.class, new GervillProcessorUIFactory(context)));
		return extensions;
	}
}
