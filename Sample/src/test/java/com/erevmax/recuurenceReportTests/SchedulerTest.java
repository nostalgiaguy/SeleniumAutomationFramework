package com.erevmax.recuurenceReportTests;

import org.testng.annotations.Test;

import com.erevmax.RTAdmin.pages.ReportRecurrenceQueryDetails;
import com.erevmax.utils.Verify;

public class SchedulerTest {
	
	ReportRecurrenceQueryDetails rr=new ReportRecurrenceQueryDetails("00045");

	@Test(priority=10,description="Checking hotel scheduler status in RT_MR_SCHEDULE table.")
	public void RT_MR_SCHEDULE_Test(){

		rr.get_RT_MR_SCHEDULE_tableDetail(rr.get_RT_MR_SCHEDULE_query());
		for(int count=0;count<rr.get_HOTEL_CODE.size();count++){
			Verify.assertEquals(rr.get_REPORT_BASE_DATE.get(count), rr.todayDateForDashboardDB());
			Verify.assertEquals(rr.get_STATUS.get(count),"COMPLETED");
			Verify.assertEquals(rr.get_AUTO_CE_NF_FETCH_STATUS.get(count),"COMPLETED");
		}
	}
	
	@Test(priority=20,description="Checking hotel scheduler status in RT_MR_SCHEDULE_RECURRENCE table.")
	public void RT_MR_SCHEDULE_RECURRENCE_Test(){

		rr.get_RT_MR_SCHEDULE_RECURRENCE_tableDetail(rr.get_RT_MR_SCHEDULE_RECURRENCE_query());
		for(int count=0;count<rr.get_HOTEL_CODE.size();count++){
			Verify.assertEquals(rr.get_REPORT_BASE_DATE.get(count), rr.todayDateForDashboardDB());
			Verify.assertEquals(rr.get_STATUS.get(count),"COMPLETED");
		}
	}
	
	@Test(priority=30,description="Checking hotel scheduler status in RT_MR_SHOPPING_COMBINATION table.")
		public void RT_MR_SHOPPING_COMBINATION_Test(){

			rr.get_RT_MR_SHOPPING_COMBINATION_tableDetail(rr.get_RT_MR_SHOPPING_COMBINATION_query());
			String expected_Schedule_ID=rr.checkDataBaseForString(rr.get_RT_MR_SCHEDULE_query(),"MR_Schedule_ID");
			for(int count=0;count<rr.get_HOTEL_CODE.size();count++){
				Verify.assertEquals(rr.get_REPORT_BASE_DATE.get(count), rr.todayDateForDashboardDB());
				Verify.assertEquals(rr.get_STATUS.get(count),"COMPLETED");
				Verify.assertEquals(rr.get_MR_SCHEDULE_ID.get(count), expected_Schedule_ID);
			}
		}
		
		//@Test(description="Checking hotel scheduler status in RT_MR_SHOPPING_COMBINATION_SLICE table.")
		public void RT_MR_SHOPPING_COMBINATION_SLICE_Test(){

			rr.get_RT_MR_SHOPPING_COMBINATION_SLICE_tableDetail(rr.get_RT_MR_SHOPPING_COMBINATION_SLICE_query());
			for(int count=0;count<rr.get_HOTEL_CODE.size();count++){
				Verify.assertEquals(rr.get_REPORT_BASE_DATE.get(count),"2014-12-16");
				Verify.assertEquals(rr.get_STATUS.get(count),"COMPLETED");
				Verify.assertEquals(rr.get_SHOP_QUEUE.get(count),"MR-VS-Q-1");
			}
			Verify.assertEquals(rr.checkDataBaseForInteger("select (SELECT SUM(report_days) FROM dbo.RT_MR_SHOPPING_COMBINATION where Report_Base_Date='2014-12-16'  AND hotel_code ='00045' AND schedule_id IN ('14107')) as report_days", "report_days"), rr.checkDataBaseForInteger("SELECT (select sum(NO_of_days) FROM dbo.RT_MR_SHOPPING_COMBINATION_SLICE where Report_Base_Date='2014-12-16'  AND hotel_code ='00045' AND combination_id IN(SELECT combination_id FROM dbo.RT_MR_SHOPPING_COMBINATION where Report_Base_Date='2014-12-16'  AND hotel_code ='00045' AND schedule_id IN ('14107')))as NO_of_days", "NO_of_days"));
		}

}
