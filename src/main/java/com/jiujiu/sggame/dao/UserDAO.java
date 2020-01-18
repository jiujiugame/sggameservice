package com.jiujiu.sggame.dao;

import java.util.List;

import com.jiujiu.sggame.common.PaginationSupport;
import com.jiujiu.sggame.entity.User;

/**
 * 
 * UserDAO 接口
 * 
 * @author 00fly
 * @version [版本号, 2018-09-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface UserDAO
{
    /**
     * 根据条件删除数据
     * 
     * @param criteria 条件对象
     * @return
     * 
     */
    int deleteByCriteria(User criteria);
    
    /**
     * 根据主键id删除数据
     * 
     * @param id 主键
     * @return
     * 
     */
    int deleteById(String id);
    
    /**
     * 根据主键id列表删除数据
     * 
     * @param ids 主键列表
     * @return
     * 
     */
    int deleteById(String[] ids);
    
    /**
     * 根据主键id列表删除数据
     * 
     * @param ids 主键列表
     * @return
     * 
     */
    int deleteById(List<String> ids);
    
    /**
     * 增加记录(插入全字段)
     * 
     * @param bean 待插入对象
     * @return
     * 
     */
    int insert(User bean);
    
    /**
     * 增加记录(仅插入非空字段)
     * 
     * @param bean 待插入对象
     * @return
     * 
     */
    int insertSelective(User bean);
    
    /**
     * 查询全部
     * 
     * @return
     * 
     */
    List<User> queryAll();
    
    /**
     * 根据条件查询
     * 
     * @param criteria 条件对象
     * @return
     * 
     */
    List<User> queryByCriteria(User criteria);
    
    /**
     * 根据id查找数据
     * 
     * @param id 主键
     * @return
     * 
     */
    User queryById(String id);
    
    /**
     * 根据条件分页查询
     * 
     * @param criteria 条件对象
     * @param pageNo 页号
     * @param pageSize 页大小
     * @return
     * 
     */
    PaginationSupport<User> queryForPagination(User criteria, int pageNo, int pageSize);
    
    /**
     * 根据条件查询数据条数
     * 
     * @param criteria 条件对象
     * @return
     * 
     */
    long queryTotal(User criteria);
    
    /**
     * 根据复杂条件更新全字段数据
     * 
     * @param bean 待更新对象
     * @param criteria 条件对象
     * @return
     * 
     */
    int updateByCriteria(User bean, User criteria);
    
    /**
     * 根据复杂条件更新非空字段数据
     * 
     * @param bean 待更新对象
     * @param criteria 条件对象
     * @return
     * 
     */
    int updateByCriteriaSelective(User bean, User criteria);
    
    /**
     * 根据id更新全部数据
     * 
     * @param bean 待更新对象
     * @return
     * 
     */
    int updateById(User bean);
    
    /**
     * 根据id更新非空字段数据
     * 
     * @param bean 待更新对象
     * @return
     * 
     */
    int updateByIdSelective(User bean);
}