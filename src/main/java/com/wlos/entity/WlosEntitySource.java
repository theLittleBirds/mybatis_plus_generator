package com.wlos.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * <p>
 * 
 * </p>
 *
 * @author wlos
 * @since 2021-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wlos_entity_source")
@Entity(name = "WlosEntitySource")
public class WlosEntitySource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 32位长度主建
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String uuid;

    /**
     * 企业id
     */
    @TableField("company_id")
    private String companyId;

    /**
     * 迁移任务名称
     */
    @TableField("task_name")
    private String taskName;

    /**
     * 0-mysql 1-oracle 2-待补充
     */
    @TableField("jdbc_type")
    private Boolean jdbcType;

    /**
     * 数据库名称
     */
    @TableField("jdbc_name")
    private String jdbcName;

    /**
     * 数据库连接url
     */
    @TableField("jdbc_url")
    private String jdbcUrl;

    /**
     * 端口
     */
    @TableField("jdbc_port")
    private String jdbcPort;

    /**
     * 数据库账号
     */
    @TableField("jdbc_user_name")
    private String jdbcUserName;

    /**
     * 数据库密码
     */
    @TableField("jdbc_password")
    private String jdbcPassword;


}
