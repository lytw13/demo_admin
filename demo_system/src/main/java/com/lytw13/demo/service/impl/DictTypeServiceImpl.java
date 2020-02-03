package com.lytw13.demo.service.impl;

import com.lytw13.demo.mapper.sys.DictTypeMapper;
import com.lytw13.demo.model.BaseResult;
import com.lytw13.demo.model.TbDictType;
import com.lytw13.demo.service.DictTypeService;
import com.lytw13.demo.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class DictTypeServiceImpl implements DictTypeService {
    @Autowired
    DictTypeMapper dictTypeMapper;
    @Override
    public BaseResult list(TbDictType tbDictType) {
        List<TbDictType> dictTypeList = dictTypeMapper.list(tbDictType);
        if(dictTypeList.size() == 0) {
            return new ResponseResult().setResultFail("没有查到相应结果");
        }
        return new ResponseResult().setResultSuccess(dictTypeList);
    }

    @Override
    public BaseResult insert(TbDictType tbDictType) {
        int result = dictTypeMapper.insert(tbDictType);
        if(result<=0) {
            return new ResponseResult().setResultFail("字典插入失败");
        };
        return new ResponseResult().setResultSuccess(tbDictType);
    }

    @Override
    public BaseResult delete(@PathVariable("dictTypeId") Integer dictTypeId) {
        Integer result = dictTypeMapper.deleteByPrimaryKey(dictTypeId);
        if(result == 0) {
            return new ResponseResult().setResultFail("删除失败");
        }
        return new ResponseResult().setResultSuccess("删除成功");
    }

    @Override
    public BaseResult update(TbDictType tbDictType) {
        Integer result =  dictTypeMapper.updateByPrimaryKeySelective(tbDictType);
        if(result == 0) {
            return new ResponseResult().setResultFail("更新失败");
        }
        return new ResponseResult().setResultSuccess("更新成功");
    }
}
