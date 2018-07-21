package priv.jesse.mall.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import priv.jesse.mall.entity.Classification;

import java.util.List;

public interface ClassificationService {
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Classification findById(int id);

    /**
     * 按分类查询所有
     *
     * @param type
     * @return
     */
    List<Classification> findAll(int type);

    /**
     * 按分类查询所有
     *
     * @param pageable
     * @return
     */
    Page<Classification> findAll(int type,Pageable pageable);

    /**
     * 按条件查询
     *
     * @param example
     * @return
     */
    List<Classification> findAllExample(Example<Classification> example);

    /**
     * 更新
     *
     * @param Classification
     * @return
     */
    void update(Classification Classification);

    /**
     * 创建
     *
     * @param Classification
     * @return
     */
    int create(Classification Classification);

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    void delById(int id);

    /**
     * 通过一级分类id查找它所有的二级分类
     * @param cid
     * @return
     */
    List<Classification> findByParentId(int cid);
}
