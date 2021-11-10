package com.emergentes.controlador;


import com.emergentes.modelo.Seminario;
import com.emergentes.utiles.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       PreparedStatement ps;
      ConexionDB canal=new ConexionDB();
      Connection conn =canal.conectar();
      ResultSet rs;
      String op;
      ArrayList<Seminario> lista =new ArrayList<Seminario>();
      
      int id;
      
      op=(request.getParameter("op")!=null)?request.getParameter("op"):"list";
      
          if (op.equals("list")) {
            String sql="select *from seminarios" ; 
          try {
              //consulata de selecccion y alamacenamiento
              ps=conn.prepareStatement(sql);
              rs=ps.executeQuery();
              while(rs.next()){
                
            Seminario pro= new Seminario();
            
            pro.setId(rs.getInt("id"));
            pro.setTitulo(rs.getString("titulo"));
            pro.setExpositor(rs.getString("expositor"));
            pro.setFecha(rs.getString("fecha"));
            pro.setHora(rs.getString("hora"));
            pro.setCupo(rs.getInt("cupo"));
            lista.add(pro);
            
            }
            request.setAttribute("lista", lista);
            request.getRequestDispatcher("index.jsp").forward(request, response);  
            
          } catch (SQLException ex) {
              Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
          }
            
          }
          
          
          if (op.equals("nuevo")) {
              Seminario li = new Seminario();
              request.setAttribute("pro", li);
              request.getRequestDispatcher("editar.jsp").forward(request, response);
          }
          
          
          if (op.equals("editar")) {
           id=Integer.parseInt(request.getParameter("id"));
           try{
               Seminario lib1= new Seminario();
               ps=conn.prepareStatement("select * from seminarios where id=?");
               ps.setInt(1, id);
               rs= ps.executeQuery();
               if (rs.next()) {
                lib1.setId(rs.getInt("id"));
                lib1.setTitulo(rs.getString("titulo"));
                lib1.setExpositor(rs.getString("expositor"));
                lib1.setFecha(rs.getString("fecha"));
                lib1.setHora(rs.getString("hora"));
                lib1.setCupo(rs.getInt("cupo"));
               }
               request.setAttribute("pro", lib1);
               request.getRequestDispatcher("editar.jsp").forward(request, response);
               
           }catch(SQLException ex){
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null,ex);
           }
          }
          
          
          if (op.equals("eliminar")) {
             id=Integer.parseInt(request.getParameter("id"));
          try {
              ps=conn.prepareStatement("delete from seminarios where id=?");
              ps.setInt(1,id);
              
              ps.executeUpdate();
              response.sendRedirect("MainController");
             } catch (SQLException ex) {
              Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
             }
             
            }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            int id=Integer.parseInt(request.getParameter("id"));
            String titulo=request.getParameter("titulo");
            String expositor=request.getParameter("expositor");
            String fecha=request.getParameter("fecha");
            String hora=request.getParameter("hora");
            int cupo=Integer.parseInt(request.getParameter("cupo"));
            
            Seminario pro = new Seminario();
            pro.setId(id);
            pro.setTitulo(titulo);
            pro.setExpositor(expositor);
            pro.setFecha(fecha);
            pro.setHora(hora);
            pro.setCupo(cupo);
            
            ConexionDB canal=new ConexionDB();
            Connection conn =canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
                    
            if (id==0) {
                ///isertar registro
            String sql="insert into seminarios(titulo,expositor,fecha,hora,cupo)values(?,?,?,?,?)" ;  
            try {
                ps=conn.prepareStatement(sql);
                
                ps.setString(1, pro.getTitulo());
                ps.setString(2, pro.getExpositor());
                ps.setString(3, pro.getFecha());
                ps.setString(4, pro.getHora());
                ps.setInt(5, pro.getCupo());
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            }else{
              //actualizar de registro .............................
            String sql="update seminarios set titulo=?,expositor=?,fecha=?,hora=?,cupo=? where id=?";
            try {
                ps = conn.prepareStatement(sql);
                
                ps.setString(1, pro.getTitulo());
                ps.setString(2, pro.getExpositor());
                ps.setString(3, pro.getFecha());
                ps.setString(4, pro.getHora());
                ps.setInt(5, pro.getCupo());
                ps.setInt(6, pro.getId());
                
                ps.executeUpdate();
                 
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
                response.sendRedirect("MainController");
       
    }
    

    

}
