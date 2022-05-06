package sub.model;

public class Sub {
	private int no;
	private int water_ind;
	private int ele_basic;
	private int ele_ind;

	public Sub(int no, int water_ind, int ele_basic, int ele_ind) {
		this.no = no;
		this.water_ind = water_ind;
		this.ele_basic = ele_basic;
		this.ele_ind = ele_ind;
	}

	public Sub() {
	}

	public Sub(int no, int water_ind) {
		this.no = no;
		this.water_ind = water_ind;
	}

	public Sub(int no, int ele_basic, int ele_ind) {
		this.no = no;
		this.ele_basic = ele_basic;
		this.ele_ind = ele_ind;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getEle_basic() {
		return ele_basic;
	}

	public void setEle_basic(int ele_basic) {
		this.ele_basic = ele_basic;
	}

	public int getEle_ind() {
		return ele_ind;
	}

	public void setEle_ind(int ele_ind) {
		this.ele_ind = ele_ind;
	}

	public int getWater_ind() {
		return water_ind;
	}

}
