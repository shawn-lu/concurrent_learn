/**
 * Project Name:netty_001
 * File Name:TimeServerHandler.java
 * Package Name:com.shawn.timesyn
 * Date:2016年7月25日下午4:13:16
 *
 */

package com.shawn.timesyn003;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * ClassName: TimeServerHandler <br/>
 * Date: 2016年7月25日 下午4:13:16 <br/>
 * Description: TODO
 *
 * @author luxf
 * @version
 * @see
 */
public class TimeServerHandler extends ChannelHandlerAdapter {
    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
/*        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8").substring(0,
                req.length - System.getProperty("line.separator").length());*/
        String body = (String)msg;
        System.out.println(
                "The time server receive order : " + body + ";the counter is  : " + ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
                ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
