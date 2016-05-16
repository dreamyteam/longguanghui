package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/4
 * Time: 下午4:37
 */
public enum BookRankEnums {
    composite(1, "综合指数排名","book_composite_rank"),
    develop(2, "潜力指数排行","book_develop_rank"),
    propagation(3, "传播指数排行","book_propagation_rank"),
    hot(4, "热度指数排行","book_hot_rank"),
    activity(5, "活跃指数排行","book_activity_rank");
    private Integer type;

    private String Description;

    private String cacheKey;

    BookRankEnums(Integer type, String description, String cacheKey) {
        this.type = type;
        Description = description;
        this.cacheKey = cacheKey;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
