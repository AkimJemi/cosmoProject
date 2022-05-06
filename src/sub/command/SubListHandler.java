package sub.command;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;
import sub.model.Sub;
import sub.model.SubPaging;
import sub.service.SubService;

public class SubListHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/subList.jsp";
	private SubService subService = new SubService();
	String search = null;
	SubPaging sp = null;
	Map<String, Object> pagingValues = new HashMap<>();
	int total, startPage, currentPage, limit;

	@Override
	public String process(HttpServletRequest rq, HttpServletResponse rs)
			throws Exception {
		try (Connection conn = ConnectionProvider.getConnection()) {
			//
			if (rq.getParameter("startPage") != null)
				startPage = Integer.parseInt(rq.getParameter("startPage"));
			else
				startPage = 1;
			//
			if (rq.getParameter("currentPage") != null)
				currentPage = Integer.parseInt(rq.getParameter("currentPage"));
			else
				currentPage = 1;
			//
			if (rq.getParameter("search") != null) {
				search = rq.getParameter("search");
				pagingValues.put("search", search);
				startPage = 1;
				currentPage = 1;
			}
			total = subService.subAllCount(search);
			//
			if (rq.getParameter("limit") != null)
				limit = Integer.parseInt(rq.getParameter("limit"));
			else
				limit = 10;

			sp = new SubPaging(total, startPage, currentPage, limit);
			pagingValues.put("paging", sp);
			if (rq.getParameter("autoInsert") != null) {
				int num = Integer.parseInt(rq.getParameter("num"));
				subService.autoInsertSubService(num);
			}
			ArrayList<Sub> sub = subService.listSubService(pagingValues);
			rq.setAttribute("search", pagingValues);
			rq.setAttribute("paging", sp);
			rq.setAttribute("sub", sub);
			if (rq.getMethod().equalsIgnoreCase("GET")) {
				return processForm(rq, rs);
			} else if (rq.getMethod().equalsIgnoreCase("POST")) {
				return processSubmit(rq, rs);
			} else {
				rs.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return null;
			}
		}
	}
	private String processForm(HttpServletRequest rq, HttpServletResponse rs) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest rq,
			HttpServletResponse rs) {
		return FORM_VIEW;
	}
}
