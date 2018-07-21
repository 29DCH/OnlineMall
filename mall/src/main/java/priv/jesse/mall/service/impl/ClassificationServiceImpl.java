package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import priv.jesse.mall.dao.ClassificationDao;
import priv.jesse.mall.entity.Classification;
import priv.jesse.mall.service.ClassificationService;

import java.util.List;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    private ClassificationDao classificationDao;

    @Override
    public Classification findById(int id) {
        return classificationDao.getOne(id);
    }

    @Override
    public List<Classification> findAll(int type) {
        return classificationDao.findByType(type);
    }

    /**
     * 按分类查询所有
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<Classification> findAll(int type, Pageable pageable) {
        return classificationDao.findByType(type, pageable);
    }

    @Override
    public List<Classification> findAllExample(Example<Classification> example) {
        return classificationDao.findAll(example);
    }

    @Override
    public void update(Classification classification) {
        classificationDao.save(classification);
    }

    @Override
    public int create(Classification classification) {
        Classification classification1 = classificationDao.save(classification);
        return classification.getId();
    }

    @Override
    public void delById(int id) {
        classificationDao.delete(id);
    }

    /**
     * 通过一级分类id查找它所有的二级分类
     *
     * @param cid
     * @return
     */
    @Override
    public List<Classification> findByParentId(int cid) {
        return classificationDao.findByParentId(cid);
    }
}
