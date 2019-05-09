package com.gzw.netty.protocol;

/**
 *   
 *  * <p>  </p>
 *   
 *  * @author: gujian（gujian@maihaoche.com）
 *  * @date: 2019-05-06 21:06
 *
 * @since V1.0
 *  
 */
public enum SerializeAlgorithmEnum {
    JSON(1,"json");

    private Integer code;

    private String desc;

    SerializeAlgorithmEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
