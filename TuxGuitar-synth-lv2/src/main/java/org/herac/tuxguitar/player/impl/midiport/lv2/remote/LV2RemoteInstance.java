package org.herac.tuxguitar.player.impl.midiport.lv2.remote;

import java.util.List;

import org.herac.tuxguitar.midi.synth.remote.TGConnection;
import org.herac.tuxguitar.midi.synth.remote.TGRemoteException;
import org.herac.tuxguitar.midi.synth.remote.TGRemoteHost;
import org.herac.tuxguitar.midi.synth.remote.TGSession;
import org.herac.tuxguitar.player.impl.midiport.lv2.jni.LV2Plugin;
import org.herac.tuxguitar.player.impl.midiport.lv2.remote.command.LV2ProcessAudioCommand;
import org.herac.tuxguitar.player.impl.midiport.lv2.remote.command.LV2ProcessCloseUICommand;
import org.herac.tuxguitar.player.impl.midiport.lv2.remote.command.LV2ProcessFocusUICommand;
import org.herac.tuxguitar.player.impl.midiport.lv2.remote.command.LV2ProcessGetControlPortValueCommand;
import org.herac.tuxguitar.player.impl.midiport.lv2.remote.command.LV2ProcessGetStateCommand;
import org.herac.tuxguitar.player.impl.midiport.lv2.remote.command.LV2ProcessIsUIAvailableCommand;
import org.herac.tuxguitar.player.impl.midiport.lv2.remote.command.LV2ProcessIsUIOpenCommand;
import org.herac.tuxguitar.player.impl.midiport.lv2.remote.command.LV2ProcessMidiMessageCommand;
import org.herac.tuxguitar.player.impl.midiport.lv2.remote.command.LV2ProcessOpenUICommand;
import org.herac.tuxguitar.player.impl.midiport.lv2.remote.command.LV2ProcessSetControlPortValueCommand;
import org.herac.tuxguitar.player.impl.midiport.lv2.remote.command.LV2ProcessSetStateCommand;
import org.herac.tuxguitar.util.TGContext;

public class LV2RemoteInstance {

	private TGSession session;

	public LV2RemoteInstance(TGContext context, LV2Plugin plugin, int bufferSize) throws TGRemoteException {
		this.session = TGRemoteHost.getInstance(context).createSession(new LV2ClientStarter(context, plugin, bufferSize));
	}

	public boolean isClosed(){
		return this.session.isClosed();
	}

	public TGSession getSession() {
		return this.session;
	}

	public TGConnection getConnection() {
		return this.session.getConnection();
	}

	public void close(){
		if(!this.isClosed() ){
			this.session.close();
		}
	}

	public void setState(String state) {
		if(!this.isClosed() ){
			new LV2ProcessSetStateCommand(this.getConnection(), state).safeProcess();
		}
	}

	public String getState() {
		if(!this.isClosed() ){
			return new LV2ProcessGetStateCommand(this.getConnection()).safeProcess();
		}
		return null;
	}

	public void setControlPortValue(int index, float value) {
		if(!this.isClosed() ){
			new LV2ProcessSetControlPortValueCommand(this.getConnection(), index, value).safeProcess();
		}
	}

	public float getControlPortValue(int index) {
		if(!this.isClosed() ){
			return new LV2ProcessGetControlPortValueCommand(this.getConnection(), index).safeProcess(0f);
		}
		return 0f;
	}

	public void processMidiMessages(List<byte[]> messages) {
		if(!this.isClosed() ){
			new LV2ProcessMidiMessageCommand(this.getConnection(), messages).safeProcess();
		}
	}

	public boolean processAudio(float[][] inputs, float[][] outputs) {
		if(!this.isClosed() ){
			return new LV2ProcessAudioCommand(this.getConnection(), inputs, outputs).safeProcess(false);
		}
		return false;
	}

	public void openUI(){
		if(!this.isClosed() ){
			new LV2ProcessOpenUICommand(this.getConnection()).safeProcess();
		}
	}

	public void closeUI(){
		if(!this.isClosed() ){
			new LV2ProcessCloseUICommand(this.getConnection()).safeProcess();
		}
	}

	public void focusUI(){
		if(!this.isClosed() ){
			new LV2ProcessFocusUICommand(this.getConnection()).safeProcess();
		}
	}

	public boolean isUIOpen(){
		if(!this.isClosed() ){
			return new LV2ProcessIsUIOpenCommand(this.getConnection()).safeProcess(false);
		}
		return false;
	}

	public boolean isUIAvailable(){
		if(!this.isClosed() ){
			return new LV2ProcessIsUIAvailableCommand(this.getConnection()).safeProcess(false);
		}
		return false;
	}
}
