package xyz.sudr.photoscue.ui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ProjectPart {

	@PostConstruct
	void construct(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("Hello RCP");
	}

}