package xyz.sudr.photoscue.persistence;

import org.neo4j.graphdb.*;

enum Labels implements Label {
	PROJECT, SOURCE, ITEM, TAG;
}