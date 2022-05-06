package sub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import jdbc.JdbcUtil;
import sub.model.Sub;
import sub.model.SubPaging;

public class SubDAO {
	private Sub sub = new Sub();
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public ArrayList<Sub> subSelectAll(Connection conn, Map pagingValues)
			throws SQLException {
		ArrayList<Sub> SubMixed = new ArrayList<>();
		SubPaging subPaging = (SubPaging) pagingValues.get("paging");
		System.out.println("test3");

		if (pagingValues.get("search") != null) {
			String search = (String) pagingValues.get("search");
			pstmt = conn.prepareStatement(
					"select t.a, t.b, t.c, t.d from ( select e.no a , w.water_ind b, e.ele_basic c, e.ele_ind d from ele e, water w where w.no = e.no group by e.no having  e.no like ? or w.water_ind like ? or e.ele_basic like ? or e.ele_ind like ?) as t limit ? offset ?");
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, "%" + search + "%");
			pstmt.setString(3, "%" + search + "%");
			pstmt.setString(4, "%" + search + "%");
			pstmt.setInt(5, subPaging.getLimit());
			pstmt.setInt(6,
					subPaging.getLimit() * (subPaging.getCurrentPage() - 1));
		} else {
			pstmt = conn.prepareStatement(
					"select w.no, w.water_ind, e.ele_basic, e.ele_ind from ele e, water w where w.no = e.no order by w.no limit ? offset ?");
			pstmt.setInt(1, subPaging.getLimit());
			pstmt.setInt(2,
					subPaging.getLimit() * (subPaging.getCurrentPage() - 1));
		}

		try {
			System.out.println("test5");
			
			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				sub = new Sub(rs.getInt(1), rs.getInt(2), rs.getInt(3),
						rs.getInt(4));
				SubMixed.add(sub);
				i++;
			}
		} catch (Exception e) {
			e.getMessage();
			System.out.println(" error :  subSelectAll");
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return SubMixed;
	}

	public Sub eleSelectByNo(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select *from ele where no =?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sub = new Sub(rs.getInt(1), rs.getInt(2), rs.getInt(3),
						rs.getInt(4));
			}
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
			System.out.println(" error :  eleSelectByNo");
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return sub;
	}

	public Sub waterSelectByNo(Connection conn, int no) throws SQLException {
		try {
			pstmt = conn.prepareStatement("select *from water where no =?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				sub = new Sub(rs.getInt(1), rs.getInt(2));
			}

		} catch (Exception e) {
			e.getMessage();
			System.out.println(" error :  waterSelectByNo");
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return sub;
	}
	public void eleInsert(Connection conn, Sub sub) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("insert into ele values(?, ?,?)")) {
			pstmt.setInt(1, sub.getNo());
			pstmt.setInt(2, sub.getEle_basic());
			pstmt.setInt(3, sub.getEle_ind());
			pstmt.executeUpdate();
			JdbcUtil.close(pstmt);
		} catch (Exception e) {
			e.getMessage();
			System.out.println(" error :  eleInsert");
		}
	}
	public void waterInsert(Connection conn, Sub sub) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("insert into water values(?,?)")) {

			pstmt.setInt(1, sub.getNo());
			pstmt.setInt(2, sub.getWater_ind());
			pstmt.executeUpdate();
			JdbcUtil.close(pstmt);
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
			System.out.println(" error :  waterInsert");
		}
	}

	public void autoSubInsert(Connection conn, int num) throws SQLException {
		SubDAO subdao = new SubDAO();
		int i = 0;
		int j = 0;
		while (i <= num) {
			while (true) {
				if (subdao.checkIsSubNullByNo(conn, j)) {
					++i;
					try (PreparedStatement pstmt = conn.prepareStatement(
							"insert into water values(?,?)")) {
						pstmt.setInt(1, j);
						pstmt.setInt(2, 0);
						pstmt.executeUpdate();
					} catch (Exception e) {
					}
					try (PreparedStatement pstmt = conn.prepareStatement(
							"insert into ele values(?,?,?)")) {
						pstmt.setInt(1, j);
						pstmt.setInt(2, 0);
						pstmt.setInt(3, 0);
						pstmt.executeUpdate();
					} catch (Exception e) {
						System.out.println("error");
					}
					conn.commit();
					break;
				}
				j++;
			}
		}
		System.out.println("test1");
		// System.out.println("test1");
		JdbcUtil.close(pstmt);
	}

	public void eleUpdate(Connection conn, Sub Sub) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"update ele set ele_basic=?, ele_ind=? where no=?")) {
			pstmt.setInt(1, Sub.getEle_basic());
			pstmt.setInt(2, Sub.getEle_ind());
			pstmt.setInt(3, Sub.getNo());
			pstmt.executeUpdate();
			JdbcUtil.close(pstmt);
		} catch (Exception e) {
			e.getMessage();
			System.out.println(" error :  eleUpdate");
		}
	}
	public void waterUpdate(Connection conn, Sub Sub) throws SQLException {

		try (PreparedStatement pstmt = conn
				.prepareStatement("update water set water_ind=? where no=?")) {
			pstmt.setInt(1, Sub.getWater_ind());
			pstmt.setInt(2, Sub.getNo());
			pstmt.executeUpdate();
			JdbcUtil.close(pstmt);
		} catch (Exception e) {
			System.out.println(" error :  waterUpdate");
			e.getMessage();
		} finally {

		}
	}

	public Sub getSubByNo(Connection conn, int no) throws SQLException {
		ArrayList<Sub> SubMixed = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(
					"select w.no, w.water_ind, e.ele_basic, e.ele_ind from ele e, water w where w.no = e.no having w.no = ? ");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sub = new Sub(rs.getInt(1), rs.getInt(2), rs.getInt(3),
						rs.getInt(4));
			}
		} catch (Exception e) {
			System.out.println(" error :  getSubByNo");
			e.getMessage();

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return sub;
	}

	public boolean checkIsSubNullByNo(Connection conn, int no) {
		boolean result = true;
		try {
			pstmt = conn.prepareStatement(
					"select w.no, w.water_ind, e.ele_basic, e.ele_ind from ele e, water w where w.no = e.no having w.no = ? ");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = false;
			}
		} catch (Exception e) {
			System.out.println(" error :  checkIsSubNullByNo");
			e.getMessage();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return result;
	}

	public boolean deleteSubByNo(Connection conn, int no) {
		boolean waterResult = true;
		boolean eleResult = true;
		try {
			pstmt = conn.prepareStatement("delete from water where no = ? ");
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (Exception e) {
			waterResult = false;
			System.out.println(" error :  deleteSubByNo_water");
			e.getMessage();
		}
		try {
			pstmt = conn.prepareStatement("delete from ele where no = ? ");
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (Exception e) {
			eleResult = false;
			System.out.println(" error :  deleteSubByNo_ele");
			e.getMessage();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		if (waterResult && eleResult)
			return true;
		else
			return false;
	}

	public int subAllCount(Connection conn, String search) {
		int result = 0;
		try {
			if (search != null) {
				pstmt = conn.prepareStatement(
						"select count(t.test) from ( select e.no test, w.water_ind, e.ele_basic, e.ele_ind from ele e, water w where w.no = e.no group by e.no having  e.no like ? or w.water_ind like ? or e.ele_basic like ? or e.ele_ind like ?) as t;");
				pstmt.setString(1, "%" + search + "%");
				pstmt.setString(2, "%" + search + "%");
				pstmt.setString(3, "%" + search + "%");
				pstmt.setString(4, "%" + search + "%");
			} else {
				pstmt = conn.prepareStatement(
						"select count(w.no) from ele e, water w where w.no = e.no");
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(" error :  subAllCount");
			e.getMessage();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		System.out.println("result : " + result);
		return result;
	}
}
