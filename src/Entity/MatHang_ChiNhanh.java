package Entity;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by DELL on 09/14/2018.
 */
@XmlRootElement(name="MatHang_ChiNhanh")
@XmlAccessorType(XmlAccessType.FIELD)
public class MatHang_ChiNhanh implements Serializable {
	private static final long serialVersionUID = 1447199063826949783L;
    private int ma_Mathang_chiNhanh;


    private ChiNhanh chiNhanh;


    private MatHang matHang;

    /*số lượng tồn trong kho*/
    private int soLuongTon;

    /*số lượng có tại cửa hàng*/
    private int soLuongDangBan;

    /*ngày sản xuất*/
    private Date ngaysanxuat;

    /*hạn sử dụng*/
    private int hansudung;

    /*đã đồng bộ ảnh*/
    private boolean  dongBoanh;
        /*1 mặt hàng của chi nhánh có thể xuất hiện trong nhiều phiếu nhập*/

	public int getMa_Mathang_chiNhanh() {
		return ma_Mathang_chiNhanh;
	}

	public void setMa_Mathang_chiNhanh(int ma_Mathang_chiNhanh) {
		this.ma_Mathang_chiNhanh = ma_Mathang_chiNhanh;
	}

	public ChiNhanh getChiNhanh() {
		return chiNhanh;
	}

	public void setChiNhanh(ChiNhanh chiNhanh) {
		this.chiNhanh = chiNhanh;
	}

	public MatHang getMatHang() {
		return matHang;
	}

	public void setMatHang(MatHang matHang) {
		this.matHang = matHang;
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public int getSoLuongDangBan() {
		return soLuongDangBan;
	}

	public void setSoLuongDangBan(int soLuongDangBan) {
		this.soLuongDangBan = soLuongDangBan;
	}

	public Date getNgaysanxuat() {
		return ngaysanxuat;
	}

	public void setNgaysanxuat(Date ngaysanxuat) {
		this.ngaysanxuat = ngaysanxuat;
	}

	public int getHansudung() {
		return hansudung;
	}

	public void setHansudung(int hansudung) {
		this.hansudung = hansudung;
	}

	public boolean isDongBoanh() {
		return dongBoanh;
	}

	public void setDongBoanh(boolean dongBoanh) {
		this.dongBoanh = dongBoanh;
	}
}