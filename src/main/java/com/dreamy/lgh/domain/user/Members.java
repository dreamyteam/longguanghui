package com.dreamy.lgh.domain.user;

import com.dreamy.lgh.domain.BaseDomain;
import java.io.Serializable;
import java.util.Date;

public class Members extends BaseDomain<Integer> implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer status;

    private Integer type;

    private String wxOrderId;

    private String wxId;

    private String wxUserName;

    private String wxImageUrl;

    private Date startedAt;

    private Date endedAt;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getWxOrderId() {
        return wxOrderId;
    }

    public void setWxOrderId(String wxOrderId) {
        this.wxOrderId = wxOrderId;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getWxUserName() {
        return wxUserName;
    }

    public void setWxUserName(String wxUserName) {
        this.wxUserName = wxUserName;
    }

    public String getWxImageUrl() {
        return wxImageUrl;
    }

    public void setWxImageUrl(String wxImageUrl) {
        this.wxImageUrl = wxImageUrl;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Date endedAt) {
        this.endedAt = endedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Members other = (Members) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getWxOrderId() == null ? other.getWxOrderId() == null : this.getWxOrderId().equals(other.getWxOrderId()))
            && (this.getWxId() == null ? other.getWxId() == null : this.getWxId().equals(other.getWxId()))
            && (this.getWxUserName() == null ? other.getWxUserName() == null : this.getWxUserName().equals(other.getWxUserName()))
            && (this.getWxImageUrl() == null ? other.getWxImageUrl() == null : this.getWxImageUrl().equals(other.getWxImageUrl()))
            && (this.getStartedAt() == null ? other.getStartedAt() == null : this.getStartedAt().equals(other.getStartedAt()))
            && (this.getEndedAt() == null ? other.getEndedAt() == null : this.getEndedAt().equals(other.getEndedAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getWxOrderId() == null) ? 0 : getWxOrderId().hashCode());
        result = prime * result + ((getWxId() == null) ? 0 : getWxId().hashCode());
        result = prime * result + ((getWxUserName() == null) ? 0 : getWxUserName().hashCode());
        result = prime * result + ((getWxImageUrl() == null) ? 0 : getWxImageUrl().hashCode());
        result = prime * result + ((getStartedAt() == null) ? 0 : getStartedAt().hashCode());
        result = prime * result + ((getEndedAt() == null) ? 0 : getEndedAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    public Members id(Integer value) {
        this.id = value;
        return this;
    }

    public Members userId(Integer value) {
        this.userId = value;
        return this;
    }

    public Members status(Integer value) {
        this.status = value;
        return this;
    }

    public Members type(Integer value) {
        this.type = value;
        return this;
    }

    public Members wxOrderId(String value) {
        this.wxOrderId = value;
        return this;
    }

    public Members wxId(String value) {
        this.wxId = value;
        return this;
    }

    public Members wxUserName(String value) {
        this.wxUserName = value;
        return this;
    }

    public Members wxImageUrl(String value) {
        this.wxImageUrl = value;
        return this;
    }

    public Members startedAt(Date value) {
        this.startedAt = value;
        return this;
    }

    public Members endedAt(Date value) {
        this.endedAt = value;
        return this;
    }

    public Members createdAt(Date value) {
        this.createdAt = value;
        return this;
    }

    public Members updatedAt(Date value) {
        this.updatedAt = value;
        return this;
    }
}