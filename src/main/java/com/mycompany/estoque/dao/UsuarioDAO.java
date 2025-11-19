/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estoque.dao;

import com.mycompany.estoque.database.Conexao;
import com.mycompany.estoque.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author npc12
 */
public class UsuarioDAO {
    private Connection conn;

    public Usuario autenticar(String email, String senha){
        
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        
        try(Connection con = Conexao.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql)
                ){
            
            ps.setString(1, email);
            ps.setString(2, senha);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setRole(rs.getString("role"));
                
                return u;
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return null;        
    }
}
