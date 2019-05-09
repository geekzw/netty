package com.gzw.netty.server;

import com.gzw.netty.common.handler.DecodeHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

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
public class ImServer {


    public static void main(String[] args) {
        try {
            new ImServer().bind(8080);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bind(int port) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new DecodeHandler());
                        socketChannel.pipeline().addLast(new LoginHandler());
                    }
                });
        bindInter(bootstrap,port);
    }

    private void bindInter(ServerBootstrap bootstrap, Integer port){
        try {
            ChannelFuture future = bootstrap.bind(port).sync();
            future.addListener(result -> {
                if(result.isSuccess()){
                    System.out.println("端口绑定成功["+port+"]");
                }else{
                    System.out.println("端口绑定失败");
                    bindInter(bootstrap,port+1);
                }
            });
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
