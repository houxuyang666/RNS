package com.tdkj.RNS.service.impl;

import com.tdkj.RNS.entity.Company;
import com.tdkj.RNS.dao.CompanyDao;
import com.tdkj.RNS.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Company)表服务实现类
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
    @Resource
    private CompanyDao companyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param companyId 主键
     * @return 实例对象
     */
    @Override
    public Company queryById(Integer companyId) {
        return this.companyDao.queryById(companyId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Company> queryAllByLimit(int offset, int limit) {
        return this.companyDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    @Override
    public Company insert(Company company) {
        this.companyDao.insert(company);
        return company;
    }

    /**
     * 修改数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    @Override
    public Company update(Company company) {
        this.companyDao.update(company);
        return this.queryById(company.getCompanyId());
    }

    /**
     * 通过主键删除数据
     *
     * @param companyId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer companyId) {
        return this.companyDao.deleteById(companyId) > 0;
    }

    @Override
    public List<Company> queryAllCompany() {
        return companyDao.queryAllCompany();
    }

    @Override
    public Company queryByName(String companyName) {
        return companyDao.queryByName(companyName);
    }
}