package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeleteSurveyBL;
import model.ShowAllSurveyBL;
import model.SurveyDto;
import model.UserInfoDto;



/**----------------------------------------------------------------------*
 *■■■SaveSurveyクラス■■■
 *概要：サーブレット
 *詳細：リクエスト（アンケートデータ）を「survey」テーブルに登録し、画面遷移する。
 *　　　＜遷移先＞登録成功：回答完了画面（finish.html）／登録失敗：エラー画面（error.html）
 *----------------------------------------------------------------------**/
public class DeleteSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteSurvey() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");     //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");                  //文字コードをUTF-8で設定

		//セッションからユーザーデータを取得
		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		//ログイン状態によって表示画面を振り分ける
		if (userInfoOnSession != null) {
			//ログイン済：登録処理＆結果画面への遷移を実施

			boolean succesFlg = true;  //成功フラグ（true:成功/false:失敗）
			
			
			
			
			//パラメータのバリデーションチェック
			if( !(validatePrmchk(request.getParameter("chk"))))        {

				//バリデーションNGの場合
				succesFlg = false ;

			}else {
			
			
			
			
			
			
				String[]          DEL_COUNT    =request.getParameterValues("chk"); //パラメータchkをリストで取得する（複数選択対応）
			
				int[] intArrays = Stream.of(DEL_COUNT).mapToInt(Integer::parseInt).toArray();//String配列をint配列に変換
				
				List<SurveyDto> list  = new ArrayList<SurveyDto>();
				ShowAllSurveyBL logic = new ShowAllSurveyBL();
				list = logic.executeSelectSurvey();  //listに一覧結果を代入
				
		        for(int intArray : intArrays) { //配列の要素ごとに削除処理を行う
		            
		        	SurveyDto dto = list.get(intArray);
		        	DeleteSurveyBL logic1 = new DeleteSurveyBL();
		        	
		        	succesFlg          = logic1.executeDeleteSurvey(dto);//成功フラグ（true:成功/false:失敗）
		        	
		        }
			}
			//成功/失敗に応じて表示させる画面を振り分ける
			if (succesFlg) {

				//成功した場合、回答完了画面（finish-del.html）を表示する
				response.sendRedirect("htmls/finish-del.html");

			} else {

				//失敗した場合、エラー画面（error-del.html）を表示する
				response.sendRedirect("htmls/error-del.html");

			}

		} else {
			//未ログイン：ログイン画面へ転送
			response.sendRedirect("Login");
		}
	}


		
		
	private boolean validatePrmchk( String pr) {

		boolean validateResult = true ;

		//入力値がnullまたは空白の場合はエラーとする
		if( pr == null || pr.equals("") ) {
			validateResult = false ;
		}

		return validateResult ;
	}
		
		
		
		

}
