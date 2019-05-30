package com.tyj.craftshow.api;

import com.tyj.craftshow.bean.ProjectDataBean;
import com.tyj.craftshow.http.BaseResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zcm on 2019/3/29.
 * 存放接口
 * 1.http://www.weather.com.cn/data/sk/101010100.html
 */
public interface ApiService {


    //todo 获取角色权限数据信息
    @FormUrlEncoded
    @POST("Login!getRolePermission.action")
    Observable<BaseResponse> queryUserPermissionInfo(@FieldMap Map<String, Object> map);

    //todo 获取登录用户数据信息
    @FormUrlEncoded
    @POST("check")
    Observable<BaseResponse> postLoginInfo(@FieldMap Map<String, Object> map);
    //todo 新增用户数据信息
    @FormUrlEncoded
    @POST("insert")
    Observable<BaseResponse> postInsertInfo(@FieldMap Map<String, Object> map);

    //todo 获取项目库列表数据信息
    @FormUrlEncoded
    @POST("MonProjectAction!getQueryVo.action")
    Observable<BaseResponse<List<ProjectDataBean>>> queryScreenProjectInfo(@FieldMap Map<String, Object> map);
}
