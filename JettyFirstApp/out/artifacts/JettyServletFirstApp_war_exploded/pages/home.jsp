<%--
  Created by IntelliJ IDEA.
  User: cpu11118-local
  Date: 21/07/2017
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<head>
    <title>Title</title>
</head>
<body>
<form action="http://localhost:8080/JettyServletFirstApp_war_exploded/profile/mysite" method="post">
    <h1>Hello everybody, we coming Servlet and Jetty first app</h1>
    First Name: <input type = "text" name = "value1">
    <br />
    Last Name: <input type = "text" name = "value2" />
    <input type = "submit" value = "Submit" />
</form>
</body>
</html>
