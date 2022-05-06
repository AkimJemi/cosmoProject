package sub.service;

import java.util.Map;

public class SubRequest {
	private String no;
	private String water_ind;
	private String ele_basic;
	private String ele_ind;
	
	public SubRequest() {
	}
	public SubRequest(String no, String water_ind, String ele_basic, String ele_ind) {
		super();
		this.no = no;
		this.water_ind = water_ind;
		this.ele_basic = ele_basic;
		this.ele_ind = ele_ind;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getWater_ind() {
		return water_ind;
	}
	public void setWater_ind(String water_ind) {
		this.water_ind = water_ind;
	}
	public String getEle_basic() {
		return ele_basic;
	}
	public void setEle_basic(String ele_basic) {
		this.ele_basic = ele_basic;
	}
	public String getEle_ind() {
		return ele_ind;
	}
	public void setEle_ind(String ele_ind) {
		this.ele_ind = ele_ind;
	}
	
	
	
	
}



