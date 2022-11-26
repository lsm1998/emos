package com.lsm1998.api.service.impl;

import com.lsm1998.api.mongo.model.Temp;
import com.lsm1998.api.mongo.repository.TempRepository;
import com.lsm1998.api.service.TempService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TempServiceImpl implements TempService
{
    @Resource
    private TempRepository tempRepository;

    @Override
    public String save(Temp temp)
    {
        Temp save = tempRepository.save(temp);
        return save.getId();
    }
}
