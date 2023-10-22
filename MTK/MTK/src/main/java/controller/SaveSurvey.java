package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SaveSurveyBL;
import model.SurveyDto;
import model.UserInfoDto;

/**----------------------------------------------------------------------*
 *■■■SaveSurveyクラス■■■
 *概要：サーブレット
 *詳細：リクエスト（アンケートデータ）を「survey」テーブルに登録し、画面遷移する。
 *　　　＜遷移先＞登録成功：回答完了画面（finish.html）／登録失敗：エラー画面（error.html）
 *----------------------------------------------------------------------**/
public class SaveSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveSurvey() {
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
			if( !(  validatePrmCANDIDATE_NAME(request.getParameter("CANDIDATE_NAME"))            &&
					validatePrmDO_EXAM_TIME(request.getParameter("DO_EXAM_TIME"))                &&
					validatePrmEXAM_NAME(request.getParameter("EXAM_NAME"))                      &&
					validatePrmEXAM_LEVEL(request.getParameter("EXAM_LEVEL"))                    &&
					validatePrmDO_EXAM_COUNT(request.getParameter("DO_EXAM_COUNT"))              &&
					validatePrmEXAM_LEARN_DAY(request.getParameter("EXAM_LEARN_DAY"))            &&
					validatePrmEXAM_TEXT(request.getParameter("EXAM_TEXT"))                      &&
					validatePrmEXAM_SITE(request.getParameter("EXAM_SITE"))                      &&
					validatePrmHOW_TRAIN(request.getParameter("HOW_TRAIN"))                      &&
					validatePrmEXAM_MESSAGE(request.getParameter("EXAM_MESSAGE")) 	            ) ) {

				//バリデーションNGの場合
				succesFlg =false ;

			}else {
				
				String       CANDIDATE_NAME    = request.getParameter("CANDIDATE_NAME"); 
				int          DO_EXAM_TIME      = Integer.parseInt( request.getParameter("DO_EXAM_TIME")); 
				String       EXAM_NAME         = request.getParameter("EXAM_NAME"); 
				int          EXAM_LEVEL        = Integer.parseInt( request.getParameter("EXAM_LEVEL"));
				int          DO_EXAM_COUNT     = Integer.parseInt( request.getParameter("DO_EXAM_COUNT"));
				int          EXAM_LEARN_DAY    = Integer.parseInt( request.getParameter("EXAM_LEARN_DAY"));
				String       EXAM_TEXT         = request.getParameter("EXAM_TEXT"); 
				String       EXAM_SITE         = request.getParameter("EXAM_SITE"); 
				String       HOW_TRAIN         = request.getParameter("HOW_TRAIN"); 
				String       EXAM_MESSAGE      = request.getParameter("EXAM_MESSAGE"); 
				

				//（SurveyDto型）の作成
				SurveyDto dto = new SurveyDto();
				dto.setCANDIDATE_NAME(CANDIDATE_NAME);
				dto.setDO_EXAM_TIME(DO_EXAM_TIME);
				dto.setEXAM_NAME(EXAM_NAME);
				dto.setEXAM_LEVEL(EXAM_LEVEL);
				dto.setDO_EXAM_COUNT(DO_EXAM_COUNT);
				dto.setEXAM_LEARN_DAY(EXAM_LEARN_DAY);
				dto.setEXAM_TEXT(EXAM_TEXT);
				dto.setEXAM_SITE(EXAM_SITE);
				dto.setHOW_TRAIN(HOW_TRAIN);
				dto.setEXAM_MESSAGE(EXAM_MESSAGE);
				dto.setUPDATE_TIME(new Timestamp(System.currentTimeMillis()) );   //現在時刻を更新時刻として設定

				//DBに登録
				SaveSurveyBL logic = new SaveSurveyBL();
				succesFlg          = logic.executeInsertSurvey(dto);  //成功フラグ（true:成功/false:失敗）

			}

			//成功/失敗に応じて表示させる画面を振り分ける
			if (succesFlg) {

				//成功した場合、回答完了画面（finish.html）を表示する
				response.sendRedirect("htmls/finish.html");

			} else {

				//失敗した場合、エラー画面（error.html）を表示する
				response.sendRedirect("htmls/error.html");

			}

		} else {
			//未ログイン：ログイン画面へ転送
			response.sendRedirect("Login");
		}
	}

	/**----------------------------------------------------------------------*
	 *■■■validatePrmクラス■■■
	 *概要：バリデーションチェック
	 *詳細：入力値（名前）の検証を行う
	 *----------------------------------------------------------------------**/
	private boolean validatePrmCANDIDATE_NAME( String pr) {

		boolean validateResult = true ;

		//入力値がnullまたは空白の場合はエラーとする
		if( pr == null || pr.equals("") ) {
			validateResult = false ;
		}

		return validateResult ;
	}


	private boolean validatePrmDO_EXAM_TIME( String pr) {

		boolean validateResult = true ;

		//入力値がnullまたは正の数以外の場合はエラーとする
		if( pr == null ) {
			validateResult = false ;
		}

		return validateResult ;
	}


	private boolean validatePrmEXAM_NAME( String pr) {

		boolean validateResult = true ;

		//入力値がnullまたは「1」「2」でない場合はエラーとする
		if( pr == null || pr.equals("") ) {
			validateResult = false ;
		}

		return validateResult ;
	}


	private boolean validatePrmEXAM_LEVEL( String pr) {

		boolean validateResult = true ;

		//入力値がnullまたは「1」「2」「3」でない場合はエラーとする
		if( pr == null || !( pr.equals("1") || pr.equals("2") || pr.equals("3") ) ) {
			validateResult = false ;
		}

		return validateResult ;
	}


	private boolean validatePrmDO_EXAM_COUNT( String pr) {

		boolean validateResult = true ;

		//入力値が空白またはnullの場合はエラーとする
		if( pr == null || pr == "") {
			validateResult = false ;
		}

		return validateResult ;
	}
	
	
	private boolean validatePrmEXAM_LEARN_DAY( String pr) {

		boolean validateResult = true ;

		//入力値が空白またはnullの場合はエラーとする
		if( pr == null || pr == "") {
			validateResult = false ;
		}

		return validateResult ;
	}
	
	private boolean validatePrmEXAM_TEXT( String pr) {

		boolean validateResult = true ;

		//入力値が空白またはnullの場合はエラーとする
		if( pr == null || pr == "") {
			validateResult = false ;
		}

		return validateResult ;
	}
	
	private boolean validatePrmEXAM_SITE( String pr) {

		boolean validateResult = true ;

		//入力値が空白またはnullの場合はエラーとする
		if( pr == null || pr == "") {
			validateResult = false ;
		}

		return validateResult ;
	}
	
	private boolean validatePrmHOW_TRAIN( String pr) {

		boolean validateResult = true ;

		//入力値が空白またはnullの場合はエラーとする
		if( pr == null || pr == "") {
			validateResult = false ;
		}

		return validateResult ;
	}
	
	private boolean validatePrmEXAM_MESSAGE( String pr) {

		boolean validateResult = true ;

		//入力値が空白またはnullの場合はエラーとする
		if( pr == null || pr == "") {
			validateResult = false ;
		}

		return validateResult ;
	}

}
