package com.wisesoft.bigdata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 
 * @author flyCloud
 * @email 
 * @date 2018-08-28 15:27:21
 */
@TableName("interface_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class InterfaceInfoEntity extends Model<InterfaceInfoEntity> {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/**
	 * 
	 */
	private String serverId;
	/**
	 * 服务器id
	 */
	private String serverUrl;
	/**
	 * 编码code
	 */
	private String interfaceCode;

}
