package xyz.sudr.photoscue.ui.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;

public class AddSourceHandler {

	@Inject
	private Logger LOG;

	@Execute
	public void execute(Shell shell) {
		LOG.debug("Add source to project...");

		DirectoryDialog dirDialog = new DirectoryDialog(shell);
		dirDialog.setText("Select directory to add as source");
		String selectedDir = dirDialog.open();
		System.out.println(selectedDir);

		LOG.debug("Add source to project...completed");
	}
	
	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) @Optional Object selection) {
		//return (selection != null && selection instanceof Project)
		return true;
	}
}
