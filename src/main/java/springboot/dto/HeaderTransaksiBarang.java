package springboot.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import springboot.entity.EHeaderTransaksiBarang;

// dto (data transfer object) -> model komunikasi bagi 'luar' dgn BE (bukan pojo murni krn ada toEntity/fromEntity)
// "detail" ditambahkan di sini supaya header + detail bisa dikirim & disimpan SEKALIGUS dalam satu kali panggil endpoint
@JsonInclude(Include.ALWAYS)
public class HeaderTransaksiBarang {

	public String nomorBon;

	// @JsonFormat maksa Jackson serialize/deserialize LocalDate sebagai string "yyyy-MM-dd"
	// (bukan array [2026,8,30]) karena modul jackson-datatype-jsr310 belum ada di project versi lama ini
	@JsonFormat(pattern = "yyyy-MM-dd")
	public LocalDate tanggalBon;

	public String bagianPeminta;
	public String keterangan;
	public List<DetailTransaksiBarang> detail = new ArrayList<>();

	public EHeaderTransaksiBarang toEntity() {
		EHeaderTransaksiBarang entity = new EHeaderTransaksiBarang();
		entity.setNomorBon(nomorBon);
		entity.setTanggalBon(toSqlDate(tanggalBon));
		entity.setBagianPeminta(bagianPeminta);
		entity.setKeterangan(keterangan);
		return entity;
	}

	public static HeaderTransaksiBarang fromEntity(EHeaderTransaksiBarang entity) {
		if (entity == null) return null;

		HeaderTransaksiBarang dto = new HeaderTransaksiBarang();
		dto.nomorBon = entity.getNomorBon();
		dto.tanggalBon = toLocalDate(entity.getTanggalBon());
		dto.bagianPeminta = entity.getBagianPeminta();
		dto.keterangan = entity.getKeterangan();

		return dto;
	}

	// dipakai supaya response GET/POST/PUT balikin header SEKALIGUS sama detail-detailnya (satu kali panggil, satu response utuh)
	public static HeaderTransaksiBarang fromEntity(EHeaderTransaksiBarang entity, List<springboot.entity.EDetailTransaksiBarang> detailEntities) {
		HeaderTransaksiBarang dto = fromEntity(entity);
		if (dto == null) return null;

		dto.detail = DetailTransaksiBarang.fromEntities(detailEntities);
		return dto;
	}

	public static List<HeaderTransaksiBarang> fromEntities(List<EHeaderTransaksiBarang> entities) {
		if (entities == null) return new ArrayList<>();

		List<HeaderTransaksiBarang> dtos = new ArrayList<>();
		for (EHeaderTransaksiBarang entity : entities) {
			dtos.add(HeaderTransaksiBarang.fromEntity(entity));
		}
		return dtos;
	}

	// --- konversi LocalDate (DTO/JSON) <-> java.util.Date (entity, krn Hibernate 5.0.11 blm support LocalDate) ---
	// PENTING: pakai java.sql.Date.valueOf()/.toLocalDate(), BUKAN toInstant()/atStartOfDay().
	// Hibernate + JDBC driver Postgres selalu balikin java.sql.Date buat kolom bertipe "date", dan
	// java.sql.Date.toInstant() SELALU throw UnsupportedOperationException (dokumentasi resmi Java).
	private static java.util.Date toSqlDate(LocalDate localDate) {
		if (localDate == null) return null;
		return Date.valueOf(localDate);
	}

	private static LocalDate toLocalDate(java.util.Date date) {
		if (date == null) return null;
		if (date instanceof Date) {
			return ((Date) date).toLocalDate();
		}
		// jaga-jaga kalau suatu saat dapet java.util.Date murni (bukan java.sql.Date)
		return new Date(date.getTime()).toLocalDate();
	}
}