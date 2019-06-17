package com.project.plan.entity.order;

import com.alibaba.fastjson.annotation.JSONField;
import com.project.plan.entity.support.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_order")
public class Order extends AbstractEntity {
    public static final int STAT_DEFAULT = 0;   //待处理
    public static final int STAT_HANDLE = 1;    //已处理

    public static final int PAY_STAT_DEFAULT = 0;//货到付款

    @NotNull(message="产品名称")
    @Column(name="pruduct_name",length=500)
    private String productName;

    @NotNull(message="订货人不能为空")
    @Column(name="userName",length=50,nullable = false)
    private String userName ;

    @NotNull(message="手机不能为空")
    @Column(name="phone",length=50,nullable = false)
    private String phone;

    @NotNull(message="订货地址不能为空")
    @Column(name="adress",length=50,nullable = false)
    private String adress ;

    @NotNull(message="订单状态不能为空")
    @Column(name="stat",length=2,nullable = false)
    private Integer status ;

    @NotNull(message="付款方式不能为空")
    @Column(name="pay_stat",length=2,nullable = false)
    private Integer payStatus ;

    @NotNull(message="快递公司不能为空")
    @Column(name="express_company",length=50,nullable = true)
    private String expressCompany ;

    @NotNull(message="快递公司不能为空")
    @Column(name="express_number",length=50,nullable = true)
    private String expressNumber ;

    //    @NotNull(message="下单时间不能为空")
    @JSONField(format = "yyyy-MM-dd")
    @Column(name="create_time",length=500)
    protected Date createTime;

    //    @NotNull(message="处理时间不能为空")
    @JSONField(format = "yyyy-MM-dd")//相当于修改时间,
    @Column(name="handle_time",length=500)
    protected Date handleTime;

}
