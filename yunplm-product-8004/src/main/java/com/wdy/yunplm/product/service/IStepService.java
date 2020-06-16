package com.wdy.yunplm.product.service;


import com.wdy.yunplm.po.Step;

import java.util.List;

public interface IStepService {
	List<Step> getAllStep();
	Step addStep(Step step);
	Step modifyStepById(Integer id, Step step);
	Step deleteStepById(Integer id);
}
