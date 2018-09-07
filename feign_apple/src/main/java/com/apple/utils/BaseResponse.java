package com.apple.utils;

/**
 * 
 * @title BaseResponse.java
 *
 */
public class BaseResponse<T> {

	// 返回状态吗
	private String code;
	// 返回说明
	private String message;

	// 自定义返回数据
	private T data;

    public BaseResponse() {
		this.code = ResponseCode.SUCCESS.value;
		this.message = ResponseCode.SUCCESS.description;
	}


	public BaseResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}



	public BaseResponse(String code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public BaseResponse(T data) {
		this.code = ResponseCode.SUCCESS.value;
		this.message = ResponseCode.SUCCESS.description;
		this.data =data;
	}

	public BaseResponse(ResponseCode code){
		this.code =code.getValue();
		this.message =code.getDescription();
	}


	public static <T> BaseResponse<T>  ok(T data){
		return new BaseResponse<>(data);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void systemError(){
		this.code = ResponseCode.SYS_ERROR.value;
		this.message = ResponseCode.SYS_ERROR.description;
	}

	public void systemError(String message){
		this.code = ResponseCode.SYS_ERROR.value;
		this.message = message;
	}

	public void paramCheckFail(){
		this.code = ResponseCode.PARAM_CHECK_FAIL.value;
		this.message = ResponseCode.PARAM_CHECK_FAIL.description;
	}


	public boolean success(){
		return this.getCode().equals(ResponseCode.SUCCESS.getValue());
	}

	public boolean fail(){
		return !success();
	}
	public enum ResponseCode{

		SUCCESS("00","success"),

		PARAM_CHECK_FAIL("01","request params check fail"),


		SYS_ERROR("99","system error");


		private String value;
		private String description;

		ResponseCode(String value, String description) {
			this.value = value;
			this.description = description;
		}

		public String getValue() {
			return value;
		}

		public String getDescription() {
			return description;
		}

	}


}
