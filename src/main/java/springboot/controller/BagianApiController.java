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

import springboot.dto.Bagian;
import springboot.service.BagianService;
import springboot.util.ApiResponse;

@RestController
@RequestMapping("/api/bagian")
public class BagianApiController {

	@Autowired
	private BagianService bagianService;
	
	@GetMapping
	public ApiResponse get(@RequestParam String kode) throws JsonProcessingException {
		Bagian bagian = bagianService.findByKode(kode);
		return ApiResponse.data("item", bagian);
	}

	@PostMapping
	public ApiResponse add(@RequestBody Bagian pojo) {
		Bagian added = bagianService.add(pojo);
		return ApiResponse.data("item", added);
	}

	@PutMapping
	public ApiResponse edit(@RequestBody Bagian pojo) {
		Bagian edited = bagianService.edit(pojo);
		return ApiResponse.data("item", edited);
	}

	@DeleteMapping
	public ApiResponse delete(@RequestParam String kode) {
		bagianService.delete(kode);
		return ApiResponse.ok();
	}
}
