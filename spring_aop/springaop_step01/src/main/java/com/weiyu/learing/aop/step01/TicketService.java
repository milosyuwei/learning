package com.weiyu.learing.aop.step01;

/**
 *
 *
 */
public interface TicketService {
    //售票
    public void sellTicket();

    //问询
    public void inquire();

    //退票
    public void withdraw() throws Exception;
}
