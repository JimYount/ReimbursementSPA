package com.revature.delegates;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.beans.Submission;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceOracle;
import com.revature.services.SubmissionService;
import com.revature.services.SubmissionServiceOracle;

public class SubmitDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(SubmitDelegate.class);
	SubmissionService subserv = new SubmissionServiceOracle();
	EmployeeService empserv = new EmployeeServiceOracle();

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		log.trace(req.getMethod() + " received by submit delegate");

		switch (req.getMethod()) {
		case "POST":
			checkForm(req, resp);
			break;
		default:
			break;
		}
	}

	public void checkForm(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		log.trace("Submitting form.");

		Submission sub = new Submission();
		int status = 0;
		int employeeID = Integer.parseInt(req.getParameter("employeeID"));
		int departmentHead = empserv.getDepartmentHead(employeeID);
		int supervisorID = empserv.getSupervisorID(employeeID);

		sub.setEmployeeID(employeeID);
		sub.setEventTypeID(Integer.parseInt(req.getParameter("eventType")));
		sub.setEventDate(req.getParameter("eventYear") + "-" + req.getParameter("eventMonth") + "-"
				+ req.getParameter("eventDay"));
		sub.setEventCountry(req.getParameter("eventCountry"));
		sub.setEventState(req.getParameter("eventState"));
		sub.setEventCity(req.getParameter("eventCity"));
		sub.setDescription(req.getParameter("description"));
		sub.setReimAmount(Double.parseDouble(req.getParameter("reimAmount")));
		
		if (req.getParameter("gradePresent") == "1")
			sub.setGradePresNum(Double.parseDouble(req.getParameter("gradePresNum")));
		else if (req.getParameter("gradePresent") == "2")
			sub.setGradePresNum(-1);
		
		sub.setJustification(req.getParameter("justification"));
		
		if (req.getParameter("optEvtAttach") != "")
			sub.setOptEvtAttach(req.getParameter("optEvtAttach"));
		else
			sub.setOptEvtAttach(" ");
		
		if (req.getParameter("optDaysMissed").matches("[0-9]+"))
			sub.setOptDaysMissed(Integer.parseInt(req.getParameter("optDaysMissed")));
		else {
			sub.setOptDaysMissed(-1);
		}
		
		sub.setApprSubDate(java.time.LocalDate.now() + "");
		
		
		if (req.getParameter("preApproval").matches("[0-9]+"))
			status = Integer.parseInt(req.getParameter("preApproval"));
		else
			status = 0;
		
		status = (status & 1) + (status & 2) + (status & 4);
		
		log.trace("Submission Employee's Department Head: " + departmentHead);
		
		if (departmentHead == employeeID) {
			status = status | 2;
			}
		
		log.trace("Submisson Employee's Supervisor: " + supervisorID);
		
		if (supervisorID == -1)
			status = status | 1;
			
			sub.setStatus(status);
			
		sub.setAssignedHR(empserv.getNextHR(employeeID));

		subserv.makeSubmission(sub);
	}
}
