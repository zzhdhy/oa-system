package com.zzh.oa_system.common.formValid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 14:43
 * description:
 */
public class MapToList<T> {

    T data;

    //	Map强转成List
    public List<Object> mapToList(T data) {
        Map<Object, Object> map = (Map<Object, Object>) data;
        Collection<Object> collects = map.values();
        final int size = collects.size();
        List<Object> list = new ArrayList<Object>(collects);
        return list;
    }

}

