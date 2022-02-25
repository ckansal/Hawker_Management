package customerTable;

public class CustomerBean {

	String cname,address,area,hawker,mobile,sel_paper,sel_price,dos;

	public CustomerBean(String cname, String address, String area,String hawker,  String mobile , String sel_paper,
			String sel_price, String dos) {
		super();
		this.cname = cname;
		this.address = address;
		this.area = area;
		this.hawker=hawker;
		this.mobile = mobile;
		this.sel_paper = sel_paper;
		this.sel_price = sel_price;
		this.dos = dos;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getHawker() {
		return hawker;
	}

	public void setHawker(String hawker) {
		this.hawker = hawker;
	}
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSel_paper() {
		return sel_paper;
	}

	public void setSel_paper(String sel_paper) {
		this.sel_paper = sel_paper;
	}

	public String getSel_price() {
		return sel_price;
	}

	public void setSel_price(String sel_price) {
		this.sel_price = sel_price;
	}

	public String getDos() {
		return dos;
	}

	public void setDos(String dos) {
		this.dos = dos;
	}
}
