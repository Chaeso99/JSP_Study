package guestbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class GuestBookMgr {

	private DBConnectionMgr pool;
	private final SimpleDateFormat SDF_DATE =
			new SimpleDateFormat("yyyy'년'  M'월' d'일' (E)");
	private final SimpleDateFormat SDF_TIME =
			new SimpleDateFormat("H:mm:ss");
	
	public GuestBookMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	//Join Login 
	//select id from tblJoin where id = ? and pwd = ?
	public boolean loginJoin(String id, String pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select id from tblJoin where id = ? and pwd = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				flag = true;
			}
			//flag = rs.next();//결과값 있다면 true, 없다면 false
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	
	//Join Information
	//select * from tblJoin where id = ?
	public JoinBean getJoin(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		JoinBean bean = new JoinBean();
		try {
			con = pool.getConnection();
			sql = "select * from tblJoin where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();			
			if(rs.next())
			{
				bean.setId(rs.getString("id"));
				bean.setPwd(rs.getString("pwd"));
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setHp(rs.getString("hp"));
				bean.setGrade(rs.getString("grade"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
	
	//GuestBook List
	public Vector<GuestBookBean> listGuestBook(String id, String
			grade) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<GuestBookBean> vlist = new Vector<GuestBookBean>();
		
		try {
			con = pool.getConnection();
			//관리자인지 아닌지 판단
			if(grade.equals("1")) //관리자
			{
				sql = "select * from tblGuestBook order by num desc";
				pstmt = con.prepareStatement(sql);
			}
			else if(grade.equals("0")) //일반 사용자
			{
				sql = "select * from tblGuestBook "
						+ "where id = ? or secret = '0' order by num desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestBookBean bean = new GuestBookBean();
				bean.setNum(rs.getInt("num"));
				bean.setId(rs.getString("id"));
				bean.setContents(rs.getString("contents"));
				bean.setIp(rs.getString("ip"));
				String tempDate = SDF_DATE.format(rs.getDate("regDate"));
				bean.setRegdate(tempDate);
				String tempTime = SDF_TIME.format(rs.getTime("regTime"));
				bean.setRegtime(tempTime);
				bean.setSecret(rs.getString("secret"));
				vlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	//GuestBook Get-하나의 방명록 가져오기
	public GuestBookBean getGusetBook(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		GuestBookBean bean = new GuestBookBean();
		
		try {
			con = pool.getConnection();
			sql = "select * from tblGuestBook where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bean.setNum(rs.getInt("num"));
				bean.setId(rs.getString("id"));
				bean.setContents(rs.getString("contents"));
				bean.setIp(rs.getString("ip"));
				String tempDate = SDF_DATE.format(rs.getDate("regDate"));
				bean.setRegdate(tempDate);
				String tempTime = SDF_TIME.format(rs.getTime("regTime"));
				bean.setRegtime(tempTime);
				bean.setSecret(rs.getString("secret"));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
	
	//GuestBook Insert
	//sql = "insert tblGuestBook(id, contents, ip, regdate,regtime,secret) 
	//values(?, ?, ?, now(), now(), ?)";
	public void insertGuestBook(GuestBookBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			con = pool.getConnection();
			sql = "insert tblGuestBook(id, contents, ip, regdate, regtime, secret) "
					+ "values(?, ?, ?, now(), now(), ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getContents());
			pstmt.setString(3, bean.getIp());
			pstmt.setString(4, bean.getSecret());
			
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	//GuestBook Update
	//sql = "update tblGuestBook set contents = ?, ip = ?, secret = ? where num = ?";
	public void updateGuestBook(GuestBookBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = pool.getConnection();
			sql = "update tblGuestBook set contents = ?, ip = ?, secret = ?"
					+ " where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getContents());
			pstmt.setString(2, bean.getIp());
			pstmt.setString(3, bean.getSecret());
			pstmt.setInt(4, bean.getNum());
			
			pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	//GuestBook Delete
	//"delete from tblGuestBook where num=?";
	public void deleteGuestBook(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = pool.getConnection();
			sql = "delete from tblGuestBook where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	
}