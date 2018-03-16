package com.jin.test;

import com.jin.service.LikeService;
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

    @BeforeClass
    public static void  beforeClass(){
        System.out.println("beforeClass");
    }

    @AfterClass
    public static  void afterClass(){
        System.out.println("AfterClass");
    }
}
