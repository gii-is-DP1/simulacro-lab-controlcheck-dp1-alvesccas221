package org.springframework.samples.petclinic.product;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.OwnerService;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")

public class ProductController {
	private static final String VIEWS_PRODUCT_CREATE_OR_UPDATE_FORM = "products/createOrUpdateProductForm";

	private final ProductService productService;

	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	@GetMapping(value="/create")
	public String initCreationForm(Map<String,Object> model) {
		
		Product product = new Product();
		model.put("product", product);
		model.put("productType", this.productService.getAllProductsTypes());
		return VIEWS_PRODUCT_CREATE_OR_UPDATE_FORM;
		
	}
	
	@PostMapping(value="/create")
	public String processCreationForm(@Valid Product product, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("product", product);
			model.put("productType", this.productService.getAllProductsTypes());
			return VIEWS_PRODUCT_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.productService.save(product);
			return "welcome";
		}

		
	}
	
}
