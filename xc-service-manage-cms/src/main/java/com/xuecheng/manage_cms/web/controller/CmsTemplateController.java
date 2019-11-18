package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.api.cms.CmsSiteControllerApi;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.service.CmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("template")
public class CmsTemplateController implements CmsSiteControllerApi {

    @Autowired
    private CmsTemplateService cmsTemplateService;
    @Override
    @GetMapping("all")
    public QueryResponseResult findAll() {
        return cmsTemplateService.findAll();
    }
}
