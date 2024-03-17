package com.ruoyi.common.exception.fvehicles;

public class UserException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    protected final String message;

    public UserException(String message)
    {
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}