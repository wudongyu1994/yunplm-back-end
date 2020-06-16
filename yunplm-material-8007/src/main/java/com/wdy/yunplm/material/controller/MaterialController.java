package com.wdy.yunplm.material.controller;

import com.wdy.yunplm.base.BaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.material.service.IMaterialService;
import com.wdy.yunplm.po.Material;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
//@PreAuthorize("hasAuthority('/jkjkj')")
public class MaterialController extends BaseApiController {
	private final IMaterialService iMaterialService;

	@Autowired
	public MaterialController(IMaterialService iMaterialService) {
		this.iMaterialService = iMaterialService;
	}

	@GetMapping("/productItem/{productItemId}/material")
	public Result<List<Material>> getMaterialByProductItemId(@PathVariable Integer productItemId) {
		List<Material> materialList = iMaterialService.getMaterialByProductItemId(productItemId);
		return Result.success(materialList,materialList.size());
	}

	@PostMapping("/material")
	public Result<Material> addMaterial(@RequestBody @Valid Material material,
	                                          BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			showBinding(bindingResult);
			return Result.failure("bindingResult.hasErrors");
		}
		log.info(material.toString());
		Material materialRet = iMaterialService.addMaterial(material);
		if(materialRet==null){
			return Result.failure(ResultCode.NOT_EXIST);
		}else{
			return Result.success(materialRet);
		}
	}

	@PutMapping("/material/{id}")
	public Result<Material> modifyMaterialById(@PathVariable Integer id,
	                                                 @RequestBody Material material) {
		Material materialRet = iMaterialService.modifyMaterialById(id, material);
		if (materialRet == null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(materialRet);
	}

	@DeleteMapping("/material/{id}")
	public Result<Material> deleteMaterialById(@PathVariable Integer id) {
		Material material = iMaterialService.deleteMaterialById(id);
		if (material == null) return Result.failure(ResultCode.NOT_EXIST);
		return Result.success(material);
	}

}
