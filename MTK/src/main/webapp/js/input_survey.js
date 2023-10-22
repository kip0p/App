var elmSubmit = document.getElementById("ID_SUBMIT");
elmSubmit.onclick = function(){
  var elmEXAM_COUNT     = document.getElementById("ID_EXAM_COUNT");
  var elmMessage = document.getElementById("ID_EXAM_MESSAGE");
  var canSubmit  = true;
  if(elmEXAM_COUNT.value == ""  || !(elmEXAM_COUNT.match("^[0-9]+$")) || elmMessage.value == "" ){
    alert("不正な入力項目があります。");
    canSubmit = false;
  }
  return canSubmit;
}
