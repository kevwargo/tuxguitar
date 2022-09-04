package org.herac.tuxguitar.ui.qt.event;

import org.herac.tuxguitar.ui.event.UIZoomEvent;
import org.herac.tuxguitar.ui.event.UIZoomListenerManager;
import org.herac.tuxguitar.ui.qt.QTComponent;

import com.trolltech.qt.core.QEvent;
import com.trolltech.qt.core.Qt.KeyboardModifier;
import com.trolltech.qt.gui.QWheelEvent;

public class QTZoomListenerManager extends UIZoomListenerManager implements QTEventHandler {

	private QTComponent<?> control;

	public QTZoomListenerManager(QTComponent<?> control) {
		this.control = control;
	}

	public boolean handle(QWheelEvent event) {
		if( event.modifiers().isSet(KeyboardModifier.ControlModifier) ) {
			this.onZoom(new UIZoomEvent(this.control, (event.delta() > 0 ? 1 : -1)));

			return true;
		}
		return false;
	}

	public boolean handle(QEvent event) {
		return this.handle((QWheelEvent) event);
	}
}
