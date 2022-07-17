package cn.dao.Impl;

import cn.dao.ProvinceDAO;
import cn.domain.Province;
import cn.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProvinceDAOImpl implements ProvinceDAO {
    //声明成员变量jdbctemplate
    private JdbcTemplate template=new JdbcTemplate(JDBCUtil.getDs());
    @Override
    public List<Province> findAll() {
        //定义sql
        String sql="select *from province";
        List<Province> provinces = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return provinces;
    }
}
