package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CmsPageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
        if (queryPageRequest == null) {
            queryPageRequest = new QueryPageRequest();
        }
        if (page <= 0) {
            page = 1;
        }
        page = page - 1; //为了适应mongodb的接口将页码减1
        if (size <= 0) {
            size = 20;
        }
        CmsPage cmsPage = new CmsPage();
        if(!StringUtils.isEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }

        if(!StringUtils.isEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        //
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());

        Pageable pageable = PageRequest.of(page, size);
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);

        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        QueryResult<CmsPage> queryResult = new QueryResult<>();
        queryResult.setList(all.getContent());
        queryResult.setTotal(all.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    public ResponseResult add(CmsPage cmsPage) {
        System.out.println(cmsPage);
        CmsPage cmsPageIsExist = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(
                cmsPage.getPageName(),
                cmsPage.getSiteId(),
                cmsPage.getPageWebPath()
        );
        // 页面存在
        if (cmsPageIsExist != null) {
            return new CmsPageResult(CmsCode.CMS_ADDPAGE_EXISTSNAME, null);
        }
        cmsPage.setPageCreateTime(new Date());
        cmsPageRepository.save(cmsPage);
        return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
    }

    public ResponseResult updPage(CmsPage cmsPage) {
        CmsPage save = cmsPageRepository.save(cmsPage);
        if (save == null) {
            return new CmsPageResult(CommonCode.FAIL, null);
        }
        return new CmsPageResult(CommonCode.SUCCESS, save);
    }

    public ResponseResult delById(String pageId) {
        Optional<CmsPage> one = cmsPageRepository.findById(pageId);
        if (one.isPresent()) {
            return new CmsPageResult(CommonCode.FAIL, null);
        }
        cmsPageRepository.deleteById(pageId);
        return ResponseResult.SUCCESS();
    }

    public ResponseResult getOne(String pageId) {
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if(optional.isPresent()) {
            return new CmsPageResult(CommonCode.SUCCESS, optional.get());
        }
        return new CmsPageResult(CommonCode.FAIL, null);
    }
}
