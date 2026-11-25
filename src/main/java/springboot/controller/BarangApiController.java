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

import springboot.dto.Barang;
import springboot.service.BarangService;
import springboot.util.ApiResponse;

@RestController
@RequestMapping("/api/barang")
public class BarangApiController {

    @Autowired
    private BarangService barangService;

    @GetMapping
    public ApiResponse get(@RequestParam String kodeBarang) throws JsonProcessingException {
        Barang barang = barangService.findByKodeBarang(kodeBarang);
        return ApiResponse.data("item", barang);
    }

    @PostMapping
    public ApiResponse add(@RequestBody Barang pojo) {
        Barang added = barangService.add(pojo);
        return ApiResponse.data("item", added);
    }

    @PutMapping
    public ApiResponse edit(@RequestBody Barang pojo) {
        Barang edited = barangService.edit(pojo);
        return ApiResponse.data("item", edited);
    }

    @DeleteMapping
    public ApiResponse delete(@RequestParam String kodeBarang) {
        barangService.delete(kodeBarang);
        return ApiResponse.ok();
    }
}
