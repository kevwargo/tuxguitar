package org.herac.tuxguitar.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.io.PrintStream;

public abstract class TGAbstractContext {

	private Map<String, Object> attributes;

	public TGAbstractContext(){
		this.attributes = new ConcurrentHashMap<String, Object>();
	}

	public <T extends Object> void setAttribute(String key, T value){
		if( value != null ) {
			this.attributes.put(key, value);
		} else {
			this.removeAttribute(key);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> T getAttribute(String key){
		if( this.hasAttribute(key) ) {
			return (T) this.attributes.get(key);
		}
		return null;
	}

	public void removeAttribute(String key){
		if( this.hasAttribute(key) ) {
			this.attributes.remove(key);
		}
	}

	public Map<String, Object> getAttributes(){
		return this.attributes;
	}

	public void addAttributes(Map<String, Object> attributes) {
		this.attributes.putAll(attributes);
	}

	public boolean hasAttribute(String key){
		return this.attributes.containsKey(key);
	}

	public void clear() {
		this.attributes.clear();
	}

	public void addContext(TGAbstractContext context) {
		this.addAttributes(context.getAttributes());
	}

    public void dumpAttributes(PrintStream out, String name) {
        if (name != null) {
            out.printf("Context from %s:\n", name);
        }
        for (Map.Entry<String, Object> e : attributes.entrySet()) {
            out.printf("%s: %s\n", e.getKey(), e.getValue().toString());
        }
        if (name != null) {
            out.println("===================");
        }
    }

    public void dumpAttributes(String name) {
        dumpAttributes(System.out, name);
    }

    public void dumpAttributes(PrintStream out) {
        dumpAttributes(out, null);
    }

    public void dumpAttributes() {
        dumpAttributes(System.out, null);
    }
}
