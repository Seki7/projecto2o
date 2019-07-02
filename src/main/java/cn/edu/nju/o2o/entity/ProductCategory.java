package cn.edu.nju.o2o.entity;

import java.util.Date;

public class ProductCategory {
    //商品类别id
    private Integer productCategoryId;
    //商品类别名称
    private String productCategoryName;
    //优先级 0-10 数字越大优先级越高
    private Integer priority;
    //创建时间
    private Date createTime;
    //最后修改时间
    private Date lastEditTime;
    //状态 1表示有效 0表示无效
    private Integer status;

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
