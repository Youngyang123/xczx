package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {

    @Autowired
    CmsPageRepository cmsPageRepository;

    //分页测试
    @Test
    public void testFindPage() {
        Pageable pageable = PageRequest.of(1, 10);
        Page<CmsPage> page = cmsPageRepository.findAll(pageable);
        long totalElements = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<CmsPage> cmsPages = page.getContent();
        System.out.println("totalElements==============" + totalElements);
        System.out.println("totalPages============" + totalPages);
        System.out.println("cmsPages================" + cmsPages);
    }

    @Test
    public void testFindOne() {
        CmsPage cmsPage = null;
        Optional<CmsPage> optional = cmsPageRepository.findById("5a754adf6abb500ad05688d9");
        if (optional.isPresent()) {
            cmsPage = optional.get();
        }
        System.out.println(cmsPage);
    }

}