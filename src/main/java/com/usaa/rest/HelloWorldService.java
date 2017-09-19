package com.usaa.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldService {
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMsg(@PathParam("param") String msg) throws SQLException, ClassNotFoundException {
		
		String url="jdbc:mysql://mysql:3306/sampledb";
		String uname="user";
		String pass="password";
		String query="SELECT * FROM potluck";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn= DriverManager.getConnection(url,uname,pass);
		Statement stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery(query);
		List<model> he = new ArrayList<model>();
		while(rs.next())
		{
			model obj = new model();
			obj.setName(rs.getString("name"));
			obj.setFood(rs.getString("food"));
			obj.setId(rs.getInt("id"));
			he.add(obj);
		}
		return Response.status(200).entity(he).build();

	}
}
