package com.tdkj.RNS.dao;

import com.tdkj.RNS.entity.User;
import com.tdkj.RNS.entity.UserinfoVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 账户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-04 15:03:18
 */
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param User 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User User);

    /**
     * 新增数据
     *
     * @param User 实例对象
     * @return 影响行数
     */
    int insert(User User);

    /**
     * 修改数据
     *
     * @param User 实例对象
     * @return 影响行数
     */
    int update(User User);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * @Author houxuyang
     * @Description //查询用户是否存在
     * @Date 15:04 2020/6/4
     * @Param [username]
     * @return com.tdkj.RNS.entity.User
     **/
    User findByName(String username);

    /**
     * @Author houxuyang
     * @Description //查询所有用户
     * @Date 15:13 2020/6/16
     * @Param []
     * @return java.util.List<com.tdkj.RNS.entity.User>
     **/
    List<User> selectUser();

    List<UserinfoVO> selectUserUserinfo();
}