package priv.jesse.mall.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import priv.jesse.mall.entity.User;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private UserService userService;

    /**
     * 打开用户列表页面
     * @return
     */
    @RequestMapping("/toList.html")
    public String toList() {
        return "admin/user/list";
    }

    /**
     * 打开编辑页面
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/toEdit.html")
    public String toEdit(int id, Map<String, Object> map) {
        User user = userService.findById(id);
        map.put("user", user);
        return "admin/user/edit";
    }

    /**
     * 获取所有用户列表
     *
     * @param pageindex
     * @return
     */
    @ResponseBody
    @RequestMapping("/list.do")
    public ResultBean<List<User>> findAllUser(int pageindex,
                                              @RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
        Pageable pageable = new PageRequest(pageindex, pageSize, null);
        List<User> users = userService.findAll(pageable).getContent();
        return new ResultBean<>(users);
    }

    @ResponseBody
    @RequestMapping("/getTotal.do")
    public ResultBean<Integer> geTotal() {
        Pageable pageable = new PageRequest(1, 15, null);
        int total = (int) userService.findAll(pageable).getTotalElements();
        return new ResultBean<>(total);
    }

    @ResponseBody
    @RequestMapping("/del.do")
    public ResultBean<Boolean> del(int id) {
        userService.delById(id);
        return new ResultBean<>(true);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/update.do")
    public ResultBean<Boolean> update(int id,String username,
                                      String password,String name,
                                      String phone,String email,
                                      String addr) {
        // 更新前先查询
        User user = userService.findById(id);
        user.setId(id);
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setAddr(addr);
        user.setEmail(email);
        user.setPhone(phone);
        userService.update(user);
        return new ResultBean<>(true);
    }
}
