package com.weiyu.netty.third.server;

import com.weiyu.netty.Constant;
import com.weiyu.protobuf.SubscribeProto;
import com.weiyu.protobuf.SubscribeRequest;
import com.weiyu.protobuf.SubscribeRequestOrBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by weiyu on 2018/1/11.
 */
public class TimeServer3 {
    public static void main(String[] args) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //ProtobufVarint32FrameDecoder 解决半包问题
                            //这是针对protobuf协议的ProtobufVarint32LengthFieldPrepender()所加的长度属性的解码器
                            socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                            //ProtobufDecoder解码
                            socketChannel.pipeline().addLast(new ProtobufDecoder(
                                    SubscribeRequest.getDefaultInstance()
                            ));
                            // ProtobufVarint32LengthFieldPrepender
                            // 对protobuf协议的的消息头上加上一个长度为32的整形字段，用于标志这个消息的长度。
                            socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            socketChannel.pipeline().addLast(new ProtobufEncoder());
                            socketChannel.pipeline().addLast(new TimeServer3Handler());
                        }
                    });
            ChannelFuture f = b.bind(Constant.PORT).sync();
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
