package springboot.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "headertransaksibarang")
public class EHeaderTransaksiBarang implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "nomor_bon")
	private String nomorBon;

	// pakai java.util.Date (bukan java.time.LocalDate) karena Hibernate 5.0.11 (bundled Spring Boot 1.4.3)
	// belum bisa native handle LocalDate -> kalau dipaksa LocalDate, error "column tanggal_bon is of type date but expression is of type bytea"
	@Temporal(TemporalType.DATE)
	@Column(name = "tanggal_bon")
	private Date tanggalBon;

	@Column(name = "bagian_peminta")
	private String bagianPeminta;

	@Column(name = "keterangan")
	private String keterangan;

	public String getNomorBon() {
		return nomorBon;
	}

	public void setNomorBon(String nomorBon) {
		this.nomorBon = nomorBon;
	}

	public Date getTanggalBon() {
		return tanggalBon;
	}

	public void setTanggalBon(Date tanggalBon) {
		this.tanggalBon = tanggalBon;
	}

	public String getBagianPeminta() {
		return bagianPeminta;
	}

	public void setBagianPeminta(String bagianPeminta) {
		this.bagianPeminta = bagianPeminta;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
}