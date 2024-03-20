package com.ruoyi.fvehicles.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单对象 fveh_order
 *
 * @author wxq
 * @date 2024-03-20
 */
public class FvehOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单编号 */
    private String orderId;

    /** 车主编号 */
    @Excel(name = "车主编号")
    private Long ownerId;

    /** 车位个数 */
    @Excel(name = "车位个数")
    private Long parkingSpaceCount;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private BigDecimal orderAmount;

    /** 优惠金额 */
    @Excel(name = "优惠金额")
    private BigDecimal discountAmount;

    /** 应付金额 */
    @Excel(name = "应付金额")
    private BigDecimal amountDue;

    /** 订单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderDate;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private String paymentMethod;

    /** 付款状态 */
    @Excel(name = "付款状态")
    private String paymentStatus;

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderId()
    {
        return orderId;
    }
    public void setOwnerId(Long ownerId)
    {
        this.ownerId = ownerId;
    }

    public Long getOwnerId()
    {
        return ownerId;
    }
    public void setParkingSpaceCount(Long parkingSpaceCount)
    {
        this.parkingSpaceCount = parkingSpaceCount;
    }

    public Long getParkingSpaceCount()
    {
        return parkingSpaceCount;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setOrderAmount(BigDecimal orderAmount)
    {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderAmount()
    {
        return orderAmount;
    }
    public void setDiscountAmount(BigDecimal discountAmount)
    {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getDiscountAmount()
    {
        return discountAmount;
    }
    public void setAmountDue(BigDecimal amountDue)
    {
        this.amountDue = amountDue;
    }

    public BigDecimal getAmountDue()
    {
        return amountDue;
    }
    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }
    public void setPaymentMethod(String paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod()
    {
        return paymentMethod;
    }
    public void setPaymentStatus(String paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus()
    {
        return paymentStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("orderId", getOrderId())
                .append("ownerId", getOwnerId())
                .append("parkingSpaceCount", getParkingSpaceCount())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("orderAmount", getOrderAmount())
                .append("discountAmount", getDiscountAmount())
                .append("amountDue", getAmountDue())
                .append("orderDate", getOrderDate())
                .append("paymentMethod", getPaymentMethod())
                .append("paymentStatus", getPaymentStatus())
                .toString();
    }
}
