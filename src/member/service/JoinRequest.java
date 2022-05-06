package member.service;

import java.util.Map;

public class JoinRequest {
	private String id;
	private String name;
	private String password;
	private String confirmPassword;
	//회원가입 기능을 구현할 때 필요한 요청 데이터를 보관하는 필드로 아이디, 이름, 암호, 암호확인값을 저장한다.
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public boolean isPasswordEqualToConfirm() {
		return password!=null&& password.equals(confirmPassword);
	}
	//password 필드와 confirmPassword 필드값이 같은지 검사한다. 필드 데이터가 유요한지 여부를 검사할때 사용한다.
	
	public void validate(Map<String, Boolean>errors) {
		checkEmpty(errors, id,"id");
		checkEmpty(errors, name,"name");
		checkEmpty(errors, password,"password");
		checkEmpty(errors, confirmPassword,"confirmPassword");
		if(!errors.containsKey("confirmPassword")) {
			if(!isPasswordEqualToConfirm()) {
				errors.put("notMatch",Boolean.TRUE);
			}
		}
	}
	//각 필드의 데이터가 유효한지 검사한다. 파라미터로 전달받은 errors맵 객체는 에러 정보를 담기 ㅇ ㅟ해 사용한다. 
	//예를 들어, id 필드값이 올바르지 않은경우 errors 맵 객체에 "id"키의 값으로 TRUE를 추출한다.
	//이 errors 파라미터는 뒤에서 살펴볼 JoinHandler에서 생성해서 전달한다.
	
	private void checkEmpty(Map<String, Boolean>errors, String value,String fieldName) {
		if(value ==null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}
}
