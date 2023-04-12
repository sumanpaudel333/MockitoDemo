package org.example;

import org.example.DAO.ILoginDao;
import org.example.service.ILoginMgmt;
import org.example.service.LoginMgmtServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MockVsSpyTest {
    @Test
    public void mockVsSpy() {
        List<String> stringList = Mockito.mock(ArrayList.class); // Mock
        List<String> stringList1 = Mockito.spy(new ArrayList<>()); // Spy
        stringList1.add("Spy");
        stringList.add("Mock");
        Mockito.when(stringList.size()).thenReturn(10); // Stub in mock object
        Mockito.when(stringList1.size()).thenReturn(10); // Stub in spy object
        System.out.println(stringList.size() + " " + stringList1.size());
    }
    @Test
    public void testRegisterWithSpy(){
        ILoginDao iLoginDaoSpy=Mockito.spy(ILoginDao.class);
        LoginMgmtServiceImpl iLoginMgmtSpy=new LoginMgmtServiceImpl(iLoginDaoSpy);
        iLoginMgmtSpy.registerUser("Ram","Hello");
        iLoginMgmtSpy.registerUser("hari","HelloWorld");
        iLoginMgmtSpy.registerUser("shyam","kxa");
        Mockito.verify(iLoginDaoSpy,Mockito.times(1)).addUser("Ram","Hello");
        Mockito.verify(iLoginDaoSpy,Mockito.times(0)).addUser("shyam","Hello");
        Mockito.verify(iLoginDaoSpy,Mockito.times(1)).addUser("shyam","kxa");
    }
}
