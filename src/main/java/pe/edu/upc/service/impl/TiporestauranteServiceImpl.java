package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Tiporestaurante;
import pe.edu.upc.repository.ITiporestauranteRepository;
import pe.edu.upc.service.ITiporestauranteService;

@Service
public class TiporestauranteServiceImpl implements ITiporestauranteService {
	@Autowired
	private ITiporestauranteRepository trR;

	@Override
	@Transactional
	public Integer insert(Tiporestaurante tiporestaurante) {
		int rpta = trR.buscarNombreTiporestaurante(tiporestaurante.getName());
		if (rpta == 0) {
			trR.save(tiporestaurante);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void delete(long idTiporestaurante) {
		trR.deleteById(idTiporestaurante);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tiporestaurante> list() {
		// TODO Auto-generated method stub
		return trR.findAll(Sort.by(Sort.Direction.DESC, "name"));
	}

	@Override
	public Optional<Tiporestaurante> listarId(long idTiporestaurante) {
		// TODO Auto-generated method stub
		return trR.findById(idTiporestaurante);
	}

	


}