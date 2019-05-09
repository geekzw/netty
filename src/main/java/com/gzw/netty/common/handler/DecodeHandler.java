package com.gzw.netty.common.handler;

import com.gzw.netty.common.Constant;
import com.gzw.netty.common.dto.LoginPacket;
import com.gzw.netty.common.dto.Packet;
import com.gzw.netty.protocol.CommandEnum;
import com.gzw.netty.protocol.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   
 *  * <p>  </p>
 *   
 *  * @author: gujian（gujian@maihaoche.com）
 *  * @date: 2019-05-06 22:28
 *
 * @since V1.0
 *  
 */
public class DecodeHandler extends ByteToMessageDecoder {


    private static Map<Byte,Class<? extends Packet>> requestTypeMap = new HashMap<>();

    static {
        requestTypeMap.put(CommandEnum.LOGIN.getCode(),LoginPacket.class);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        Integer magic = in.readInt();
        if(!magic.equals(Constant.MAGIC)){
            System.out.println("协议不合法");
            ctx.channel().close();
        }
        int version = in.readByte();
        if(version != 1){
            System.out.println("版本号不正确"+version);
            ctx.channel().close();
        }
        byte algorithm = in.readByte();
        byte command = in.readByte();
        int length = in.readInt();
        System.out.println("序列化算法："+algorithm);
        System.out.println("命令："+command);
        System.out.println("内容长度"+length);
        byte[] bytes = new byte[length];
        in.readBytes(bytes);
        Class<? extends Packet> clazz = requestTypeMap.get(command);
        Packet deserialize = Serializer.DEFAULT.deserialize(clazz, bytes);
        out.add(deserialize);
    }
}
