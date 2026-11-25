package springboot.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mstbagian")
public class EBagian implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "kode")
	private String kode;

	@Column(name = "nama")
	private String nama;

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
}
