<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"      %>
<%@ page import="model.SurveyDto"      %>

<%--
-------------------------------------------------------------------------------------------------
■■■ファイル名：show_all_survey.jsp■■■
概要：JSP
詳細：HTML文書（回答一覧画面）を出力する。
-------------------------------------------------------------------------------------------------
--%>

<%
//「survey」テーブルからデータを全件抽出
List<SurveyDto> list = (List<SurveyDto>)request.getAttribute("ALL_SURVEY_LIST");
%>


<html>
  <head>
    <title>MTK</title>
  </head>
  <body>
    <h2>受験資格一覧</h2>
    <table class="surbey_list" border=1>
      <tr bgcolor="#c0c0c0">
        <th>選択</th>
        <th>名前</th>
        <th>試験名</th>
        <th>試験時間</th>
        <th>難易度</th>
        <th>受験回数</th>
        <th>勉強時間</th>
        <th>役に立った教材</th> 
        <th>役に立ったサイト</th> 
        <th>勉強方法</th> 
        <th>試験感想</th> 
        <th>登録日</th>
      </tr>
      <form action="DeleteSurvey" method="post">
<%
for (int i = 0; i < list.size(); i++) {
	SurveyDto dto = list.get(i);
%>
      <tr>
        <td>  
        <label>
    <input type="checkbox" name="chk" value="<%= i %>" id="ID_chk">
       </label>
       </td>
        <td><%= replaceEscapeChar(dto.getCANDIDATE_NAME()) %></td>
        <td><%= replaceEscapeChar(dto.getEXAM_NAME()) %></td>
        <td><%= dto.getDO_EXAM_TIME()%></td>
        

<% 
	switch (dto.getEXAM_LEVEL()) {
	case 1:
%>
        <td>簡単</td>
<%
		break;
	case 2:
%>
        <td>普通</td>
<%
		break;
	case 3:
%>
        <td>難しい</td>
<%
		break;
	}
%>
        <td><%= dto.getDO_EXAM_COUNT() %></td>
        <td><%= dto.getEXAM_LEARN_DAY() %></td>
        <td><%= replaceEscapeChar(dto.getEXAM_TEXT()) %></td>
        <td><%= replaceEscapeChar(dto.getEXAM_SITE()) %></td>
        <td><%= replaceEscapeChar(dto.getHOW_TRAIN()) %></td>
        <td><%= replaceEscapeChar(dto.getEXAM_MESSAGE()) %></td>
        <td><%= dto.getUPDATE_TIME() %></td>
      </tr>
<%
}
%>
    </table>
    <br>
    <a href="InputSurvey">登録画面に戻る</a>
  <br>
  
    
    <input type="submit" value="登録を削除する" id="ID_SUBMIT">
  </form>
  <script type="text/javascript" src="js/delete_survey.js"></script>
  <br>
  </body>
</html>

<%!
/**----------------------------------------------------------------------*
 *■■■replaceEscapeCharクラス■■■
 *概要：文字列データのエスケープを行う
 *----------------------------------------------------------------------**/
String replaceEscapeChar(String inputText) {

	String charAfterEscape = inputText ; //エスケープ後の文字列データ

	// 「&」を変換
	charAfterEscape = charAfterEscape.replace("&", "&amp;");
	// 「<」を変換
	charAfterEscape = charAfterEscape.replace("<", "&lt;");
	// 「>」を変換
	charAfterEscape = charAfterEscape.replace(">", "&gt;");
	// 「"」を変換
	charAfterEscape = charAfterEscape.replace("\"", "&quot;");
	// 「'」を変換
	charAfterEscape = charAfterEscape.replace("'", "&#039;");

	return charAfterEscape;
}
%>