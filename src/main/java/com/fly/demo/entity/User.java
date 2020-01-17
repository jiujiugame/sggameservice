package com.fly.demo.entity;

/**
 * 
 * user表对应的User实体
 * 
 * @author 00fly
 * @version [版本号, 2018-09-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class User
{
    // id
    private Long id;
    
    // age
    private Integer age;
    
    // name
    private String name;
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public void setAge(Integer age)
    {
        this.age = age;
    }
    
    public Integer getAge()
    {
        return this.age;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
}
