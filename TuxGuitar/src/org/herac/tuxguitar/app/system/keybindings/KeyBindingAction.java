package org.herac.tuxguitar.app.system.keybindings;

import org.herac.tuxguitar.app.lisp.actions.LispActionContext;
import org.herac.tuxguitar.ui.resource.UIKeyCombination;

public class KeyBindingAction {

	private String action;
	private UIKeyCombination combination;
    private LispActionContext lispContext;

	public KeyBindingAction(String action, UIKeyCombination combination) {
		this.action = action;
		this.combination = combination;
	}

	public KeyBindingAction(String action, UIKeyCombination combination, LispActionContext lispContext) {
		this(action, combination);
        this.lispContext = lispContext;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public UIKeyCombination getCombination() {
		return this.combination;
	}

	public void setCombination(UIKeyCombination combination) {
		this.combination = combination;
	}

    public LispActionContext getLispContext() {
        return lispContext;
    }

    public void setLispContext(LispActionContext lispContext) {
        this.lispContext = lispContext;
    }
}
