package Entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@XmlRootElement(name="DonHang")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DonHang {
    private int madonhang;

    private double vat;

    private Date ngaylap;

    private float chietKhau;

    private ChiNhanh chiNhanh;
    /*1 đơn hàng có nhiều mặt hàng*/
 //   List<ChiTietDonHang> lsCTDonHang;

    public DonHang() {
   //     List<ChiTietDonHang> lsCTDonHang= new ArrayList<>();
     //   this.lsCTDonHang = lsCTDonHang;
        this.nguoiDung = new NguoiDung();
    }

    private NguoiDung nguoiDung;

    private String ghiChu;

	public int getMadonhang() {
		return madonhang;
	}

	public void setMadonhang(int madonhang) {
		this.madonhang = madonhang;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	public Date getNgaylap() {
		return ngaylap;
	}

	public void setNgaylap(Date ngaylap) {
		this.ngaylap = ngaylap;
	}

	public float getChietKhau() {
		return chietKhau;
	}

	public void setChietKhau(float chietKhau) {
		this.chietKhau = chietKhau;
	}

	public ChiNhanh getChiNhanh() {
		return chiNhanh;
	}

	public void setChiNhanh(ChiNhanh chiNhanh) {
		this.chiNhanh = chiNhanh;
	}

//	public List<ChiTietDonHang> getLsCTDonHang() {
//		return lsCTDonHang;
//	}
//
//	public void setLsCTDonHang(List<ChiTietDonHang> lsCTDonHang) {
//		this.lsCTDonHang = lsCTDonHang;
//	}

	public NguoiDung getNguoiDung() {
		return nguoiDung;
	}

	public void setNguoiDung(NguoiDung nguoiDung) {
		this.nguoiDung = nguoiDung;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
    
}
