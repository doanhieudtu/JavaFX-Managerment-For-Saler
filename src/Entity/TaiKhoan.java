package Entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DELL on 08/23/2018.
 */
@XmlRootElement(name="TaiKhoan")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaiKhoan implements Serializable{

	private static final long serialVersionUID = 1447199063826949783L;

    private String maTK;
    
    private String tenTK;
    
	private String matKhau;

    private NguoiDung nguoi_dung;
    
    

    public TaiKhoan() {
		super();
	}

    public String getTenTK() {
		return tenTK;
	}

	public void setTenTK(String tenTK) {
		this.tenTK = tenTK;
	}
    
	public String getMatk() {
        return maTK;
    }

    public void setMatk(String matk) {
        this.maTK = matk;
    }

    public NguoiDung getNguoi_dung() {
        return nguoi_dung;
    }

    public void setNguoi_dung(NguoiDung nguoi_dung) {
        this.nguoi_dung = nguoi_dung;
    }

    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public NguoiDung getNguoiDung() {
        return nguoi_dung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoi_dung = nguoiDung;
    }


    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}