package com.lytw13.demo.service.impl;

import com.lytw13.demo.mapper.sys.DictDataMapper;
import com.lytw13.demo.model.BaseResult;
import com.lytw13.demo.model.TbDictData;
import com.lytw13.demo.service.DictDataService;
import com.lytw13.demo.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictDataServiceImpl implements DictDataService {
    @Autowired
    DictDataMapper dictDataMapper;
    @Override
    public List<TbDictData> getType(String type) {
        TbDictData tbDictData = new TbDictData();
        tbDictData.setDictDataType(type);
        List<TbDictData> tbDictDataList = dictDataMapper.get(tbDictData);
        return tbDictDataList;
    }

    @Override
    public BaseResult list(TbDictData tbDictData) {
        List<TbDictData> dictDataList = dictDataMapper.get(tbDictData);
        if(dictDataList.size() == 0) {
            return new ResponseResult().setResultFail("没有查到相应结果");
        }
        return new ResponseResult().setResultSuccess(dictDataList);
    }

    @Override
    public BaseResult insert(TbDictData tbDictData) {
        int result = dictDataMapper.insert(tbDictData);
        if(result<=0) {
            return new ResponseResult().setResultFail("字典插入失败");
        };
        return new ResponseResult().setResultSuccess(tbDictData);
    }

    @Override
    public BaseResult delete(Integer dictDataId) {
        Integer result = dictDataMapper.deleteByPrimaryKey(dictDataId);
        if(result == 0) {
            return new ResponseResult().setResultFail("删除失败");
        }
        return new ResponseResult().setResultSuccess("删除成功");
    }

    @Override
    public BaseResult update(TbDictData tbDictData) {
        Integer result =  dictDataMapper.updateByPrimaryKeySelective(tbDictData);
        if(result == 0) {
            return new ResponseResult().setResultFail("更新失败");
        }
        return new ResponseResult().setResultSuccess("更新成功");
    }
}
