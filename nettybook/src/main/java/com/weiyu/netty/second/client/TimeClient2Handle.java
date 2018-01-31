package com.weiyu.netty.second.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.util.CharsetUtil;

/**
 * Created by weiyu on 2018/1/11.
 */
public class TimeClient2Handle extends ChannelHandlerAdapter {


    public TimeClient2Handle(){
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //LineBasedFrameDecoder  是根据 \r\n 或者 \n 进行分隔
        //DelimiterBasedFrameDecoder  $_
        for(int i=0;i<100;i++) {
            ByteBuf message;
            byte[] req = ("query time order\r\n").getBytes();
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String buf = (String)msg;

        System.out.println(buf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
