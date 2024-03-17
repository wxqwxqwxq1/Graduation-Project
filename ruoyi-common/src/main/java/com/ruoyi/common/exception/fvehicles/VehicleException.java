package com.ruoyi.common.exception.fvehicles;

public class VehicleException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    protected final String message;

    public VehicleException(String message)
        {
            this.message = message;
        }
        @Override
        public String getMessage()
        {
            return message;
        }
}
