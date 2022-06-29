package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Tiporestaurante;

@Repository
public interface ITiporestauranteRepository extends JpaRepository<Tiporestaurante, Long> {
	@Query("select count(t.name) from Tiporestaurante t where t.name =:name")
	public int buscarNombreTiporestaurante(@Param("name") String nombreTiporestaurante);

	
	
}
