package hawkerTable;

public class HawkerBean {

	String name,address,contact,areas,adhaar;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

	public String getAdhaar() {
		return adhaar;
	}

	public void setAdhaar(String adhaar) {
		this.adhaar = adhaar;
	}

	public HawkerBean(String name, String address, String contact, String areas, String adhaar) {
		super();
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.areas = areas;
		this.adhaar = adhaar;
	}

}
