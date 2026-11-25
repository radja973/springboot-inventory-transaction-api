package springboot.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import springboot.entity.EDetailTransaksiBarang;

// dto (data transfer object) -> model komunikasi dgn 'luar' (client/Bruno), makanya ada toEntity/fromEntity di sini
@JsonInclude(Include.ALWAYS)
public class DetailTransaksiBarang {

	public Integer id;
	public String nomorBon; // FK ke header (nomorBon), ikut tipe PK header yg String
	public String kodeBarang;
	public Double jumlahDiminta;
	public String unit;
	public Double jumlahUnitStok;
	public String unitStok;

	public EDetailTransaksiBarang toEntity() {
		EDetailTransaksiBarang entity = new EDetailTransaksiBarang();
		entity.setId(id);
		entity.setNomorBon(nomorBon);
		entity.setKodeBarang(kodeBarang);
		entity.setJumlahDiminta(jumlahDiminta);
		entity.setUnit(unit);
		entity.setJumlahUnitStok(jumlahUnitStok);
		entity.setUnitStok(unitStok);
		return entity;
	}

	public static DetailTransaksiBarang fromEntity(EDetailTransaksiBarang entity) {
		if (entity == null) return null;

		DetailTransaksiBarang dto = new DetailTransaksiBarang();
		dto.id = entity.getId();
		dto.nomorBon = entity.getNomorBon();
		dto.kodeBarang = entity.getKodeBarang();
		dto.jumlahDiminta = entity.getJumlahDiminta();
		dto.unit = entity.getUnit();
		dto.jumlahUnitStok = entity.getJumlahUnitStok();
		dto.unitStok = entity.getUnitStok();

		return dto;
	}

	public static List<DetailTransaksiBarang> fromEntities(List<EDetailTransaksiBarang> entities) {
		if (entities == null) return new ArrayList<>();

		List<DetailTransaksiBarang> dtos = new ArrayList<>();
		for (EDetailTransaksiBarang entity : entities) {
			dtos.add(DetailTransaksiBarang.fromEntity(entity));
		}
		return dtos;
	}
}