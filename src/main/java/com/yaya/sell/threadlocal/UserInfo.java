package com.yaya.sell.threadlocal;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author changhr2013
 * @date 2020/3/19
 */
@Data
@Accessors(chain = true)
public class UserInfo {

    private String id;

    private String userName;

    private String password;

    private String threadName;
}
