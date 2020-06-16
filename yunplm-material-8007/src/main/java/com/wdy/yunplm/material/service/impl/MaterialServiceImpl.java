package com.wdy.yunplm.material.service.impl;

import com.wdy.yunplm.material.dao.IMaterialDao;
import com.wdy.yunplm.material.service.IMaterialService;
import com.wdy.yunplm.po.Material;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class MaterialServiceImpl implements IMaterialService {
	@Resource
	private IMaterialDao iMaterialDao;

	@Override
	public List<Material> getMaterialByProductItemId(Integer productItemId) {
		return iMaterialDao.findByProductItemId(productItemId);
	}

	@Override
	public Material addMaterial(Material material) {
		return iMaterialDao.saveAndFlush(material);
	}

	@Override
	public Material modifyMaterialById(Integer id, Material material) {
		if (iMaterialDao.findById(id).isPresent()) {
			Material oldMaterial = iMaterialDao.findById(id).get();
			Integer oldId = oldMaterial.getId();
			BeanUtils.copyProperties(material, oldMaterial);
			oldMaterial.setId(oldId);
			return iMaterialDao.saveAndFlush(oldMaterial);
		} else return null;
	}

	@Override
	public Material deleteMaterialById(Integer id) {
		if (iMaterialDao.findById(id).isPresent()) {
			Material material = iMaterialDao.findById(id).get();
			iMaterialDao.deleteById(id);
			iMaterialDao.flush();
			return material;
		} else return null;
	}
}
