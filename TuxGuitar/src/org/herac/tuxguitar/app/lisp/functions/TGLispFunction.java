package org.herac.tuxguitar.app.lisp.functions;

import kevwargo.jlp.objects.LispFunction;
import kevwargo.jlp.objects.LispType;
import kevwargo.jlp.utils.FormalArguments;

import org.herac.tuxguitar.util.TGContext;

public abstract class TGLispFunction extends LispFunction {

    private TGContext context;

    public TGLispFunction(TGContext context, String name, FormalArguments args) {
        super(LispType.FUNCTION, name, args);
        this.context = context;
    }

    public TGContext getContext() {
        return context;
    }

}
