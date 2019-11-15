package p3.shiro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import p3.pojo.Student;
import p3.service.StudentService;
import p3.service.TeacherService;
import p3.vo.UserVo;

/**
 * Student
 * 定义自己的安全策略，安全检验规则，定义什么是安全
 */
public class StudentShiroRealm extends AuthorizingRealm {
    Logger logger= LogManager.getLogger(AuthorizingRealm.class);
    @Autowired
    private StudentService studentService;

    /**
     * 自定义鉴权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*获取用户名*/
        String primaryPrincipal =(String) principalCollection.getPrimaryPrincipal();
        /*根据用户名，查询该用户的角色清单*/

        return null;
    }

    /**
     * 自定义认证方法
     * @param token 从controller中传递过来的检测令牌
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName =(String) token.getPrincipal();
        Student vo = studentService.selectByLoginName(userName);

        if (vo!=null){
            String password = vo.getSPassword();/*从数据库中查询出来的密码*/
            /*将库中的密码与输入的账号组合，连带当前检验策略一同？？？*/
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, password, this.getName());
            logger.info("------------stuRealm------------");
            return authenticationInfo;
        }
        /*认证不通过则返回null*/
        return null;
    }
}
