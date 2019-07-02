package cn.edu.nju.o2o.dao;

import cn.edu.nju.o2o.BaseTest;
import cn.edu.nju.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;
    @Test
    public void testQueryArea(){
        List<Area> areaList = areaDao.queryArea();
        //assertEquals(2,areaList.size());
        //Area area = new Area();
        System.out.println(areaList.size());

    }
}
