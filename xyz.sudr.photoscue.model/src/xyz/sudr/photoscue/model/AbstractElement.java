package xyz.sudr.photoscue.model;

class AbstractElement {

	private String id;
	
	AbstractElement(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
