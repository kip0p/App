package model;

import java.sql.Timestamp;

/**----------------------------------------------------------------------*
 *■■■SurveyDtoクラス■■■
 *概要：DTO（「CERT_INFO」テーブル）
 *----------------------------------------------------------------------**/
public class SurveyDto {

	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
	private String       CANDIDATE_NAME ;
	private int          DO_EXAM_TIME   ;
	private String       EXAM_NAME      ;
	private int          EXAM_LEVEL     ;
	private int          DO_EXAM_COUNT  ;
	private int          EXAM_LEARN_DAY ;
	private String       EXAM_TEXT      ;
	private String       EXAM_SITE      ;
	private String       HOW_TRAIN      ;
	private String       EXAM_MESSAGE   ;
	private Timestamp    UPDATE_TIME    ;
	//----------------------------------------------------------------
	//getter/setter
	//----------------------------------------------------------------
	public String getCANDIDATE_NAME() {
		return CANDIDATE_NAME;
	}
	public void setCANDIDATE_NAME(String cANDIDATE_NAME) {
		CANDIDATE_NAME = cANDIDATE_NAME;
	}
	public int  getDO_EXAM_TIME() {
		return DO_EXAM_TIME;
	}
	public void setDO_EXAM_TIME(int dO_EXAM_TIME) {
		DO_EXAM_TIME = dO_EXAM_TIME;
	}
	public String getEXAM_NAME() {
		return EXAM_NAME;
	}
	public void setEXAM_NAME(String eXAM_NAME) {
		EXAM_NAME = eXAM_NAME;
	}
	public int getEXAM_LEVEL() {
		return EXAM_LEVEL;
	}
	public void setEXAM_LEVEL(int eXAM_LEVEL) {
		EXAM_LEVEL = eXAM_LEVEL;
	}
	public int getDO_EXAM_COUNT() {
		return DO_EXAM_COUNT;
	}
	public void setDO_EXAM_COUNT(int dO_EXAM_COUNT) {
		DO_EXAM_COUNT = dO_EXAM_COUNT;
	}
	public int getEXAM_LEARN_DAY() {
		return EXAM_LEARN_DAY;
	}
	public void setEXAM_LEARN_DAY(int eXAM_LEARN_DAY) {
		EXAM_LEARN_DAY = eXAM_LEARN_DAY;
	}
	public String getEXAM_TEXT() {
		return EXAM_TEXT;
	}
	public void setEXAM_TEXT(String eXAM_TEXT) {
		EXAM_TEXT = eXAM_TEXT;
	}
	public String getEXAM_SITE() {
		return EXAM_SITE;
	}
	public void setEXAM_SITE(String eXAM_SITE) {
		EXAM_SITE = eXAM_SITE;
	}
	public String getHOW_TRAIN() {
		return HOW_TRAIN;
	}
	public void setHOW_TRAIN(String hOW_TRAIN) {
		HOW_TRAIN = hOW_TRAIN;
	}
	public String getEXAM_MESSAGE() {
		return EXAM_MESSAGE;
	}
	public void setEXAM_MESSAGE(String eXAM_MESSAGE) {
		EXAM_MESSAGE = eXAM_MESSAGE;
	}
	public Timestamp getUPDATE_TIME() {
		return UPDATE_TIME;
	}
	public void setUPDATE_TIME(Timestamp uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}

	
}
