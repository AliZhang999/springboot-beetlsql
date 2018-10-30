/**
 * 
 */
package com.ali.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录表单。
 * 
 * @author qqwer
 *
 */
@ApiModel(value = "用户登录表单")
public class LoginModel {

	@ApiModelProperty(value = "登录名", required = true, position = 0)
	private String username;

	@ApiModelProperty(value = "登录密码", required = true, position = 1)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
