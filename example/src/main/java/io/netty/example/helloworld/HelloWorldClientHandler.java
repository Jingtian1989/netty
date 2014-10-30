package io.netty.example.helloworld;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;


/**
 * Created by jingtian.zjt on 2014/10/29.
 */
public class HelloWorldClientHandler extends ChannelHandlerAdapter{

    private ByteBuf byteBuf;

    public HelloWorldClientHandler () {
        byteBuf = Unpooled.buffer();
        byteBuf.writeBytes("Hello, Server!".getBytes());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf recvBuf = (ByteBuf)msg;
        System.out.print("Server Message:");
        System.out.println(recvBuf.toString(CharsetUtil.US_ASCII));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


}
