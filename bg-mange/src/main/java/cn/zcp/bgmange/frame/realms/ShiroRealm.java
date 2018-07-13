package cn.zcp.bgmange.frame.realms;


import cn.zcp.bgmange.base.constant.BaseConstant;
import cn.zcp.bgmange.base.utils.BaseUtils;
import cn.zcp.bgmange.frame.dao.IAdminDao;
import cn.zcp.bgmange.frame.dao.ISysRoleDao;
import cn.zcp.bgmange.frame.pojo.AdminCustom;
import cn.zcp.bgmange.frame.pojo.AdminQueryVo;
import cn.zcp.bgmange.frame.pojo.AdminRole;
import cn.zcp.bgmange.frame.pojo.SysMenuSysRole;
import cn.zcp.bgmange.frame.utils.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/11/28.
 */
public class ShiroRealm extends AuthorizingRealm {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private IAdminDao adminDao;
    @Autowired
    private ISysRoleDao sysRoleDao;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        logger.info("***********身份认证************");

        //1. 把 AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        //2. 从 UsernamePasswordToken 中来获取 username
        String username = upToken.getUsername();

        AdminQueryVo adminQueryVo = new AdminQueryVo();
        AdminCustom adminCustom = new AdminCustom();
        adminQueryVo.setAdminCustom(adminCustom);
        adminCustom.setAccount(username);
        adminQueryVo.setAdminCustom(adminCustom);

        List<AdminCustom> admins = adminDao.queryObjs(adminQueryVo);

        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        logger.info("从数据库中获取 username: " + username + " 所对应的用户信息.");


        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if(BaseUtils.collectionIsEmpty(admins)) {
            throw new UnknownAccountException("用户不存在!");
        }

//    }catch (UnknownAccountException uae) {
//
//    }catch (LockedAccountException lae)  {
//        throw new BaseException(BaseEnum.LOGIN_ADMIN_NO_USE);
//    }catch (AuthenticationException ae) { // 所有认证时异常的父类.
//        throw new BaseException(BaseEnum.LOGIN_FAIL);
//    }


//		if("unknown".equals(username)){
//			throw new UnknownAccountException("用户不存在!");
//		}


        AdminCustom ac = admins.get(0);

        //5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常.



        //6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        //以下信息是从数据库中获取的.
        //1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
        Object principal = ac;
        //2). credentials: 密码.
        Object credentials = ac.getPassword(); //"fc1709d0a95a6be30bc5926fdb7f22f4";
        if(!credentials.equals(ShiroUtils.getMd5Pwd(null,new String(upToken.getPassword())))) {
            throw new IncorrectCredentialsException("密码错误");
        }

        if(BaseConstant.USE_STATUS_NO.equals(ac.getUseStatus())) {
            throw new LockedAccountException("用户被锁定");
        }
//        if("admin".equals(username)){
//            credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
//        }

        //3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
        String realmName = getName();
        //4). 盐值.
//        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        ByteSource credentialsSalt = ByteSource.Util.bytes("ticc");

        SimpleAuthenticationInfo info = null; //new SimpleAuthenticationInfo(principal, credentials, realmName);
        info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
        return info;
    }

    public static void main(String[] args) {
//        String hashAlgorithmName = "SHA1";
        String hashAlgorithmName = "MD5";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("ticc");
        int hashIterations = 1024;

        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }

    //授权会被 shiro 回调的方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
//        Object principal = principals.getPrimaryPrincipal();
        AdminCustom ac = (AdminCustom) principals.getPrimaryPrincipal();
        List<AdminRole> adminRoles = ac.getAdminRoles();
        AdminRole adminRole;
        //2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
        Set<String> roles = new HashSet<>();
        if(!BaseUtils.collectionIsEmpty(adminRoles)) {
            for (int i = 0, len = adminRoles.size(); i < len; i++){
                adminRole = adminRoles.get(i);
                roles.add(adminRole.getRoleNo());
            }
        }

        Set<String> permissions = new HashSet<>();
        if(!BaseUtils.collectionIsEmpty(roles)) {
            List<String> roleNos = new ArrayList<>();
            roleNos.addAll(roles);
            //查询中间表记录通过多个角色编号
            List<SysMenuSysRole> sysMenuSysRoles = sysRoleDao.querySysMenuSysRoleBySysRoleNos(roleNos);
            SysMenuSysRole sysMenuSysRole;
            for (int i = 0, len = sysMenuSysRoles.size(); i < len; i++){
                sysMenuSysRole = sysMenuSysRoles.get(i);
//                permissions.add(sysMenuSysRole.getSysRoleNo()+":"+sysMenuSysRole.getSysMenuNo());
                permissions.add(sysMenuSysRole.getSysMenuNo());
            }
        }


//        permissions.add("admin:add");
//        permissions.add("admin:del");

        //3. 创建 SimpleAuthorizationInfo, 并设置其 reles 属性.
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        info.addStringPermissions(permissions);
        //4. 返回 SimpleAuthorizationInfo 对象.
        return info;
    }
}
