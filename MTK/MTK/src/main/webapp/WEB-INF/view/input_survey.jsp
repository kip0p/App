<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="model.UserInfoDto"      %>

<%--
-------------------------------------------------------------------------------------------------
■■■ファイル名：input_survey.jsp■■■
概要：JSP
詳細：HTML文書（回答入力画面）を出力する。
-------------------------------------------------------------------------------------------------
--%>

<%
//セッションからユーザーデータを取得
UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");
String userName  = userInfoOnSession.getUserName();
%>

<html>
<head>
  <title>ようこそMTKへ</title>
</head>
<body>
  <h2>試験登録画面</h2>
  <form action="SaveSurvey" method="post">
    <p>名前：<%= userName %>
      <input type="hidden" name="CANDIDATE_NAME"  value="<%= userName %>">
    </p>
    
    <p>試験名：
      <input type="text" name="EXAM_NAME" maxlength = "20" id="ID_EXAM_NAME">
    </p>
    
    <p>試験時間：
      <input type="number" name="DO_EXAM_TIME" maxlength = "2" id="ID_DO_EXAM_TIME">分
    </p>
    
    <p>難易度：
          <select name="EXAM_LEVEL"id=ID_EXAM_LEVEL>
        <option value="3">難しい      </option>
        <option value="2">普通        </option>
        <option value="1">簡単        </option>
      </select>
   </p>
    
    <p>受験回数：
      <input type="number" name="DO_EXAM_COUNT" maxlength = "2" id="ID_DO_EXAM_COUNT">
    </p>
    
    <p>勉強時間：
      <input type="number" name="EXAM_LEARN_DAY" maxlength = "2" id="ID_EXAM_LEARN_DAY">日
    </p>
    
    <p>試験勉強で役に立った教材をご記入ください：<br>
      <textarea name="EXAM_TEXT" rows="2" cols="50" maxlength = "150" id="ID_EXAM_TEXT"></textarea>
    </p>
    
    <p>試験勉強で役に立ったサイトをご記入ください：<br>
      <textarea name="EXAM_SITE" rows="2" cols="50" maxlength = "150" id="ID_EXAM_SITE"></textarea>
    </p>
    
    <p>勉強方法をご記入ください：<br>
      <textarea name="HOW_TRAIN" rows="4" cols="50" maxlength = "150" id="ID_HOW_TRAIN"></textarea>
    </p>

    <p>感想をご記入ください：<br>
      <textarea name="EXAM_MESSAGE" rows="4" cols="50" maxlength = "150" id="EXAM_MESSAGE"></textarea>
    </p>
    
    <input type="submit" value="登録する" id="ID_SUBMIT">
  </form>
  <script type="text/javascript" src="js/input_survey.js"></script>
  <br>
  <a href="ShowAllSurvey">登録一覧画面へ</a>
  <br>
  <a href="ExecuteLogout">ログアウトする</a>
</body>
</html>
