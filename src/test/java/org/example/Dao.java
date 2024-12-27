package org.example;

import com.mysql.cj.protocol.Resultset;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class Dao {


    public static void main(String[] args) throws SQLException, IOException {

        System.out.println(LocalDate.now().toString());
//        String file = "C:\\Users\\DELL\\Documents\\s.csv";
//        File f = new File(file);
//        if(!f.exists()){
//            System.out.println("File not found");
//            return;
//        }
//        BufferedReader br = new BufferedReader(new FileReader(f));
//        String line  = null;
//        while((line =br.readLine())!= null){
//            String[] words = line.split(",");
//            for(String word: words){
//                System.out.print(word+" ");
//            }
//            System.out.println();
//        }




//        Connection con= DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/vinay","root","root");
//        String sql = "Select * from sales where sale_id = ?";
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setInt(1,1);
//        ResultSet rs = ps.executeQuery();
//        while(rs.next()){
//            System.out.println(rs.getInt(1));
//            System.out.println(rs.getInt(2));
//            System.out.println(rs.getInt(3));
//            System.out.println(rs.getDate(4));
//            System.out.println();
//        }
    }

    public void csvRead() throws IOException {
        String file = "";
        File f = new File(file);
        if(!f.exists()){
            System.out.println("File not found");
            return;
        }
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line  = null;
        while((line =br.readLine())!= null){
            String[] words = line.split(",");
            for(String word: words){
                System.out.print(word+" ");
            }
            System.out.println();
        }
    }
}
