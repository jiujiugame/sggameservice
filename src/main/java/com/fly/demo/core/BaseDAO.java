package com.fly.demo.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.fly.demo.common.DataException;
import com.fly.demo.common.PaginationSupport;

/**
 * 
 * BaseDAO
 * 
 * @author 00fly
 * @version [版本号, 2018-09-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository
@SuppressWarnings({"rawtypes", "unchecked", "hiding"})
public class BaseDAO<T>
{
    @Autowired
    public QueryRunner queryRuner;
    
    /**
     * 批量更新
     * 
     * @param sql 需执行的sql
     * @param params 参数组
     * @return 顺序返回执行后所影响到的行数
     * 
     */
    public int[] batch(String sql, Object[][] params)
    {
        try
        {
            Connection connection = DataSourceUtils.getConnection(queryRuner.getDataSource());
            return queryRuner.batch(connection, sql, params);
        }
        catch (SQLException e)
        {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
    
    /**
     * 批量更新
     * 
     * @param sql 需执行的sql
     * @param params List参数组
     * @return 顺序返回执行后所影响到的行数
     * 
     */
    public int[] batch(String sql, List<Object[]> params)
    {
        try
        {
            Connection connection = DataSourceUtils.getConnection(queryRuner.getDataSource());
            return queryRuner.batch(connection, sql, params.toArray(new Object[0][]));
        }
        catch (SQLException e)
        {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
    
    /**
     * 带可变参数, 执行sql插入，返回新增记录的自增主键<BR>
     * 注意： 若插入的表无自增主键则返回 0，异常的话则返回 null
     * 
     * @param sql 需执行的sql
     * @param para 可变参数
     * @return
     * 
     */
    public Long insert(String sql, Object... para)
    {
        try
        {
            Connection connection = DataSourceUtils.getConnection(queryRuner.getDataSource());
            return (Long)queryRuner.insert(connection, sql, new ScalarHandler<Object>(), para);
        }
        catch (SQLException e)
        {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
    
    /**
     * 带可变参数查询,返回执行结果
     * 
     * @param sql 查询sql
     * @param para 可变参数
     * @return
     * 
     */
    public List<Map<String, Object>> query(String sql, Object... para)
    {
        try
        {
            Connection connection = DataSourceUtils.getConnection(queryRuner.getDataSource());
            return queryRuner.query(connection, sql, new MapListHandler(), para);
        }
        catch (SQLException e)
        {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
    
    /**
     * 带可变参数查询,返回执行结果
     * 
     * @param clazz
     * @param sql 查询sql
     * @param para 可变参数
     * @return
     * 
     */
    public <T> List query(T clazz, String sql, Object... para)
    {
        try
        {
            Connection connection = DataSourceUtils.getConnection(queryRuner.getDataSource());
            BeanProcessor bean = new GenerousBeanProcessor();
            RowProcessor processor = new BasicRowProcessor(bean);
            return (List)queryRuner.query(connection, sql, new BeanListHandler((Class)clazz, processor), para);
        }
        catch (SQLException e)
        {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
    
    /**
     * 带可变参数查询,返回首条执行结果
     * 
     * @param sql 查询sql
     * @param para 可变参数
     * @return
     * 
     */
    public Map<String, Object> queryFirst(String sql, Object... para)
    {
        try
        {
            Connection connection = DataSourceUtils.getConnection(queryRuner.getDataSource());
            return queryRuner.query(connection, sql, new MapHandler(), para);
        }
        catch (SQLException e)
        {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
    
    /**
     * 带可变参数查询,返回首条执行结果
     * 
     * @param clazz
     * @param sql 查询sql
     * @param para 可变参数
     * @return
     * 
     */
    public <T> Object queryFirst(T clazz, String sql, Object... para)
    {
        try
        {
            Connection connection = DataSourceUtils.getConnection(queryRuner.getDataSource());
            BeanProcessor bean = new GenerousBeanProcessor();
            RowProcessor processor = new BasicRowProcessor(bean);
            return queryRuner.query(connection, sql, new BeanHandler((Class)clazz, processor), para);
        }
        catch (SQLException e)
        {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
    
    /**
     * 带可变参数查询，返回long类型数据
     * 
     * @param countSql 查询记录条数的sql
     * @param para 可变参数
     * @return
     * 
     */
    public Long queryForLong(String countSql, Object... para)
    {
        try
        {
            Connection connection = DataSourceUtils.getConnection(queryRuner.getDataSource());
            return queryRuner.query(connection, countSql, new ScalarHandler<Long>(), para);
        }
        catch (SQLException e)
        {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
    
    /**
     * 带可变参数条件的分页查询
     * 
     * @param sql 查询sql
     * @param pageNo 页号
     * @param pageSize 每页记录数
     * @param para 可变参数
     * @return
     * 
     */
    public PaginationSupport queryForPagination(String sql, int pageNo, int pageSize, Object... para)
    {
        // 保证正整数
        pageNo = Math.max(pageNo, 1);
        pageSize = Math.max(pageSize, 1);
        // 查询记录总条数
        int index = sql.toLowerCase().indexOf(" from ");
        String countSql = "select count(1)" + StringUtils.substring(sql, index);
        long total = queryForLong(countSql, para);
        // 查询当前页数据
        StringBuffer sbSql = new StringBuffer(sql).append(" limit ").append(pageSize * (pageNo - 1)).append(", ").append(pageSize);
        List<Map<String, Object>> list = query(sbSql.toString(), para);
        // 封装返回分页对象
        PaginationSupport page = new PaginationSupport(total, pageNo, pageSize);
        page.setItems(list);
        return page;
    }
    
    /**
     * 带可变参数条件的分页查询
     * 
     * @param clazz
     * @param sql 查询sql
     * @param pageNo 页号
     * @param pageSize 每页记录数
     * @param para 可变参数
     * @return
     * 
     */
    public <T> PaginationSupport queryForPagination(T clazz, String sql, int pageNo, int pageSize, Object... para)
    {
        // 保证正整数
        pageNo = Math.max(pageNo, 1);
        pageSize = Math.max(pageSize, 1);
        // 查询记录总条数
        int index = sql.toLowerCase().indexOf(" from ");
        String countSql = "select count(1)" + StringUtils.substring(sql, index);
        long total = queryForLong(countSql, para);
        // 查询当前页数据
        StringBuffer sbSql = new StringBuffer(sql).append(" limit ").append(pageSize * (pageNo - 1)).append(", ").append(pageSize);
        List<T> list = query((Class)clazz, sbSql.toString(), para);
        // 封装返回分页对象
        PaginationSupport page = new PaginationSupport(total, pageNo, pageSize);
        page.setItems(list);
        return page;
    }
    
    /**
     * 带可变参数, 执行sql，返回执行影响的记录条数
     * 
     * @param sql 执行的sql 语句
     * @param para 可变参数
     * @return
     * 
     */
    public int update(String sql, Object... para)
    {
        try
        {
            Connection connection = DataSourceUtils.getConnection(queryRuner.getDataSource());
            return queryRuner.update(connection, sql, para);
        }
        catch (SQLException e)
        {
            throw new DataException(e.getMessage(), e.getCause());
        }
    }
}
