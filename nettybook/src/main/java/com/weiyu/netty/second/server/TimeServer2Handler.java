package com.weiyu.netty.second.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by weiyu on 2018/1/11.
 */
public class TimeServer2Handler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        String buf = (String)msg;


        String body = buf;
        if("query time order".equals(body)) {
            System.out.println(body);
            String dt = DateFormat.getInstance().format(new Date());
            ByteBuf respBuf = Unpooled.copiedBuffer(dt.getBytes());
            ctx.write(respBuf);
        }else {
            ctx.write(Unpooled.copiedBuffer("bad order".getBytes()));
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
