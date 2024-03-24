package com.ruoyi.manage.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车库-价格视图对象 view_pricing_garage_info
 *
 * @author wxq
 * @date 2024-03-20
 */
public class ViewPricingGarageInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 价格编号 */
    @Excel(name = "价格编号")
    private Long pricingId;

    /** 车库编号 */
    @Excel(name = "车库编号")
    private Long garageId;

    /** 车库位置描述 */
    @Excel(name = "车库位置描述")
    private String location;

    /** 车库总车位数 */
    @Excel(name = "车库总车位数")
    private Long totalCapacity;

    /** 车库类型 */
    @Excel(name = "车库类型")
    private String type;

    /** 车位价格  */
    @Excel(name = "车位价格 ")
    private BigDecimal price;

    /** 货币单位 */
    @Excel(name = "货币单位")
    private String currency;

    /** 记录创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 记录创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setPricingId(Long pricingId)
    {
        this.pricingId = pricingId;
    }

    public Long getPricingId()
    {
        return pricingId;
    }
    public void setGarageId(Long garageId)
    {
        this.garageId = garageId;
    }

    public Long getGarageId()
    {
        return garageId;
    }
    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
    }
    public void setTotalCapacity(Long totalCapacity)
    {
        this.totalCapacity = totalCapacity;
    }

    public Long getTotalCapacity()
    {
        return totalCapacity;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public String getCurrency()
    {
        return currency;
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
                .append("pricingId", getPricingId())
                .append("garageId", getGarageId())
                .append("location", getLocation())
                .append("totalCapacity", getTotalCapacity())
                .append("type", getType())
                .append("price", getPrice())
                .append("currency", getCurrency())
                .append("createdAt", getCreatedAt())
                .append("updatedAt", getUpdatedAt())
                .toString();
    }
}
