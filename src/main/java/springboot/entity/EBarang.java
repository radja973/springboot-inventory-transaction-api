package springboot.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mstbarang")
public class EBarang implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "kode_barang")
	private String kodeBarang;

	@Column(name = "nama_barang")
	private String namaBarang;

	@Column(name = "unit_1")
	private String unit1;

	@Column(name = "konversi_unit_1_ke_unit_2")
	private Double konversiUnit1KeUnit2;

	@Column(name = "unit_2")
	private String unit2;

	@Column(name = "konversi_unit_2_ke_unit_stok")
	private Double konversiUnit2KeUnitStok;

	@Column(name = "unit_stok")
	private String unitStok;

	@Column(name = "aktif")
	private String aktif;

	public String getKodeBarang() {
		return kodeBarang;
	}

	public void setKodeBarang(String kodeBarang) {
		this.kodeBarang = kodeBarang;
	}

	public String getNamaBarang() {
		return namaBarang;
	}

	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}

	public String getUnit1() {
		return unit1;
	}

	public void setUnit1(String unit1) {
		this.unit1 = unit1;
	}

	public Double getKonversiUnit1KeUnit2() {
		return konversiUnit1KeUnit2;
	}

	public void setKonversiUnit1KeUnit2(Double konversiUnit1KeUnit2) {
		this.konversiUnit1KeUnit2 = konversiUnit1KeUnit2;
	}

	public String getUnit2() {
		return unit2;
	}

	public void setUnit2(String unit2) {
		this.unit2 = unit2;
	}

	public Double getKonversiUnit2KeUnitStok() {
		return konversiUnit2KeUnitStok;
	}

	public void setKonversiUnit2KeUnitStok(Double konversiUnit2KeUnitStok) {
		this.konversiUnit2KeUnitStok = konversiUnit2KeUnitStok;
	}

	public String getUnitStok() {
		return unitStok;
	}

	public void setUnitStok(String unitStok) {
		this.unitStok = unitStok;
	}

	public String getAktif() {
		return aktif;
	}

	public void setAktif(String aktif) {
		this.aktif = aktif;
	}
}
