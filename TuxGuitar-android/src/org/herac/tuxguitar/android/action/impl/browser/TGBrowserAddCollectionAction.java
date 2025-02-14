package org.herac.tuxguitar.android.action.impl.browser;

import org.herac.tuxguitar.action.TGActionContext;
import org.herac.tuxguitar.action.TGActionException;
import org.herac.tuxguitar.android.action.TGActionBase;
import org.herac.tuxguitar.android.browser.TGBrowserCollection;
import org.herac.tuxguitar.android.browser.TGBrowserManager;
import org.herac.tuxguitar.android.browser.model.TGBrowserException;
import org.herac.tuxguitar.util.TGContext;

public class TGBrowserAddCollectionAction extends TGActionBase {

	public static final String NAME = "action.browser.add-collection";

	public static final String ATTRIBUTE_COLLECTION = TGBrowserCollection.class.getName();

	public TGBrowserAddCollectionAction(TGContext context) {
		super(context, NAME);
	}

	protected void processAction(final TGActionContext context) {
		try {
			TGBrowserCollection collection = context.getAttribute(ATTRIBUTE_COLLECTION);
			TGBrowserManager browserManager = TGBrowserManager.getInstance(getContext());

			browserManager.addCollection(collection);
			browserManager.storeCollections();
		} catch (TGBrowserException e) {
			throw new TGActionException(e);
		}
	}
}
