package Entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement(name="NhaCungCap")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhaCungCap {

	private int manhaccap;

    private String tencongty;

    private String diachi;

    private String sodt;

    /*email*/
    private String email;

    private NhomNhaCCap nhomNhaCCap;

	 	public int getManhaccap() {
		return manhaccap;
	}

	public void setManhaccap(int manhaccap) {
		this.manhaccap = manhaccap;
	}

	public String getTencongty() {
		return tencongty;
	}

	public void setTencongty(String tencongty) {
		this.tencongty = tencongty;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getSodt() {
		return sodt;
	}

	public void setSodt(String sodt) {
		this.sodt = sodt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public NhomNhaCCap getNhomNhaCCap() {
		return nhomNhaCCap;
	}

	public void setNhomNhaCCap(NhomNhaCCap nhomNhaCCap) {
		this.nhomNhaCCap = nhomNhaCCap;
	}


	    /*1 nhà cung cấp có thể cung cấp nhiều loại hàng*/
//	    @OneToMany(mappedBy = "nhaCungCap", fetch = FetchType.LAZY)
//	    List<NhaCC_LoaiHang> lsNhaCC_loaiHang;
//
//	    /*1 nhà cung cấp có thể cung cấp nhiều mặt hàng*/
//	    @JsonBackReference
//	    @OneToMany(mappedBy = "nhaCungCap", fetch = FetchType.LAZY)
//	    List<NhaCC_MatHang> ls_Mat_hang_NhaCC;
	    
}
