package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    private CmsPageService cmsPageService;

    @Override
    @GetMapping("/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {
        return cmsPageService.findList(page, size, queryPageRequest);
    }

    @Override
    @PostMapping
    public ResponseResult add(@RequestBody CmsPage cmsPage) {
        return cmsPageService.add(cmsPage);
    }

    @Override
    @DeleteMapping("{pageId}")
    public ResponseResult delById(@PathVariable String pageId) {
        return cmsPageService.delById(pageId);
    }

    @Override
    @PutMapping
    public ResponseResult updPage(@RequestBody CmsPage cmsPage) {
        return cmsPageService.updPage(cmsPage);
    }

    @Override
    @GetMapping("{pageId}")
    public ResponseResult findOne(@PathVariable String pageId) {
        return cmsPageService.getOne(pageId);
    }

}
