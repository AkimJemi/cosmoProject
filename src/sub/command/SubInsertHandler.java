package sub.command;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import sub.model.Sub;
import sub.service.SubRequest;
import sub.service.SubService;

public class SubInsertHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/subForm.jsp";
	private SubService subService = new SubService();
	private SubRequest subReq = new SubRequest();
	private Sub sub = new Sub();

	@Override
	public String process(HttpServletRequest rq,
			HttpServletResponse rp) throws Exception {
		Properties pagingValues = new Properties();
		int num =  Integer.parseInt(rq.getParameter("num"));
		subService.autoInsertSubService(num);
		ArrayList<Sub> sub = subService.listSubService(pagingValues);
		rq.setAttribute("sub", sub);
		return "/WEB-INF/view/subList.jsp";
		
	}

}
