package ch15;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class BCommentMgr {

	private DBConnectionMgr pool;
	
	public BCommentMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	//Comment Insert 댓글 입력
	//(read.jsp에서 호출)
	public void insertBComment(BCommentBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = pool.getConnection();
			sql = "insert tblBComment(num, name, comment, regdate) "
					+ "values(?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bean.getNum());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getComment());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return;
	}
	
	//Comment List 댓글 리스트
	//(read.jsp에서 호출)
	public Vector<BCommentBean>getBComment(int num){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<BCommentBean> vlist = new Vector<BCommentBean>();
		
		try {
			con = pool.getConnection();
			sql = "select cnum, num, name, comment, regdate from tblBComment "
					+ "where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BCommentBean bean = new BCommentBean();
				bean.setCnum(rs.getInt("cnum"));
				bean.setName(rs.getString("name"));
				bean.setComment(rs.getString("comment"));
				bean.setRegdate(rs.getString("regdate"));
				vlist.addElement(bean);
				}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	
	//Comment Count 게시물에 달린 댓글 수
	//(list.jsp에 필요)
	public int getCommentCount(int num){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int commentCount = 0;
		
		try {
			con = pool.getConnection();
			sql = "select count(num) from "
					+ "tblBComment where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				commentCount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		//System.out.println("Mgr.CommentCount"+commentCount);
		return commentCount;
	}
	
	//Comment Delete 댓글 삭제
	//(read.jsp에서 호출)
	public void deleteBComment(int cnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = pool.getConnection();
			sql = "delete from tblBComment where cnum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cnum);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return;
	}
	
	//Comment All Delete 게시물 삭제 시 달린 전체 댓글 삭제
	//(BoardDeleteServlet에서 호출)
	public void deleteAllBComment(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = pool.getConnection();
			sql = "delete from tblBComment where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return;
	}
}