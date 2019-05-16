package com.wisesoft.constant;

public class BigdataConstants {
	public static final String big_method_url="big_method_url";
	public static final String big_path_url="big_path_url";
	public static final String big_data_url="big_data_url";

	public static final String JOB_PARAM_KEY = "ScheduleJobVO";
	public static final String JOB_PARAM_KEY_INFO = "InterfaceInfoVO";
	public static final String JOB_PARAM_KEY_SERVRE = "InterfaceServerVO";
	public static final String protocol_type_http = "http";
	public static final String protocol_type_httpget = "httpget";
	public static final String protocol_type_httppost = "httppost";
	public static final String protocol_type_udp = "udp";
	public static final String protocol_type_tcp = "tcp";
	public static final String protocol_type_db = "db";
	public static final String protocol_type_other = "other";

	/**
	 * Token 参数
	 */
	public static final String OAUTH_TOKEN = "oauth_token";

	/**
	 * Token 参数
	 */
//	public static final String OAUTH_TOKEN_KEY = "oauth_token";
	/**
	 * 用户key
	 */
	public static final String SESSION_KEY = "userKey";
	/**
	 * 秘钥
	 */
	public static final String SECRET_KEY = "sanxia_secret_!_wisesoft_#$_api_login_";

	/**
	 * 用户登录后缓存过期时间(暂定30分钟，在三十分钟内无操作，登录将自动失效)
	 */
	public static final int USER_LOGIN_SESSION_KEY_EXPIRE = 60 * 30;

	/**
	 * 保存用户缓存数据的KEY
	 */
	public static final String USER_LOGIN_SESSION_KEY = "login_session_key_";
	/**
	 * 额外的系统code 【景管通APP cs_code = jgt_app 编码定死，不参与后台系统业务，只是后台系统用于授权】
	 */
	public static final String SYSTEM_EXCESS_JGT_APP_CODE = "jgt_app";
	/**
	 * 保存部门缓存数据的KEY
	 */
	public static final String DEPT_KEY = "DEPT_KEY";
}
