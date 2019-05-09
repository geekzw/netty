package com.gzw.netty.client;

import com.alibaba.fastjson.JSON;
import com.gzw.netty.common.Constant;
import com.gzw.netty.common.dto.LoginPacket;
import com.gzw.netty.protocol.Serializer;
import com.gzw.netty.utils.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *   
 *  * <p>  </p>
 *   
 *  * @author: gujian（gujian@maihaoche.com）
 *  * @date: 2019-05-06 15:05
 *
 * @since V1.0
 *  
 */
public class SimpleClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接成功...");
        String string = "你好，我是古剑";
        LoginPacket loginPacket = new LoginPacket();
        loginPacket.setUserName("gujian");
        loginPacket.setPassword("gujian");
        byte[] serialize = Serializer.DEFAULT.serialize(loginPacket);
        ctx.writeAndFlush(ByteUtil.createByte(ctx,serialize));

    }
}
