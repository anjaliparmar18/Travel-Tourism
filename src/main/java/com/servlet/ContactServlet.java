package com.servlet;

import java.io.IOException;

import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {

 private static final long serialVersionUID = 1L;


//    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/travel";
//
//    private static final String JDBC_USER = "root";
//
//    private static final String JDBC_PASSWORD = "mysql";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      response.setContentType("text/html;charset=UTF-8");

      PrintWriter out = response.getWriter();

      // Get form parameters

      String name = request.getParameter("name");

      String email = request.getParameter("email");

      String message = request.getParameter("message");

      try {

        // Connect to the database

        Class.forName("com.mysql.cj.jdbc.Driver");

      //  Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel", "root", "mysql");
        // SQL query to insert contact details

        String query = "INSERT INTO contact_us (name, email, message) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = con.prepareStatement(query);

        preparedStatement.setString(1, name);

        preparedStatement.setString(2, email);

        preparedStatement.setString(3, message);

        int result = preparedStatement.executeUpdate();

        if (result > 0) {

         response.sendRedirect("main.jsp");

        } else {

          out.println("<h1>Failed to send message. Please try again later.</h1>");

        }

        con.close();

      } catch (Exception e) {

        e.printStackTrace();

      }

      out.close();

    }

  }

























