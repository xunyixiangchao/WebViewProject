package com.lis.common.eventbus;

/**
 * Created by lis on 2021/8/21.
 */
public class LoginEvent {
    public String userName;
    public LoginEvent(String userName) {
        this.userName = userName;
    }
}