package com.validity.takehome.util;

import com.validity.takehome.vo.EmployeeVO;

public class Util {


    public static EmployeeVO buildToVO(String[] strs) {

        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setId(strs[0]);
        employeeVO.setFirstName(strs[1]);
        employeeVO.setLastName(strs[2]);
        employeeVO.setCompany(strs[3]);
        employeeVO.setEmail(strs[4]);
        employeeVO.setAddress1(strs[5]);
        employeeVO.setAddress2(strs[6]);
        employeeVO.setZip(strs[7]);
        employeeVO.setCity(strs[8]);
        employeeVO.setStateLong(strs[9]);
        employeeVO.setState(strs[10]);
        employeeVO.setPhone(strs[11]);

        return employeeVO;
    }

}
