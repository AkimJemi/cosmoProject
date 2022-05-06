package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class JoinService {
	private MemberDao memberDao = new MemberDao();
	
	public void join(JoinRequest joinReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			//18행~19행 DB커넥션을 구하고 , 트랜색션 실행.
			
//			Member member = memberDao.selectById(conn, joinReq.getId());
//			//memberDao 의 selectById()를 이용해서 Join.Req.getId()에 해당하는 회원 데이터를 구한다.
//			if(member != null) {
//				JdbcUtil.rollback(conn);
//				throw new DuplicateIdException();
//			}
//			
//			memberDao.insert(conn, 
//					new Member(
//							joinReq.getId(),
//							joinReq.getName(),
//							joinReq.getPassword(),
//							new Date())
//					);
			//가입하려는 Id에 해당하는 데이터가 이미 존재한다면 트랜잭션을 롤백하고 DuplicateIdException을 발생시팈ㄴ다.
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
