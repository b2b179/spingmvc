package com.zpark.michael.util;
/**
 * 
 * @author MichaelY
 *	系统中要用到的常量
 */
public interface IConstants {
	/**
	 * 设置默认页面大小
	 */
	int DEFAULT_PAGE_SIZE = 2;
	/**
	 * 新增纪录出现异常信息
	 * error:更新信息出错，请重新提交
	 */
	 String SAVE_ERROR="保存信息出错，请重新提交";
	/**
	 * 更新纪录出现异常信息
	 * error:保存信息出错，请重新提交
	 */
	 String UPDATE_ERROR="更新信息出错，请重新提交";
	/**
	 * 更新纪录出现异常信息
	 * error:删除信息出错
	 */
	 String DELETE_ERROR="删除信息出错，请重新提交";
	/**
	 * 服务器出现异常，请稍后访问
	 * error:服务器出现异常，请稍后访问
	 */
	 String SERVICE_ERROR="服务器出现异常，请稍后访问";
	/**
	 * 不合理操作，请勿恶意操作
	 * error:不合理操作，请勿恶意操作
	 */
	 String OPERATOR_ERROR="不合理操作，请勿恶意操作";
	/**
	 * 异常信息，请联系管理员
	 * error:异常信息，请联系管理员
	 */
	 String COMMON_ERROR="异常信息，请联系管理员";
	/**
	 * 输入信息不合理，请重新检查
	 * error:输入信息不合理，请重新检查
	 */
	 String BAD_DATA_ERROR="输入信息不合理，请重新检查";
	/**
	 * 没有此请求
	 */
	 String NO_COMMAND_ERROR = "请求参数不合理，请重新检查";
	/**
	 * 登入失败，请检查用户名和密码
	 * LOGIN_FAILD 登入失败，请检查用户名和密码
	 */
	 String LOGIN_FAILD ="登入失败，请检查用户名和密码";
	/**
	 * 异常信息调用标志
	 */
	 String ERROR_MESS ="error";
	/**
	 * 加密KEY
	 */
	int ENCRYPTKEY =0xFF;
	/**
	 * 页面打印的普通消息
	 */
	String PAGE_MESSAGE ="page_message";
	/**
	 * 退出成功
	 */
	String LOGOUT_SUCCESS ="退出成功，欢迎再次使用";
	/**
	 * 您还未登陆，请登入后进行操作
	 */
	String NO_LOGIN = "您还未登陆，请登入后进行操作";
	/**
	 * 默认要展示的页面总数为五页
	 */
	int DEFAULT_SHOW_PAGE=5;
	/**
	 * 当前页的页面属性名称
	 */
	String CURRENT_PAGE = "currentPage";
	/**
	 * 页面传入的页面大小 属性名称
	 */
	String CURRENT_PAGE_SIZE = "pageSize";
	/**
	 * 编码引用名称
	 */
	String DEFAULT_ENCODING = "encoding";
	/**
	 * 当前用户在Session 中保存的引用变量
	 */
	String CURRENT_USER = "CURRENT_USER";
	/**
	 * 页面统一使用 FRONT_PAGE_NAME 存放在 request.setAttribute()
	 */
	String FRONT_PAGE_NAME ="page";
	/**
	 * 当前用户没有此权限异常
	 */
	String NO_RIGHT_ERROR = "您没有执行此操作的权限...";
	/**
	 * 返回页面结果 成功
	 */
	String RESULT_SUCCESS = "success";
	/**
	 * 返回页面结果 失败
	 */
	String RESULT_FAILED = "failed";
	/**
	 * 返回页面结果 失败
	 */
	String RESULT_ERROR = "error";
	/**
	 * 存放在session中的验证码名称
	 */
	String SECURITY_CODE ="securityCode";
	/**
	 * struts.xml中只要是[back_]开头的actionName都要经过登陆验证
	 */
	String BACK_CK_ACTION="back_";
	/**
	 * struts.xml中只要是[front_]开头的actionName无需登陆验证 表示前端请求
	 */
	String FRONT_ACTION="front_";
	/**
	 * 上传文件的文件夹地址
	 */
	String UPLOAD_DIRECTORY="upload";
}
