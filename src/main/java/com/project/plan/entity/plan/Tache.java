package com.project.plan.entity.plan;

import com.project.plan.entity.support.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Barry on 2018/4/20.
 * 环节
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_tache")
public class Tache  extends AbstractEntity {


    @NotNull(message="计划时间不能为空")
    @Column(name="plan_time",length=500)
    protected Date planTime;

    //实际时间
    @Column(name="real_time",length=500)
    protected Date realTime;
    //归档时间
    @Column(name="archive_time",length=500)
    protected String archiveTime;


    //@NotNull(message="责任人不能为空")
    @Column(name="user_id",length=500)
    private Integer userId;


    //模块
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private Module module;

}
