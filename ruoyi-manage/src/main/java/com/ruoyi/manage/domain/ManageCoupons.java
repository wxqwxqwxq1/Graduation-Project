package com.ruoyi.manage.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 优惠券表对象 manage_coupons
 *
 * @author wxq
 * @date 2024-03-20
 */
public class ManageCoupons extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 优惠券编号 */
    private Long couponId;

    /** 优惠券代码 */
    @Excel(name = "优惠券代码")
    private String couponCode;

    /** 优惠券描述 */
    @Excel(name = "优惠券描述")
    private String description;

    /** 折扣金额 */
    @Excel(name = "折扣金额")
    private BigDecimal discountAmount;

    /** 折扣百分比 */
    @Excel(name = "折扣百分比")
    private BigDecimal discountPercentage;

    /** 最低消费金额 */
    @Excel(name = "最低消费金额")
    private BigDecimal minimumPurchaseAmount;

    /** 使用限制次数 */
    @Excel(name = "使用限制次数")
    private Long usageLimit;

    /** 有效起始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效起始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validFrom;

    /** 有效结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validTo;

    /** 是否激活 */
    @Excel(name = "是否激活")
    private Integer isActive;

    /**  记录创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = " 记录创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 记录更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setCouponId(Long couponId)
    {
        this.couponId = couponId;
    }

    public Long getCouponId()
    {
        return couponId;
    }
    public void setCouponCode(String couponCode)
    {
        this.couponCode = couponCode;
    }

    public String getCouponCode()
    {
        return couponCode;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
    public void setDiscountAmount(BigDecimal discountAmount)
    {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getDiscountAmount()
    {
        return discountAmount;
    }
    public void setDiscountPercentage(BigDecimal discountPercentage)
    {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getDiscountPercentage()
    {
        return discountPercentage;
    }
    public void setMinimumPurchaseAmount(BigDecimal minimumPurchaseAmount)
    {
        this.minimumPurchaseAmount = minimumPurchaseAmount;
    }

    public BigDecimal getMinimumPurchaseAmount()
    {
        return minimumPurchaseAmount;
    }
    public void setUsageLimit(Long usageLimit)
    {
        this.usageLimit = usageLimit;
    }

    public Long getUsageLimit()
    {
        return usageLimit;
    }
    public void setValidFrom(Date validFrom)
    {
        this.validFrom = validFrom;
    }

    public Date getValidFrom()
    {
        return validFrom;
    }
    public void setValidTo(Date validTo)
    {
        this.validTo = validTo;
    }

    public Date getValidTo()
    {
        return validTo;
    }
    public void setIsActive(Integer isActive)
    {
        this.isActive = isActive;
    }

    public Integer getIsActive()
    {
        return isActive;
    }
    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }
    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("couponId", getCouponId())
                .append("couponCode", getCouponCode())
                .append("description", getDescription())
                .append("discountAmount", getDiscountAmount())
                .append("discountPercentage", getDiscountPercentage())
                .append("minimumPurchaseAmount", getMinimumPurchaseAmount())
                .append("usageLimit", getUsageLimit())
                .append("validFrom", getValidFrom())
                .append("validTo", getValidTo())
                .append("isActive", getIsActive())
                .append("createdAt", getCreatedAt())
                .append("updatedAt", getUpdatedAt())
                .toString();
    }
}
