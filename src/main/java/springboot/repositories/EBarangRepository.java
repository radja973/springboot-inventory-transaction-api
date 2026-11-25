package springboot.repositories;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import springboot.entity.EBarang;

@Repository
public class EBarangRepository extends SimpleJpaRepository<EBarang, String> {

	protected final EntityManager em;

	public EBarangRepository(EntityManager em) {
		super(EBarang.class, em);
		this.em = em;
	}

	public EBarang findByKodeBarang(String kodeBarang) {
		String query = "SELECT e FROM EBarang e WHERE e.kodeBarang = :kodeBarang";
		try {
			return em.createQuery(query, EBarang.class)
					.setParameter("kodeBarang", kodeBarang)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
