package org.herac.tuxguitar.app.lisp.actions;

import java.util.List;
import java.util.Map;

import kevwargo.jlp.LispException;
import kevwargo.jlp.objects.LispBool;
import kevwargo.jlp.objects.LispJavaObject;
import kevwargo.jlp.objects.LispObject;
import kevwargo.jlp.objects.LispType;
import kevwargo.jlp.utils.FormalArguments;
import kevwargo.jlp.utils.LispNamespace;

import org.herac.tuxguitar.action.TGAction;
import org.herac.tuxguitar.action.TGActionContext;
import org.herac.tuxguitar.action.TGActionException;
import org.herac.tuxguitar.app.TuxGuitar;
import org.herac.tuxguitar.app.action.TGActionAdapterManager;
import org.herac.tuxguitar.app.action.installer.TGActionConfigMap;
import org.herac.tuxguitar.app.action.installer.TGActionInstaller;
import org.herac.tuxguitar.app.lisp.functions.TGLispFunction;
import org.herac.tuxguitar.util.TGContext;


public abstract class ActionFunction extends TGLispFunction {

    public static final String ARG_CONTEXT = "context";


    public ActionFunction(TGContext context, String name, FormalArguments args) {
        super(context, name, args.pos(0, ARG_CONTEXT));
    }

    protected LispObject callInternal(LispNamespace namespace, Map<String, LispObject> arguments) throws LispException {
        Object ctxObj = ((LispJavaObject)arguments.get(ARG_CONTEXT).cast(LispType.JAVA_OBJECT)).getObject();

        if (!(ctxObj instanceof TGActionContext)) {
            throw new LispException(String.format("First argument passed to `%s` must be a TGActionContext, not %s", getName(), ctxObj.getClass().getName()));
        }

        TGActionContext context = (TGActionContext)ctxObj;
        return callAction(namespace, arguments, context);
    }

    public ActionFunction register() {
        new LispAction(getContext(), this).register();
        return this;
    }

    protected abstract LispObject callAction(LispNamespace namespace, Map<String, LispObject> arguments, TGActionContext context) throws LispException;

}
