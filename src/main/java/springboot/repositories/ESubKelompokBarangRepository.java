package springboot.repositories;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import springboot.entity.ESubKelompokBarang;

@Repository
public class ESubKelompokBarangRepository extends SimpleJpaRepository<ESubKelompokBarang, String> {

	protected final EntityManager em;
	
	public ESubKelompokBarangRepository(EntityManager em) {
		super(ESubKelompokBarang.class, em);
		this.em = em;
	}

	public ESubKelompokBarang findByBK(String kodeKelompokBarang, String kodeSubKelompokBarang) {
		String query = "SELECT e FROM ESubKelompokBarang e " + 
	                   "WHERE e.kodeKelompokBarang = :kodeKelompokBarang and e.kodeSubKelompokBarang = :kodeSubKelompokBarang";
		try {
			return em.createQuery(query, ESubKelompokBarang.class)
					.setParameter("kodeKelompokBarang", kodeKelompokBarang)
					.setParameter("kodeSubKelompokBarang", kodeSubKelompokBarang)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
