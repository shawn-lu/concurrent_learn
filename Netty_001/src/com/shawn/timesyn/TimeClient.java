/**
 * Project Name:netty_001
 * File Name:TimeClient.java
 * Package Name:com.shawn.timesyn
 * Date:2016年7月25日下午5:21:15
 *
 */

package com.shawn.timesyn;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * ClassName: TimeClient <br/>
 * Date: 2016年7月25日 下午5:21:15 <br/>
 * Description: 时间同步客户端
 *
 * @author luxf
 * @version
 * @see
 */
public class TimeClient {
    public void connect(int port, String host) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 配置客户端nio线程组
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        new TimeClient().connect(8080, "127.0.0.1");
    }
}
