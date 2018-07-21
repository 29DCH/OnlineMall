package priv.jesse.mall.constant;

/**
 * 系统全局常量
 */
public interface SysConstant {
    /**
     * 系统默认字符集
     */
    String DEFAULT_CHARSET = "utf-8";
    /**
     * 需要爬取的网站
     */
    String BASE_URL = "https://search.jd.com/Search";
    String HTTPS_PROTOCOL = "https:";

    interface Header {
        String ACCEPT = "Accept";
        String ACCEPT_ENCODING = "Accept-Encoding";
        String ACCEPT_LANGUAGE = "Accept-Language";
        String CACHE_CONTROL = "Cache-Controle";
        String COOKIE = "Cookie";
        String HOST = "Host";
        String PROXY_CONNECTION = "Proxy-Connection";
        String REFERER = "Referer";
        String USER_AGENT = "User-Agent";
    }
    /**
     * 默认日期格式
     */
    String DEFAULT_DATE_FORMAT = "yyy-MM-dd HH:mm:ss";
}