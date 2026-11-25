package springboot.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import springboot.entity.EBarang;

@JsonInclude(Include.ALWAYS)
public class Barang {

	public String kodeBarang;
	public String namaBarang;
	public String unit1;
	public Double konversiUnit1KeUnit2;
	public String unit2;
	public Double konversiUnit2KeUnitStok;
	public String unitStok;
	public String aktif;

	public EBarang toEntity() {
		EBarang entity = new EBarang();
		entity.setKodeBarang(kodeBarang);
		entity.setNamaBarang(namaBarang);
		entity.setUnit1(unit1);
		entity.setKonversiUnit1KeUnit2(konversiUnit1KeUnit2);
		entity.setUnit2(unit2);
		entity.setKonversiUnit2KeUnitStok(konversiUnit2KeUnitStok);
		entity.setUnitStok(unitStok);
		entity.setAktif(aktif);
		return entity;
	}

	public static Barang fromEntity(EBarang entity) {
		if (entity == null) return null;

		Barang pojo = new Barang();
		pojo.kodeBarang = entity.getKodeBarang();
		pojo.namaBarang = entity.getNamaBarang();
		pojo.unit1 = entity.getUnit1();
		pojo.konversiUnit1KeUnit2 = entity.getKonversiUnit1KeUnit2();
		pojo.unit2 = entity.getUnit2();
		pojo.konversiUnit2KeUnitStok = entity.getKonversiUnit2KeUnitStok();
		pojo.unitStok = entity.getUnitStok();
		pojo.aktif = entity.getAktif();

		return pojo;
	}

	public static List<Barang> fromEntities(List<EBarang> entities) {
		if (entities == null) return new ArrayList<>();

		List<Barang> pojos = new ArrayList<>();
		for (EBarang entity : entities) {
			pojos.add(Barang.fromEntity(entity));
		}
		return pojos;
	}
}
