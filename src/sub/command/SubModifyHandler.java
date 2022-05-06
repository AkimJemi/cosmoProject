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

public class SubModifyHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/subModifyForm.jsp";
	private static final String FORM_VIEWI = "/WEB-INF/view/subForm.jsp";
	private SubService subService = new SubService();
	private Sub sub = new Sub();
	private SubRequest subReq = new SubRequest();

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
		int no = Integer.parseInt(rq.getParameter("no"));
		try {
			sub = subService.readSubService(no);
			rq.setAttribute("sub", sub);
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
		rq.setAttribute("sub", sub);
		rq.setAttribute("readOnly", Boolean.FALSE);
		if (!errors.isEmpty()) {
			rq.setAttribute("errors", errors);
			return FORM_VIEW;
		}
		try {
			sub = new Sub(Integer.parseInt(no), Integer.parseInt(water_ind), Integer.parseInt(ele_basic),
					Integer.parseInt(ele_ind));
			sub = subService.modifySubService(sub);
			rq.setAttribute("subReq", sub);
			rq.setAttribute("SubSuccess", "update");
			rq.setAttribute("readOnly", Boolean.TRUE);
			return FORM_VIEWI;
		} catch (DuplicateIdException de) {
			return de.getMessage();
		}
	}
}
