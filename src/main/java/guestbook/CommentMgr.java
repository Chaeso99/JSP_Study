package guestbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class CommentMgr 
{

	private DBConnectionMgr pool;
	private final SimpleDateFormat SDF_DATE =
			new SimpleDateFormat("yyyy'년'  M'월' d'일' (E)");
	
	public CommentMgr() 
	{
		pool = DBConnectionMgr.getInstance();
	}
	
	//Comment Insert
	//insert tblComment(num,cid,comment,cip,cregDate)values(?,?,?,?,now())	
	public void insertComment(CommentBean bean)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try 
		{
			con = pool.getConnection();
			sql = "insert tblComment(num,cid,comment,cip,cregDate)"
					+ "values(?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bean.getNum());
			pstmt.setString(2, bean.getCid());
			pstmt.setString(3, bean.getComment());
			pstmt.setString(4, bean.getCip());
			
			pstmt.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			pool.freeConnection(con, pstmt);
		}
	}
	
	//Comment List
	//select * from tblComment where num = ?
	public Vector<CommentBean> listComment(int num)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<CommentBean> vlist = new Vector<CommentBean>();
		try {
			con = pool.getConnection();
			sql = "select cnum, num, cid, comment, "
					+ "cip, cregDate from tblComment where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while(rs.next()) 
			{
				CommentBean bean = new CommentBean();
				bean.setCnum(rs.getInt("cnum")); //댓글번호
				bean.setNum(rs.getInt("num")); //방명록번호
				bean.setCid(rs.getString("cid")); //댓글작성자ID
				bean.setComment(rs.getString("comment")); //댓글내용
				bean.setCip(rs.getString("cip")); //댓글작성자IP
				bean.setCregDate(SDF_DATE.format
						(rs.getDate("cregDate"))); //댓글작성시간
				vlist.addElement(bean);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		finally 
		{
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	//Comment Delete
	//delete from tblComment where cnum = ?
	public void deleteComment(int cnum)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try 
		{
			con = pool.getConnection();
			sql = "delete from tblComment where cnum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cnum);
			pstmt.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			pool.freeConnection(con, pstmt);
		}
	}
	
	//Comment All Delete
	//delete from tblComment where num = ?
	public void deleteAllComment(int num) 
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try 
		{
			con = pool.getConnection();
			sql = "delete from tblComment where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			pool.freeConnection(con, pstmt);
		}
	}
}