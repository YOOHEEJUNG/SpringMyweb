package com.coding404.myweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coding404.myweb.command.TripVO;
import com.coding404.myweb.trip.service.TripMapper;

@RunWith(SpringJUnit4ClassRunner.class) // junit���� �׽�Ʈȯ���� ����
@ContextConfiguration("file:src/main/webapp/WEB-INF/config/root-context.xml") // ���۽�ų ������ ��������
public class PageTest {

	@Autowired
	TripMapper tripMapper;

	/*
	 * @Test public void testCode01() {
	 * 
	 * for (int i = 1; i <= 300; i++) { TripVO VO = new TripVO(0, "2023-02-08",
	 * "admin" + i, "test" + i, "example" + i, 0, null);
	 * tripMapper.noticeRegist(VO); }
	 * 
	 * }
	 */
	
}
