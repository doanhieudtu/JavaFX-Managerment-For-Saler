package Entity;

public class ProductTableViewCell {
	private String sott;
	private String id;
	private String tenMatHang;
	private String dvt;
	private String soLuong;
	private String giaBan;
	private boolean xoa;
	
	
	public String getSott() {
		return sott;
	}
	public void setSott(String sott) {
		this.sott = sott;
	}
	public String getTenMatHang() {
		return tenMatHang;
	}
	public void setTenMatHang(String tenMatHang) {
		this.tenMatHang = tenMatHang;
	}
	public String getDvt() {
		return dvt;
	}
	public void setDvt(String dvt) {
		this.dvt = dvt;
	}
	
	public String getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}
	public String getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(String giaBan) {
		this.giaBan = giaBan;
	}
	public boolean isXoa() {
		return xoa;
	}
	public void setXoa(boolean xoa) {
		this.xoa = xoa;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
