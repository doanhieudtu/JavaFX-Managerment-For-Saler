package Entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DELL on 08/23/2018.
 */
@XmlRootElement(name="LoaiHang")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoaiHang implements Serializable{
	private static final long serialVersionUID = 1447199063826949783L;
    private int  maloai;

    private String tenloai;

	public int getMaloai() {
		return maloai;
	}

	public void setMaloai(int maloai) {
		this.maloai = maloai;
	}

	public String getTenloai() {
		return tenloai;
	}

	public void setTenloai(String tenloai) {
		this.tenloai = tenloai;
	}
    
   
}