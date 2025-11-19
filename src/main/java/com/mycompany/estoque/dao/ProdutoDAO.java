/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estoque.dao;

import com.mycompany.estoque.database.Conexao;
import com.mycompany.estoque.model.Categoria;
import com.mycompany.estoque.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class ProdutoDAO {
    
    public void inserir(Produto p){
        String sql = "INSERT INTO produto ("
                + "nome, "
                + "id_categoria, "
                + "estoque, "
                + "preco) VALUES (?,?,?,?)";

        try(Connection con = Conexao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){
            
            ps.setString(1, p.getNome());
            ps.setInt(2, p.getCategoria().getId());
            ps.setInt(3, p.getEstoque());
            ps.setDouble(4, p.getPreco());
            ps.executeUpdate();
                
        } catch(SQLException e){
            System.out.println("Erro -> " + e);
        }
    }
    
    public List<Produto> listar(){
        List<Produto> lista = new ArrayList<>();
        
        String sql = "SELECT p.*, c.nome AS nome_categoria "
                + "FROM produto p JOIN categoria c ON "
                + "p.id_categoria = c.id ORDER BY p.id";
        
        try(Connection con = Conexao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setEstoque(rs.getInt("estoque"));
                
                Categoria c =  new Categoria(
                        rs.getInt("id_categoria"), 
                        rs.getString("nome_categoria"));
                
                p.setCategoria(c);
                                
                lista.add(p);
                       
            }
        }catch(SQLException e){
            System.out.println("Erro -> " + e);
        }
        return lista;
    }
    
    public void atualizar(int id, Produto produto){
        String sql = "UPDATE produto "
                + "SET nome = ?, "
                + "preco = ?, "
                + "id_categoria = ?, "
                + "estoque = ? "
                + "WHERE id = ?";
        
        try (Connection conn  = Conexao.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getCategoria().getId());
            ps.setInt(4, produto.getEstoque());
            ps.setInt(5,id);
            
            ps.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void deletar(int id){
        String sql = "DELETE from produto WHERE id = ?";
        
         try (Connection conn  = Conexao.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, id);
            ps.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
