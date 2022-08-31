package org.herac.tuxguitar.app.lisp.actions;

import kevwargo.jlp.utils.LispNamespace;
import kevwargo.jlp.objects.LispList;

public class LispActionContext {

    private LispList arguments;
    private LispNamespace namespace;

    public LispActionContext(LispList arguments, LispNamespace namespace) {
        this.arguments = arguments;
        this.namespace = namespace;
    }

    public LispList getArguments() {
        return this.arguments;
    }

    public LispNamespace getNamespace() {
        return this.namespace;
    }
}
