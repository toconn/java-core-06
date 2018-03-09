package ua.core.data;
/**
 * Created with CodeCrank.io
 */
public class Item {

    private final int typeId;
    private final int id;

    public Item (int typeId, int id) {

        this.typeId = typeId;
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public int getId() {
        return id;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + typeId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id != other.id)
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "Item [" +
            "typeId=" + typeId +
            ", id=" + id +
            "]";
    }
}