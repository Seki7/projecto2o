package cn.edu.nju.o2o.service;

import cn.edu.nju.o2o.BaseTest;
import cn.edu.nju.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;
    @Test
    public void testGetAreaList(){
        List<Area> areaList = areaService.getAreaList();
        assertEquals("西市",areaList.get(0).getAreaName());
    }

}
