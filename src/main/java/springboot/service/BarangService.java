package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.entity.EBarang;
import springboot.exceptions.CustomException;
import springboot.dto.Barang;
import springboot.repositories.EBarangRepository;

@Service("barangService")
public class BarangService {

	@Autowired
	private EBarangRepository eBarangRepository;

	public Barang findByKodeBarang(String kodeBarang) {
		// gada inisialisasi karena yg dikirim cuma string plain bukan pojo tanpa perlu inisialisasi
		
		// 2. Validasi
		validasiKodeWajibDiisi(kodeBarang);

		EBarang eBarang = eBarangRepository.findByKodeBarang(kodeBarang);
		validasiKodeBarangHarusTerdaftar(eBarang);

		return Barang.fromEntity(eBarang);
	}

	@Transactional
	public Barang add(Barang pojo) {
		EBarang entity = pojo.toEntity();

		// Mencegah duplikasi data akibat perbedaan spasi di awal/akhir input (misal: "BRG01" dan "  BRG01  ")
		if (entity.getKodeBarang() != null) {
			entity.setKodeBarang(entity.getKodeBarang().trim());
		}
		if (entity.getNamaBarang() != null) {
			entity.setNamaBarang(entity.getNamaBarang().trim());
		}

		validasiKodeBarangWajibDiisi(entity);
		validasiNamaBarangWajibDiisi(entity);
		validasiUnit1WajibDiisi(entity);
		validasiUnit2WajibDiisi(entity);
		validasiUnitStokWajibDiisi(entity);
		validasiKonversiUnit1KeUnit2HarusLebihDariNol(entity);
		validasiKonversiUnit2KeUnitStokHarusLebihDariNol(entity);
		validasiStatusAktifHanyaBolehYAtauN(entity);

		validasiKodeBarangBelumTerdaftar(entity);

		EBarang saved = eBarangRepository.save(entity);
		return Barang.fromEntity(saved);
	}

	@Transactional
	public Barang edit(Barang pojo) {

		// inisialisasi
		EBarang entity = pojo.toEntity();

		// Mencegah duplikasi data akibat perbedaan spasi di awal/akhir input (misal: "BRG01" dan "  BRG01  ")
		if (entity.getKodeBarang() != null) {
			entity.setKodeBarang(entity.getKodeBarang().trim());
		}
		if (entity.getNamaBarang() != null) {
			entity.setNamaBarang(entity.getNamaBarang().trim());
		}

		// validasi
		validasiKodeBarangWajibDiisi(entity);
		validasiNamaBarangWajibDiisi(entity);
		validasiUnit1WajibDiisi(entity);
		validasiUnit2WajibDiisi(entity);
		validasiUnitStokWajibDiisi(entity);
		validasiKonversiUnit1KeUnit2HarusLebihDariNol(entity);
		validasiKonversiUnit2KeUnitStokHarusLebihDariNol(entity);
		validasiStatusAktifHanyaBolehYAtauN(entity);

		// PEMROSESAN data
		// cek apakah data sudah ada
		EBarang existing = eBarangRepository.findByKodeBarang(entity.getKodeBarang());
		validasiKodeBarangHarusTerdaftar(existing);
		
		// update data
		existing.setNamaBarang(entity.getNamaBarang());
		existing.setUnit1(entity.getUnit1());
		existing.setKonversiUnit1KeUnit2(entity.getKonversiUnit1KeUnit2());
		existing.setUnit2(entity.getUnit2());
		existing.setKonversiUnit2KeUnitStok(entity.getKonversiUnit2KeUnitStok());
		existing.setUnitStok(entity.getUnitStok());
		existing.setAktif(entity.getAktif());

		EBarang saved = eBarangRepository.save(existing);
		return Barang.fromEntity(saved);
	}

	@Transactional
	public void delete(String kodeBarang) {
		// gada inisialisasi karena yg dikirim cuma string plain bukan pojo tanpa perlu inisialisasi

		// 2. Validasi
		validasiKodeWajibDiisi(kodeBarang);

		EBarang eBarang = eBarangRepository.findByKodeBarang(kodeBarang);
		validasiKodeBarangHarusTerdaftar(eBarang);

		eBarangRepository.delete(eBarang);
	}

	// ==================== VALIDASI

	private void validasiKodeWajibDiisi(String kodeBarang) {
		if (kodeBarang == null || kodeBarang.trim().isEmpty()) {
			throw new CustomException("VALIDATION_ERROR", "Kode barang wajib diisi.");
		}
	}

	private void validasiKodeBarangWajibDiisi(EBarang barang) {
		if (barang == null || barang.getKodeBarang() == null || barang.getKodeBarang().trim().isEmpty()) {
			throw new CustomException("VALIDATION_ERROR", "Kode barang wajib diisi.");
		}
	}

	private void validasiNamaBarangWajibDiisi(EBarang barang) {
		if (barang == null || barang.getNamaBarang() == null || barang.getNamaBarang().trim().isEmpty()) {
			throw new CustomException("VALIDATION_ERROR", "Nama barang wajib diisi.");
		}
	}

	private void validasiUnit1WajibDiisi(EBarang barang) {
		if (barang == null || barang.getUnit1() == null || barang.getUnit1().trim().isEmpty()) {
			throw new CustomException("VALIDATION_ERROR", "Unit 1 wajib diisi.");
		}
	}

	private void validasiUnit2WajibDiisi(EBarang barang) {
		if (barang == null || barang.getUnit2() == null || barang.getUnit2().trim().isEmpty()) {
			throw new CustomException("VALIDATION_ERROR", "Unit 2 wajib diisi.");
		}
	}

	private void validasiUnitStokWajibDiisi(EBarang barang) {
		if (barang == null || barang.getUnitStok() == null || barang.getUnitStok().trim().isEmpty()) {
			throw new CustomException("VALIDATION_ERROR", "Unit stok wajib diisi.");
		}
	}

	private void validasiKonversiUnit1KeUnit2HarusLebihDariNol(EBarang barang) {
		if (barang == null || barang.getKonversiUnit1KeUnit2() == null || barang.getKonversiUnit1KeUnit2() <= 0) {
			throw new CustomException("VALIDATION_ERROR", "Konversi Unit 1 ke Unit 2 harus lebih dari 0.");
		}
	}

	private void validasiKonversiUnit2KeUnitStokHarusLebihDariNol(EBarang barang) {
		if (barang == null || barang.getKonversiUnit2KeUnitStok() == null || barang.getKonversiUnit2KeUnitStok() <= 0) {
			throw new CustomException("VALIDATION_ERROR", "Konversi Unit 2 ke Unit Stok harus lebih dari 0.");
		}
	}

	private void validasiStatusAktifHanyaBolehYAtauN(EBarang barang) {
		if (barang == null || barang.getAktif() == null || (!barang.getAktif().equalsIgnoreCase("Y") && !barang.getAktif().equalsIgnoreCase("N"))) {
			throw new CustomException("VALIDATION_ERROR", "Status aktif hanya boleh Y atau N.");
		}
	}

	private void validasiKodeBarangBelumTerdaftar(EBarang barang) {
		EBarang existing = eBarangRepository.findByKodeBarang(barang.getKodeBarang());
		if (existing != null) {
			throw new CustomException("VALIDATION_ERROR", "Kode barang sudah ada.");
		}
	}

	private void validasiKodeBarangHarusTerdaftar(EBarang barang) {
		if (barang == null) {
			throw new CustomException("VALIDATION_ERROR", "Kode barang tidak ditemukan.");
		}
	}
}
