package xyz.sudr.photoscue.admin.services;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import xyz.sudr.photoscue.persistence.PersistenceService;

@Component
public class AdminServiceImpl implements AdminService {

	@Reference
	private PersistenceService persistenceService;

	@Override
	public void logDb() {
		persistenceService.logGraph();
	}

}
