
package xyz.sudr.photoscue.ui.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import xyz.sudr.photoscue.domain.ProjectService;
import xyz.sudr.photoscue.model.Project;
import xyz.sudr.photoscue.ui.dialogs.CreateProjectDialog;

public class CreateProjectHandler {

	@Inject
	private Logger LOG;
	
	@Inject
	private ProjectService service;
		
	@Execute
	public void execute(Shell shell) {
		LOG.debug("Create new project...");

		CreateProjectDialog dialog = new CreateProjectDialog(shell);
		dialog.create();

		if (dialog.open() == Window.OK) {
			service.createOrUpdateProject(new Project(dialog.getProjectName().trim()));
		}
		LOG.debug("Create new project...completed");
		
		
	}

	/*
	 * @CanExecute public boolean canExecute(EPartService partService) { if
	 * (partService != null) { return !partService.getDirtyParts().isEmpty(); }
	 * return false; }
	 * 
	 * @Execute public void execute(EPartService partService) {
	 * partService.saveAll(false); }
	 */

}