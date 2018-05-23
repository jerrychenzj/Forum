package com.jin.test;

import com.jin.service.LikeService;
import com.jin.utils.MyUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by JINS on 2018/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:application.xml")
public class TestLikeService {
    @Autowired
    LikeService likeService;

    @Before
    public  void setUp(){
        System.out.println("before");
    }

    @After
    public void tearDown(){
        System.out.println("After");
    }

    @Test
    public void testlike(){
        System.out.println("testlike");
        likeService.like(123,1,1);
        Assert.assertEquals(1,likeService.getLikeStatus(123,1,1));

        System.out.println("testdislike");
        likeService.dislike(123,1,1);
        Assert.assertEquals(-1,likeService.getLikeStatus(123,1,1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testException(){
        System.out.println("测试异常");
        throw new IllegalArgumentException("出现异常了");
    }

    @Test
    public  void testNotHtml(){
        String ss = "系统 ，编辑于 2018-03-14 10:28:28\n" +
                ":#333333;\"> 推荐一本<font style='color:red' >你</font>认为<font style='color:red' >好</font>的技术类书籍_____？ </div> <div style=\"color:#333333;\"> <br/> </div> <h2 style=\"font-weight ";
        System.out.println(MyUtils.stripHtml(ss));
    }
    @BeforeClass
    public static void  beforeClass(){
        System.out.println("beforeClass");
    }

    @AfterClass
    public static  void afterClass(){
        System.out.println("AfterClass");
    }
}
