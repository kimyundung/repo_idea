package com.lagou.service;

import com.lagou.domain.Test;

import java.util.List;

public interface TestService {
    // test表 查询所有
    public List<Test> findAllTest();
}
