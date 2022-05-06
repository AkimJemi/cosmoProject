package sub.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import sub.dao.SubDAO;
import sub.model.Sub;

public class SubService {
	private SubDAO subDao = new SubDAO();
	Connection conn = null;

	public void insertSubService(Sub sub) {
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			subDao.waterInsert(conn, new Sub(sub.getNo(), sub.getWater_ind()));
			conn.commit();

			subDao.eleInsert(conn,
					new Sub(sub.getNo(), sub.getEle_basic(), sub.getEle_ind()));
			conn.commit();

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	public void autoInsertSubService(int num) {
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			subDao.autoSubInsert(conn, num);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public ArrayList<Sub> listSubService(Map pagingValues) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			ArrayList<Sub> ListSub = subDao.subSelectAll(conn, pagingValues);
			System.out.println("test4");
			return ListSub;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public Sub readSubService(int no) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Sub getSubByNo = subDao.getSubByNo(conn, no);
			return getSubByNo;

		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public boolean selectSubByNo(int no) {
		boolean IsSubNullById = true;
		try (Connection conn = ConnectionProvider.getConnection()) {
			IsSubNullById = subDao.checkIsSubNullByNo(conn, no);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return IsSubNullById;
	}

	public Sub modifySubService(Sub sub) {
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			subDao.waterUpdate(conn, new Sub(sub.getNo(), sub.getWater_ind()));
			conn.commit();

			subDao.eleUpdate(conn,
					new Sub(sub.getNo(), sub.getEle_basic(), sub.getEle_ind()));
			conn.commit();

			sub = subDao.getSubByNo(conn, sub.getNo());
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		return sub;
	}

	public boolean deleteSubByNo(int no) {
		boolean result = false;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			result = subDao.deleteSubByNo(conn, no);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;

	}
	public int subAllCount(String search) {
		int result = 0;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			result = subDao.subAllCount(conn, search);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;

	}

}