package com.wdy.yunplm.material.service;


import com.wdy.yunplm.po.Material;

import java.util.List;

public interface IMaterialService {
	List<Material> getMaterialByProductItemId(Integer productItemId);
	Material addMaterial(Material material);
	Material modifyMaterialById(Integer id, Material material);
	Material deleteMaterialById(Integer id);
}
