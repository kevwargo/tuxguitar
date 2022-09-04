package org.herac.tuxguitar.app.lisp.functions.impl;

import java.util.Map;

import kevwargo.jlp.LispException;
import kevwargo.jlp.objects.LispBool;
import kevwargo.jlp.objects.LispList;
import kevwargo.jlp.objects.LispObject;
import kevwargo.jlp.objects.LispString;
import kevwargo.jlp.objects.LispType;
import kevwargo.jlp.utils.FormalArguments;
import kevwargo.jlp.utils.LispNamespace;

import org.herac.tuxguitar.app.lisp.actions.LispActionContext;
import org.herac.tuxguitar.app.lisp.functions.TGLispFunction;
import org.herac.tuxguitar.app.system.keybindings.KeyBindingAction;
import org.herac.tuxguitar.app.system.keybindings.KeyBindingActionManager;
import org.herac.tuxguitar.ui.resource.UIKey;
import org.herac.tuxguitar.ui.resource.UIKeyCombination;
import org.herac.tuxguitar.util.TGContext;


public class DefineKeyBinding extends TGLispFunction {

    private static final String NAME = "define-key-binding";
    private static final String ARG_KEYS = "keys";
    private static final String ARG_ACTION = "action";
    private static final String ARG_ARGS = "args";

    public DefineKeyBinding(TGContext context) {
        super(context, NAME, buildArgs());
    }

    private static FormalArguments buildArgs() {
        return new FormalArguments(ARG_KEYS, ARG_ACTION).rest(ARG_ARGS);
    }

    protected LispObject callInternal(LispNamespace namespace, Map<String, LispObject> arguments) throws LispException {
        LispList keys = (LispList)arguments.get(ARG_KEYS).cast(LispType.LIST);
        String action = ((LispString)arguments.get(ARG_ACTION).cast(LispType.STRING)).getValue();
        LispList actionArgs = (LispList)arguments.get(ARG_ARGS).cast(LispType.LIST);

        UIKeyCombination keyCombination = new UIKeyCombination();
        for (LispObject keyObj : keys) {
            String key = ((LispString)keyObj.cast(LispType.STRING)).getValue();
            keyCombination.getKeys().add(new UIKey(key));
        }

        KeyBindingActionManager manager = KeyBindingActionManager.getInstance(getContext());
        KeyBindingAction kba = findBinding(manager, keyCombination);
        LispActionContext lispContext = new LispActionContext(actionArgs, namespace);

        if (kba == null) {
            manager.getKeyBindingActions().add(new KeyBindingAction(action, keyCombination, lispContext));
            System.out.printf("New key combination %s set to %s\n", keyCombination, action);
        } else {
            kba.setAction(action);
            kba.setLispContext(lispContext);
            System.out.printf("Key combination %s already set to %s, resetting to %s\n", keyCombination, kba.getAction(), action);
        }

        return LispBool.NIL;
    }

    private KeyBindingAction findBinding(KeyBindingActionManager manager, UIKeyCombination keyCombination) {
        for (KeyBindingAction kba : manager.getKeyBindingActions()) {
            if (kba.getCombination().equals(keyCombination)) {
                return kba;
            }
        }

        return null;
    }
}
