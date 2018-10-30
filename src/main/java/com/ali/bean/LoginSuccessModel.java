/**
 * 
 */
package com.ali.bean;


/**
 * 登录成功。
 * 
 * @author qqwer
 *
 */
public class LoginSuccessModel {

	private String token;

	private String uuid;

	private String name;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
