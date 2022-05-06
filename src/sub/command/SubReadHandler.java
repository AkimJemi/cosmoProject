package sub.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicateIdException;
import mvc.command.CommandHandler;
import sub.model.Sub;
import sub.service.SubRequest;
import sub.service.SubService;

public class SubReadHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/subForm.jsp";
	private SubService subService = new SubService();
	private SubRequest subReq = new SubRequest();
	private Sub sub = new Sub();

	@Override
	public String process(HttpServletRequest rq, HttpServletResponse rs) {
		if (rq.getMethod().equalsIgnoreCase("GET")) {
			return processForm(rq, rs);
		} else if (rq.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(rq, rs);
		} else {
			rs.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest rq, HttpServletResponse rs) {
		Sub sub = null;
		if (rq.getParameter("no") != null) {
			int no = Integer.parseInt(rq.getParameter("no"));
			sub = subService.readSubService(no);
			rq.setAttribute("subReq", sub);
			return FORM_VIEW;
		}
		try {
			rq.setAttribute("readOnly", Boolean.FALSE);
			return FORM_VIEW;
		} catch (DuplicateIdException de) {
			return de.getMessage();
		}
	}

	private String processSubmit(HttpServletRequest rq, HttpServletResponse rs) {
		String no = (String) rq.getParameter("no");
		String water_ind = (String) rq.getParameter("water_ind");
		String ele_basic = (String) rq.getParameter("ele_basic");
		String ele_ind = (String) rq.getParameter("ele_ind");
		Map<String, Boolean> errors = new HashMap<>();
		rq.setAttribute("errors", errors);
		if (no.equals("0"))
			subReq.setNo("0");
		else if (no == null || no.equals("") || no.isEmpty())
			errors.put("no", Boolean.TRUE);

		if (water_ind.equals("0"))
			water_ind = "0";
		else if (water_ind == null || water_ind.equals("") || water_ind.isEmpty())
			errors.put("water_ind", Boolean.TRUE);

		if (ele_basic.equals("0"))
			ele_basic = "0";
		else if (ele_basic == null || ele_basic.equals("") || ele_basic.isEmpty())
			errors.put("ele_basic", Boolean.TRUE);

		if (ele_ind.equals("0"))
			ele_basic = "0";
		else if (ele_ind == null || ele_ind.equals("") || ele_ind.isEmpty())
			errors.put("ele_ind", Boolean.TRUE);

		subReq = new SubRequest(no, water_ind, ele_basic, ele_ind);
		rq.setAttribute("subReq", subReq);
		rq.setAttribute("readOnly", Boolean.FALSE);
		if (!errors.isEmpty()) {
			rq.setAttribute("errors", errors);
			return FORM_VIEW;
		} 

		boolean checkSubByNo = subService.selectSubByNo(Integer.parseInt(rq.getParameter("no")));
		if (!checkSubByNo) {
			rq.setAttribute("NumExist", checkSubByNo);
			return FORM_VIEW;
		}

		try {
			
			sub = new Sub(Integer.parseInt(no), Integer.parseInt(water_ind), Integer.parseInt(ele_basic),
					Integer.parseInt(ele_ind));
			subService.insertSubService(sub);
			rq.setAttribute("SubSuccess", "insert");
			rq.setAttribute("readOnly", Boolean.TRUE);
			return FORM_VIEW;
		} catch (DuplicateIdException de) {
			de.getMessage();
			return FORM_VIEW;
		}
	}
}
//	private String trim(String str) {
//		return str == null ? null : str.trim();
//	}
