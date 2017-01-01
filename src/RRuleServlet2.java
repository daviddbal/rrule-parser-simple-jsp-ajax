

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jfxtras.icalendarfx.properties.component.recurrence.RecurrenceRule;
import jfxtras.icalendarfx.properties.component.time.DateTimeStart;

/**
 * Servlet implementation
 */
@WebServlet("/RRuleServlet2")
public class RRuleServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LS = "<br>";
	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String rruleContent = request.getParameter("rruleContent");
		int limit = Integer.parseInt(request.getParameter("limit"));
		DateTimeStart dateTimeStart = DateTimeStart.parse(request.getParameter("dateTimeStart"));
		
		String rrules;
		try {
		RecurrenceRule rrule = RecurrenceRule.parse(rruleContent);
		rrules = rrule.getValue().streamRecurrences(dateTimeStart.getValue())
				.limit(limit)
				.map(t -> t.toString())
				.collect(Collectors.joining(LS));
		} catch (Exception e)
		{
			rrules = "Invalid";
		}
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.print("Recurrence Series" + LS + rrules);
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public RRuleServlet2() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

}