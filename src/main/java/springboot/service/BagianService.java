package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.entity.EBagian;
import springboot.exceptions.CustomException;
import springboot.dto.Bagian;
import springboot.repositories.EBagianRepository;

@Service("bagianService")
public class BagianService {

	@Autowired
	private EBagianRepository eBagianRepository;

	public Bagian findByKode(String kode) {
		// gada inisialisasi karena yg dikirim cuma string plain bukan pojo tanpa perlu inisialisasi
		
		// 2. Validasi
		validasiKodeWajibDiisi(kode);

		EBagian eBagian = eBagianRepository.findByKode(kode);
		validasiKodeBagianHarusTerdaftar(eBagian);

		return Bagian.fromEntity(eBagian);
	}

	@Transactional
	public Bagian add(Bagian pojo) {
		// 1. Inisialisasi
		EBagian entity = pojo.toEntity();

		// Mencegah duplikasi data akibat perbedaan spasi di awal/akhir input (misal: "PROD" dan "  PROD  ")
		if (entity.getKode() != null) {
			entity.setKode(entity.getKode().trim());
		}
		if (entity.getNama() != null) {
			entity.setNama(entity.getNama().trim());
		}

		// 2. Validasi
		validasiKodeBagianWajibDiisi(entity);
		validasiNamaBagianWajibDiisi(entity);
		validasiKodeBagianBelumTerdaftar(entity);

		// 3. Pemrosesan
		EBagian saved = eBagianRepository.save(entity);
		return Bagian.fromEntity(saved);
	}

	@Transactional
	public Bagian edit(Bagian pojo) {
		// 1. Inisialisasi
		EBagian entity = pojo.toEntity();

		// Mencegah duplikasi data akibat perbedaan spasi di awal/akhir input (misal: "PROD" dan "  PROD  ")
		if (entity.getKode() != null) {
			entity.setKode(entity.getKode().trim());
		}
		if (entity.getNama() != null) {
			entity.setNama(entity.getNama().trim());
		}

		// 2. Validasi
		validasiKodeBagianWajibDiisi(entity);
		validasiNamaBagianWajibDiisi(entity);

		EBagian existing = eBagianRepository.findByKode(entity.getKode());
		validasiKodeBagianHarusTerdaftar(existing);

		// 3. Pemrosesan
		existing.setNama(entity.getNama());
		EBagian saved = eBagianRepository.save(existing);
		return Bagian.fromEntity(saved);
	}

	@Transactional
	public void delete(String kode) {
		// gada inisialisasi karena yg dikirim cuma string plain bukan pojo tanpa perlu inisialisasi

		// 2. Validasi
		validasiKodeWajibDiisi(kode);

		EBagian eBagian = eBagianRepository.findByKode(kode);
		validasiKodeBagianHarusTerdaftar(eBagian);

		// 3. Pemrosesan
		eBagianRepository.delete(eBagian);
	}

	// ==================== VALIDASI

	private void validasiKodeWajibDiisi(String kode) {
		if (kode == null || kode.trim().isEmpty()) {
			throw new CustomException("VALIDATION_ERROR", "Kode bagian wajib diisi.");
		}
	}

	private void validasiKodeBagianWajibDiisi(EBagian bagian) {
		if (bagian == null || bagian.getKode() == null || bagian.getKode().trim().isEmpty()) {
			throw new CustomException("VALIDATION_ERROR", "Kode bagian wajib diisi.");
		}
	}

	private void validasiNamaBagianWajibDiisi(EBagian bagian) {
		if (bagian == null || bagian.getNama() == null || bagian.getNama().trim().isEmpty()) {
			throw new CustomException("VALIDATION_ERROR", "Nama bagian wajib diisi.");
		}
	}

	private void validasiKodeBagianBelumTerdaftar(EBagian bagian) {
		EBagian existing = eBagianRepository.findByKode(bagian.getKode());
		if (existing != null) {
			throw new CustomException("VALIDATION_ERROR", "Kode Bagian sudah ada.");
		}
	}

	private void validasiKodeBagianHarusTerdaftar(EBagian bagian) {
		if (bagian == null) {
			throw new CustomException("VALIDATION_ERROR", "Bagian tidak ditemukan.");
		}
	}
}
