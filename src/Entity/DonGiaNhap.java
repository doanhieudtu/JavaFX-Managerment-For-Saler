package Entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement(name="DonGiaNhap")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DonGiaNhap {
	private int maDongia;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private double donGia;
	private MatHang matHang;
	public int getMaDongia() {
		return maDongia;
	}
	public void setMaDongia(int maDongia) {
		this.maDongia = maDongia;
	}
	public Date getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double giaNhap) {
		this.donGia = giaNhap;
	}
	public MatHang getMatHang() {
		return matHang;
	}
	public void setMatHang(MatHang matHang) {
		this.matHang = matHang;
	}
	
}
