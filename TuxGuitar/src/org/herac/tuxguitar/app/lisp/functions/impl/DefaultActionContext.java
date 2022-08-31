package org.herac.tuxguitar.app.lisp.functions.impl;

import java.util.Map;

import kevwargo.jlp.LispException;
import kevwargo.jlp.objects.LispJavaObject;
import kevwargo.jlp.objects.LispObject;
import kevwargo.jlp.utils.FormalArguments;
import kevwargo.jlp.utils.LispNamespace;

import org.herac.tuxguitar.action.TGActionManager;
import org.herac.tuxguitar.app.lisp.data.LispContext;
import org.herac.tuxguitar.app.lisp.functions.TGLispFunction;
import org.herac.tuxguitar.util.TGContext;


public class DefaultActionContext extends TGLispFunction {

    public static final String NAME = "default-action-context";

    public DefaultActionContext(TGContext context) {
        super(context, NAME, new FormalArguments());
    }

    protected LispObject callInternal(LispNamespace namespace, Map<String, LispObject> arguments) throws LispException {
        return new LispContext(TGActionManager.getInstance(getContext()).createActionContext());
    }

}
