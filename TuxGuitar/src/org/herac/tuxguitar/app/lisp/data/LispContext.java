package org.herac.tuxguitar.app.lisp.data;

import java.util.Map;

import kevwargo.jlp.LispCastException;
import kevwargo.jlp.objects.LispBool;
import kevwargo.jlp.objects.LispJavaObject;
import kevwargo.jlp.objects.LispObject;
import kevwargo.jlp.objects.LispType;

import org.herac.tuxguitar.document.TGDocumentContextAttributes;
import org.herac.tuxguitar.util.TGAbstractContext;


public class LispContext extends LispJavaObject {

    private static final String ATTR_PREFIX = "ATTRIBUTE_";

    private TGAbstractContext context;

    public LispContext(TGAbstractContext context) {
        super(context);
        this.context = context;
    }

    public LispObject getAttr(String name, boolean withDict) {
        name = tryResolveAttribute(name);
        Object obj = context.getAttribute(name);
        if (obj != null) {
            return new LispJavaObject(obj);
        }
        return LispBool.NIL;
    }

    public void setAttr(String name, LispObject value) {
        name = tryResolveAttribute(name);
        context.setAttribute(name, value.getJavaObject());
    }

    private String tryResolveAttribute(String name) {
        if (!name.startsWith(ATTR_PREFIX)) {
            return name;
        }

        try {
            return (String)TGDocumentContextAttributes.class.getField(name).get(null);
        } catch (NoSuchFieldException e) {
            return name;
        } catch (IllegalAccessException e) {
            return name;
        }
    }

}
