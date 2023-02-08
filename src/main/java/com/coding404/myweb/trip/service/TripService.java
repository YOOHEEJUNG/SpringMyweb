package com.coding404.myweb.trip.service;

import java.util.ArrayList;

import com.coding404.myweb.command.TripVO;

public interface TripService {

	public int noticeRegist(TripVO vo); //���
	public ArrayList<TripVO> getTripList(); //��ȸ
	public TripVO getContent(int tno); //����ȸ
	public int noticeModify(TripVO vo); //����
	public int noticeDelete(int tno); //����
	
	public void upHit(int tno); //��ȸ��
	public ArrayList<TripVO> getPrevNext(int tno); //������, ������
}
