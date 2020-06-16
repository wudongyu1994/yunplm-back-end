package com.wdy.yunplm.feign;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.po.Material;
import com.wdy.yunplm.feign.impl.MyFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(value = "material-server-8007", fallback = MyFallback.class)
public interface IMaterialService {
	@GetMapping("/api/v1/material")
	Result<List<Material>> getAllMaterial_Feign();

	@GetMapping("/api/v1/productItem/{productItemId}/material")
	Result<List<Material>> getMaterialByProductItemId_Feign(@PathVariable Integer productItemId);

	@PostMapping("/api/v1/material")
	Result<Material> addMaterial_Feign(@RequestBody Material material);

	@PutMapping("/api/v1/material/{id}")
	Result<Material> modifyMaterialById_Feign(@PathVariable Integer id, @RequestBody Material material);

	@DeleteMapping("/api/v1/material/{id}")
	Result<Material> deleteMaterialById_Feign(@PathVariable Integer id);
}
