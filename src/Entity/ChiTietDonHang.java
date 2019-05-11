package Entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement(name="ChiTietDonHang")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChiTietDonHang {
	 	private int maCT;

	    /*số lượng hàng được mua trong hóa đơn*/
	    private int soLuong;

	    /* hàng trong bảng chi tiết*/
	    private MatHang_ChiNhanh matHang_chiNhanh;

	    /*hóa đơn trong bảng chi tiết*/
	    private DonHang donHang;

		public int getMaCT() {
			return maCT;
		}

		public void setMaCT(int maCT) {
			this.maCT = maCT;
		}

		public int getSoLuong() {
			return soLuong;
		}

		public void setSoLuong(int soLuong) {
			this.soLuong = soLuong;
		}

		public MatHang_ChiNhanh getMatHang_chiNhanh() {
			return matHang_chiNhanh;
		}

		public void setMatHang_chiNhanh(MatHang_ChiNhanh matHang_chiNhanh) {
			this.matHang_chiNhanh = matHang_chiNhanh;
		}

		public DonHang getDonHang() {
			return donHang;
		}

		public void setDonHang(DonHang donHang) {
			this.donHang = donHang;
		}
	    
	    
}
