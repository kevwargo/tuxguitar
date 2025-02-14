package org.herac.tuxguitar.player.impl.midiport.oss;

import java.util.List;

import org.herac.tuxguitar.app.system.plugins.TGPluginSettingsAdapter;
import org.herac.tuxguitar.app.system.plugins.TGPluginSettingsHandler;
import org.herac.tuxguitar.util.TGContext;
import org.herac.tuxguitar.util.plugin.TGPluginException;
import org.herac.tuxguitar.util.plugin.TGPluginManager;

public class MidiSettingsPlugin extends TGPluginSettingsAdapter {

	protected TGPluginSettingsHandler createHandler(TGContext context) throws TGPluginException {
		return new MidiSettingsHandler(context, this);
	}

	public String getModuleId() {
		return MidiPlugin.MODULE_ID;
	}

	public MidiOutputPortProviderImpl findMidiOutputPortProvider(TGContext context){
		MidiOutputPortProviderPlugin plugin = findMidiOutputPortProviderPlugin(context);
		if( plugin != null ){
			return (MidiOutputPortProviderImpl) plugin.createProvider(context);
		}
		return null;
	}

	private MidiOutputPortProviderPlugin findMidiOutputPortProviderPlugin(TGContext context){
		List<MidiOutputPortProviderPlugin> pluginInstances = TGPluginManager.getInstance(context).getPluginInstances(MidiOutputPortProviderPlugin.class);
		if( pluginInstances != null && !pluginInstances.isEmpty() ){
			return pluginInstances.get(0);
		}
		return null;
	}
}
