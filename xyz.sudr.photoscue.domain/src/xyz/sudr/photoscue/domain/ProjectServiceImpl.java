package xyz.sudr.photoscue.domain;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import xyz.sudr.photoscue.model.Project;
import xyz.sudr.photoscue.persistence.PersistenceService;

@Component
public class ProjectServiceImpl implements ProjectService {

	@Reference
	private PersistenceService persistenceService;
	
	@Override
	public void createOrUpdateProject(Project project) {
		persistenceService.createOrUpdate(project);
	}
}
