package xyz.sudr.photoscue.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Project extends AbstractElement {

	private final String name;

	private List<Source> sources = new ArrayList<>();
	private List<Item> items = new ArrayList<>();

	public Project(final String name) {
		super(UUID.randomUUID().toString().replaceAll("-", ""));
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	// TODO
	public void addSource(final Source source) {
		this.sources.add(source);
	}

	private void addItem(Item item) {
		this.items.add(item);
	}

}
