//package com.project.plan.entity.plan;
//
//import com.project.plan.entity.support.AbstractEntity;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
///**
// * Created by yingjing.liu on 2018/4/20.
// * 操作日志
// */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "t_openate_log")
//public class OpenateLog  extends AbstractEntity {
//
//    private String
//
//}
//
//
//
//package com.wheatek.push.log.entity;
//
//        import javax.persistence.Column;
//        import javax.persistence.Entity;
//        import javax.persistence.Table;
//        import javax.persistence.Transient;
//        import javax.validation.constraints.NotNull;
//
//        import com.wheatek.push.entity.Apks;
//        import com.wheatek.push.entity.User;
//
//        import lombok.AllArgsConstructor;
//        import lombok.Data;
//        import lombok.EqualsAndHashCode;
//        import lombok.NoArgsConstructor;
//        import lombok.NonNull;
//        import lombok.RequiredArgsConstructor;
//
///**
// * 后台操作log 保存到 log 数据库
// * <p>Title: </p>
// * @author yingjing.liu
// * @date 2018年3月2日
// *
// */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "t_openate_log")
//@EqualsAndHashCode(callSuper = false)
//public class OpenateLog extends AbstractEntity{
//    public static final int STATUS_DEBUG = 1;
//    public static final int STATUS_INFO = 2;
//    public static final int STATUS_WARN = 3;
//    public static final int STATUS_ERROR = 4;
//
//    public static final long DURATION_DEFAULT=-1L;//有些地方不需要记录请求了多少时间,就用-1L默认
//
//    @Column(name="status",length=10,nullable=false)
//    @NotNull(message="status不能为空")
//    private Integer status; //状态
//
//    //创建描述
//    @Column(name="user_id",length=10,nullable=false)
//    @NotNull(message="创建人不能为空")
//    protected Long userId;
//
//    @Column(name="ip",length=20,nullable=false)
//    @NotNull(message="ip不能为空")
//    private String ip; // 访问ip地址
//
//    @Column(name="url",length=255,nullable=false)
//    @NotNull(message="url不能为空")
//    private String url; // 访问的url地址
//
//    @Column(name="duration",length=10,nullable=false)
//    @NotNull(message="duration不能为空")
//    private Long duration; //访问处理时间
//    //创建描述
//    @Column(name="create_comment",length=4000)
//    protected String createComment;
//
//
//    //创建人
//    @Transient
//    private User user;
//
//
//    public OpenateLog(OpenateLog log,
//                      User user) {
//        this.user = user;
//
//        if(log!=null) {
//            this.id = log.getId();
//            this.createTime = log.getCreateTime();
//
//            this.status = log.getStatus();
//            this.userId = log.getUserId();
//            this.ip = log.getIp();
//            this.url = log.getUrl();
//            this.duration = log.getDuration();
//            this.createComment = log.getCreateComment();
//        }
//    }
//
//}
//
