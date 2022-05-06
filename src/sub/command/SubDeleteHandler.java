package sub.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import sub.model.Sub;
import sub.service.SubRequest;
import sub.service.SubService;

public class SubDeleteHandler implements CommandHandler {
	private static final String FORM_VIEW = "subList.do";
	private SubService subService = new SubService();
	private Sub sub = new Sub();

	@Override
	public String process(HttpServletRequest rq, HttpServletResponse rs) {
		if (rq.getMethod().equalsIgnoreCase("GET")) {
			return processForm(rq, rs);
		} else {
			rs.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest rq, HttpServletResponse rs) {
		int no = Integer.parseInt(rq.getParameter("no"));
		System.out.println(no);
		boolean checkSub = subService.selectSubByNo(no);
		if(checkSub) {
			rq.setAttribute("checkId", "fail");
			return FORM_VIEW;
		}
		boolean result = subService.deleteSubByNo(no);
		if(result) {
			rq.setAttribute("DeleteResult", Boolean.TRUE);
			System.out.println("test1");
		}else {
			rq.setAttribute("DeleteResult", Boolean.FALSE);
			System.out.println("test2");
		}
		System.out.println("test3");
		return FORM_VIEW;
	}

}
