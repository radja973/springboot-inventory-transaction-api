package springboot.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "am15", uniqueConstraints = {@UniqueConstraint(columnNames = {"klbrcd"}) })
public class EKelompokBarang implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "klbrcd")
	private String kodeKelompokBarang;

	@Column(name = "klbrnm")
	private String namaKelompokBarang;

	@Column(name = "klbrqc")
	private String flagPerluQC;

	@Column(name = "kbdfqc")
	private String defaultStatusTerimaDrSupplier;

	@Column(name = "kbgrbn")
	private String flagAttrKelompokPemasaran;
	
	@Column(name = "kbbgpr")
	private String flagAttrBagianProduksi;

	@Column(name = "kbregs")
	private String flagAttrKodeRegistrasi;

	@Column(name = "kbexpd")
	private String flagExpiredDateAda;

	@Column(name = "kbbarc")
	private String flagAttrBarCode;

	@Column(name = "kbbtkt")
	private String flagAttrBobotKotor;

	@Column(name = "kbdmkr")
	private String flagAttrDimensiWadah;

	@Column(name = "kbptno")
	private String flagAttrNomorSparePart;

	@Column(name = "kbspsb")
	private String flagAttrSpesifikasiBarang;

	@Column(name = "kbkmtk")
	private String flagAttrKelMesinVersiTeknik;

	@Column(name = "kbsupp")
	private String flagAttrSupplier;

	@Column(name = "kbthbt")
	private String flagAttrTahunPembuatan;

	@Column(name = "kbnote")
	private String flagAttrCatatanLainLain;

	@Column(name = "kbaksc")
	private String flagAttrSubLedger;

	@Column(name = "kbsubc")
	private String flagAdaSubCode;

	@Column(name = "active")
	private String flagActive;

	@Column(name = "tglkey")
	private Date tanggalInput;

	@Column(name = "usrkey")
	private String userInput;

	public String getKodeKelompokBarang() {
		return kodeKelompokBarang;
	}

	public void setKodeKelompokBarang(String kodeKelompokBarang) {
		this.kodeKelompokBarang = kodeKelompokBarang;
	}

	public String getNamaKelompokBarang() {
		return namaKelompokBarang;
	}

	public void setNamaKelompokBarang(String namaKelompokBarang) {
		this.namaKelompokBarang = namaKelompokBarang;
	}

	public String getFlagPerluQC() {
		return flagPerluQC;
	}

	public void setFlagPerluQC(String flagPerluQC) {
		this.flagPerluQC = flagPerluQC;
	}

	public String getDefaultStatusTerimaDrSupplier() {
		return defaultStatusTerimaDrSupplier;
	}

	public void setDefaultStatusTerimaDrSupplier(String defaultStatusTerimaDrSupplier) {
		this.defaultStatusTerimaDrSupplier = defaultStatusTerimaDrSupplier;
	}

	public String getFlagAttrKelompokPemasaran() {
		return flagAttrKelompokPemasaran;
	}

	public void setFlagAttrKelompokPemasaran(String flagAttrKelompokPemasaran) {
		this.flagAttrKelompokPemasaran = flagAttrKelompokPemasaran;
	}

	public String getFlagAttrBagianProduksi() {
		return flagAttrBagianProduksi;
	}

	public void setFlagAttrBagianProduksi(String flagAttrBagianProduksi) {
		this.flagAttrBagianProduksi = flagAttrBagianProduksi;
	}

	public String getFlagAttrKodeRegistrasi() {
		return flagAttrKodeRegistrasi;
	}

	public void setFlagAttrKodeRegistrasi(String flagAttrKodeRegistrasi) {
		this.flagAttrKodeRegistrasi = flagAttrKodeRegistrasi;
	}

	public String getFlagExpiredDateAda() {
		return flagExpiredDateAda;
	}

	public void setFlagExpiredDateAda(String flagExpiredDateAda) {
		this.flagExpiredDateAda = flagExpiredDateAda;
	}

	public String getFlagAttrBarCode() {
		return flagAttrBarCode;
	}

	public void setFlagAttrBarCode(String flagAttrBarCode) {
		this.flagAttrBarCode = flagAttrBarCode;
	}

	public String getFlagAttrBobotKotor() {
		return flagAttrBobotKotor;
	}

	public void setFlagAttrBobotKotor(String flagAttrBobotKotor) {
		this.flagAttrBobotKotor = flagAttrBobotKotor;
	}

	public String getFlagAttrDimensiWadah() {
		return flagAttrDimensiWadah;
	}

	public void setFlagAttrDimensiWadah(String flagAttrDimensiWadah) {
		this.flagAttrDimensiWadah = flagAttrDimensiWadah;
	}

	public String getFlagAttrNomorSparePart() {
		return flagAttrNomorSparePart;
	}

	public void setFlagAttrNomorSparePart(String flagAttrNomorSparePart) {
		this.flagAttrNomorSparePart = flagAttrNomorSparePart;
	}

	public String getFlagAttrSpesifikasiBarang() {
		return flagAttrSpesifikasiBarang;
	}

	public void setFlagAttrSpesifikasiBarang(String flagAttrSpesifikasiBarang) {
		this.flagAttrSpesifikasiBarang = flagAttrSpesifikasiBarang;
	}

	public String getFlagAttrKelMesinVersiTeknik() {
		return flagAttrKelMesinVersiTeknik;
	}

	public void setFlagAttrKelMesinVersiTeknik(String flagAttrKelMesinVersiTeknik) {
		this.flagAttrKelMesinVersiTeknik = flagAttrKelMesinVersiTeknik;
	}

	public String getFlagAttrSupplier() {
		return flagAttrSupplier;
	}

	public void setFlagAttrSupplier(String flagAttrSupplier) {
		this.flagAttrSupplier = flagAttrSupplier;
	}

	public String getFlagAttrTahunPembuatan() {
		return flagAttrTahunPembuatan;
	}

	public void setFlagAttrTahunPembuatan(String flagAttrTahunPembuatan) {
		this.flagAttrTahunPembuatan = flagAttrTahunPembuatan;
	}

	public String getFlagAttrCatatanLainLain() {
		return flagAttrCatatanLainLain;
	}

	public void setFlagAttrCatatanLainLain(String flagAttrCatatanLainLain) {
		this.flagAttrCatatanLainLain = flagAttrCatatanLainLain;
	}

	public String getFlagAttrSubLedger() {
		return flagAttrSubLedger;
	}

	public void setFlagAttrSubLedger(String flagAttrSubLedger) {
		this.flagAttrSubLedger = flagAttrSubLedger;
	}

	public String getFlagAdaSubCode() {
		return flagAdaSubCode;
	}

	public void setFlagAdaSubCode(String flagAdaSubCode) {
		this.flagAdaSubCode = flagAdaSubCode;
	}

	public String getFlagActive() {
		return flagActive;
	}

	public void setFlagActive(String flagActive) {
		this.flagActive = flagActive;
	}

	public Date getTanggalInput() {
		return tanggalInput;
	}

	public void setTanggalInput(Date tanggalInput) {
		this.tanggalInput = tanggalInput;
	}

	public String getUserInput() {
		return userInput;
	}

	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}

}
