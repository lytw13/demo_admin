package com.lytw13.demo.service;

import com.lytw13.demo.model.BaseResult;
import com.lytw13.demo.model.TbDictData;
import com.lytw13.demo.model.TbDictType;

import java.util.List;

public interface DictDataService {

    List<TbDictData> getType(String type);

    BaseResult list(TbDictData tbDictData);

    BaseResult insert(TbDictData tbDictData);

    BaseResult delete(Integer dictDataId);

    BaseResult update(TbDictData tbDictData);
}
