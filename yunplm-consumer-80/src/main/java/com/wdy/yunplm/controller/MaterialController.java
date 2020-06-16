package com.wdy.yunplm.controller;

import com.wdy.yunplm.base.ConsumerBaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.po.Material;
import com.wdy.yunplm.feign.IMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class MaterialController extends ConsumerBaseApiController {
	@Resource
	private IMaterialService iMaterialService;

	@GetMapping("/material")
	@PreAuthorize("hasAuthority('/material/get')")
	public Result<List<Material>> getAllMaterial() {
		return iMaterialService.getAllMaterial_Feign();
	}

	@GetMapping("/productItem/{productItemId}/material")
	@PreAuthorize("hasAuthority('/material/get')")
	public Result<List<Material>> getMaterialByProductItemId(@PathVariable Integer productItemId) {
		return iMaterialService.getMaterialByProductItemId_Feign(productItemId);
	}

	@PostMapping("/material")
	@PreAuthorize("hasAuthority('/material/add')")
	public Result<Material> addMaterial(@RequestBody @Valid Material material,
	                                    BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return Result.failure(ResultCode.BINDING_ERROR);
		return iMaterialService.addMaterial_Feign(material);
	}

	@PutMapping("/material/{id}")
	@PreAuthorize("hasAuthority('/material/modify')")
	public Result<Material> modifyMaterialById(@PathVariable Integer id, @RequestBody Material material) {
		return iMaterialService.modifyMaterialById_Feign(id,material);
	}

	@DeleteMapping("/material/{id}")
	@PreAuthorize("hasAuthority('/material/delete')")
	public Result<Material> deleteMaterialById(@PathVariable Integer id) {
		return iMaterialService.deleteMaterialById_Feign(id);
	}
}
