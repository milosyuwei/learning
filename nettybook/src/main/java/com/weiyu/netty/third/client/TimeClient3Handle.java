package com.weiyu.netty.third.client;

import com.google.common.collect.Lists;
import com.weiyu.protobuf.SubscribeRequest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * Created by weiyu on 2018/1/11.
 */
public class TimeClient3Handle extends ChannelHandlerAdapter {


    public TimeClient3Handle(){
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i=0;i<10;i++) {
            List<String> addresses = Lists.newArrayList("ctrip");

            SubscribeRequest request = SubscribeRequest.newBuilder()
                    .setSubReqID(i)
                    .setUserName("weiyu")
                    .setProductName("netty")
                    .addAllAddress(addresses)
                    .build();
            ctx.writeAndFlush(request);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
