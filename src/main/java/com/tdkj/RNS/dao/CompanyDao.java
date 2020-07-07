package com.tdkj.RNS.dao;

import com.tdkj.RNS.entity.Company;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Company)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
public interface CompanyDao {

    /**
     * 通过ID查询单条数据
     *
     * @param companyId 主键
     * @return 实例对象
     */
    Company queryById(Integer companyId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Company> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param company 实例对象
     * @return 对象列表
     */
    List<Company> queryAll(Company company);

    /**
     * 新增数据
     *
     * @param company 实例对象
     * @return 影响行数
     */
    int insert(Company company);

    /**
     * 修改数据
     *
     * @param company 实例对象
     * @return 影响行数
     */
    int update(Company company);

    /**
     * 通过主键删除数据
     *
     * @param companyId 主键
     * @return 影响行数
     */
    int deleteById(Integer companyId);
    /**
     * 查询所有公司
     *
     * @param
     * @return 对象列表
     */
    List<Company> queryAllCompany(@Param("companyName")String companyName,@Param("vehicleOfficerName")String vehicleOfficerName);
    /**
     * @Author houxuyang
     * @Description //通过名称查询该公司是否存在
     * @Date 16:29 2020/7/1
     * @Param [companyName]
     * @return com.tdkj.RNS.entity.Company
     **/
    Company queryByName(String companyName);


}