package springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.dto.DetailTransaksiBarang;
import springboot.dto.HeaderTransaksiBarang;
import springboot.entity.EBarang;
import springboot.entity.EDetailTransaksiBarang;
import springboot.entity.EHeaderTransaksiBarang;
import springboot.exceptions.CustomException;
import springboot.repositories.EBagianRepository;
import springboot.repositories.EBarangRepository;
import springboot.repositories.EDetailTransaksiBarangRepository;
import springboot.repositories.EHeaderTransaksiBarangRepository;

// blm waktunya ada DetailTransaksiBarangService/Controller terpisah -> untuk sekarang transaksi (header + detail)
// disatukan di service ini, detail-nya cukup di-LOOP di sini. Jadi input transaksi cukup SATU KALI panggil endpoint
// (POST /api/headertransaksi) yg isinya header + list detail sekaligus, bukan hit endpoint detail satu-satu.
@Service("headerTransaksiBarangService")
public class HeaderTransaksiBarangService {

	@Autowired
	private EHeaderTransaksiBarangRepository eHeaderTransaksiBarangRepository;

	@Autowired
	private EDetailTransaksiBarangRepository eDetailTransaksiBarangRepository;

	@Autowired
	private EBagianRepository eBagianRepository;

	@Autowired
	private EBarangRepository eBarangRepository;

	public HeaderTransaksiBarang findByNomorBon(String nomorBon) {
		// gada inisialisasi karena yg dikirim cuma string plain bukan pojo tanpa perlu inisialisasi

		// 2. Validasi
		validasiNomorBonWajibDiisi(nomorBon);

		EHeaderTransaksiBarang header = eHeaderTransaksiBarangRepository.findByNomorBon(nomorBon);
		validasiNomorBonDitemukan(header);

		// ambil detail-detailnya SEKALIGUS biar client dapet transaksi utuh dalam satu kali panggil
		List<EDetailTransaksiBarang> details = eDetailTransaksiBarangRepository.findByNomorBon(nomorBon);

		return HeaderTransaksiBarang.fromEntity(header, details);
	}

	@Transactional
	public HeaderTransaksiBarang add(HeaderTransaksiBarang dto) {
		// 1. Inisialisasi
		EHeaderTransaksiBarang entity = dto.toEntity();

		// Mencegah duplikasi data akibat perbedaan spasi di awal/akhir input (misal: "BON-2026-0001" dan "  BON-2026-0001  ")
		if (entity.getNomorBon() != null) {
			entity.setNomorBon(entity.getNomorBon().trim());
		}
		if (entity.getBagianPeminta() != null) {
			entity.setBagianPeminta(entity.getBagianPeminta().trim().toUpperCase());
		}

		// 2. Validasi header
		validasiNomorBonWajibDiisi(entity);
		validasiTanggalBonWajibDiisi(entity);
		validasiBagianPemintaWajibDiisi(entity);
		validasiBagianPemintaAdaDiMaster(entity);
		validasiNomorBonBelumPernahAda(entity);

		// Validasi detail wajib ada minimal 1 baris (transaksi permintaan barang = header + detail)
		validasiDetailWajibAda(dto.detail);

		// 3. Pemrosesan -> simpan header dulu
		EHeaderTransaksiBarang savedHeader = eHeaderTransaksiBarangRepository.save(entity);

		// lalu LOOPING simpan tiap baris detail, masih di transaksi yg sama (satu kali panggil endpoint = satu kali commit)
		List<EDetailTransaksiBarang> savedDetails = new ArrayList<>();
		for (DetailTransaksiBarang detailDto : dto.detail) {
			savedDetails.add(prosesSatuBarisDetail(detailDto, savedHeader.getNomorBon()));
		}

		return HeaderTransaksiBarang.fromEntity(savedHeader, savedDetails);
	}

	@Transactional
	public HeaderTransaksiBarang edit(HeaderTransaksiBarang dto) {
		// 1. Inisialisasi
		EHeaderTransaksiBarang entity = dto.toEntity();

		// Mencegah duplikasi data akibat perbedaan spasi di awal/akhir input (misal: "BON-2026-0001" dan "  BON-2026-0001  ")
		if (entity.getNomorBon() != null) {
			entity.setNomorBon(entity.getNomorBon().trim());
		}
		if (entity.getBagianPeminta() != null) {
			entity.setBagianPeminta(entity.getBagianPeminta().trim().toUpperCase());
		}

		// 2. Validasi
		validasiNomorBonWajibDiisiUntukUpdate(entity);
		validasiTanggalBonWajibDiisi(entity);
		validasiBagianPemintaWajibDiisi(entity);
		validasiBagianPemintaAdaDiMaster(entity);
		validasiDetailWajibAda(dto.detail);

		EHeaderTransaksiBarang existing = eHeaderTransaksiBarangRepository.findByNomorBon(entity.getNomorBon());
		validasiNomorBonDitemukan(existing);

		// 3. Pemrosesan header
		existing.setTanggalBon(entity.getTanggalBon());
		existing.setBagianPeminta(entity.getBagianPeminta());
		existing.setKeterangan(entity.getKeterangan());

		EHeaderTransaksiBarang savedHeader = eHeaderTransaksiBarangRepository.save(existing);

		// detail lama dibuang, ganti dgn detail baru dari payload -> tetap SATU KALI panggil utk update transaksi lengkap
		eDetailTransaksiBarangRepository.deleteByNomorBon(savedHeader.getNomorBon());

		List<EDetailTransaksiBarang> savedDetails = new ArrayList<>();
		for (DetailTransaksiBarang detailDto : dto.detail) {
			savedDetails.add(prosesSatuBarisDetail(detailDto, savedHeader.getNomorBon()));
		}

		return HeaderTransaksiBarang.fromEntity(savedHeader, savedDetails);
	}

	@Transactional
	public void delete(String nomorBon) {
		// gada inisialisasi karena yg dikirim cuma string plain bukan pojo tanpa perlu inisialisasi

		// 2. Validasi
		validasiNomorBonWajibDiisi(nomorBon);

		EHeaderTransaksiBarang existing = eHeaderTransaksiBarangRepository.findByNomorBon(nomorBon);
		validasiNomorBonDitemukan(existing);

		// 3. Pemrosesan -> detail-nya ikut dibuang biar ngga jadi data yatim (orphan)
		eDetailTransaksiBarangRepository.deleteByNomorBon(nomorBon);
		eHeaderTransaksiBarangRepository.delete(existing);
	}

	// ==================== PEMROSESAN 1 BARIS DETAIL (dipindah dari DetailTransaksiBarangService, sekarang di-loop di sini)

	private EDetailTransaksiBarang prosesSatuBarisDetail(DetailTransaksiBarang detailDto, String nomorBon) {
		EDetailTransaksiBarang entity = detailDto.toEntity();
		entity.setId(null); // baris detail baru, id biar di-generate DB (auto increment)
		entity.setNomorBon(nomorBon);

		// Inisialisasi (Trim)
		// Mencegah duplikasi data akibat perbedaan spasi di awal/akhir input (misal: "BRG01" dan "  BRG01  ")
		if (entity.getKodeBarang() != null) {
			entity.setKodeBarang(entity.getKodeBarang().trim());
		}
		if (entity.getUnit() != null) {
			entity.setUnit(entity.getUnit().trim());
		}

		// Validasi input wajib
		validasiKodeBarangWajibDiisi(entity);
		validasiJumlahDimintaHarusLebihDariNol(entity);
		validasiUnitWajibDiisi(entity);

		// Validasi data master barang
		EBarang barang = eBarangRepository.findByKodeBarang(entity.getKodeBarang());
		validasiBarangTerdaftar(barang, entity.getKodeBarang());
		validasiBarangAktif(barang);

		// Perhitungan unit stok
		double jumlahUnitStok = hitungJumlahUnitStok(entity, barang);
		entity.setJumlahUnitStok(jumlahUnitStok);
		entity.setUnitStok(barang.getUnitStok());

		return eDetailTransaksiBarangRepository.save(entity);
	}

	// ==================== VALIDASI HEADER

	private void validasiNomorBonWajibDiisi(String nomorBon) {
		if (nomorBon == null || nomorBon.trim().isEmpty()) {
			throw new CustomException("VALIDATION_ERROR", "Nomor bon wajib diisi.");
		}
	}

	private void validasiNomorBonWajibDiisi(EHeaderTransaksiBarang header) {
		if (header.getNomorBon() == null || header.getNomorBon().trim().isEmpty()) {
			throw new CustomException("VALIDATION_ERROR", "Nomor bon wajib diisi.");
		}
	}

	private void validasiTanggalBonWajibDiisi(EHeaderTransaksiBarang header) {
		if (header.getTanggalBon() == null) {
			throw new CustomException("VALIDATION_ERROR", "Tanggal bon wajib diisi.");
		}
	}

	private void validasiBagianPemintaWajibDiisi(EHeaderTransaksiBarang header) {
		if (header.getBagianPeminta() == null || header.getBagianPeminta().trim().isEmpty()) {
			throw new CustomException("VALIDATION_ERROR", "Bagian peminta wajib diisi.");
		}
	}

	private void validasiBagianPemintaAdaDiMaster(EHeaderTransaksiBarang header) {
		if (eBagianRepository.findByKode(header.getBagianPeminta()) == null) {
			throw new CustomException("VALIDATION_ERROR",
					"Kode Bagian '" + header.getBagianPeminta() + "' tidak terdaftar di master bagian.");
		}
	}

	private void validasiNomorBonBelumPernahAda(EHeaderTransaksiBarang header) {
		EHeaderTransaksiBarang existing = eHeaderTransaksiBarangRepository.findByNomorBon(header.getNomorBon());
		if (existing != null) {
			throw new CustomException("VALIDATION_ERROR", "Nomor bon sudah digunakan.");
		}
	}

	private void validasiNomorBonWajibDiisiUntukUpdate(EHeaderTransaksiBarang header) {
		if (header.getNomorBon() == null || header.getNomorBon().trim().isEmpty()) {
			throw new CustomException("VALIDATION_ERROR", "Nomor Bon harus diisi untuk update");
		}
	}

	private void validasiNomorBonDitemukan(EHeaderTransaksiBarang existing) {
		if (existing == null) {
			throw new CustomException("VALIDATION_ERROR", "Nomor bon tidak ditemukan.");
		}
	}

	private void validasiDetailWajibAda(List<DetailTransaksiBarang> detail) {
		if (detail == null || detail.isEmpty()) {
			throw new CustomException("VALIDATION_ERROR", "Detail transaksi (barang yang diminta) wajib diisi minimal 1 baris.");
		}
	}

	// ==================== VALIDASI & PERHITUNGAN DETAIL (dipindah dari DetailTransaksiBarangService)

	private void validasiKodeBarangWajibDiisi(EDetailTransaksiBarang detail) {
		if (detail.getKodeBarang() == null || detail.getKodeBarang().trim().isEmpty()) {
			throw new CustomException("VALIDATION_ERROR", "Kode barang wajib diisi.");
		}
	}

	private void validasiJumlahDimintaHarusLebihDariNol(EDetailTransaksiBarang detail) {
		if (detail.getJumlahDiminta() == null || detail.getJumlahDiminta() <= 0) {
			throw new CustomException("VALIDATION_ERROR", "Jumlah barang harus lebih dari 0.");
		}
	}

	private void validasiUnitWajibDiisi(EDetailTransaksiBarang detail) {
		if (detail.getUnit() == null || detail.getUnit().trim().isEmpty()) {
			throw new CustomException("VALIDATION_ERROR", "Unit wajib diisi.");
		}
	}

	private void validasiBarangTerdaftar(EBarang barang, String kodeBarang) {
		if (barang == null) {
			throw new CustomException("VALIDATION_ERROR", "Barang dengan kode '" + kodeBarang + "' tidak ditemukan.");
		}
	}

	private void validasiBarangAktif(EBarang barang) {
		if (barang.getAktif() == null || !barang.getAktif().equalsIgnoreCase("Y")) {
			throw new CustomException("VALIDATION_ERROR", "Barang " + barang.getKodeBarang() + " tidak aktif.");
		}
	}

	private double hitungJumlahUnitStok(EDetailTransaksiBarang detail, EBarang barang) {
		String inputUnit = detail.getUnit().trim();
		double jumlah = detail.getJumlahDiminta();

		if (inputUnit.equalsIgnoreCase(barang.getUnit1())) {
			return jumlah * barang.getKonversiUnit1KeUnit2() * barang.getKonversiUnit2KeUnitStok();
		} else if (inputUnit.equalsIgnoreCase(barang.getUnit2())) {
			return jumlah * barang.getKonversiUnit2KeUnitStok();
		} else if (inputUnit.equalsIgnoreCase(barang.getUnitStok())) {
			return jumlah;
		}

		throw new CustomException(
				"VALIDATION_ERROR",
				"Unit '" + inputUnit
						+ "' tidak valid untuk barang " + barang.getKodeBarang()
						+ ". Unit yang diperbolehkan: " + barang.getUnit1()
						+ ", " + barang.getUnit2()
						+ ", " + barang.getUnitStok()
		);
	}
}