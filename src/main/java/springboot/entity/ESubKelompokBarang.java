package springboot.entity;

import java.io.Serializable;
//import java.sql.Date;
import java.util.Date;

	import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Access(AccessType.FIELD)
@Table(name = "am16", uniqueConstraints = {@UniqueConstraint(columnNames = {"klbrcd","sbkbcd"}) })
@IdClass(ESubKelompokBarangId.class)
public class ESubKelompokBarang implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "klbrcd")
	private String kodeKelompokBarang;

	@Id
	@Column(name = "sbkbcd")
	private String kodeSubKelompokBarang;

	@Column(name = "sbkbnm")
	private String namaSubKelompokBarang;

	@Column(name = "klbrqc")
	private String flagBarangPerluDiQC;

	@Column(name = "kbdfqc")
	private String defaultStatusTerimaDariSupplier;

	@Column(name = "flgsch")
	private String flagSubDiSchedule;

	@Column(name = "active")
	private String flagActive;

	@Column(name = "tglkey")
	private Date tanggalInput;

	@Column(name = "usrkey")
	private String userInput;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "klbrcd", updatable = false, insertable = false)
	private EKelompokBarang eKelompokBarang;

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

	public String getNamaSubKelompokBarang() {
		return namaSubKelompokBarang;
	}

	public void setNamaSubKelompokBarang(String namaSubKelompokBarang) {
		this.namaSubKelompokBarang = namaSubKelompokBarang;
	}

	public String getFlagBarangPerluDiQC() {
		return flagBarangPerluDiQC;
	}

	public void setFlagBarangPerluDiQC(String flagBarangPerluDiQC) {
		this.flagBarangPerluDiQC = flagBarangPerluDiQC;
	}

	public String getDefaultStatusTerimaDariSupplier() {
		return defaultStatusTerimaDariSupplier;
	}

	public void setDefaultStatusTerimaDariSupplier(String defaultStatusTerimaDariSupplier) {
		this.defaultStatusTerimaDariSupplier = defaultStatusTerimaDariSupplier;
	}

	public String getFlagSubDiSchedule() {
		return flagSubDiSchedule;
	}

	public void setFlagSubDiSchedule(String flagSubDiSchedule) {
		this.flagSubDiSchedule = flagSubDiSchedule;
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

	public EKelompokBarang getKelompokBarang() {
		return eKelompokBarang;
	}

	public void setKelompokBarang(EKelompokBarang eKelompokBarang) {
		this.eKelompokBarang = eKelompokBarang;
	}

	@PrePersist
	private void prePersist() {
		Date currentDateTime = new Date();
		
		this.userInput = "";  // ini nanti harusnya ambil dari : CurrentUser.getUsername();
		this.tanggalInput = currentDateTime;
	}

	@PreUpdate
	private void preUpdate() {
		Date currentDateTime = new Date();
		
		this.userInput = "";  // ini nanti harusnya ambil dari : CurrentUser.getUsername();
		this.tanggalInput = currentDateTime;
	}
}
