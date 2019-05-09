package com.gzw.netty.utils;

import com.gzw.netty.common.Constant;
import com.gzw.netty.protocol.CommandEnum;
import com.gzw.netty.protocol.SerializeAlgorithmEnum;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 *   
 *  * <p>  </p>
 *   
 *  * @author: gujian（gujian@maihaoche.com）
 *  * @date: 2019-05-06 21:02
 *
 * @since V1.0
 *  
 */
public class ByteUtil {

    public static ByteBuf createByte(ChannelHandlerContext ctx,byte[] bytes){
        ByteBuf buffer = ctx.alloc().buffer();
        //魔数
        buffer.writeInt(Constant.MAGIC);
        //版本号
        buffer.writeByte(1);
        buffer.writeByte(SerializeAlgorithmEnum.JSON.getCode());
        buffer.writeByte(CommandEnum.LOGIN.getCode());
        buffer.writeInt(bytes.length);
        buffer.writeBytes(bytes);
        return buffer;
    }
}
