package com.wangxiaohui.two;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        List<TestVo> testVos = new ArrayList<>();
        TestVo testVo = new TestVo();
        testVo.setId(null);
        testVo.setName(null);
        testVos.add(testVo);
        testVos = testVos.stream().filter(f ->
                !StringUtils.isEmpty(f.getName())).collect(Collectors.toList());
        System.out.println(111);
    }
}
