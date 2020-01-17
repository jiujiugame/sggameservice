package com.fly.demo.common;

/**
 * 
 * DataBaseException
 * 
 * @author 00fly
 * @version [版本号, 2018-09-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DataException extends RuntimeException
{
    private static final long serialVersionUID = -4983182908260262069L;
    
    public DataException()
    {
        super();
    }
    
    public DataException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    public DataException(String message)
    {
        super(message);
    }
    
    public DataException(Throwable cause)
    {
        super(cause);
    }
}
