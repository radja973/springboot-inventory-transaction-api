package springboot.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import springboot.entity.EDetailTransaksiBarang;

@Repository
public class EDetailTransaksiBarangRepository extends SimpleJpaRepository<EDetailTransaksiBarang, Integer> {

	protected final EntityManager em;

	public EDetailTransaksiBarangRepository(EntityManager em) {
		super(EDetailTransaksiBarang.class, em);
		this.em = em;
	}

	public EDetailTransaksiBarang findById(Integer id) {
		String query = "SELECT e FROM EDetailTransaksiBarang e WHERE e.id = :id";
		try {
			return em.createQuery(query, EDetailTransaksiBarang.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	// dipakai supaya detail-detail transaksi satu nomorBon bisa diambil/dihapus SEKALIGUS (bukan satu-satu)
	public List<EDetailTransaksiBarang> findByNomorBon(String nomorBon) {
		String query = "SELECT e FROM EDetailTransaksiBarang e WHERE e.nomorBon = :nomorBon";
		return em.createQuery(query, EDetailTransaksiBarang.class)
				.setParameter("nomorBon", nomorBon)
				.getResultList();
	}

	public void deleteByNomorBon(String nomorBon) {
		String query = "DELETE FROM EDetailTransaksiBarang e WHERE e.nomorBon = :nomorBon";
		em.createQuery(query)
				.setParameter("nomorBon", nomorBon)
				.executeUpdate();
	}
}