package com.jiujiu.sggame.service;

import java.util.List;

import com.jiujiu.sggame.common.PaginationSupport;
import com.jiujiu.sggame.entity.User;

/**
 * 
 * UserService 接口
 * 
 * @author 00fly
 * @version [版本号, 2018-09-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface UserService
{
    /**
     * 新增
     * 
     * @param user
     * @see [类、类#方法、类#成员]
     */
	int insert(User user);
    
    /**
     * 根据id删除
     * 
     * @param id
     * @see [类、类#方法、类#成员]
     */
    void deleteById(String id);
    
    /**
     * 根据主键id列表删除数据
     * 
     * @param ids 主键列表
     * @return
     */
    public long deleteById(String[] ids);
    
    /**
     * 根据主键id列表删除数据
     * 
     * @param ids 主键列表
     * @return
     */
    public long deleteById(List<String> ids);
    
    /**
     * 根据id更新
     * 
     * @param user
     * @see [类、类#方法、类#成员]
     */
    int update(User user);
    
    /**
     * 新增/根据id更新
     * 
     * @param user
     * @see [类、类#方法、类#成员]
     */
    void saveOrUpdate(User user);
    
    /**
     * 根据id查询
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    User queryById(String id);
    
    /**
     * 查询全部
     * 
     * @return
     */
    List<User> queryAll();
    
    /**
     * 根据条件分页查询
     * 
     * @param criteria 条件对象
     * @param pageNo 页号
     * @param pageSize 页大小
     * @return
     * @throws DaoException
     */
    PaginationSupport<User> queryForPagination(User criteria, int pageNo, int pageSize);
    
    /**
     * 事务方法
     * 
     * @see [类、类#方法、类#成员]
     */
    public void testTrans();
}
