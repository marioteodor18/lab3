<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>

<br>
<br>
<a href="CounterServlet?inc=1">CounterServlet GET (inc=1)</a>
<br>
<a href="CounterServlet?dec=1">CounterServlet GET (dec=1)</a>

<form action="CounterServlet" method="GET">
    <br>inc: <input type="text" name="inc" value="1">
    <br>dec: <input type="text" name="dec" value="0">
    <br><input type="submit" value="CounterServlet GET">
</form>

<form action="CounterServlet" method="POST">
    <br>inc: <input type="text" name="inc" value="1">
    <br>dec: <input type="text" name="dec" value="0">
    <br><input type="submit" value="CounterServlet POST">
</form>
</body>
</html>