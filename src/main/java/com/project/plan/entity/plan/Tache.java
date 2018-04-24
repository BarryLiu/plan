package com.project.plan.entity.plan;

import com.alibaba.fastjson.annotation.JSONField;
import com.project.plan.entity.User;
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
 *
 * tacheIndex 和moduleId 唯一约束
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_tache")
public class Tache  extends AbstractEntity {
    //各个环节名称
    public static final String[] TACHE_INDEX_NAMES=new String[]{"线框图评审","原型交互稿评审","原型交互稿归档","UI效果图","软件概要设计归档","软件Case归档"
            ,"Server借口提供","UI / 产品检查","测试","产品验收","上线评审"};

    public static final int STAT_NEW = 0;//新创建
    public static final int STAT_DEBUG = 1;//执行中
    public static final int STAT_TESTING = 2;//测试中
    public static final int STAT_SUCCESS = 3;//归档完成


    public static final String TACHE_STATUS(int stat){
        String statStr = null;
        switch (stat){
            case STAT_NEW:statStr="新创建";break;
            case STAT_DEBUG:statStr="执行中";break;
            case STAT_TESTING:statStr="测试中";break;
            case STAT_SUCCESS:statStr="归档完成";break;
            default: statStr="未知";break;
        }
        return statStr;
    }


    @NotNull(message="环节名称不能为空")
    @Column(name="name",length=500)
    private String name;

    @NotNull(message="环节序号不能为空")
    @Column(name="tache_index",length=2,nullable = false)
    private Integer tacheIndex ;


//    @NotNull(message="计划开始时间不能为空")
    @JSONField(format = "yyyy-MM-dd")
    @Column(name="plan_begin_time",length=500)
    protected Date planBeginTime;

//    @NotNull(message="计划结束时间不能为空")
    @JSONField(format = "yyyy-MM-dd")
    @Column(name="plan_end_time",length=500)
    protected Date planEndTime;

    //实际开始时间
    @JSONField(format = "yyyy-MM-dd")
    @Column(name="real_begin_time",length=500)
    protected Date realBeginTime;
    //实际结束时间
    @JSONField(format = "yyyy-MM-dd")
    @Column(name="real_end_time",length=500)
    protected Date realEndTime;

    @NotNull(message="环节状态不能为空")
    @Column(name="stat",length=2,nullable = false)
    private Integer status ;

    //归档时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name="archive_time",length=500)
    protected String archiveTime;

    //@NotNull(message="责任人不能为空")
//    @Column(name="user_id",length=500)
//    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY)//责任人
    @JoinColumn(name = "user_id")
    private User user;

    //模块
    @NotNull(message="模块不能为空")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private Module module;

}
