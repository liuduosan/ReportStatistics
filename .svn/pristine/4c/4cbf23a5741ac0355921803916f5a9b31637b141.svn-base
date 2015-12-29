package com.dc.flamingo.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;

/**
 * 处理jackson时间转换问题
 * @Class Name JsonTimeSerializer
 * @Author lee
 * @Create In Oct 21, 2011
 */
@Component 
public class JsonTimeSerializer extends JsonSerializer<Date>{

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
    	SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = timeFormat.format(date);
        gen.writeString(formattedDate);
    }

}