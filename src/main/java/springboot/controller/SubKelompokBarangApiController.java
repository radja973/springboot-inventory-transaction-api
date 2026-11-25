package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import springboot.entity.ESubKelompokBarang;
import springboot.dto.SubKelompokBarang;
import springboot.service.SubKelompokBarangService;
import springboot.util.ApiResponse;

@RestController
@RequestMapping("/api/subkelompokbarang")
public class SubKelompokBarangApiController {

	@Autowired
	private SubKelompokBarangService subKelompokBarangService;
	

	// -------------------Retrieve Single Data ------------------------------------------
	@GetMapping
	public ApiResponse get(@RequestParam String kodeKelompokBarang, @RequestParam String kodeSubKelompokBarang) throws JsonProcessingException {

		SubKelompokBarang subKelompokBarang = subKelompokBarangService.findByBk(kodeKelompokBarang, kodeSubKelompokBarang);
		
		return ApiResponse.data("item", subKelompokBarang);
		
	}

	// -------------------Create a User-------------------------------------------
	@PostMapping
	public ApiResponse add(@RequestBody SubKelompokBarang pojo) {
		ESubKelompokBarang added = subKelompokBarangService.add(pojo.toEntity());

		return ApiResponse.data("item", SubKelompokBarang.fromEntity(added));
	}

	@PutMapping
	public ApiResponse edit(@RequestBody SubKelompokBarang pojo) {
		ESubKelompokBarang edited = subKelompokBarangService.edit(pojo.toEntity());
		
		return ApiResponse.data("item", SubKelompokBarang.fromEntity(edited));
	}

	@DeleteMapping
	public ApiResponse delete(@RequestParam String kodeKelompokBarang, @RequestParam String kodeSubKelompokBarang, @RequestParam(required = false) Long version) {
		subKelompokBarangService.delete(kodeKelompokBarang, kodeSubKelompokBarang, version);
		return ApiResponse.ok();
	}

}