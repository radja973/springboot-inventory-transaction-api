package springboot.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detailtransaksibarang")
public class EDetailTransaksiBarang implements Serializable {

	private static final long serialVersionUID = 1L;

	// Field id ini bertindak sebagai Primary Key (PK) auto-increment untuk tabel detailtransaksibarang
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// FK ke header -> header pakai nomorBon (String) sbg PK, jadi FK di sini juga String, BUKAN Integer
	// (sebelumnya "headerId" Integer -> mismatch sama PK header yg String, makanya diganti)
	@Column(name = "nomor_bon")
	private String nomorBon;

	@Column(name = "kode_barang")
	private String kodeBarang;

	@Column(name = "jumlah_diminta")
	private Double jumlahDiminta;

	@Column(name = "unit")
	private String unit;

	@Column(name = "jumlah_unit_stok")
	private Double jumlahUnitStok;

	@Column(name = "unit_stok")
	private String unitStok;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomorBon() {
		return nomorBon;
	}

	public void setNomorBon(String nomorBon) {
		this.nomorBon = nomorBon;
	}

	public String getKodeBarang() {
		return kodeBarang;
	}

	public void setKodeBarang(String kodeBarang) {
		this.kodeBarang = kodeBarang;
	}

	public Double getJumlahDiminta() {
		return jumlahDiminta;
	}

	public void setJumlahDiminta(Double jumlahDiminta) {
		this.jumlahDiminta = jumlahDiminta;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getJumlahUnitStok() {
		return jumlahUnitStok;
	}

	public void setJumlahUnitStok(Double jumlahUnitStok) {
		this.jumlahUnitStok = jumlahUnitStok;
	}

	public String getUnitStok() {
		return unitStok;
	}

	public void setUnitStok(String unitStok) {
		this.unitStok = unitStok;
	}
}