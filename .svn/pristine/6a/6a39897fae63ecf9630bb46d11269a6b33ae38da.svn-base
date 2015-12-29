/**
 * ============================================================================
 * 业务分类: 用户登录
 * ----------------------------------------------------------------------------
 * 机能   :  LDAP验证
 * 所属   :  神州数码集团_信息化管理部
 * 作者   :  DC张磊
 * 做成日 : 2009.08.26
 * ----------------------------------------------------------------------------
 * 修正履历:  2009.08.29 初版做成  Ver1.00
 * ============================================================================
 */
package com.dc.flamingo.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;



/**
 * <p>
 * <dl>
 * <dt> <b>概要:</b>
 * <dd> LDAP验证模块<br>
 * <ul> 目前DCONE提供了WebService的方式进行LDAP验证
 * <li> 登录验证</li>
 * </ul>
 * </dd>
 * </dt>
 * </dl>
 * </p>
 */
public class LdapUtils {
	private static LogUtils log = LogUtils.getLogUtil(LdapUtils.class);
    // 资源文件KEY:Ldap url
    public static final String PROPERTY_LDAP_URL = "ldap.url";
    // 资源文件KEY:Ldap base名
    public static final String PROPERTY_LDAP_BASEDN = "ldap.basedn";
    // 资源文件KEY:Ldap 工厂名
    public static final String PROPERTY_LDAP_FACTORY = "ldap.factory";
    // 资源文件KEY:Ldap 检索方式
    public static final String PROPERTY_LDAP_MODE = "ldap.mode";
    // 资源文件KEY:Ldap 安全等级
    public static final String PROPERTY_LDAP_DEFAULT_LEVEL = "ldap.level";
    // 资源文件KEY:Ldap 默认用户名
    public static final String PROPERTY_LDAP_DEFAULT_USER = "ldap.defaultuser";
    // 资源文件KEY:Ldap 默认用户密码
    public static final String PROPERTY_LDAP_DEFAULT_PASSWORD = "ldap.defaultpassword";
    // DirContext环境
    private static LdapContext ctx = null;
    // 不使用任何连接请求控件
    private static Control[] connCtls = null;
    // 人员信息列表
//    private static String[] PERSON_ATTRIBUTE_LIST = { "dcStatus", "uid", "cn",
//            "sn", "employeeNumber", "departmentNumber", "ou", "givenname",
//            "dcGender", "homePostalAddress", "homePhone", "telePhoneNumber",
//            "facsimileTelephoneNumber", "mobile", "mail", "dcOrder", "Title",
//            "dcCompanyCode", "employeeType", "dcBusinessCategoryCode",
//            "businessCategory", "dcLocationCode", "L", "dcJoinDate",
//            "dcFormalDate", "dcFlatName", "dcPersonalAreaCode", "dcTitleCode",
//            "dcPersonalSubAreaCode", "dcPersonalSubArea", "dcCostCenterCode",
//            "dcCostCenterName", "dcPosition", "dcPositionCode" };
//    // 部门信息列表
//    private static String[] DEPT_ATTRIBUTE_LIST = { "dcStatus", "ou",
//            "dcSupervisoryDepartment", "name", "displayName", "dcOrder",
//            "dcManagerNumber", "dcGroupCostCenter" };

    /**
     * <p>
     * <dl>
     * <dt> <b>方法概要:</b>
     * <dd>名称:与LDAP服务器进行连接<br>
     * </dd>
     * </dt>
     * </dl>
     * </p>
     * @return True:LDAP服务器连接成功 False:服务器连接失败
     */
    private static boolean LDAP_connect() {
        // 返回值初始化
        boolean isConnect = false;
        
        // 读取配置文件
        String strUrl = PropertiesUtils.getProperty(PROPERTY_LDAP_URL);
        String strBaseDn = PropertiesUtils.getProperty(PROPERTY_LDAP_BASEDN);
        String strFactory = PropertiesUtils.getProperty(PROPERTY_LDAP_FACTORY);
        String strMode = PropertiesUtils.getProperty(PROPERTY_LDAP_MODE);
        String strUser = PropertiesUtils.getProperty(PROPERTY_LDAP_DEFAULT_USER);
        String strPassword = PropertiesUtils.getProperty(PROPERTY_LDAP_DEFAULT_PASSWORD);
        
        // 创建初始 DirContext 的环境
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, strFactory);
        env.put(Context.PROVIDER_URL, strUrl + strBaseDn);
        env.put(Context.SECURITY_AUTHENTICATION, strMode);
        env.put(Context.SECURITY_PRINCIPAL, strUser);
        env.put(Context.SECURITY_CREDENTIALS, strPassword);
        try {
            // 创建初始 DirContext 
            ctx = new InitialLdapContext(env, connCtls);
            // 连接LDAP服务器正常
            isConnect = true;
        } catch (AuthenticationException e) {
            // 连接LDAP服务器异常
        } catch (NamingException e) {
            // 连接LDAP服务器异常
        }
        // 方法结束
        return isConnect;
    }

    /**
     * <p>
     * <dl>
     * <dt> <b>方法概要:</b>
     * <dd>名称:对输入的用户名及密码进行校验<br>
     * </dd>
     * </dt>
     * </dl>
     * </p>
     * @param String itCode 登录用户名
     * @param String password 登录密码
     * @return True:认证成功 False:输入错误
     */
    public static boolean chkLogin(String itCode, String password) {
        // 返回值初始化
        boolean valide = false;
        // 用户DN取得
        String userDN = getUserDN(itCode);
        try {
            // 添加环境属性
            ctx.addToEnvironment(Context.SECURITY_PRINCIPAL, userDN);
            ctx.addToEnvironment(Context.SECURITY_CREDENTIALS, password);
            ctx.reconnect(connCtls);
            // 匿名用户判断
            if (!"".equals(userDN)) {
                // 用户认证成功
                valide = true;
            }
        } catch (AuthenticationException e) {
            // 用户认证失败
        } catch (NamingException e) {
            // 用户认证失败
        } catch (Exception e) {
            // 用户认证失败
        }
        // 用户认证结束结果返回
        return valide;
    }
    
    /**
     * <p>
     * <dl>
     * <dt> <b>方法概要:</b>
     * <dd>名称:取得指定用户的DB<br>
     * </dd>
     * </dt>
     * </dl>
     * </p>
     * @param String name 登录用户名
     * @return 登录用户名的DN串
     */
    public static String getUserDN(String name) {
        // 返回值初始化
        StringBuffer userDN = new StringBuffer();
        
        // 读取配置文件       
        String strBaseDn = PropertiesUtils.getProperty(PROPERTY_LDAP_BASEDN);
        
        // LDAP服务器连接
        if (LDAP_connect()) {
            try {
                // 搜索结果初始化
                SearchControls constraints = new SearchControls();
                // 指定搜索范围:整棵树
                constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
                // 按过滤器进行搜索
                NamingEnumeration<SearchResult> en = ctx.search("", "uid=" + name,
                        constraints);

                // 组合登录用户名的DN串
                while (en != null && en.hasMoreElements()) {
                    Object obj = en.nextElement();
                    if (obj instanceof SearchResult) {
                        SearchResult si = (SearchResult) obj;
                        userDN.append(si.getName());
                        userDN.append(",");
                        userDN.append(strBaseDn);
                    }
                }
            } catch (Exception e) {
                // LDAP用户搜索异常
            }
        }

        return userDN.toString();
    }

    /**
     * <p>
     * <dl>
     * <dt> <b>方法概要:</b>
     * <dd>名称:根据条件从企业目录中搜索数据，返回结果<br>
     * </dd>
     * </dt>
     * </dl>
     * </p>
     * @param String baseSearch 要搜索的目录
     * @param String filter 搜索条件
     * @param String[] attributelist 要属性列表
     * @return NamingEnumeration 返回结果列表
     */
    private static NamingEnumeration<SearchResult> searchWithFilter(
            String baseSearch, String filter, String[] attributelist) {
        // 返回结果初始化
        NamingEnumeration<SearchResult> results = null;
        DirContext ctx = null;
        try {
            // 读取配置文件
            String strFactory = PropertiesUtils.getProperty(PROPERTY_LDAP_FACTORY);
            String strUrl = PropertiesUtils.getProperty(PROPERTY_LDAP_URL);
            String strMode = PropertiesUtils.getProperty(PROPERTY_LDAP_MODE);
            String strUser = PropertiesUtils.getProperty(PROPERTY_LDAP_DEFAULT_USER);
            String strPassword = PropertiesUtils.getProperty(PROPERTY_LDAP_DEFAULT_PASSWORD);

            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, strFactory);
            env.put(Context.PROVIDER_URL, strUrl);
            env.put(Context.SECURITY_AUTHENTICATION, strMode);
            env.put(Context.SECURITY_PRINCIPAL, strUser);
            env.put(Context.SECURITY_CREDENTIALS, strPassword);

            // 取得连接
            ctx = new InitialLdapContext(env, null);
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

            // 取得结果集
            results = ctx.search(baseSearch, filter, constraints);

            
            while(results != null && results.hasMore()) {
				SearchResult sr = (SearchResult) results.next();
				String dn = sr.getName() + "," + baseSearch;
				Attributes ar = ctx.getAttributes(dn, attributelist);
				if (ar == null) {
					log.debug("对应的uid没有多余的属性");
				}
				else{
					Hashtable<String, Object> obj = new Hashtable<String, Object>();
					for(int i=0;i<attributelist.length;i++){
						Attribute attr = ar.get(attributelist[i]);
						if (attr != null){ 
							obj.put(attributelist[i],attr.get());
						}
					}
				}
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ctx)
                    ctx.close();
            } catch (Exception e2) {
            }
        }
        return results;
    }
    
    /**
     * 根据条件搜索ladp并返回list
     * @Methods Name getSearchWithFilter
     * @Create In Jun 20, 2011 By lee
     * @param baseSearch
     * @param filter
     * @param attributelist
     * @return List<Hashtable<String,Object>>
     */
    public static List<Hashtable<String, Object>> getSearchWithFilter(
            String baseSearch, String filter, String[] attributelist) {
        // 返回结果初始化
    	List<Hashtable<String, Object>> list = new ArrayList<Hashtable<String, Object>>();
        NamingEnumeration<SearchResult> results = null;
        DirContext ctx = null;
        try {
            // 读取配置文件
            String strFactory = PropertiesUtils.getProperty(PROPERTY_LDAP_FACTORY);
            String strUrl = PropertiesUtils.getProperty(PROPERTY_LDAP_URL);
            String strMode = PropertiesUtils.getProperty(PROPERTY_LDAP_MODE);
            String strUser = PropertiesUtils.getProperty(PROPERTY_LDAP_DEFAULT_USER);
            String strPassword = PropertiesUtils.getProperty(PROPERTY_LDAP_DEFAULT_PASSWORD);

            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, strFactory);
            env.put(Context.PROVIDER_URL, strUrl);
            env.put(Context.SECURITY_AUTHENTICATION, strMode);
            env.put(Context.SECURITY_PRINCIPAL, strUser);
            env.put(Context.SECURITY_CREDENTIALS, strPassword);

            // 取得连接
            ctx = new InitialLdapContext(env, null);
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

            // 取得结果集
            results = ctx.search(baseSearch, filter, constraints);

            
            while(results != null && results.hasMore()) {
				SearchResult sr = (SearchResult) results.next();
				String dn = sr.getName() + "," + baseSearch;
				Attributes ar = ctx.getAttributes(dn, attributelist);
				if (ar == null) {
					log.debug("对应的uid没有多余的属性");
				}
				else{
					Hashtable<String, Object> obj = new Hashtable<String, Object>();
					for(int i=0;i<attributelist.length;i++){
						Attribute attr = ar.get(attributelist[i]);
						if (attr != null){ 
							obj.put(attributelist[i],attr.get());
						}
					}
					list.add(obj);
				}
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ctx)
                    ctx.close();
            } catch (Exception e2) {
            }
        }
        return list;
    }
}