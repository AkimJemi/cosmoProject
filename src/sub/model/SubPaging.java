package sub.model;

public class SubPaging {
	private int currentPage;
<<<<<<< HEAD
	private int startPage;
	private int total;
	private int limit;
	private int endPage;
	private String search;
	private int lastPage;
	
	public SubPaging(int total, int startPage, int currentPage, int limit) {
		this.currentPage = currentPage;
		this.total = total;
		this.limit = limit;
		this.startPage = startPage;
		this.endPage = startPage +4;
		this.lastPage = total/limit;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
=======
	private int firstPage;
	private int total;
	private String search;
>>>>>>> be475a442771350ff55f0f6b9e89b56684a9a29c
	
}
