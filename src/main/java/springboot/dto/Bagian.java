package springboot.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import springboot.entity.EBagian;

@JsonInclude(Include.ALWAYS)
public class Bagian {

	public String kode;
	public String nama;
	
	public EBagian toEntity() {
		EBagian entity = new EBagian();
		entity.setKode(kode);
		entity.setNama(nama);
		return entity;
	}
	
	public static Bagian fromEntity(EBagian entity) {
		if (entity == null) return null;
		
		Bagian pojo = new Bagian();
		pojo.kode = entity.getKode();
		pojo.nama = entity.getNama();
		
		return pojo;
	}
	
	public static List<Bagian> fromEntities(List<EBagian> entities) {
		if (entities == null) return new ArrayList<>();
		
		List<Bagian> pojos = new ArrayList<>();
		for (EBagian entity : entities) {
			pojos.add(Bagian.fromEntity(entity));
		}
		return pojos;
	}
}
