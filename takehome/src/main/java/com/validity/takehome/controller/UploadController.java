package com.validity.takehome.controller;


import com.validity.takehome.service.DedupService;

import com.validity.takehome.util.Util;
import com.validity.takehome.vo.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadController {


    @Autowired
    DedupService dedupService;

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/file")
    public String upload(@RequestParam("file") MultipartFile file,
                         HttpSession session) {


        if(file.isEmpty()) {
            return "error";
        }

        try {
            InputStream in = file.getInputStream();
            List<String[]> res =  dedupService.readFile(in);
            List<String[]> duplicates = dedupService.checkDuplicate(res);
            List<String[]> other = new ArrayList<>();

            for(String[] strs : res) {
                if(!duplicates.contains(strs)) {
                    other.add(strs);
                }
            }


            List<EmployeeVO> employeeVOList = new ArrayList<>();

            for(String[] strs : duplicates) {
                employeeVOList.add(Util.buildToVO(strs));
            }

            List<EmployeeVO> otherList = new ArrayList<>();

            for(String[] strs : other) {
                otherList.add(Util.buildToVO(strs));
            }

            session.setAttribute("resData", employeeVOList);
            session.setAttribute("otherData", otherList);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "result";
    }


}
