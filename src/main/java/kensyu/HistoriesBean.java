package kensyu;

import java.sql.Timestamp;

public class HistoriesBean {
	private int id;
	private int user_id;
	private int point;
	private Timestamp created_at;
	private byte deleteflag; 
	private Timestamp deleted_at;

	/**
	 * コンストラクタ
	 */
	public HistoriesBean (int id, int user_id, int point, Timestamp created_at){
		this.id = id;
		this.user_id = user_id;
		this.point = point;
		this.created_at = created_at;
	}
	
	/** 引数無しのコンストラクタ **/
	public HistoriesBean() {
	}

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	} 
	public int getUserId() {
		return this.user_id;
	}	
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	public int getPoint() {
		return this.point;
	}
	public void setPoint(int point) {
		this.point = point;
	} 
	public Timestamp getCreatedAt() {
		return this.created_at;
	}
	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}
	public byte getDeleteflag() {
		return this.deleteflag;
	}
	public void setDeleteflag(byte deleteflag) {
		this.deleteflag = deleteflag;
	}
}
