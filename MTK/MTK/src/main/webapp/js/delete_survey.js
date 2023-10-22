var elmSubmit = document.getElementById("ID_SUBMIT");
elmSubmit.onclick = function(){
  var elmChk = document.getElementById("ID_chk");
  var canSubmit  = true;
  if(elmChk.value == "" ){
    alert("削除する項目を選択してください。");
    canSubmit = false;
  }
  return canSubmit;
}
