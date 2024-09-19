package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Animal;

public class AnimalDAO extends DAO {
    
    public AnimalDAO() {
        super();
        conectar();
    }
    
    public boolean insert(Animal animal) {
        boolean status = false;
        try {
            String sql = "INSERT INTO animal (nome, idade, especie, peso) VALUES (?, ?, ?, ?)";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, animal.getNome());
            st.setInt(2, animal.getIdade());
            st.setString(3, animal.getEspecie());
            st.setFloat(4, animal.getPeso());
            st.executeUpdate();
            status = true;
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public Animal get(int id) {
        Animal animal = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM animal WHERE id = " + id;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                animal = new Animal(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), 
                                    rs.getString("especie"), rs.getFloat("peso"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return animal;
    }
    
    public List<Animal> getAll(String orderBy) {
        List<Animal> animais = new ArrayList<Animal>();
        try {
            String sql = "SELECT * FROM animal ORDER BY " + orderBy;
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Animal a = new Animal(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), 
                                      rs.getString("especie"), rs.getFloat("peso"));
                animais.add(a);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return animais;
    }


    public boolean update(Animal animal) {
        boolean status = false;
        try {
            String sql = "UPDATE animal SET nome = ?, idade = ?, especie = ?, peso = ? WHERE id = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, animal.getNome());
            st.setInt(2, animal.getIdade());
            st.setString(3, animal.getEspecie());
            st.setFloat(4, animal.getPeso());
            st.setInt(5, animal.getId());
            st.executeUpdate();
            status = true;
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean delete(int id) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM animal WHERE id = " + id);
            status = true;
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
}
