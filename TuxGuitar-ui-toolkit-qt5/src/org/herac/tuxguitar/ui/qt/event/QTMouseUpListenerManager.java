package org.herac.tuxguitar.ui.qt.event;

import org.herac.tuxguitar.ui.event.UIMouseEvent;
import org.herac.tuxguitar.ui.event.UIMouseUpListenerManager;
import org.herac.tuxguitar.ui.qt.QTComponent;
import org.herac.tuxguitar.ui.qt.resource.QTMouseButton;
import org.herac.tuxguitar.ui.resource.UIPosition;
import org.qtjambi.qt.core.QEvent;
import org.qtjambi.qt.gui.QMouseEvent;

public class QTMouseUpListenerManager extends UIMouseUpListenerManager implements QTEventHandler {

	private QTComponent<?> control;

	public QTMouseUpListenerManager(QTComponent<?> control) {
		this.control = control;
	}

	public void handle(QMouseEvent event) {
		this.onMouseUp(new UIMouseEvent(this.control, new UIPosition(event.x(), event.y()), QTMouseButton.getMouseButton(event.button())));
	}

	public boolean handle(QEvent event) {
		this.handle((QMouseEvent) event);

		return true;
	}
}
