package com.zzh.oa_system.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 14:41
 * description:字符串转日期工具类
 */
@Configuration
public class StringtoDateUtil implements Converter<String, Date> {

    SimpleDateFormat sdf = new SimpleDateFormat();
    private List<String> patterns = new ArrayList<>();

    @Override
    public Date convert(String source) {
        patterns.add("yyyy-MM-dd HH:mm:ss");
        patterns.add("yyyy-MM-dd HH:mm");
        patterns.add("yyyy-MM-dd");
        patterns.add("HH:mm");
        patterns.add("yyyy-MM");
        Date date = null;
        for (String p : patterns) {
            try {
                sdf.applyPattern(p);
                date = sdf.parse(source);
                break;
            } catch (ParseException e) {
            }
        }
        if (date == null) {
            throw new IllegalArgumentException("日期格式错误！[" + source + "]");
        }
        return date;
    }

    public void setPatterns(List<String> patterns) {
        this.patterns = patterns;
    }

}
