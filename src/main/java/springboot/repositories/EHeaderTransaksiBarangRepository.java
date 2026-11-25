package springboot.repositories;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import springboot.entity.EHeaderTransaksiBarang;

@Repository
public class EHeaderTransaksiBarangRepository extends SimpleJpaRepository<EHeaderTransaksiBarang, String> {

	protected final EntityManager em;

	public EHeaderTransaksiBarangRepository(EntityManager em) {
		super(EHeaderTransaksiBarang.class, em);
		this.em = em;
	}

	public EHeaderTransaksiBarang findByNomorBon(String nomorBon) {
		String query = "SELECT e FROM EHeaderTransaksiBarang e WHERE e.nomorBon = :nomorBon";
		try {
			return em.createQuery(query, EHeaderTransaksiBarang.class)
					.setParameter("nomorBon", nomorBon)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
