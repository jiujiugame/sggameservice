package com.jiujiu.sggame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiujiu.sggame.common.PaginationSupport;
import com.jiujiu.sggame.dao.UserDAO;
import com.jiujiu.sggame.entity.User;
import com.jiujiu.sggame.service.UserService;

/**
 * 
 * UserService 接口实现类
 * 
 * @author 00fly
 * @version [版本号, 2018-09-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService
{
    
    @Autowired
    UserDAO userDAO;
    
    @Override
    public int insert(User user)
    {
        return userDAO.insert(user);
    }
    
    @Override
    public void deleteById(String id)
    {
        userDAO.deleteById(id);
    }
    
    @Override
    public long deleteById(String[] ids)
    {
        return userDAO.deleteById(ids);
    }
    
    @Override
    public long deleteById(List<String> ids)
    {
        return userDAO.deleteById(ids);
    }
    
    @Override
    public int update(User user)
    {
        return userDAO.updateById(user);
    }
    
    @Override
    public void saveOrUpdate(User user)
    {
        if (user.getAccountId() != null)
        {
            userDAO.updateById(user);
        }
        else
        {
            userDAO.insert(user);
        }
    }
    
    @Override
    public User queryById(String id)
    {
        return userDAO.queryById(id);
    }
    
    @Override
    public List<User> queryAll()
    {
        List<User> list = userDAO.queryAll();
        return list;
    }
    
    /**
     * 根据条件分页查询
     * 
     * @param user 条件对象
     * @param pageNo 页号
     * @param pageSize 页大小
     * @return
     */
    @Override
    public PaginationSupport<User> queryForPagination(User user, int pageNo, int pageSize)
    {
        return userDAO.queryForPagination(user, pageNo, pageSize);
    }
    
    /**
     * 事务方法
     * 
     * 
     * @see [类、类#方法、类#成员]
     */
    public void testTrans()
    {
        List<User> list = queryAll();
        for (User user : list)
        {
            userDAO.insert(user);
        }
        int i = 1 / 0; // 抛出异常
        System.out.println(i);
    }
}
