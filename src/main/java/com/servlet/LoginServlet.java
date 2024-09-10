package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public LoginServlet() {
        super();
        
    }


      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException

      {

          response.setContentType("text/html;charset=UTF-8");

          PrintWriter out = response.getWriter();

          String email = request.getParameter("email");

          String password = request.getParameter("password");

          try

          {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel", "root", "mysql");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");

            ps.setString(1, email);

            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next())

            {

              response.sendRedirect("main.jsp");

            }

            else

            {

              out.println("Invalid email or password.");

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

