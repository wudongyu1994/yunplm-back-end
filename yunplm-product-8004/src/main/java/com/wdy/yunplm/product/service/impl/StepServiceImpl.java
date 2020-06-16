package com.wdy.yunplm.product.service.impl;

import com.wdy.yunplm.product.dao.IStepDao;
import com.wdy.yunplm.po.Step;
import com.wdy.yunplm.product.service.IStepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class StepServiceImpl implements IStepService {
	@Resource
	private IStepDao iStepDao;

	@Override
	public List<Step> getAllStep() {
		return iStepDao.findAll();
	}

	@Override
	public Step addStep(Step step) {
		return iStepDao.saveAndFlush(step);
	}

	@Override
	public Step modifyStepById(Integer id, Step step) {
		if (iStepDao.findById(id).isPresent()) {
			Step oldStep = iStepDao.findById(id).get();
			Integer oldId = oldStep.getId();
			BeanUtils.copyProperties(step, oldStep);
			oldStep.setId(oldId);
			return iStepDao.saveAndFlush(oldStep);
		} else return null;
	}

	@Override
	public Step deleteStepById(Integer id) {
		if (iStepDao.findById(id).isPresent()) {
			Step step = iStepDao.findById(id).get();
			iStepDao.deleteById(id);
			iStepDao.flush();
			return step;
		} else return null;
	}
}
