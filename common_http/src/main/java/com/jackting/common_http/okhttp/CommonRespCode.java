package com.jackting.common_http.okhttp;

/**
 * Description:
 * Company:
 * Email:
 * Created by Devin Sun on 2017/4/2.
 */
public class CommonRespCode {

    public static final int DEFAULT_ERROR_CODE = -1024;
    public static final String DEFAULT_ERROR_MSG = "网络异常，请重试";


    public static final int HTTP_RESP_SUCCESS_CODE = 200;
    public static final String HTTP_RESP_SUCCESS_MSG = "请求成功";


    public static final int BAD_REQUEST_RESPONSE_CODE = 400;
    public static final String BAD_REQUEST_RESPONSE_MSG = "参数错误";

    public static final int UNAUTHORIZED_RESPONSE_CODE = 401;
    public static final String UNAUTHORIZED_RESPONSE_MSG = "用户权限非法";

    public static final int FORBIDDEN_RESPONSE_CODE = 403;
    public static final String FORBIDDEN_RESPONSE_MSG = "禁止访问";

    public static final int NOT_FOUND_RESPONSE_CODE = 404;
    public static final String NOT_FOUND_RESPONSE_MSG = "地址未找到";

    public static final int METHOD_NOT_ALLOWED_RESPONSE_CODE = 405;
    public static final String METHOD_NOT_ALLOWED_RESPONSE_MSG = "请求方式错误";

    public static final int REQUEST_TIMEOUT_RESPONSE_CODE = 408;
    public static final String EQUEST_TIMEOUT_RESPONSE_MSG = "请求超时";

    public static final int INTERNAL_SERVER_ERROR_RESPONSE_CODE = 500;
    public static final String INTERNAL_SERVER_ERROR_RESPONSE_MSG = "内部服务器错误";

    public static final int BAD_GATEWAY_RESPONSE_CODE = 502;
    public static final String BAD_GATEWAY_RESPONSE_MSG = "网关错误";

    public static final int SERVICE_UNAVAILABLE_RESPONSE_CODE = 503;
    public static final String SERVICE_UNAVAILABLE_RESPONSE_MSG = "临时的服务器维护";

    public static final int GATEWAY_TIMEOUT_RESPONSE_CODE = 504;
    public static final String GATEWAY_TIMEOUT_RESPONSE_MSG = "网关超时";

}
