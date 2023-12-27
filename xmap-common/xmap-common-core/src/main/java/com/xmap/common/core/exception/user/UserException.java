package com.xmap.common.core.exception.user;

import com.xmap.common.core.exception.base.BaseException;

/**
 * 用户信息异常类
 * 
 * @author xmap
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
