package com.gzw.netty.common.dto;

import com.gzw.netty.protocol.CommandEnum;

import java.io.Serializable;

/**
 *   
 *  * <p>  </p>
 *   
 *  * @author: gujian（gujian@maihaoche.com）
 *  * @date: 2019-05-06 21:33
 *
 * @since V1.0
 *  
 */
public class LoginPacket extends Packet implements Serializable {

    private String userName;

    private String password;

    @Override
    public int getCommand() {
        return CommandEnum.LOGIN.getCode();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
