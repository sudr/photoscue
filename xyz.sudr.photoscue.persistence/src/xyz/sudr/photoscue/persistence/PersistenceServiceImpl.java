package xyz.sudr.photoscue.persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import xyz.sudr.photoscue.model.Project;

import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.Relationship;
import org.eclipse.e4.core.services.log.Logger;
import org.neo4j.graphdb.Direction;

@Component
public class PersistenceServiceImpl implements PersistenceService {
	
	// TODO why is LOG null?
	@Inject
	private Logger LOG;

	private enum RelTypes implements RelationshipType {
		CONTAINS
	}

	private static File baseDir = new File(System.getProperty("user.home") + "/.photoscue");

	private GraphDatabaseService graphDb;
	
	public PersistenceServiceImpl() {
		startDb();
	}

	void startDb() {
		File dataDir = new File(baseDir, "/data/");
		File logsDir = new File(baseDir, "/logs");
		
		// logging https://neo4j.com/docs/java-reference/3.5/tutorials-java-embedded/logging/
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(dataDir)
				.setConfig("dbms.directories.logs", logsDir.getAbsolutePath()).newGraphDatabase();
		
		if (!dataDir.exists()) {
			try ( Transaction tx = graphDb.beginTx() )
		       {
				graphDb.schema()
		                   .constraintFor( Label.label( "User" ) )
		                   .assertPropertyIsUnique( "name" )
		                   .create();
		           tx.success();
		}
		
	}

		registerShutdownHook(graphDb);
	}

	private static void registerShutdownHook(final GraphDatabaseService graphDb) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				graphDb.shutdown();
			}
		});
	}
	
	@Override
	public void createOrUpdate(Project project) {
		Label projectLabel = Label.label("project");

					//Node projectNode = graphDb.createNode(projectLabel);
			//projectNode.setProperty("name", project.getName());
			//projectNode.setProperty("id", project.getId());
		
			Node projectNode = null;
			ResourceIterator<Node> resultIterator = null;
			try (Transaction tx = graphDb.beginTx()) {
				String queryString = "MERGE (p:Project {id: $id, name: $name}) RETURN p";
				Map<String, Object> parameters = new HashMap<>();
				parameters.put("id", project.getId());
				parameters.put("name", project.getName());
				resultIterator = graphDb.execute(queryString, parameters).columnAs("p");
				projectNode = resultIterator.next();
				tx.success();
			}
		       
			//Relationship relationship = firstNode.createRelationshipTo(secondNode, RelTypes.CONTAINS);
			//relationship.setProperty("message", "brave Neo4j ");
		
		// for uniqueness
		/*Node result = null;
	       ResourceIterator<Node> resultIterator = null;
	       try ( Transaction tx = graphDb.beginTx() )
	       {
	           String queryString = "MERGE (n:User {name: $name}) RETURN n";
	           Map<String, Object> parameters = new HashMap<>();
	           parameters.put( "name", "jok" );
	           resultIterator = graphDb.execute( queryString, parameters ).columnAs( "n" );
	           result = resultIterator.next();
	           tx.success();
	           return result;
	       }*/
	       
	}

	public void fetch() {
		Label label = Label.label("User");
		int idToFind = 45;
		String nameToFind = "user" + idToFind + "@neo4j.org";
		try (Transaction tx = graphDb.beginTx()) {
			try (ResourceIterator<Node> users = graphDb.findNodes(label, "username", nameToFind)) {
				ArrayList<Node> userNodes = new ArrayList<>();
				while (users.hasNext()) {
					userNodes.add(users.next());
				}

				for (Node node : userNodes) {
					System.out.println("The username of user " + idToFind + " is " + node.getProperty("username"));
				}

				// OR
				Node firstUserNode;
				if (users.hasNext()) {
					firstUserNode = users.next();
				}
				users.close(); // excplicit close
			}
		}

	}

	void removeData() {
		try (Transaction tx = graphDb.beginTx()) {
			Node firstNode = null;
			Node secondNode = null;

			firstNode.getSingleRelationship(RelTypes.CONTAINS, Direction.OUTGOING).delete();
			firstNode.delete();
			secondNode.delete();

			tx.success();
		}
	}

	@Override
	public Node createNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateNode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Node> findAllByType(Label nodeType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node loadGraph(Label stopNodeType) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void logGraph() {
		try (Transaction tx = graphDb.beginTx()) {
			ResourceIterable<Node> allNodes = graphDb.getAllNodes();
			for (Node node : allNodes) {
				System.out.println(node.getId() + "; " + node.getLabels() + "; " + node.getAllProperties());
			}
			
			ResourceIterable<Relationship> allRelationships = graphDb.getAllRelationships();
			for (Relationship relationship : allRelationships) {
				System.out.println(relationship.getId() + "; " + relationship.getType() + "; " + relationship.getStartNodeId() + " to " + relationship.getStartNodeId() + "; " + relationship.getAllProperties());
			}
		}
	}

	
}
