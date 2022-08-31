package org.herac.tuxguitar.app.lisp.actions.impl;

import java.util.Map;

import kevwargo.jlp.LispException;
import kevwargo.jlp.objects.LispBool;
import kevwargo.jlp.objects.LispFunction;
import kevwargo.jlp.objects.LispInt;
import kevwargo.jlp.objects.LispObject;
import kevwargo.jlp.objects.LispType;
import kevwargo.jlp.utils.FormalArguments;
import kevwargo.jlp.utils.LispNamespace;

import org.herac.tuxguitar.action.TGActionContext;
import org.herac.tuxguitar.app.lisp.actions.ActionFunction;
import org.herac.tuxguitar.document.TGDocumentContextAttributes;
import org.herac.tuxguitar.editor.action.TGActionProcessor;
import org.herac.tuxguitar.editor.action.note.TGChangeNoteAction;
import org.herac.tuxguitar.song.managers.TGSongManager;
import org.herac.tuxguitar.song.models.TGDuration;
import org.herac.tuxguitar.song.models.TGMeasure;
import org.herac.tuxguitar.song.models.TGString;
import org.herac.tuxguitar.song.models.TGVoice;
import org.herac.tuxguitar.util.TGContext;


public class SetFretAction extends ActionFunction {

    public static final String NAME = "set-fret";


    public SetFretAction(TGContext context) {
        super(context, NAME, new FormalArguments().pos("num"));
    }

    protected LispObject callAction(LispNamespace namespace, Map<String, LispObject> arguments, TGActionContext actionContext) throws LispException {
        long num = ((LispInt)arguments.get("num").cast(LispType.INT)).getValue();

        actionContext.setAttribute(TGDocumentContextAttributes.ATTRIBUTE_FRET, new Integer((int)num));
        new TGActionProcessor(getContext(), TGChangeNoteAction.NAME).processOnCurrentThread(actionContext);

        return LispBool.NIL;
    }

}
