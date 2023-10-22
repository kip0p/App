package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**----------------------------------------------------------------------*
 *■■■SurveyDaoクラス■■■
 *概要：DAO（「CERT_INFO」テーブル）
 *----------------------------------------------------------------------**/
public class SurveyDao {
	//-------------------------------------------
	//データベースへの接続情報
	//-------------------------------------------

	//JDBCドライバの相対パス
	//※バージョンによって変わる可能性があります（MySQL5系の場合は「com.mysql.jdbc.Driver」）
	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	//接続先のデータベース
	//※データベース名が「test_db」でない場合は該当の箇所を変更してください
	String JDBC_URL    = "jdbc:mysql://localhost/test_db?characterEncoding=UTF-8&useSSL=false";

	//接続するユーザー名
	//※ユーザー名が「test_user」でない場合は該当の箇所を変更してください
	String USER_ID     = "test_user";

	//接続するユーザーのパスワード
	//※パスワードが「test_pass」でない場合は該当の箇所を変更してください
	String USER_PASS   = "test_pass";


	//----------------------------------------------------------------
	//メソッド
	//----------------------------------------------------------------

	/**----------------------------------------------------------------------*
	 *■doInsertメソッド
	 *概要　：テーブルに対象のアンケートデータを挿入する
	 *引数　：対象のデータ（SurveyDto型）
	 *戻り値：実行結果（真：成功、偽：例外発生）
	 *----------------------------------------------------------------------**/
	public boolean doInsert(SurveyDto dto) {

		//-------------------------------------------
		//JDBCドライバのロード
		//-------------------------------------------
		try {
			Class.forName(DRIVER_NAME);       //JDBCドライバをロード＆接続先として指定
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//-------------------------------------------
		//SQL発行
		//-------------------------------------------

		//JDBCの接続に使用するオブジェクトを宣言
		//※finallyブロックでも扱うためtryブロック内で宣言してはいけないことに注意
		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数

		//実行結果（真：成功、偽：例外発生）格納用変数
		//※最終的にreturnするため、tryブロック内で宣言してはいけないことに注意
		boolean isSuccess = true ;

		try {

			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//トランザクションの開始
			//-------------------------------------------
			//オートコミットをオフにする（トランザクション開始）
			con.setAutoCommit(false);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------

			//発行するSQL文の生成（INSERT）
			StringBuffer buf = new StringBuffer();
			buf.append("INSERT INTO CERT_INFO (  ");
			buf.append(" CANDIDATE_NAME,         ");
			buf.append(" DO_EXAM_TIME,           ");
			buf.append(" EXAM_NAME,              ");
			buf.append(" EXAM_LEVEL,             ");
			buf.append(" DO_EXAM_COUNT,          ");
			buf.append(" EXAM_LEARN_DAY,         ");
			buf.append(" EXAM_TEXT,              ");
			buf.append(" EXAM_SITE,              ");
			buf.append(" HOW_TRAIN,              ");
			buf.append(" EXAM_MESSAGE,           ");
			buf.append(" UPDATE_TIME             ");
			buf.append(") VALUES (               ");
			buf.append("  ?,                     ");
			buf.append("  ?,                     ");
			buf.append("  ?,                     ");
			buf.append("  ?,                     ");
			buf.append("  ?,                     ");
			buf.append("  ?,                     ");
			buf.append("  ?,                     ");
			buf.append("  ?,                     ");
			buf.append("  ?,                     ");
			buf.append("  ?,                     ");
			buf.append("  ?                      ");
			buf.append(")                        ");

			//PreparedStatementオブジェクトを生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());

			//パラメータをセット
			ps.setString(      1, dto.getCANDIDATE_NAME()  );
			ps.setInt(         2, dto.getDO_EXAM_TIME()    );
			ps.setString(      3, dto.getEXAM_NAME()       );
			ps.setInt(         4, dto.getEXAM_LEVEL()      );
			ps.setInt(         5, dto.getDO_EXAM_COUNT()   );
			ps.setInt(         6, dto.getEXAM_LEARN_DAY()  );
			ps.setString(      7, dto.getEXAM_TEXT()       );
			ps.setString(      8, dto.getEXAM_SITE()       );
			ps.setString(      9, dto.getHOW_TRAIN()       );
			ps.setString(      10, dto.getEXAM_MESSAGE()   );
			ps.setTimestamp(   11, dto.getUPDATE_TIME()    );
			
			//SQL文の実行
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

			//実行結果を例外発生として更新
			isSuccess = false ;

		} finally {
			//-------------------------------------------
			//トランザクションの終了
			//-------------------------------------------
			if(isSuccess){
				//明示的にコミットを実施
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}else{
				//明示的にロールバックを実施
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//-------------------------------------------
			//接続の解除
			//-------------------------------------------

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) {    //接続が確認できている場合のみ実施
				try {
					ps.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//Connectionオブジェクトの接続解除
			if (con != null) {    //接続が確認できている場合のみ実施
				try {
					con.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		//実行結果を返す
		return isSuccess;
	}

	
	
	public boolean doDelete(SurveyDto dto) {

		//-------------------------------------------
		//JDBCドライバのロード
		//-------------------------------------------
		try {
			Class.forName(DRIVER_NAME);       //JDBCドライバをロード＆接続先として指定
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//-------------------------------------------
		//SQL発行
		//-------------------------------------------

		//JDBCの接続に使用するオブジェクトを宣言
		//※finallyブロックでも扱うためtryブロック内で宣言してはいけないことに注意
		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数

		//実行結果（真：成功、偽：例外発生）格納用変数
		//※最終的にreturnするため、tryブロック内で宣言してはいけないことに注意
		boolean isSuccess = true ;

		try {

			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//トランザクションの開始
			//-------------------------------------------
			//オートコミットをオフにする（トランザクション開始）
			con.setAutoCommit(false);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------

			//発行するSQL文の生成（INSERT）
			StringBuffer buf = new StringBuffer();
			buf.append("DELETE FROM              ");
			buf.append(" CERT_INFO               ");
			buf.append(" WHERE                   ");
			buf.append(" CANDIDATE_NAME=? and  ");
			buf.append(" DO_EXAM_TIME=? and      ");
			buf.append(" EXAM_NAME=? and       ");
			buf.append(" EXAM_LEVEL=? and        ");
			buf.append(" DO_EXAM_COUNT=? and     ");
			buf.append(" EXAM_LEARN_DAY=? and    ");
			buf.append(" EXAM_TEXT=? and       ");
			buf.append(" EXAM_SITE=? and       ");
			buf.append(" HOW_TRAIN=? and       ");
			buf.append(" EXAM_MESSAGE=? ;       ");
			

			//PreparedStatementオブジェクトを生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());

			//パラメータをセット
			ps.setString(      1, dto.getCANDIDATE_NAME()  );
			ps.setInt(         2, dto.getDO_EXAM_TIME()    );
			ps.setString(      3, dto.getEXAM_NAME()       );
			ps.setInt(         4, dto.getEXAM_LEVEL()      );
			ps.setInt(         5, dto.getDO_EXAM_COUNT()   );
			ps.setInt(         6, dto.getEXAM_LEARN_DAY()  );
			ps.setString(      7, dto.getEXAM_TEXT()       );
			ps.setString(      8, dto.getEXAM_SITE()       );
			ps.setString(      9, dto.getHOW_TRAIN()       );
			ps.setString(      10, dto.getEXAM_MESSAGE()   );
			
			//SQL文の実行
			ps.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();

			//実行結果を例外発生として更新
			isSuccess = false ;

		} finally {
			//-------------------------------------------
			//トランザクションの終了
			//-------------------------------------------
			if(isSuccess){
				//明示的にコミットを実施
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}else{
				//明示的にロールバックを実施
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//-------------------------------------------
			//接続の解除
			//-------------------------------------------

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) {    //接続が確認できている場合のみ実施
				try {
					ps.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//Connectionオブジェクトの接続解除
			if (con != null) {    //接続が確認できている場合のみ実施
				try {
					con.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		//実行結果を返す
		return isSuccess;
	}

	


	/**----------------------------------------------------------------------*
	 *■doSelectメソッド
	 *概要　：テーブルのデータを全件抽出する
	 *引数　：なし
	 *戻り値：抽出結果（DTOリスト）
	 *----------------------------------------------------------------------**/
	public List<SurveyDto> doSelect() {

		//-------------------------------------------
		//JDBCドライバのロード
		//-------------------------------------------
		try {
			Class.forName(DRIVER_NAME);       //JDBCドライバをロード＆接続先として指定
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//-------------------------------------------
		//SQL発行
		//-------------------------------------------

		//JDBCの接続に使用するオブジェクトを宣言
		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数
		ResultSet         rs  = null ;   // ResultSet（SQL抽出結果）格納用変数

		//抽出結果格納用DTOリスト
		List<SurveyDto> dtoList = new ArrayList<SurveyDto>();

		try {

			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------

			//発行するSQL文の生成（SELECT）
			StringBuffer buf = new StringBuffer();
			buf.append("SELECT                ");
			buf.append(" CANDIDATE_NAME,      ");
			buf.append(" DO_EXAM_TIME,        ");
			buf.append(" EXAM_NAME,           ");
			buf.append(" EXAM_LEVEL,          ");
			buf.append(" DO_EXAM_COUNT,       ");
			buf.append(" EXAM_LEARN_DAY,      ");
			buf.append(" EXAM_TEXT,           ");
			buf.append(" EXAM_SITE,           ");
			buf.append(" HOW_TRAIN,           ");
			buf.append(" EXAM_MESSAGE,        ");
			buf.append(" UPDATE_TIME          ");
			buf.append("FROM                  ");
			buf.append("  CERT_INFO           ");
			buf.append("ORDER BY              ");
			buf.append("  UPDATE_TIME         ");

			ps = con.prepareStatement(buf.toString());
			rs = ps.executeQuery();

			//ResultSetオブジェクトからDTOリストに格納
			while (rs.next()) {
				SurveyDto dto = new SurveyDto();
				dto.setCANDIDATE_NAME(   rs.getString(      "CANDIDATE_NAME" ) );
				dto.setDO_EXAM_TIME(     rs.getInt(         "DO_EXAM_TIME"   ) );
				dto.setEXAM_NAME(        rs.getString(      "EXAM_NAME"      ) );
				dto.setEXAM_LEVEL(       rs.getInt(         "EXAM_LEVEL"     ) );
				dto.setDO_EXAM_COUNT(    rs.getInt(         "DO_EXAM_COUNT"  ) );
				dto.setEXAM_LEARN_DAY(   rs.getInt(         "EXAM_LEARN_DAY" ) );
				dto.setEXAM_TEXT(        rs.getString(      "EXAM_TEXT"      ) );
				dto.setEXAM_SITE(        rs.getString(      "EXAM_SITE"      ) );
				dto.setHOW_TRAIN(        rs.getString(      "HOW_TRAIN"      ) );
				dto.setEXAM_MESSAGE(     rs.getString(      "EXAM_MESSAGE"   ) );
				dto.setUPDATE_TIME(      rs.getTimestamp(   "UPDATE_TIME"    ) );
				dtoList.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//-------------------------------------------
			//接続の解除
			//-------------------------------------------

			//ResultSetオブジェクトの接続解除
			if (rs != null) {    //接続が確認できている場合のみ実施
				try {
					rs.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) {    //接続が確認できている場合のみ実施
				try {
					ps.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//Connectionオブジェクトの接続解除
			if (con != null) {    //接続が確認できている場合のみ実施
				try {
					con.close();  //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		//抽出結果を返す
		return dtoList;
	}

}
