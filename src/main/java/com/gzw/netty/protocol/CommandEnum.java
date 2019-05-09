package com.gzw.netty.protocol;

/**
 *   
 *  * <p>  </p>
 *   
 *  * @author: gujian（gujian@maihaoche.com）
 *  * @date: 2019-05-06 21:08
 *
 * @since V1.0
 *  
 */
public enum CommandEnum {
    LOGIN((byte)1,"登录");

    private byte code;

    private String desc;

    CommandEnum(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
