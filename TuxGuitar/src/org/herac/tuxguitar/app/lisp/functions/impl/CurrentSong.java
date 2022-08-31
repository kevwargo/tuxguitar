package org.herac.tuxguitar.app.lisp.functions.impl;

import java.util.Map;

import kevwargo.jlp.LispException;
import kevwargo.jlp.objects.LispJavaObject;
import kevwargo.jlp.objects.LispObject;
import kevwargo.jlp.utils.FormalArguments;
import kevwargo.jlp.utils.LispNamespace;

import org.herac.tuxguitar.app.TuxGuitar;
import org.herac.tuxguitar.app.lisp.functions.TGLispFunction;
import org.herac.tuxguitar.document.TGDocumentManager;
import org.herac.tuxguitar.util.TGContext;


public class CurrentSong extends TGLispFunction {

    public static final String NAME = "current-song";

    public CurrentSong(TGContext context) {
        super(context, NAME, new FormalArguments());
    }

    protected LispObject callInternal(LispNamespace namespace, Map<String, LispObject> arguments) throws LispException {
        return new LispJavaObject(TGDocumentManager.getInstance(getContext()).getSong());
    }

}
