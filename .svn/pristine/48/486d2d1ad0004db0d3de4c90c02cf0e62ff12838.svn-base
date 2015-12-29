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
 * 处理jackson日期转换问题
 * @Class Name JsonDateSerializer
 * @Author lee
 * @Create In Oct 13, 2011
 */
@Component 
public class JsonDateSerializer extends JsonSerializer<Date>{

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(date);
        gen.writeString(formattedDate);
    }

}

