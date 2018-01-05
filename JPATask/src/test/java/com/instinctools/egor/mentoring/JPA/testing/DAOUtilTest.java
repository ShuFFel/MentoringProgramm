package com.instinctools.egor.mentoring.JPA.testing;

import com.instinctools.egor.mentoring.JPA.DAO.DAOUtil.DAOUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;

public class DAOUtilTest {

    @Test
    public void entityManagerFactoryTest(){
        EntityManagerFactory factory = DAOUtil.getSessionFactory();
        Assert.assertNotNull(factory);
    }
}
