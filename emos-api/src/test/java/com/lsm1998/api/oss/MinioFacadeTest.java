package com.lsm1998.api.oss;

import org.junit.Before;
import org.junit.Test;

public class MinioFacadeTest
{
    private MinioFacade facade;

    @Test
    public void bucketList() throws Exception
    {
        System.out.println(facade.bucketList());
    }

    @Test
    public void updateFile() throws Exception
    {
        System.out.println(facade.updateFile("system-oa", "test.txt", "D:\\code\\emos\\emos-api\\src\\test\\java\\com\\lsm1998\\api\\oss\\MinioFacadeTest.java"));
    }

    @Test
    public void previewUrl()
    {
        System.out.println(facade.previewUrl("system-oa", "test.txt"));
    }

    @Test
    public void download() throws Exception
    {
        facade.download("system-oa", "test.txt", "D:\\code\\emos\\emos-api\\src\\test\\java\\com\\lsm1998\\api\\oss\\MinioFacadeTest2.java");
    }

    @Before
    public void setup()
    {
        this.facade = new MinioFacade("http://119.91.113.111:9000", "admin", "admin123456");
        this.facade.initMinioClient();
    }
}