package priv.jesse.mall.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.jesse.mall.common.HttpClientUtils;
import priv.jesse.mall.dao.GoodsInfoDao;
import priv.jesse.mall.entity.GoodsInfo;

import java.util.List;
import java.util.Map;

@Service
public class SpiderService {
    private static Logger logger = LoggerFactory.getLogger(SpiderService.class);
    @Autowired
    private GoodsInfoDao goodsInfoDao;
    private static String HTTPS_PROTOCOL = "https:";

    public void spiderData(String url, Map<String, String> params) {
        String html = HttpClientUtils.sendGet(url, null, params);
        if(!StringUtils.isBlank(html)) {
            List<GoodsInfo> goodsInfos =parseHtml(html);
            goodsInfoDao.saveBatch(goodsInfos);
        }
    }
    /**
     * 解析html
     * @param html
     */
    private List<GoodsInfo> parseHtml(String html) {
        //商品集合
        List<GoodsInfo> goods = Lists.newArrayList();
        /**
         * 获取Dom并解析
         */
        Document document = Jsoup.parse(html);
        Elements elements = document.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
        int index = 0;
        for(Element element : elements) {
            String goodsId = element.attr("data-sku");
            String goodsName = element.select("div[class=p-name p-name-type-2]").select("em").text();
            String imgUrl = HTTPS_PROTOCOL+element.select("div[class=p-img]").select("a").select("img[src]").attr("src");
            String goodsPrice = element.select( "div[class=p-price]").select("strong").select("i").text();
            GoodsInfo goodsInfo = new GoodsInfo(goodsId, goodsName, imgUrl, goodsPrice);
            goods.add(goodsInfo);
            String jsonStr = JSON.toJSONString(goodsInfo);
            logger.info("成功爬取 " + goodsName + " 的基本信息 ");
            logger.info(jsonStr);
            if(index ++ == 9) {
                break;
            }
        }
        return goods;
    }
}
