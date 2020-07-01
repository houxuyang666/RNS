package com.tdkj.RNS.service;

import com.tdkj.RNS.entity.Company;
import java.util.List;

/**
 * (Company)表服务接口
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
public interface CompanyService {

    /**
     * 通过ID查询单条数据
     *
     * @param companyId 主键
     * @return 实例对象
     */
    Company queryById(Integer companyId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Company> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    Company insert(Company company);

    /**
     * 修改数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    Company update(Company company);

    /**
     * 通过主键删除数据
     *
     * @param companyId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer companyId);
    /**
     * @Author houxuyang
     * @Description //查询所有公司
     * @Date 10:45 2020/6/30
     * @Param []
     * @return java.util.List<com.tdkj.RNS.entity.Company>
     **/
    List<Company> queryAllCompany();
}