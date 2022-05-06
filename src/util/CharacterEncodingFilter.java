package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
	private String encoding;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig config) throws ServletException{
		encoding =config.getInitParameter("encoding");
		if(encoding ==null) {
			encoding ="UTF-8";
		}
	}
	@Override
	public void destroy() {
		
	}
}
//encoding 초기화 파라미터를 이용하여 사용할 인코딩을 지정하고 필터 매핑을 통해
//어떤 URUL 패턴에 필터를 정용할지 지정한다.
//그러면 필터를 통해 요청 캐릭터 인코딩을 설정하기 때문에 JSP 마다 요청 캐릭터 인코딩을 성ㄹ정하지 않아도 된다.
