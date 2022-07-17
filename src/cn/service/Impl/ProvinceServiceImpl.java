package cn.service.Impl;

import cn.dao.Impl.ProvinceDAOImpl;
import cn.dao.ProvinceDAO;
import cn.domain.Province;
import cn.jedis.utils.JedisPoolUtils;
import cn.service.ProvinceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    private ProvinceDAO provinceDAO=new ProvinceDAOImpl();
    @Override
    public List<Province> findAll() {
        return provinceDAO.findAll();
    }


    /*
        使用redis缓存
    */
    @Override
    public String findAllJson() {
        //先从redis查询数据
        //获取redis客户端
        Jedis jedis = JedisPoolUtils.getJedis();
        String province_json = jedis.get("province");
        //判断
        if (province_json==null||province_json.length()==0){
//            System.out.println("从数据库查询");
            //redis中没有数据，从数据库中查询
            List<Province> ps = provinceDAO.findAll();
            //将list序列化为json
            ObjectMapper mapper=new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(ps);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //将json数据存入redis中
            jedis.set("province",province_json);
            //归还连接
            jedis.close();
        }else {
            //有数据查询缓存
//            System.out.println("从缓存查询");
        }
        return province_json;
    }
}
