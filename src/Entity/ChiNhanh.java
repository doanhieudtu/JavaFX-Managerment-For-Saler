package Entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Created by DELL on 08/23/2018.
 */
@XmlRootElement(name="ChiNhanh")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChiNhanh implements Serializable {
	private static final long serialVersionUID = 1447199063826949783L;
	
    private String machinhanh;
	 
    private String diachi;

    private String sodt;
	
    public ChiNhanh() {}
	 
    public String getMachinhanh() {
        return machinhanh;
    }

    public void setMachinhanh(String machinhanh) {
        this.machinhanh = machinhanh;
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

}