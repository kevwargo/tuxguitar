package org.herac.tuxguitar.app.lisp.actions.impl;

import java.util.Map;

import kevwargo.jlp.LispException;
import kevwargo.jlp.objects.LispBool;
import kevwargo.jlp.objects.LispFunction;
import kevwargo.jlp.objects.LispObject;
import kevwargo.jlp.objects.LispString;
import kevwargo.jlp.objects.LispType;
import kevwargo.jlp.utils.FormalArguments;
import kevwargo.jlp.utils.LispNamespace;

import org.herac.tuxguitar.action.TGActionContext;
import org.herac.tuxguitar.app.lisp.actions.ActionFunction;
import org.herac.tuxguitar.editor.action.TGActionProcessor;
import org.herac.tuxguitar.util.TGContext;


public class RunAction extends ActionFunction {

    public static final String NAME = "run-action";
    public static final String ARG_ACTION = "action";


    public RunAction(TGContext context) {
        super(context, NAME, new FormalArguments(ARG_ACTION));
    }

    protected LispObject callAction(LispNamespace namespace, Map<String, LispObject> arguments, TGActionContext actionContext) throws LispException {
        String action = ((LispString)arguments.get(ARG_ACTION).cast(LispType.STRING)).getValue();
        new TGActionProcessor(getContext(), action).processOnCurrentThread(actionContext);

        return LispBool.NIL;
    }

}
