package com.gzw.netty.server;

import com.gzw.netty.common.dto.LoginPacket;
import com.gzw.netty.common.dto.Packet;
import com.gzw.netty.protocol.CommandEnum;
import com.gzw.netty.protocol.Serializer;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *   
 *  * <p>  </p>
 *   
 *  * @author: gujian（gujian@maihaoche.com）
 *  * @date: 2019-05-06 22:10
 *
 * @since V1.0
 *  
 */
public class LoginHandler extends SimpleChannelInboundHandler<LoginPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginPacket msg) throws Exception {
        System.out.println("是登录请求");
    }
}
