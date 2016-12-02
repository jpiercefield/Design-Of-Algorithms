
public class Vertex<T> {
	
	private T item;
	private String key;
	private final int index;		// Used to designate location in adjacency matrix
	private boolean marked;
	
	public Vertex(T item, int index) {
		this.item = item;
		this.key = this.item.toString();
		this.index = index;
		this.marked = false;
	}
	
	public T getItem() {
		return item;
	}
	
	public String getKey() {
		return key;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setMarked(boolean val) {
		this.marked = val;
	}
	
	public boolean isMarked() {
		return marked;
	}

}
