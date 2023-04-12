package org.example;

import org.example.DAO.ILoginDao;
import org.example.service.ILoginMgmt;
import org.example.service.LoginMgmtServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LoginMgmtServiceImplTest {
    private static ILoginMgmt iLoginMgmt;

    private static ILoginDao iLoginDaoMock;

    @BeforeAll
    public static void setUpOnce() {
        //create mock/fake/dummy object
        iLoginDaoMock = Mockito.mock(ILoginDao.class); //mock method generates InMemory class implementing ILoginDao having null method definition for authenticate() method
        iLoginMgmt = new LoginMgmtServiceImpl(iLoginDaoMock);
    }

    @AfterAll
    public static void clearOnce() {
        iLoginDaoMock = null;
        iLoginMgmt = null;
    }

    @Test
    public void testLoginWithValidCredentials() {
        //provide stub(temporary functionalities) for DAO's authenticate method
        Mockito.when(iLoginDaoMock.authenticate("hello", "kxa")).thenReturn(1);
        //Unit testing
        Assertions.assertTrue(iLoginMgmt.login("hello", "kxa"));
    }

    @Test
    public void testLoginWithInValidCredentials() {
        //provide stub(temporary functionalities) for DAO's authenticate method
        Mockito.when(iLoginDaoMock.authenticate("hello", "kxa")).thenReturn(1);
        //Unit testing
        Assertions.assertFalse(iLoginMgmt.login("hello", "ok"));
    }

    @Test
    public void testLoginWithNoCredentials() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> iLoginMgmt.login("", ""));
    }
}
