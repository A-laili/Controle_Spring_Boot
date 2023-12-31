package ma.laili.projet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ma.laili.projet.entities.Categorie;
import ma.laili.projet.services.CategorieService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategorieController {

	@Autowired
	private CategorieService service;

	@GetMapping
	public List<Categorie> categorieList() {
		return service.findAll();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCategorie(@PathVariable Long id, @RequestBody Categorie newCategorie) {

		Categorie oldCategorie = service.findById(id);
		if (oldCategorie == null) {
			return new ResponseEntity<Object>("L'categorie avec id" + id + "n'existe pas ", HttpStatus.BAD_REQUEST);

		} else {
			newCategorie.setId(id);
			return ResponseEntity.ok(service.update(newCategorie));
		}
	}

	@PostMapping
	public Categorie creatProfessseur(@RequestBody Categorie categorie) {
		categorie.setId(0L);
		return service.create(categorie);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Categorie categorie = service.findById(id);
		if (categorie == null) {
			return new ResponseEntity<Object>("La categorie avec Id " + id + " n'existe pas", HttpStatus.BAD_REQUEST);

		} else {
			return ResponseEntity.ok(categorie);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletProfessseur(@PathVariable Long id) {
		Categorie categorie = service.findById(id);
		if (categorie == null) {
			return new ResponseEntity<Object>("L'categorie avec Id " + id + " nexiste pas", HttpStatus.BAD_REQUEST);
		} else {
			service.delete(categorie);
			return ResponseEntity.ok("La categorie avec id " + id + "a été suprimée");
		}
	}
	
	@GetMapping("/categories")
	public ResponseEntity<Object> findSousCategories (@PathVariable Categorie categorie) {
		if (categorie == null) {
			return new ResponseEntity<Object>("La categorie  n'existe pas", HttpStatus.BAD_REQUEST);

		} else {
			return ResponseEntity.ok(categorie);
		}
	}

}
