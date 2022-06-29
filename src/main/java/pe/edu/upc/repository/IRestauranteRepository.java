package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Restaurante;

@Repository
public interface IRestauranteRepository extends JpaRepository<Restaurante, Long> {
	@Query("select count(rt.name) from Restaurante rt where rt.name =:name")
	public int buscarNombreRestaurante(@Param("name") String nombreRestaurante);

	@Query("select rt from Restaurante rt where rt.name like %?1%")
	List<Restaurante> fetchRestauranteByName(String nombreRestaurante);

	@Query("select rt from Restaurante rt where rt.departamento.name like %?1%")
	public List<Restaurante> findRestauranteByNameDepartamento(String nameDepartamento);

	public List<Restaurante> findByNameLikeIgnoreCase(String nameRestaurante);
	
	@Query(value="select r.name,r.aforototal from restaurantes r order by r.aforototal desc limit 3",nativeQuery=true)
	List<String[]> topRestaurante();
	
	@Query(value="select r.name, r.protocolo , cla.numeroclasificacion from restaurantes r join clasificaciones cla on r.id=cla.id_restaurante where cla.numeroclasificacion like '★★★★★' group by r.name, r.protocolo ,cla.numeroclasificacion",nativeQuery=true)
	List<String[]> topCalificacion();
	
	@Query(value="select r.name,r.estado, (cast(r.aforototal as int)-cast(r.aforoactual as int)) from restaurantes r where r.estado like 'Abierto'",nativeQuery=true)
	List<String[]> RestauranteDisponible();

}
