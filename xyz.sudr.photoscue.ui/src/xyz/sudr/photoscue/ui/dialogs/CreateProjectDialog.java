package xyz.sudr.photoscue.ui.dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CreateProjectDialog extends TitleAreaDialog {

	private Text txtProjectName;
	private String projectName;

	public CreateProjectDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();

		// TODO externalize
		setTitle("New project");
		setMessage("Create a new project to start organizing your media.", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginWidth = 10;
		gridLayout.marginHeight = 10;
		gridLayout.marginLeft = 10;
		container.setLayout(gridLayout);

		createProjectName(container);

		return area;
	}

	private void createProjectName(Composite container) {
		Label lbtProjectName = new Label(container, SWT.NONE);
		lbtProjectName.setText("Project Name:");

		GridData dataProjectName = new GridData();
		dataProjectName.grabExcessHorizontalSpace = true;
		dataProjectName.horizontalAlignment = GridData.FILL;

		txtProjectName = new Text(container, SWT.BORDER);
		txtProjectName.setLayoutData(dataProjectName);
	}
	
	@Override
    protected Point getInitialSize() {
        return new Point(400, 200);
    }
	
	@Override
	protected void okPressed() {
		this.projectName = txtProjectName.getText();
		super.okPressed();
	}
	
	public String getProjectName() {
		return projectName;
	}

}
