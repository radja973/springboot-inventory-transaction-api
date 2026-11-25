package springboot.repositories;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import springboot.entity.EKelompokBarang;

@Repository
public class EKelompokBarangRepository extends SimpleJpaRepository<EKelompokBarang, String> {

	protected final EntityManager em;
	
	public EKelompokBarangRepository(EntityManager em) {
		super(EKelompokBarang.class, em);
		this.em = em;
	}

}
