package com.fly.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.fly.demo.common.PaginationSupport;
import com.fly.demo.core.BaseDAO;
import com.fly.demo.dao.UserDAO;
import com.fly.demo.entity.User;

/**
 * 
 * UserDAO 接口实现类
 * 
 * @author 00fly
 * @version [版本号, 2018-09-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SuppressWarnings("unchecked")
@Repository
public class UserDAOImpl extends BaseDAO<User> implements UserDAO
{
    /**
     * <默认构造函数>
     */
    public UserDAOImpl()
    {
        super();
    }
    
    /**
     * 构造whereClause
     * 
     * @param criteria
     * @return
     */
    private String buildWhereClause(User criteria)
    {
        StringBuilder whereClause = new StringBuilder();
        if (criteria.getId() != null)
        {
            whereClause.append(" and id=?");
        }
        if (criteria.getAge() != null)
        {
            whereClause.append(" and age=?");
        }
        if (criteria.getName() != null)
        {
            whereClause.append(" and name=?");
        }
        if (whereClause.length() > 4)
        {
            return whereClause.substring(4);
        }
        return "";
    }
    
    /**
     * 构造whereParams
     * 
     * @param criteria
     * @return
     * 
     */
    private List<Object> buildWhereParams(User criteria)
    {
        List<Object> whereParams = new ArrayList<Object>();
        if (criteria.getId() != null)
        {
            whereParams.add(criteria.getId());
        }
        if (criteria.getAge() != null)
        {
            whereParams.add(criteria.getAge());
        }
        if (criteria.getName() != null)
        {
            whereParams.add(criteria.getName());
        }
        return whereParams;
    }
    
    /**
     * 根据条件删除数据
     * 
     * @param criteria 条件对象
     * @return
     * 
     */
    @Override
    public int deleteByCriteria(User criteria)
    {
        StringBuilder sql = new StringBuilder("delete from user");
        String whereClause = buildWhereClause(criteria);
        List<Object> whereParams = buildWhereParams(criteria);
        if (StringUtils.isNotEmpty(whereClause))
        {
            sql.append(" where ").append(whereClause);
        }
        return update(sql.toString(), whereParams.toArray());
    }
    
    /**
     * 根据主键id删除数据
     * 
     * @param id 主键
     * @return
     * 
     */
    @Override
    public int deleteById(Long id)
    {
        String sql = "delete from user where id=?";
        return update(sql, id);
    }
    
    /**
     * 根据主键id列表删除数据
     * 
     * @param ids 主键列表
     * @return
     * 
     */
    @Override
    public int deleteById(Long[] ids)
    {
        Long idsArr[][] = new Long[ids.length][1];
        for (int i = 0; i < ids.length; i++)
        {
            idsArr[i][0] = ids[i];
        }
        String sql = "delete from user where id=?";
        return batch(sql, idsArr).length;
    }
    
    /**
     * 根据主键id列表删除数据
     * 
     * @param ids 主键列表
     * @return
     * 
     */
    @Override
    public int deleteById(List<Long> ids)
    {
        Long idsArr[][] = new Long[ids.size()][1];
        for (int i = 0; i < ids.size(); i++)
        {
            idsArr[i][0] = ids.get(i);
        }
        String sql = "delete from user where id=?";
        return batch(sql, idsArr).length;
    }
    
    /**
     * 增加记录(插入全字段)
     * 
     * @param bean 待插入对象
     * @return
     * 
     */
    @Override
    public int insert(User bean)
    {
        String sql = "insert into user (age, name ) values(?, ?)";
        return update(sql, bean.getAge(), bean.getName());
    }
    
    /**
     * 增加记录(仅插入非空字段)
     * 
     * @param bean 待插入对象
     * @return
     * 
     */
    @Override
    public int insertSelective(User bean)
    {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        if (bean.getAge() != null)
        {
            columns.append(", age");
            values.append(", ?");
            params.add(bean.getAge());
        }
        if (bean.getName() != null)
        {
            columns.append(", name");
            values.append(", ?");
            params.add(bean.getName());
        }
        StringBuilder sql = new StringBuilder("insert into user (").append(columns.substring(1)).append(")");
        sql.append(" values(").append(values.substring(1)).append(")");
        return update(sql.toString(), params.toArray());
    }
    
    /**
     * 查询全部
     * 
     * @return
     * 
     */
    @Override
    public List<User> queryAll()
    {
        String sql = "select id, age, name from user";
        return query(User.class, sql);
    }
    
    /**
     * 根据条件查询
     * 
     * @param criteria 条件对象
     * @return
     * 
     */
    @Override
    public List<User> queryByCriteria(User criteria)
    {
        StringBuilder sql = new StringBuilder("select id, age, name from user ");
        String whereClause = buildWhereClause(criteria);
        List<Object> whereParams = buildWhereParams(criteria);
        if (StringUtils.isNotEmpty(whereClause))
        {
            sql.append(" where ").append(whereClause);
        }
        return query(User.class, sql.toString(), whereParams.toArray());
    }
    
    /**
     * 根据id查找数据
     * 
     * @param id 主键
     * @return
     * 
     */
    @Override
    public User queryById(Long id)
    {
        String sql = "select id, age, name from user where id=?";
        return (User)queryFirst(User.class, sql, id);
    }
    
    /**
     * 根据条件分页查询
     * 
     * @param criteria 条件对象
     * @param pageNo 页号
     * @param pageSize 页大小
     * @return
     * 
     */
    @Override
    public PaginationSupport<User> queryForPagination(User criteria, int pageNo, int pageSize)
    {
        StringBuilder sql = new StringBuilder("select id, age, name from user");
        String whereClause = buildWhereClause(criteria);
        List<Object> whereParams = buildWhereParams(criteria);
        if (StringUtils.isNotEmpty(whereClause))
        {
            sql.append(" where ").append(whereClause);
        }
        return queryForPagination(User.class, sql.toString(), pageNo, pageSize, whereParams.toArray());
    }
    
    /**
     * 根据条件查询数据条数
     * 
     * @param criteria 条件对象
     * @return
     * 
     */
    @Override
    public long queryTotal(User criteria)
    {
        StringBuilder sql = new StringBuilder("select count(1) from user");
        String whereClause = buildWhereClause(criteria);
        List<Object> whereParams = buildWhereParams(criteria);
        if (StringUtils.isNotEmpty(whereClause))
        {
            sql.append(" where ").append(whereClause);
        }
        return queryForLong(sql.toString(), whereParams.toArray());
    }
    
    /**
     * 根据复杂条件更新全字段数据
     * 
     * @param bean 待更新对象
     * @param criteria 条件对象
     * @return
     * 
     */
    @Override
    public int updateByCriteria(User bean, User criteria)
    {
        StringBuilder sql = new StringBuilder("update user set age=?, name=?");
        List<Object> params = new ArrayList<Object>();
        params.add(bean.getAge());
        params.add(bean.getName());
        String whereClause = buildWhereClause(criteria);
        List<Object> whereParams = buildWhereParams(criteria);
        if (StringUtils.isNotEmpty(whereClause))
        {
            sql.append(" where ").append(whereClause);
        }
        params.addAll(whereParams);
        return update(sql.toString(), params.toArray());
    }
    
    /**
     * 根据复杂条件更新非空字段数据
     * 
     * @param bean 待更新对象
     * @param criteria 条件对象
     * @return
     * 
     */
    @Override
    public int updateByCriteriaSelective(User bean, User criteria)
    {
        StringBuilder sql = new StringBuilder("update user set");
        StringBuilder columns = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        if (bean.getAge() != null)
        {
            columns.append(", age=?");
            params.add(bean.getAge());
        }
        if (bean.getName() != null)
        {
            columns.append(", name=?");
            params.add(bean.getName());
        }
        sql.append(columns.substring(1));
        String whereClause = buildWhereClause(criteria);
        List<Object> whereParams = buildWhereParams(criteria);
        if (StringUtils.isNotEmpty(whereClause))
        {
            sql.append(" where ").append(whereClause);
        }
        params.addAll(whereParams);
        return update(sql.toString(), params.toArray());
    }
    
    /**
     * 根据id更新全部数据
     * 
     * @param bean 待更新对象
     * @return
     * 
     */
    @Override
    public int updateById(User bean)
    {
        String sql = "update user set age=?, name=? where id=?";
        return update(sql, bean.getAge(), bean.getName(), bean.getId());
    }
    
    /**
     * 根据id更新非空字段数据
     * 
     * @param bean 待更新对象
     * @return
     * 
     */
    @Override
    public int updateByIdSelective(User bean)
    {
        StringBuilder sql = new StringBuilder("update user set");
        StringBuilder columns = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        if (bean.getAge() != null)
        {
            columns.append(", age=?");
            params.add(bean.getAge());
        }
        if (bean.getName() != null)
        {
            columns.append(", name=?");
            params.add(bean.getName());
        }
        sql.append(columns.substring(1));
        sql.append(" where id=?");
        params.add(bean.getId());
        return update(sql.toString(), params.toArray());
    }
}