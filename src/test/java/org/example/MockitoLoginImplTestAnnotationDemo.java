package org.example;

import org.example.DAO.ILoginDao;
import org.example.service.LoginMgmtServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class MockitoLoginImplTestAnnotationDemo {
    @InjectMocks
    private static LoginMgmtServiceImpl iLoginMgmt;
    @Mock
    private static ILoginDao iLoginDaoMock;
    @Spy
    private static ILoginDao iLoginDaoSpy;

    public MockitoLoginImplTestAnnotationDemo() {
        MockitoAnnotations.openMocks(this);
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
        Mockito.when(iLoginDaoMock.authenticate("hello", "world")).thenReturn(0);
        //Unit testing
        Assertions.assertFalse(iLoginMgmt.login("hello", "world"));
    }

    @Test
    public void testLoginWithNoCredentials() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> iLoginMgmt.login("", ""));
    }

    @Test
    public void testRegisterWithSpy() {
        LoginMgmtServiceImpl iLoginMgmtSpy = new LoginMgmtServiceImpl(iLoginDaoSpy);
        iLoginMgmtSpy.registerUser("Ram", "Hello");
        iLoginMgmtSpy.registerUser("hari", "HelloWorld");
        iLoginMgmtSpy.registerUser("shyam", "kxa");
        Mockito.verify(iLoginDaoSpy, Mockito.times(1)).addUser("Ram", "Hello");
        Mockito.verify(iLoginDaoSpy, Mockito.times(0)).addUser("shyam", "Hello");
        Mockito.verify(iLoginDaoSpy, Mockito.times(1)).addUser("shyam", "kxa");
    }
}
