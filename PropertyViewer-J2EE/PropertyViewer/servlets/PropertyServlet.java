package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import constants.IBuildingsConstants;
import sql.IBuildingConstants;

public class PropertyServlet extends GenericServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		
		res.setContentType(IBuildingsConstants.CONTENT_TYPE_TEXT_HTML);
		
		String bName = req.getParameter(IBuildingConstants.COLUMN_BUILDINGNAME);
		String bStreet = req.getParameter(IBuildingConstants.COLUMN_STREET);
		int bNumber = Integer.parseInt(req.getParameter(IBuildingConstants.COLUMN_NUMBER));
		int bPostCode =Integer.parseInt(req.getParameter(IBuildingConstants.COLUMN_POSTCODE));
		String bCity = req.getParameter(IBuildingConstants.COLUMN_CITY);
		String bCountry = req.getParameter(IBuildingConstants.COLUMN_COUNTRY);
		
		
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("insert into " + IBuildingConstants.TABLE_BUILDING + "  values(?,?,?,?,?,?)");
			ps.setString(1, bName);
			ps.setString(2, bStreet);
			ps.setInt(3, bNumber);
			ps.setInt(4, bPostCode);
			ps.setString(5,bCity);
			ps.setString(6, bCountry);
			
			int k = ps.executeUpdate();
			if(k==1)
			{
				RequestDispatcher rd = req.getRequestDispatcher("Property.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">New Property details updated successfully!<br/>Add More Properties</div>");
			}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher("Property.html");
				pw.println("<div class=\"tab\">Failed to Add new property! Fill up CareFully</div>");
				rd.include(req, res);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
