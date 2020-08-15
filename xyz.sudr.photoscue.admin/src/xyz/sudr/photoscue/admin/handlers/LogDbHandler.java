package xyz.sudr.photoscue.admin.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;

import xyz.sudr.photoscue.admin.services.AdminService;

public class LogDbHandler {

	@Inject
	private AdminService adminService;

	@Execute
	public void execute() {
		adminService.logDb();
	}

}
