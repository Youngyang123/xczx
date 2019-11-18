package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="cms页面管理接口",description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页 码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录 数",required=true,paramType="path",dataType="int")
    })
    QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    @ApiOperation("添加页面")
    ResponseResult add(CmsPage cmsPage);

    @ApiOperation("删除页面")
    @ApiImplicitParam(name = "pageId", value = "页面id", required = true, paramType = "path")
    ResponseResult delById(String pageId);

    @ApiOperation("修改页面")
    ResponseResult updPage(CmsPage cmsPage);

    @ApiOperation("根据id查找页面")
    @ApiImplicitParam(name = "pageId", value = "页面id", required = true, paramType = "path")
    ResponseResult findOne(String pageId);

}
