package springboot.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import springboot.entity.ESubKelompokBarang;

@JsonInclude(Include.ALWAYS)
public class SubKelompokBarang {

	public String kodeKelompokBarang;
	public String kodeSubKelompokBarang;
	public String namaSubKelompokBarang;
	public String flagBarangPerluDiQC;
	public String defaultStatusTerimaDariSupplier;
	public String flagSubDiSchedule;
	public String flagActive;
	public Date tanggalInput;
	public String userInput;
	
	public KelompokBarang kelompokBarang;
	
	public ESubKelompokBarang toEntity() {
		
		ESubKelompokBarang entity = new ESubKelompokBarang();
		
		entity.setKodeKelompokBarang(kodeKelompokBarang);
		entity.setKodeSubKelompokBarang(kodeSubKelompokBarang);
		entity.setNamaSubKelompokBarang(namaSubKelompokBarang);
		entity.setFlagBarangPerluDiQC(flagBarangPerluDiQC);
		entity.setDefaultStatusTerimaDariSupplier(defaultStatusTerimaDariSupplier);
		entity.setFlagSubDiSchedule(flagSubDiSchedule);
		entity.setFlagActive(flagActive);
		entity.setTanggalInput(tanggalInput);
		entity.setUserInput(userInput);

		return entity;
	}
	
	public static SubKelompokBarang fromEntity(ESubKelompokBarang entity) {
		if (entity == null) return null;
		
		SubKelompokBarang pojo = null;
		pojo = new SubKelompokBarang();
		
	    pojo.kodeKelompokBarang = entity.getKodeKelompokBarang();
	    pojo.kodeSubKelompokBarang = entity.getKodeSubKelompokBarang();
	    pojo.namaSubKelompokBarang = entity.getNamaSubKelompokBarang();
	    pojo.flagBarangPerluDiQC = entity.getFlagBarangPerluDiQC();
	    pojo.defaultStatusTerimaDariSupplier = entity.getDefaultStatusTerimaDariSupplier();
	    pojo.flagSubDiSchedule = entity.getFlagSubDiSchedule();
	    pojo.flagActive = entity.getFlagActive();
	    pojo.tanggalInput = entity.getTanggalInput();
	    pojo.userInput = entity.getUserInput();
		
		pojo.kelompokBarang = KelompokBarang.fromEntity(entity.getKelompokBarang());
			
		return pojo;
	}
	
	public static List<SubKelompokBarang> fromEntities(List<ESubKelompokBarang> entities){
		if (entities == null) return new ArrayList<>();
		
		List<SubKelompokBarang> pojos = new ArrayList<>();
		
		for (ESubKelompokBarang entity : entities) {
			pojos.add(SubKelompokBarang.fromEntity(entity));
		}
		return pojos;
	}
}
