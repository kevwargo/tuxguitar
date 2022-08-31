package org.herac.tuxguitar.app.lisp.actions;

import kevwargo.jlp.LispException;
import kevwargo.jlp.objects.LispFunction;
import kevwargo.jlp.objects.LispJavaObject;
import kevwargo.jlp.objects.LispList;
import kevwargo.jlp.utils.ArgumentsIterator;
import kevwargo.jlp.utils.LispNamespace;

import org.herac.tuxguitar.action.TGActionContext;
import org.herac.tuxguitar.action.TGActionException;
import org.herac.tuxguitar.app.TuxGuitar;
import org.herac.tuxguitar.app.action.TGActionAdapterManager;
import org.herac.tuxguitar.app.action.installer.TGActionConfigMap;
import org.herac.tuxguitar.app.action.installer.TGActionInstaller;
import org.herac.tuxguitar.app.lisp.data.LispContext;
import org.herac.tuxguitar.document.TGDocumentContextAttributes;
import org.herac.tuxguitar.editor.action.TGActionBase;
import org.herac.tuxguitar.util.TGContext;


public class LispAction extends TGActionBase {

    public static final String ACTION_NAME_PREFIX = "lisp:";


    private LispFunction function;

    public LispAction(TGContext context, LispFunction function) {
        super(context, ACTION_NAME_PREFIX + function.getName());
        this.function = function;
    }

    protected void processAction(TGActionContext context) {
        LispActionContext lispContext = (LispActionContext)context.getAttribute(TGDocumentContextAttributes.ATTRIBUTE_LISP_CONTEXT);

        ArgumentsIterator args;
        if (lispContext.getArguments() != null) {
            args = new ArgumentsIterator(lispContext.getArguments());
        } else {
            args = new ArgumentsIterator();
        }

        args.setFirst(new LispContext(context));

        try {
            function.call(lispContext.getNamespace(), args);
        } catch (LispException e) {
            throw new TGActionException(e);
        }
    }

    public void register() {
        TGActionAdapterManager manager = TuxGuitar.getInstance().getActionAdapterManager();
        TGActionInstaller installer = new TGActionInstaller(manager);
        installer.installAction(this, TGActionConfigMap.SHORTCUT | TGActionConfigMap.LOCKABLE | TGActionConfigMap.DISABLE_ON_PLAY);
    }

}
