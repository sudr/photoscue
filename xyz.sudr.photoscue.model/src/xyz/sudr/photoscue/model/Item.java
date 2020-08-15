package xyz.sudr.photoscue.model;

import java.util.ArrayList;
import java.util.List;

public class Item {

	private List<Tag> tags = new ArrayList<Tag>();

	public void addTag(Tag tag) {
		this.tags.add(tag);
	}

}
