package com.project.plan.entity.plan;

import com.alibaba.fastjson.annotation.JSONField;
import com.project.plan.entity.support.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Barry on 2018/4/20.
 * 功能模块
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_module")
public class Module extends AbstractEntity{

    @NotNull(message="模块名称不能为空")
    @Column(name="name",length=500)
    private String name;

    @NotNull(message="启动时间不能为空")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name="start_time",length=500)
    protected Date startTime;

    @NotNull(message="期望上线时间不能为空")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name="wish_time",length=500)
    protected Date wishTime;


    @NotNull(message="模块状态不能为空")
    @Column(name="stat",length=2,nullable = false)
    private Integer status ;

    //项目
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

}
