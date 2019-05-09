package com.gzw.netty.protocol.impl;

import com.alibaba.fastjson.JSON;
import com.gzw.netty.protocol.SerializeAlgorithmEnum;
import com.gzw.netty.protocol.Serializer;

/**
 *   
 *  * <p>  </p>
 *   
 *  * @author: gujian（gujian@maihaoche.com）
 *  * @date: 2019-05-06 22:00
 *
 * @since V1.0
 *  
 */
public class JSONSerializer implements Serializer {
    @Override
    public int getSerializerAlogrithm() {
        return SerializeAlgorithmEnum.JSON.getCode();
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
