package qhybupt.bookmanager.shiro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
 
import javax.servlet.Filter;
 
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import qhybupt.bookmanager.filter.URLPathMatchingFilter;
import qhybupt.bookmanager.realm.DatabaseRealm;
 
@Configuration
public class ShiroConfiguration {
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
 
    /**
     * ShiroFilterFactoryBean ����������Դ�ļ����⡣
     * ע�⣺����һ��ShiroFilterFactoryBean�����ǻ򱨴�ģ���Ϊ��
     * ��ʼ��ShiroFilterFactoryBean��ʱ����Ҫע�룺SecurityManager
     *
     Filter Chain����˵��
     1��һ��URL�������ö��Filter��ʹ�ö��ŷָ�
     2�������ö��������ʱ��ȫ����֤ͨ��������Ϊͨ��
     3�����ֹ�������ָ����������perms��roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
 
        // �������� SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // ���������Ĭ�ϻ��Զ�Ѱ��Web���̸�Ŀ¼�µ�"/login.jsp"ҳ��
        shiroFilterFactoryBean.setLoginUrl("/login");
        // ��¼�ɹ���Ҫ��ת������
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //δ��Ȩ����;
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        //������.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        //�Զ���������
        Map<String, Filter> customisedFilter = new HashMap<>();
        customisedFilter.put("url", getURLPathMatchingFilter());
 
        //����ӳ���ϵ
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/index", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/register", "anon");
//        filterChainDefinitionMap.put("/config/**", "anon");
//        filterChainDefinitionMap.put("/books/[0-9]+/**", "url");
//        filterChainDefinitionMap.put("/books/**", "authc");
        filterChainDefinitionMap.put("/books/**", "authc,url");
        filterChainDefinitionMap.put("/doLogout", "logout");
        filterChainDefinitionMap.put("/**", "url");
        shiroFilterFactoryBean.setFilters(customisedFilter);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
     
    public URLPathMatchingFilter getURLPathMatchingFilter() {
        return new URLPathMatchingFilter();
    }
 
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //����realm.
        securityManager.setRealm(getDatabaseRealm());
        return securityManager;
    }
 
    @Bean
    public DatabaseRealm getDatabaseRealm(){
        DatabaseRealm myShiroRealm = new DatabaseRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }
 
    /**
     * ƾ֤ƥ����
     * ���������ǵ�����У�齻��Shiro��SimpleAuthenticationInfo���д�����
     *  ����������Ҫ�޸���doGetAuthenticationInfo�еĴ���;
     * ��
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
 
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//ɢ���㷨:����ʹ��MD5�㷨;
        hashedCredentialsMatcher.setHashIterations(2);//ɢ�еĴ���������ɢ�����Σ��൱�� md5(md5(""));
 
        return hashedCredentialsMatcher;
    }
 
    /**
     *  ����shiro aopע��֧��.
     *  ʹ�ô���ʽ;������Ҫ��������֧��;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}