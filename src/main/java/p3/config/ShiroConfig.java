package p3.config;

import lombok.Builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import p3.shiro.StudentShiroRealm;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    Logger logger= LogManager.getLogger(ShiroConfig.class);


    /**
     * 创建shiro过滤器工厂实例
     * @param webSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Qualifier("securityManager") DefaultWebSecurityManager webSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        /*给安全过滤器匹配相应的安全管理器*/
        shiroFilterFactoryBean.setSecurityManager(webSecurityManager);
        /*设置shiro授权管理链*/
        Map<String,String> map=new HashMap<>();
        map.put("/my","authc");/*此路径只有登录后才能够访问*/
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/login");/*登录页面*/
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");/*认证后，当权限不足时，显示提示页*/
        logger.info("---------shiroFilterFactoryBean--------");
        return shiroFilterFactoryBean;
    }


    /**
     * 创建安全管理器的实例
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("studentRealm")AuthorizingRealm realm){
        /*创建web安全管理器*/
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        /*给安全管理器配置自定义的安全策略*/
        webSecurityManager.setRealm(realm);
        logger.info("----------securityManager--Bean---------------");
        return webSecurityManager;
    }

    /**
     * 获得realm实例
     * @return
     */
    @Bean(value = "studentRealm")
    public AuthorizingRealm getRealm(){
        return new StudentShiroRealm();
    }



    /**
     * 开启Shiro注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
