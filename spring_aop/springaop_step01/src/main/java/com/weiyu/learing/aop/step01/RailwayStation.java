package com.weiyu.learing.aop.step01;

/**
 * RailwayStation 实现 TicketService
 * @author: weiyu
 * @date: 2018/2/2
 */
public class RailwayStation implements TicketService{
    public void sellTicket(){
        System.out.println("售票............");
    }

    public void inquire() {
        System.out.println("问询.............");
    }

    public void withdraw() throws Exception {
        System.out.println("退票.............");
        throw new Exception("已经超过退票时间");
    }
}
