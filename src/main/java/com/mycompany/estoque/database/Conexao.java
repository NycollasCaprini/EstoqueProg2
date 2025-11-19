/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estoque.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author luizfkm
 */
public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/db_estoque";
    private static final String USER = "postgres";
    private static final String PASSWORD = "120406";
    
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (SQLException e){
            throw new RuntimeException("Nao foi possivel conectar no banco de dados -> ERRO " + e);
        }
    }
}
