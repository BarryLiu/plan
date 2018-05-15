package com.project.plan.entity.plan;

import com.project.plan.entity.support.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Barry on 2018/5/15.
 *
 * 状态管理：open，close，待启动，测试中，已关闭……
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_project_status")
public class Status extends AbstractEntity {

    public static final int STAT_VIEW = 0;//显示,
    public static final int STAT_HIDE = 1;//隐藏,隐藏后新加的项目环节不能选择它(ProjectTache)

    @NotNull(message="状态名称不能为空")
    @Column(name="name",length=500)
    private String name;

    @NotNull(message="环节状态不能为空")
    @Column(name="stat",length=2,nullable = false)
    private Integer status ;

}
