package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Restaurante;

public interface IRestauranteService {
	public Integer insert(Restaurante restaurante);

	public void delete(long idRestaurante);

	List<Restaurante> list();

	Optional<Restaurante> findById(Long idRestaurante);
	
	Optional<Restaurante> listarId(long idRestaurante);

	List<Restaurante> fetchRestauranteByName(String nameRestaurante);

	public List<Restaurante> fetchRestauranteByDepartamentoName(String nameDepartamento);

	public List<Restaurante> findByNameRestauranteLikeIgnoreCase(String nameRestaurante);
	
	List<String[]> topRestaurante();
	
	List<String[]> topCalificacion();
	
	List<String[]> RestauranteDisponible();

}
