package com.weiyu.netty.first.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

/**
 * Created by weiyu on 2018/1/11.
 */
public class TimeClientHandle extends ChannelHandlerAdapter {

    private final ByteBuf message ;
    public TimeClientHandle(){
        byte[] req = "query time order".getBytes();
        message = Unpooled.buffer(req.length);
        message.writeBytes(req);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] response = new byte[buf.readableBytes()];
        buf.readBytes(response);
        String body = new String(response,CharsetUtil.UTF_8);
        System.out.println(body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
