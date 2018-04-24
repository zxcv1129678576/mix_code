package com.trc.entity;

import java.sql.Timestamp;
/**
 * 
 * @author Administrator
 *
 */
public class Group {
	public String getMachine_code() {
		return machine_code;
	}
	public void setMachine_code(String machine_code) {
		this.machine_code = machine_code;
	}
	private int id;
	private String group;
	private int method_id;
	private int creater;
	private Timestamp create_time;
	private Timestamp modify_time;
	private int is_use;
	private String machine_code;
	public int getIs_use() {
		return is_use;
	}
	public void setIs_use(int is_use) {
		this.is_use = is_use;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public int getMethod_id() {
		return method_id;
	}
	public void setMethod_id(int method_id) {
		this.method_id = method_id;
	}
	public int getCreater() {
		return creater;
	}
	public void setCreater(int creater) {
		this.creater = creater;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getModify_time() {
		return modify_time;
	}
	public void setModify_time(Timestamp modify_time) {
		this.modify_time = modify_time;
	}
	
	
}
