package cn.service;

import cn.domain.Province;

import java.util.List;

public interface ProvinceService {
    List<Province> findAll();
    String findAllJson();
}
