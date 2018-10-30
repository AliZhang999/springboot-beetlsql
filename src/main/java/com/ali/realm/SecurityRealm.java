package com.ali.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SecurityRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(SecurityRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //String username = (String) principalCollection.getPrimaryPrincipal();
        logger.info("SecurityRealm doGetAuthorizationInfo 用户权限配置");
        List<String> roles = new ArrayList<>();//actvt中的group等同于shiro中的role
        List<String> perms = new ArrayList<>();

        /*String accountId = (String) principalCollection.getPrimaryPrincipal();
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        IdentityService identityService = processEngine.getIdentityService();
        User user = identityService.createUserQuery().userId(accountId).singleResult();
        String userId = user.getId();
        List<Group> groupList = identityService.createGroupQuery().groupMember(userId).list();
        for (Group group : groupList) {
            String groupId = group.getId();
            roles.add(groupId);
        }*/

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(perms);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String accountId = (String) authenticationToken.getPrincipal();

        //调用actvt的API,根据传过来的账号，获取数据库对应的密码，进行比对
        /*ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        IdentityService identityService = processEngine.getIdentityService();
        User user = identityService.createUserQuery().userId(accountId).singleResult();
        String password = user.getPassword();*/

        ByteSource salt = ByteSource.Util.bytes("adminHIOCS");
        //SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(accountId, "123456", salt, getName());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("admin", "e10adc3949ba59abbe56e057f20f883e", getName());
        return authenticationInfo;
    }

    public static void main(String[] args) {
        String algorithmName = "MD5";
        String source = "123456";//e10adc3949ba59abbe56e057f20f883e
        //ByteSource salt = ByteSource.Util.bytes("adminHIOCS");
        //int hashIterations = 7 ;
        //SimpleHash simpleHash = new SimpleHash(algorithmName,source,salt,hashIterations);
        SimpleHash simpleHash = new SimpleHash(algorithmName,source);
        logger.info(simpleHash.toString());
    }
}
