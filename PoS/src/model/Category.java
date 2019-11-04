package model;

public class Category {
	Long id;
	String name;

    public String getName() {
		return name;
	}

    public void setName(String name) {
		this.name = name;
	}
	
	

	public Long getId() {
		return id;
	}
	Category()
	{
		
	}
	public Category(Long id,String name)
	{
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		
		return id+"."+name;
	}
}
