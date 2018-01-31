package com.weiyu.netty.third.server;

import com.weiyu.protobuf.SubscribeRequest;
import com.weiyu.protobuf.SubscribeResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by weiyu on 2018/1/11.
 */
public class TimeServer3Handler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{

        SubscribeRequest request = (SubscribeRequest)msg;

        System.out.println(request.getProductName());
        SubscribeResponse response = SubscribeResponse.newBuilder().setSubReqID(request.getSubReqID()).setDesc("ok").build();
        ctx.writeAndFlush(response);
    }

    /*
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
    */

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
