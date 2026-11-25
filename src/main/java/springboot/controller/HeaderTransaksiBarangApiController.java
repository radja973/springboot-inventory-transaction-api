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

import springboot.dto.HeaderTransaksiBarang;
import springboot.service.HeaderTransaksiBarangService;
import springboot.util.ApiResponse;

@RestController
@RequestMapping("/api/headertransaksi")
public class HeaderTransaksiBarangApiController {

	@Autowired
	private HeaderTransaksiBarangService headerTransaksiBarangService;

	@GetMapping
	public ApiResponse get(@RequestParam String nomorBon) throws JsonProcessingException {
		HeaderTransaksiBarang header = headerTransaksiBarangService.findByNomorBon(nomorBon);
		return ApiResponse.data("item", header);
	}

	@PostMapping
	public ApiResponse add(@RequestBody HeaderTransaksiBarang dto) {
		HeaderTransaksiBarang added = headerTransaksiBarangService.add(dto);
		return ApiResponse.data("item", added);
	}

	@PutMapping
	public ApiResponse edit(@RequestBody HeaderTransaksiBarang dto) {
		HeaderTransaksiBarang edited = headerTransaksiBarangService.edit(dto);
		return ApiResponse.data("item", edited);
	}

	@DeleteMapping
	public ApiResponse delete(@RequestParam String nomorBon) {
		headerTransaksiBarangService.delete(nomorBon);
		return ApiResponse.ok();
	}
}
