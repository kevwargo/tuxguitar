package org.herac.tuxguitar.android.view.dialog.fragment;

import android.app.Dialog;
import android.os.Bundle;

import org.herac.tuxguitar.android.activity.TGActivity;
import org.herac.tuxguitar.android.application.TGApplicationUtil;
import org.herac.tuxguitar.android.fragment.TGFragmentTransaction;
import org.herac.tuxguitar.android.view.dialog.TGDialogContext;
import org.herac.tuxguitar.util.TGContext;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public abstract class TGDialogFragment extends DialogFragment {

	public TGDialogFragment() {
		super();
	}

	public abstract Dialog onCreateDialog();

	public Dialog onCreateDialog(Bundle savedInstanceState) {
		if( this.getDialogContext() != null ) {
			return this.onCreateDialog();
		}
		return null;
	}

	public void onDestroy() {
		this.destroyDialogContext();

		super.onDestroy();
	}

	public void show(FragmentManager manager, String tag) {
		if( manager.isStateSaved() ) {
			this.show(new TGFragmentTransaction(manager, true), tag);
		} else {
			super.show(manager, tag);
		}
	}

	public String getDialogContextKey() {
		return (TGDialogContext.class.getName() + "-" + this.getClass().getName());
	}

	public TGDialogContext getDialogContext() {
		return this.findContext().getAttribute(this.getDialogContextKey());
	}

	public void destroyDialogContext() {
		this.findContext().removeAttribute(this.getDialogContextKey());
	}

	public <T> T getAttribute(String key){
		TGDialogContext dialogContext = this.getDialogContext();
		if( dialogContext != null ) {
			return dialogContext.getAttribute(key);
		}
		return null;
	}

	public TGActivity findActivity() {
		return (TGActivity) getActivity();
	}

	public TGContext findContext() {
		return TGApplicationUtil.findContext(getActivity());
	}
}
