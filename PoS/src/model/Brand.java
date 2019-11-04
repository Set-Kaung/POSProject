package model;

public class Brand {
 private Long ID;
 private String name;
 
 
public Long getID() {
	return ID;
}
public void setID(Long iD) {
	ID = iD;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

	Brand()
	{
	
	}
 
	public Brand(Long ID,String name)
	{
		this.ID = ID;
		this.name = name;
	}
	
	@Override
	public String toString() 
	{
			// TODO Auto-generated method stub
			return ID+"."+name;
	}
}
