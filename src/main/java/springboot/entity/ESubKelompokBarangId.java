package springboot.entity;

import java.io.Serializable;

public class ESubKelompokBarangId implements Serializable {
  private static final long serialVersionUID = 593014308038043837L;
  
  private String kodeKelompokBarang;
  private String kodeSubKelompokBarang;
  
  public String getKodeKelompokBarang() {
	return kodeKelompokBarang;
  }
  
  public void setKodeKelompokBarang(String kodeKelompokBarang) {
	this.kodeKelompokBarang = kodeKelompokBarang;
  }
  
  public String getKodeSubKelompokBarang() {
	return kodeSubKelompokBarang;
  }
  
  public void setKodeSubKelompokBarang(String kodeSubKelompokBarang) {
	this.kodeSubKelompokBarang = kodeSubKelompokBarang;
  }
  
  public static long getSerialversionuid() {
	return serialVersionUID;
  }
}
