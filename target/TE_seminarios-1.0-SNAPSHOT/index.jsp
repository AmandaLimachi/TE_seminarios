<%-- 
    Document   : index seminarios
    Created on : 9 nov. 2021, 18:22:50
    Author     : WINDOWS 10
--%>


<%@page import="com.emergentes.modelo.Seminario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%
List<Seminario> lista=(List<Seminario>) request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1" ALIGN="center">
            <tr>
                <th>SEGUNDO PARCIAL TEM-742 <br> Nombre:Amanda Estrella Limachi Ramos <br> Carnet:12451380</th>
                
            </tr> 
        </table>
        <h1 ALIGN="center">Registro de Seminarios</h1>
        <p> <a href="MainController?op=nuevo">Nuevo </a></p>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Titulo</th>
                <th>Expositor</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Cupo</th>
                <th></th>
                <th></th>
            </tr>    
                <c:forEach var="item" items="${lista}">
                <tr>
                    <td>${item.id}</td>
                     <td>${item.titulo}</td>
                     <td>${item.expositor}</td>
                     <td>${item.fecha}</td>
                     <td>${item.hora}</td>
                     <td>${item.cupo}</td>
                     <td><a href="MainController?op=editar&id=${item.id}">Editar</a></td>
                     <td><a href="MainController?op=eliminar&id=${item.id}"
                           onclick="return(confirm('Esta Seguro de Eliminar???..'))" >Eliminar</a></td> 
                </tr>
                </c:forEach>
        </table>
    </body>
</html>
