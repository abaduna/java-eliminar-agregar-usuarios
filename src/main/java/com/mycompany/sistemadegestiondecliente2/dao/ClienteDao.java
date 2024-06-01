/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadegestiondecliente2.dao;



import com.mycompany.sistemadegestiondecliente2.models.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author artur
 */
public class ClienteDao {
    public Connection conectar(){
            String baseDeDatos = "java";
            String usuario = "root";
            String password = "1234";
            String hosting = "localhost";
            String puerto = "3306";
            String driver = "com.mysql.jdbc.Driver";
            String conexionUrl = "jdbc:Mysql://" + hosting + ":" + puerto + "/" + baseDeDatos + "?useSSL=false";
            
            Connection conexion = null;
            
            try {
              Class.forName(driver);
              conexion =  DriverManager.getConnection(conexionUrl, usuario, password);
            } catch (Exception ex) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return conexion;
            
           
        }
        public void agregar(Cliente a){
          
            Connection conexion = conectar();
            String sql = "INSERT INTO `java`.`clientes`(`nombre`, `apellido`, `telefono`, `email`) VALUES ('" +a.getNombre() +"', '"+a.getApellido()+"', '"+a.getTelefono()+"',  '"+a.getEmail()+"');";
            try {
                Statement statement = conexion.createStatement();
                statement.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        public List<Cliente> Listar(){
            
                Connection conexion = conectar();
                List<Cliente> Listado = new ArrayList<>();
            
            
            String sql = "SELECT * FROM java.clientes;";
            try {
                Statement statement = conexion.createStatement();
                ResultSet resultado = statement.executeQuery(sql);
                while(resultado.next()){
                    Cliente cliente = new Cliente();
                    cliente.setNombre(resultado.getString("nombre"));
                    cliente.setApellido(resultado.getString("apellido"));
                    cliente.setTelefono(resultado.getString("telefono"));
                    cliente.setEmail(resultado.getString("email"));
                    cliente.setId(resultado.getString("id"));
                    Listado.add(cliente);
                }
            } catch (Exception ex) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return Listado;
        }
        public void eliminar(String id){
           
            Connection conexion = conectar();
            String sql = "DELETE FROM `clientes` WHERE `clientes`.`id` = " + id;
            try {
                Statement statement = conexion.createStatement();
                statement.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
}
