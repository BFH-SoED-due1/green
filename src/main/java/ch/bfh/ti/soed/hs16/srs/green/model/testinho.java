package ch.bfh.ti.soed.hs16.srs.green.model;

public class testinho {

	public static void main(String[] args) {
		Role x = Role.CUSTOMER;
		System.out.println(x.toString());
		
		boolean r = x.equals(Role.ROOMMANAGER);
		System.out.println(r);
		int z =10;
		z= (z<15)? 3:20;
		System.out.println(z);
		Object rs=new Object[]{"x","r"} ;
	;
		System.out.println(	rs.equals(new Object[]{"x","r"}));
	}

}
