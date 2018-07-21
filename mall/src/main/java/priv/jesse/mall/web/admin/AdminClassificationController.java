package priv.jesse.mall.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.jesse.mall.entity.Classification;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.ClassificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/classification")
public class AdminClassificationController {
    @Autowired
    private ClassificationService classificationService;

    /**
     * 返回列表页面
     *
     * @param type
     * @return
     */
    @RequestMapping("/toList.html")
    public String toList(int type) {
        if (type == 1) {// 一级分类页面
            return "admin/category/list";
        } else if (type == 2) {// 二级分类页面
            return "admin/categorysec/list";
        } else {
            return "";
        }
    }

    /**
     * 打开添加分类页面
     *
     * @param type
     * @return
     */
    @RequestMapping("/toAdd.html")
    public String toAdd(int type) {
        if (type == 1) {// 一级分类页面
            return "admin/category/add";
        } else if (type == 2) {// 二级分类页面
            return "admin/categorysec/add";
        } else {
            return "";
        }
    }

    /**
     * 打开编辑页面
     *
     * @param id
     * @param type
     * @param map
     * @return
     */
    @RequestMapping("/toEdit.html")
    public String toEdit(int id, int type, Map<String, Object> map) {
        Classification classification = classificationService.findById(id);
        map.put("cate", classification);
        if (type == 1) {// 一级分类页面
            return "admin/category/edit";
        } else if (type == 2) {// 二级分类页面
            Classification classification1 = classificationService.findById(classification.getParentId());
            map.put("cate", classification1);
            map.put("catese",classification);
            return "admin/categorysec/edit";
        } else {
            return "";
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add.do")
    public ResultBean<Boolean> add(String cname, int parentId, int type) {
        Classification classification = new Classification();
        classification.setCname(cname);
        classification.setParentId(parentId);
        classification.setType(type);
        classificationService.create(classification);
        return new ResultBean<>(true);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/update.do")
    public ResultBean<Boolean> update(int id, String cname, int parentId, int type) {
        Classification classification = classificationService.findById(id);
        classification.setCname(cname);
        classification.setParentId(parentId);
        classification.setType(type);
        classificationService.update(classification);
        return new ResultBean<>(true);
    }

    @ResponseBody
    @RequestMapping("/del.do")
    public ResultBean<Boolean> del(int id) {
        classificationService.delById(id);
        return new ResultBean<>(true);
    }

    @RequestMapping("/list.do")
    @ResponseBody
    public ResultBean<List<Classification>> findAll(int type,
                                                    int pageindex, @RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
        List<Classification> list = new ArrayList<>();
        if (pageindex == -1)
            list = classificationService.findAll(type);
        else {
            Pageable pageable = new PageRequest(pageindex, pageSize, null);
            list = classificationService.findAll(type, pageable).getContent();
        }
        return new ResultBean<>(list);
    }

    @ResponseBody
    @RequestMapping("/getTotal.do")
    public ResultBean<Integer> getTotal(int type) {
        Pageable pageable = new PageRequest(1, 30, null);
        int count = (int) classificationService.findAll(type, pageable).getTotalElements();
        return new ResultBean<>(count);
    }
}