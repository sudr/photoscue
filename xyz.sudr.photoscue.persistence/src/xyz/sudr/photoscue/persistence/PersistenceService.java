package xyz.sudr.photoscue.persistence;

import java.util.List;

import org.neo4j.graphdb.*;

import xyz.sudr.photoscue.model.Project;

public interface PersistenceService {
	
	void createOrUpdate(Project project);

	Node createNode();

	void updateNode();

	List<Node> findAllByType(Label node);
	
	Node loadGraph(Label stopNodeType);

	void logGraph();

	
}
