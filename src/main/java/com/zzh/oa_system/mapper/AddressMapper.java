package com.zzh.oa_system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:57
 * description:
 */
@Mapper
public interface AddressMapper {

    /*根据用户来找外部通讯录联系人*/
    List<Map<String, Object>> allDirector(@Param("userId") Long userId, @Param("pinyin") String pinyin, @Param("outtype") String outtype, @Param("baseKey") String baseKey);
}

