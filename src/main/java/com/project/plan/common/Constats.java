package com.project.plan.common;

public interface Constats {
	
	public String CURRENTUSER = "_currentUser";

	public static final String DEFAULT_USER_PWD = "332211";//ldap用户默认统一设置密码, 现在登录更具ldap域登录,登录成功表示账号是对的, 系统再以这个这个登录密码登录, 如ldap密码登录不成功,系统密码成功也能登录



}
