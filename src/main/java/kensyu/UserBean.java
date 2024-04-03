package kensyu;

import java.sql.Timestamp;
public class UserBean {
	private int id;
	private String name;
	private String password;
	private Timestamp created_at;
	private Timestamp updated_at;
	private byte deleteflag; 
	private Timestamp deleted_at;

/**
 * コンストラクタ
 */
public UserBean (int id, String name, String password){
	this.id = id;
		this.name = name;
		this.password = password;
	}
	
	/** 引数無しのコンストラクタ **/
	public UserBean() {

	}

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	} 
	public String getName() {
		return this.name;
	}	
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getCreatedAt() {
		return this.created_at;
	}
	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdatedAt() {
		return this.updated_at;
	}
	public void setUpdatedAt(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	public byte getDeleteflag() {
		return this.deleteflag;
	}
	public void setDeleteflag(byte deleteflag) {
		this.deleteflag = deleteflag;
	}
}
