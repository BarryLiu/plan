package com.project.plan.common;

public class Constats {
	public static String APPLICATION_RESOURCES = "APPLICATION_RESOURCES";//application域程序内存中的权限表,基本不变,再程序添加权限的时候清空application中此值重新查询

	public static String CURRENTUSER = "_currentUser";//登录用户session保存key



	public static final String DEFAULT_USER_PWD = "332211";//ldap用户默认统一设置密码, 现在登录更具ldap域登录,登录成功表示账号是对的, 系统再以这个这个登录密码登录, 如ldap密码登录不成功,系统密码成功也能登录
	public static final String DEFAULT_USER_ADD_PWD = "111111";


}
