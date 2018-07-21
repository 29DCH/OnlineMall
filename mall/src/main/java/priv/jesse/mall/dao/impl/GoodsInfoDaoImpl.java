package priv.jesse.mall.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import priv.jesse.mall.dao.GoodsInfoDao;
import priv.jesse.mall.entity.GoodsInfo;

@Repository
public class GoodsInfoDaoImpl implements GoodsInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveBatch(List<GoodsInfo> infos) {
        String sql = "REPLACE INTO goods_info(" + "goods_id," + "goods_name," + "img_url," + "goods_price) "
                + "VALUES(?,?,?,?)";
        for(GoodsInfo info : infos) {
            jdbcTemplate.update(sql, info.getGoodsId(), info.getGoodsName(), info.getImgUrl(), info.getGoodsPrice());
        }
    }
}
