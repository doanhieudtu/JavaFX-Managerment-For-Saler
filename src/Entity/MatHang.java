package Entity;

import java.io.Serializable;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DELL on 08/23/2018.
 */
@XmlRootElement(name="MatHang")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatHang implements Serializable {
	private static final long serialVersionUID = 1447199063826949783L;
    /*Mã mặt hàng*/
    private String mahang;

    /* tên mặt hàng*/
    private  String tenhang;

    /* hình ảnh sản mặt hàng*/
    private String hinhAnh;

    /* 1 mặt hàng xuất hiện ở nhiều chi nhánh khác nhau về số lượng*/
    /*đơn vị tính sản phẩm*/
    private String donvitinh;

    /* nhiều mặt hàng thuộc 1 loại hàng*/
    
    private Set<DonGiaNhap> lsDonGiaNhap;

    /* 1 mặt hàng có nhiề đơn giá xuất*/
    private Set<DonGiaXuat> lsDonGiaXuat;
    
    private LoaiHang loaiHang;

	public String getMahang() {
		return mahang;
	}

	public void setMahang(String mahang) {
		this.mahang = mahang;
	}

	public Set<DonGiaNhap> getLsDonGiaNhap() {
		return lsDonGiaNhap;
	}

	public void setLsDonGiaNhap(Set<DonGiaNhap> lsDonGiaNhap) {
		this.lsDonGiaNhap = lsDonGiaNhap;
	}

	public Set<DonGiaXuat> getLsDonGiaXuat() {
		return lsDonGiaXuat;
	}

	public void setLsDonGiaXuat(Set<DonGiaXuat> lsDonGiaXuat) {
		this.lsDonGiaXuat = lsDonGiaXuat;
	}

	public String getTenhang() {
		return tenhang;
	}

	public void setTenhang(String tenhang) {
		this.tenhang = tenhang;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getDonvitinh() {
		return donvitinh;
	}

	public void setDonvitinh(String donvitinh) {
		this.donvitinh = donvitinh;
	}

	public LoaiHang getLoaiHang() {
		return loaiHang;
	}

	public void setLoaiHang(LoaiHang loaiHang) {
		this.loaiHang = loaiHang;
	}
    
    
  
}