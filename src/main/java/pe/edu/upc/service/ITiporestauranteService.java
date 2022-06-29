package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Tiporestaurante;

public interface ITiporestauranteService {
	public Integer insert(Tiporestaurante tiporestaurante);

	public void delete(long idTiporestaurante);

	List<Tiporestaurante> list();

	Optional<Tiporestaurante> listarId(long idTiporestaurante);

	

}
