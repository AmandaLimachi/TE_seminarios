<%-- 
    Document   : editar TE_semonarios
    Created on : 9 nov. 2021, 18:50:07
    Author     : WINDOWS 10
--%>


<%@page import="com.emergentes.modelo.Seminario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Seminario pro=(Seminario) request.getAttribute("pro");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><c:if test ="${pro.id == 0}">
                Nuevo 
        </c:if>
                <c:if test ="${pro.id != 0}">
                Editar 
        </c:if>
            </h1>
        <form action="MainController" method="post">
             <input type="hidden" name="id" value="${pro.id}">
            <table>
                <tr>
                    <td>TITULO</td>
                    <td><input type="text" name="titulo" value="${pro.titulo}"></td>
                </tr>
                <tr>
                    <td>EXPOSITOR</td>
                    <td><input type="text" name="expositor" value="${pro.expositor}"></td>
                </tr>
                <tr>
                    <td>FECHA</td>
                    <td><input type="text" name="fecha" value="${pro.fecha}"></td>
                </tr>
                <tr>
                    <td>HORA</td>
                    <td><input type="text" name="hora" value="${pro.hora}"></td>
                </tr>
               <tr>
                    <td>CUPO</td>
                    <td><input type="number" name="cupo"  value="${pro.cupo}"></td>
                </tr>
                
                <tr>
                    <td></td>
                    <td><input type="submit" value="ENVIAR"></td>
                </tr>
                
            </table>
        </form>
    </body>
</html>

