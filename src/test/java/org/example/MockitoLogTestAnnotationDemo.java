package org.example;

import org.example.DAO.ILoginDao;
import org.example.service.LoginMgmtServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class MockitoLogTestAnnotationDemo{
    @InjectMocks
    private static LoginMgmtServiceImpl iLoginMgmt;
    @Mock
    private static ILoginDao iLoginDaoMock;
   /* @Spy
    private static ILoginDao iLoginDaoSpy;*/
    // Ambiguity may occur using @mock and @spy at same time for ILoginDao as both mock object and spy object are ready to inject the service class
    // Therefore we create its instance inside the particular method in which spy is used.
    public MockitoLogTestAnnotationDemo() {
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
        Mockito.doReturn(iLoginDaoMock).when(iLoginDaoMock.authenticate("hello","world"));
        //Unit testing
        Assertions.assertFalse(iLoginMgmt.login("hello", "world"));
    }

    @Test
    public void testLoginWithNoCredentials() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> iLoginMgmt.login("", ""));
    }

    @Test
    public void testRegisterWithSpy() {
        ILoginDao iLoginDaoSpy=Mockito.spy(ILoginDao.class);
        LoginMgmtServiceImpl iLoginMgmtSpy = new LoginMgmtServiceImpl(iLoginDaoSpy);
        iLoginMgmtSpy.registerUser("Ram", "Hello");
        iLoginMgmtSpy.registerUser("hari", "HelloWorld");
        iLoginMgmtSpy.registerUser("shyam", "kxa");
        Mockito.verify(iLoginDaoSpy, Mockito.times(1)).addUser("Ram", "Hello");
        Mockito.verify(iLoginDaoSpy, Mockito.times(0)).addUser("shyam", "Hello");
        Mockito.verify(iLoginDaoSpy, Mockito.times(1)).addUser("shyam", "kxa");
    }
}
