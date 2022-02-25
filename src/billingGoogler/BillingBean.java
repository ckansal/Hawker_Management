package billingGoogler;

public class BillingBean {

	String mobile,dos,dupto;
	float bill;
	public BillingBean(String mobile, String dos, String dupto, float bill) {
		super();
		this.mobile = mobile;
		this.dos = dos;
		this.dupto = dupto;
		this.bill = bill;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getDupto() {
		return dupto;
	}
	public void setDupto(String dupto) {
		this.dupto = dupto;
	}
	public float getBill() {
		return bill;
	}
	public void setBill(float bill) {
		this.bill = bill;
	}
}
