package com.servlet;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;


@WebServlet("/SignServlet")
public class SignServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

 

  public SignServlet() {

    super();  

  }


  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException

  {

    response.setContentType("text/html;charset=UTF-8");

    PrintWriter out = response.getWriter();

    String firstName = request.getParameter("firstName");

    String lastName = request.getParameter("lastName");

    String email = request.getParameter("email");

    String password = request.getParameter("password");

    String contactNo = request.getParameter("contactNo");
    
    String dob = request.getParameter("dob");

    int result = 0;

    try

    {

      Class.forName("com.mysql.cj.jdbc.Driver");

      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel", "root", "mysql");

      String query = "INSERT INTO users (first_name, last_name, email, password, contact_no, dob) VALUES (?, ?, ?, ?, ?, ?)";

      PreparedStatement preparedStatement = con.prepareStatement(query);

      preparedStatement.setString(1, firstName);

      preparedStatement.setString(2, lastName);

      preparedStatement.setString(3, email);

      preparedStatement.setString(4, password);

      preparedStatement.setString(5, contactNo);

      preparedStatement.setString(6, dob);

      System.out.println(preparedStatement);

      result = preparedStatement.executeUpdate();

      if (result > 0)

      {

    	  out.println("<h2>Registered Sucessfully ");
    	  RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
    	  rd.include(request, response);
    //    response.sendRedirect("main.jsp");

      }

      else

      {

        out.println("Failed...Please try again");
        RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
  	    rd.include(request, response);

      }

      con.close();

    }

    catch (Exception e)

    {

      e.printStackTrace();
    }

    out.close();

  }

}















