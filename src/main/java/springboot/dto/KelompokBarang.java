package springboot.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import springboot.entity.EKelompokBarang;

@JsonInclude(Include.ALWAYS)
public class KelompokBarang {

	public String kodeKelompokBarang;
	public String namaKelompokBarang;
	public String flagPerluQC;
	public String defaultStatusTerimaDrSupplier;
	public String flagAttrKelompokPemasaran;
	public String flagAttrBagianProduksi;
	public String flagAttrKodeRegistrasi;
	public String flagExpiredDateAda;
	public String flagAttrBarCode;
	public String flagAttrBobotKotor;
	public String flagAttrDimensiWadah;
	public String flagAttrNomorSparePart;
	public String flagAttrSpesifikasiBarang;
	public String flagAttrKelMesinVersiTeknik;
	public String flagAttrSupplier;
	public String flagAttrTahunPembuatan;
	public String flagAttrCatatanLainLain;
	public String flagAttrSubLedger;
	public String flagAdaSubCode;
	public String flagActive;
	public Date tanggalInput;
	public String userInput;
	
	public EKelompokBarang toEntity() {
		
		EKelompokBarang entity = new EKelompokBarang();
		
		entity.setKodeKelompokBarang(kodeKelompokBarang);
		entity.setNamaKelompokBarang(namaKelompokBarang);
		entity.setFlagPerluQC(flagPerluQC);
		entity.setDefaultStatusTerimaDrSupplier(defaultStatusTerimaDrSupplier);
		entity.setFlagAttrKelompokPemasaran(flagAttrKelompokPemasaran);
		entity.setFlagAttrBagianProduksi(flagAttrBagianProduksi);
		entity.setFlagAttrKodeRegistrasi(flagAttrKodeRegistrasi);
		entity.setFlagExpiredDateAda(flagExpiredDateAda);
		entity.setFlagAttrBarCode(flagAttrBarCode);
		entity.setFlagAttrBobotKotor(flagAttrBobotKotor);
		entity.setFlagAttrDimensiWadah(flagAttrDimensiWadah);
		entity.setFlagAttrNomorSparePart(flagAttrNomorSparePart);
		entity.setFlagAttrSpesifikasiBarang(flagAttrSpesifikasiBarang);
		entity.setFlagAttrKelMesinVersiTeknik(flagAttrKelMesinVersiTeknik);
		entity.setFlagAttrSupplier(flagAttrSupplier);
		entity.setFlagAttrTahunPembuatan(flagAttrTahunPembuatan);
		entity.setFlagAttrCatatanLainLain(flagAttrCatatanLainLain);
		entity.setFlagAttrSubLedger(flagAttrSubLedger);
		entity.setFlagAdaSubCode(flagAdaSubCode);
		entity.setFlagActive(flagActive);
		entity.setTanggalInput(tanggalInput);
		entity.setUserInput(userInput);

		return entity;
	}
	
	public static KelompokBarang fromEntity(EKelompokBarang entity) {
		if (entity == null) return null;
		
		KelompokBarang pojo = null;
		pojo = new KelompokBarang();
		
		pojo.kodeKelompokBarang = entity.getKodeKelompokBarang();
	    pojo.namaKelompokBarang = entity.getNamaKelompokBarang();
	    pojo.flagPerluQC = entity.getFlagPerluQC();
	    pojo.defaultStatusTerimaDrSupplier = entity.getDefaultStatusTerimaDrSupplier();
	    pojo.flagAttrKelompokPemasaran = entity.getFlagAttrKelompokPemasaran();
	    pojo.flagAttrBagianProduksi = entity.getFlagAttrBagianProduksi();
	    pojo.flagAttrKodeRegistrasi = entity.getFlagAttrKodeRegistrasi();
	    pojo.flagExpiredDateAda = entity.getFlagExpiredDateAda();
	    pojo.flagAttrBarCode = entity.getFlagAttrBarCode();
	    pojo.flagAttrBobotKotor = entity.getFlagAttrBobotKotor();
	    pojo.flagAttrDimensiWadah = entity.getFlagAttrDimensiWadah();
	    pojo.flagAttrNomorSparePart = entity.getFlagAttrNomorSparePart();
	    pojo.flagAttrSpesifikasiBarang = entity.getFlagAttrSpesifikasiBarang();
	    pojo.flagAttrKelMesinVersiTeknik = entity.getFlagAttrKelMesinVersiTeknik();
	    pojo.flagAttrSupplier = entity.getFlagAttrSupplier();
	    pojo.flagAttrTahunPembuatan = entity.getFlagAttrTahunPembuatan();
	    pojo.flagAttrCatatanLainLain = entity.getFlagAttrCatatanLainLain();
	    pojo.flagAttrSubLedger = entity.getFlagAttrSubLedger();
	    pojo.flagAdaSubCode = entity.getFlagAdaSubCode();
	    pojo.flagActive = entity.getFlagActive();
	    pojo.tanggalInput = entity.getTanggalInput();
	    pojo.userInput = entity.getUserInput();
		
		return pojo;
	}
	
	public static List<KelompokBarang> fromEntities(List<EKelompokBarang> entities){
		if (entities == null) return new ArrayList<>();
		
		List<KelompokBarang> pojos = new ArrayList<>();
		
		for (EKelompokBarang entity : entities) {
			pojos.add(KelompokBarang.fromEntity(entity));
		}
		return pojos;
	}
}
