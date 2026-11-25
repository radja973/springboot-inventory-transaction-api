package springboot.repositories;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import springboot.entity.EBagian;

@Repository
public class EBagianRepository extends SimpleJpaRepository<EBagian, String> {

	protected final EntityManager em;
	
	public EBagianRepository(EntityManager em) {
		super(EBagian.class, em);
		this.em = em;
	}

	public EBagian findByKode(String kode) {
		String query = "SELECT e FROM EBagian e WHERE e.kode = :kode";
		try {
			return em.createQuery(query, EBagian.class)
					.setParameter("kode", kode)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
