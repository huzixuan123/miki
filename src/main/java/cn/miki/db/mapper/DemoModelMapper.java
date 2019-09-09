package cn.miki.db.mapper;

import cn.miki.db.pojo.DemoModel;

/**
* DemoModel mapper
*/
public interface DemoModelMapper {

    DemoModel selectById(Long id);

}