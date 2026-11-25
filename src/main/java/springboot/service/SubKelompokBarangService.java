package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.entity.ESubKelompokBarang;
import springboot.exceptions.CustomException;
import springboot.dto.SubKelompokBarang;
import springboot.repositories.ESubKelompokBarangRepository;

@Service("subKelompokBarangService")
public class SubKelompokBarangService {
	
	@Autowired
	private ESubKelompokBarangRepository eSubKelompokBarangRepository;	
	
	public SubKelompokBarang findByBk(String kodeKelompokBarang, String kodeSubKelompokBarang) {
		return SubKelompokBarang.fromEntity(eSubKelompokBarangRepository.findByBK(kodeKelompokBarang, kodeSubKelompokBarang));
	}

	@Transactional
	public ESubKelompokBarang add(ESubKelompokBarang entity) {
		// persiapan/perhitungan (bila ada)
		
		// validasi-validasi (bila ada)
		ESubKelompokBarang eSubKelompokBarang = eSubKelompokBarangRepository.findByBK(
				entity.getKodeKelompokBarang(), entity.getKodeSubKelompokBarang());
		if (eSubKelompokBarang != null) {
			throw new CustomException("error", "Kode kelompok barang dan sub kelompok barang sudah ada");
		}
		
		// update saldo (bila ada)
		return eSubKelompokBarangRepository.save(entity);
	}
	
	@Transactional
	public ESubKelompokBarang edit(ESubKelompokBarang entity) {
		// persiapan/perhitungan (bila ada)
		// validasi-validasi (bila ada)
		// update saldo (bila ada)
		return eSubKelompokBarangRepository.save(entity);
	}

	@Transactional
	public void delete (String kodeKelompokBarang, String kodeSubKelompokBarang, Long version) {
		// persiapan/perhitungan (bila ada)
		// validasi-validasi (bila ada)
		// update saldo (bila ada)
		ESubKelompokBarang eSubKelompokBarang = eSubKelompokBarangRepository.findByBK(kodeKelompokBarang, kodeSubKelompokBarang);
		eSubKelompokBarangRepository.delete(eSubKelompokBarang);
	}
}
