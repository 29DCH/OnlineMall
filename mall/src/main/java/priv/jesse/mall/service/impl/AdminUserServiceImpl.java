package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import priv.jesse.mall.dao.AdminUserDao;
import priv.jesse.mall.entity.AdminUser;
import priv.jesse.mall.service.AdminUserService;
import priv.jesse.mall.service.exception.LoginException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public AdminUser findById(int id) {
        return adminUserDao.getOne(id);
    }

    @Override
    public Page<AdminUser> findAll(Pageable pageable) {
        return adminUserDao.findAll(pageable);
    }

    @Override
    public List<AdminUser> findAllExample(Example<AdminUser> example) {
        return adminUserDao.findAll(example);
    }

    @Override
    public void update(AdminUser adminUser) {
        adminUserDao.save(adminUser);
    }

    @Override
    public int create(AdminUser adminUser) {
        AdminUser adminUser1 = adminUserDao.save(adminUser);
        return adminUser.getId();
    }

    @Override
    public void delById(int id) {
        adminUserDao.delete(id);
    }

    @Override
    public AdminUser checkLogin(HttpServletRequest request, String username, String pwd) {
        AdminUser adminUser = adminUserDao.findByUsernameAndPassword(username, pwd);
        if (adminUser != null) {
            request.getSession().setAttribute("login_user", adminUser);
        } else {
            throw new LoginException("用户名或密码错误");
        }
        return adminUser;
    }
}