package com.coding404.myweb.trip.service;

import java.util.ArrayList;

import com.coding404.myweb.command.TripVO;

public interface TripService {

	public int noticeRegist(TripVO vo); //등록
	public ArrayList<TripVO> getTripList(); //조회
	public TripVO getContent(int tno); //상세조회
	public int noticeModify(TripVO vo); //수정
	public int noticeDelete(int tno); //삭제
	
	public void upHit(int tno); //조회수
	public ArrayList<TripVO> getPrevNext(int tno); //이전글, 다음글
}
