package com.project.service;

import java.util.List;

import com.project.entity.Report;

public interface ReportService {

	public Report addReportToEmployee(int id, Report report) throws Exception;

	public Report addReportToIntern(int id,Report report) throws Exception;

		public Report updateReport(Report report);

		public List<Report> getReports();

		public Report getReport(int Id);

		public void deleteReport(int id);

}
