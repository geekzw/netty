package com.gzw.netty.client;

import com.gzw.netty.common.handler.ProtocolWrapHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *   
 *  * <p>  </p>
 *   
 *  * @author: gujian（gujian@maihaoche.com）
 *  * @date: 2019-05-04 11:05
 *
 * @since V1.0
 *  
 */
public class ImClient {

    private static final Integer MAX_RETRY = 5;

    private static final String ADDRESS = "127.0.0.1";

    private static final Integer PORT = 8080;

    public static void main(String[] args) {
        new ImClient().connect(ADDRESS,PORT);
    }


    public void connect(String ip,Integer port){

        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new SimpleClientHandler());
                        ch.pipeline().addLast(new ProtocolWrapHandler());
                    }
                });
        connectInter(bootstrap,ip,port,0);
    }

    public void connectInter(Bootstrap bootstrap,String ip,Integer port,Integer retry){
        bootstrap.connect(ip,port).addListener(future -> {
            if(future.isSuccess()){
                System.out.println("连接成功..."+new Date()+"address:"+ADDRESS+":"+PORT);
            }else if(retry == 0) {
                System.out.println("重试次数用完了...");
            }else{

                int order = MAX_RETRY-retry;
                int delay = order << 1;
                bootstrap.config().group().schedule(()->connectInter(bootstrap,ip,port,retry-1),delay,TimeUnit.SECONDS);
            }
        });
    }
}
