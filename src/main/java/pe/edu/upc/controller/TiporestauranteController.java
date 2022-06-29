package pe.edu.upc.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Tiporestaurante;
import pe.edu.upc.service.ITiporestauranteService;

@Controller
@RequestMapping("/tiporestaurantes")
public class TiporestauranteController {

	@Autowired
	private ITiporestauranteService trService;

	@GetMapping("/new")
	public String newTiporestaurante(Model model) {
		model.addAttribute("tiporestaurante", new Tiporestaurante());
		return "tiporestaurante/tiporestaurante";
	}

	@PostMapping("/save")
	public String saveTiporestaurante(@Valid Tiporestaurante tiporestaurante, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "tiporestaurante/tiporestaurante";
		} else {
			int rpta = trService.insert(tiporestaurante);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/tiporestaurante/tiporestaurante";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listTiporestaurantes", trService.list());

		return "/tiporestaurante/tiporestaurante";
	}

	@GetMapping("/list")
	public String listTiporestaurantes(Model model) {
		try {
			model.addAttribute("tiporestaurante", new Tiporestaurante());
			model.addAttribute("listTiporestaurantes", trService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/tiporestaurante/listTiporestaurantes";
	}

	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				trService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar el tiporestaurante porque se esta usando en otra lista");
		}
		model.put("listTiporestaurantes", trService.list());

//		return "redirect:/categories/list";
		return "/tiporestaurante/listTiporestaurantes";
	}

	@GetMapping("/detalle/{id}")
	public String detailsTiporestaurante(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Tiporestaurante> tiporestaurante = trService.listarId(id);
			if (!tiporestaurante.isPresent()) {
				model.addAttribute("info", "Tiporestaurante no existe");
				return "redirect:/tiporestaurantes/list";
			} else {
				model.addAttribute("tiporestaurante", tiporestaurante.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/tiporestaurante/update";
	}


}
