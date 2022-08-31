package org.herac.tuxguitar.app.lisp.macros;

import java.util.Map;

import kevwargo.jlp.LispException;
import kevwargo.jlp.objects.LispFunction;
import kevwargo.jlp.objects.LispJavaObject;
import kevwargo.jlp.objects.LispList;
import kevwargo.jlp.objects.LispObject;
import kevwargo.jlp.objects.builtins.macros.LMDefun;
import kevwargo.jlp.utils.ArgumentsIterator;
import kevwargo.jlp.utils.FormalArguments;
import kevwargo.jlp.utils.LispNamespace;

import org.herac.tuxguitar.action.TGActionContext;
import org.herac.tuxguitar.app.TuxGuitar;
import org.herac.tuxguitar.app.action.TGActionAdapterManager;
import org.herac.tuxguitar.app.action.installer.TGActionConfigMap;
import org.herac.tuxguitar.app.action.installer.TGActionInstaller;
import org.herac.tuxguitar.app.lisp.actions.LispAction;
import org.herac.tuxguitar.document.TGDocumentContextAttributes;
import org.herac.tuxguitar.editor.action.TGActionBase;
import org.herac.tuxguitar.util.TGContext;


public class DefineAction extends LMDefun {

    private TGContext context;

    public DefineAction(TGContext context) {
        super("define-action");
        this.context = context;
    }

    protected LispObject callInternal(LispNamespace namespace, Map<String, LispObject> arguments) throws LispException {
        LispFunction function = (LispFunction)super.callInternal(namespace, arguments);
        new LispAction(context, function).register();

        return function;
    }

}
