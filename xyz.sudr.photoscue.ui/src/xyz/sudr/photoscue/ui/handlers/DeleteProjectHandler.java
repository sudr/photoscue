 
package xyz.sudr.photoscue.ui.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;

public class DeleteProjectHandler {
	
	/*
	@ Inject
	public v​oid setSelection(​@Named (​I​ServiceConstants.A​CTIVE_SELECTION)​ @Optional MyObject myObject)​{​
	//Process Selection
	}*/
	
	@Execute
	public void execute() {
		
		
	}
	
	
	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) @Optional Object selection) {
		//return (selection != null && selection instanceof Project)
		return true;
	}
		
}