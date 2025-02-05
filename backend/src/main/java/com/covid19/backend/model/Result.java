package com.covid19.backend.model;


import com.covid19.backend.config.ErrorEnums;
import com.covid19.backend.utils.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@ApiModel("标准返回结果体")
@Data
@Setter
@Getter
@Component
public class Result<T> {
	@ApiModelProperty("是否成功")
	private Boolean success;
	@ApiModelProperty("返回代码")
	private long code;
	@ApiModelProperty("错误消息")
	private String message;
	@ApiModelProperty("请求ID")
	private String requestid;
//	@ApiModelProperty("Session ID (和 cookie 中相同)")
//	private String sessionid;
	@ApiModelProperty("时间戳")
	private String timestamp;
	@ApiModelProperty("总数信息（仅在分页查询时需要用）")
	private String totalCount;
	@ApiModelProperty("结果数据")
	private T data;

	// 错误代码
	public static final int CODE_SUCCESS = 200;
	public static final int CODE_ERROR = 500;
	public static final int CODE_VALIDATE_FAILED = 405;
	public static final int CODE_NOT_FOUND = 404;
	public static final int CODE_UNAUTHORIZED = 401;
	public static final int CODE_FORBIDDEN = 403;

	public Result() {
		this(200, (String) "ok");
	}


	public Result(long code, String message) {
		this.code = CODE_SUCCESS;
		this.code = code;
		this.message = message;
		this.timestamp = Utils.getISODateTime();
		this.requestid = UUID.randomUUID().toString().replaceAll("-", "");
		this.totalCount = "N/A";
		if(code != 200){
			success = false;
		}
		else{
			success = true;
		}
	}

	public Result(long code, T dataObject, String message) {
		this(code, message);
		this.data = dataObject;
	}

	/**
	 * 可用以下返回格式
	 */


	public static Result error(String message) {
		return new Result(500, message);
	}

	public static Result error(int code, String message) {
		return new Result(code, message);
	}

	public static Result error(int code, String message, Object dataObject) {
		return new Result(code, dataObject, message);
	}
	public static Result ok() {
		return new Result();
	}

	public static Result ok(Object dataObject){
		return new Result(200, dataObject, "ok");
	}

	/**
	 * 带有分页的返回结果
	 * @param dataObject
	 * @param total
	 * @return
	 */
	public static Result pagedOk(Object dataObject, long total){
		Result r = new Result(200, dataObject, "ok");
		r.totalCount = Long.toString(total);
		return r;
	}
	public static Result pagedOk(Page page){
		Result r = new Result(200, page, "ok");
		r.totalCount = Long.toString(page.getTotal());
		return r;
	}
	public T getData(){
		return this.data;
	}

}