package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/25
 * Time: 下午2:28
 */
public enum QueueRoutingKeyEnums {
    publish_book("crawler_event_book", "出版文学"),
    publish_book_am("crawler_event_book_am", "出版文学(亚马逊)"),
    publish_book_comment("crawler_comment", "出版文学评论"),

    publish_keyword("crawler_keyword_event","关键词搜索"),

    publish_keyword_weixin("crawler_keyword_weixin_event","搜狗微信关键词搜索"),

    publish_so_index("so_index","指数抓取"),

    publish_news_sougou("news_sogou","news.sogou.com上5大新闻来源数量");

    private String key;

    private String description;

    QueueRoutingKeyEnums(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
