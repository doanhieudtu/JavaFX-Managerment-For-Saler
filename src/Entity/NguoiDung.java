package Entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DELL on 08/23/2018.
 */
@XmlRootElement(name="NguoiDung")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NguoiDung implements Serializable{
	private static final long serialVersionUID = 1447199063826949783L;
 
  
    private int maND;

    /*họ tên người dùng*/
   
    private String hoTen;

    /*ngày sinh người dùng*/
    private String ngaySinh;

    /*giới tính */

    private boolean gioiTinh;

    /*địa chỉ*/
    private String diaChi;

    /* số điện thoại*/
    private String soDT;

    /*1 người dùng chỉ có 1 tài khoản đăng nhập*/
    private  TaiKhoan taiKhoan;

    /*nhiều người dùng thuộc 1 chi nhánh*/

    private ChiNhanh chiNhanh;


    public int getMaND() {
        return maND;
    }

    public void setMaND(int maND) {
        this.maND = maND;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
}