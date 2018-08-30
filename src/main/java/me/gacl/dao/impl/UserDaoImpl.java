package me.gacl.dao.impl;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import me.gacl.dao.IUserDao;
import me.gacl.domain.User;

/**
 * IUserDao接口的实现类
 * @author gacl
 */
public class UserDaoImpl implements IUserDao {
    //要连接的数据库URL
    private String url = "jdbc:mysql://localhost:3306/test";
    //连接的数据库时使用的用户名
    private String username = "root";
    //连接的数据库时使用的密码
    private String password = "root";
    @Override
    public User find(String userName, String userPwd) {
        try{
            //1.加载驱动
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());不推荐使用这种方式来加载驱动
            Class.forName("com.mysql.jdbc.Driver");//推荐使用这种方式来加载驱动
            //2.获取与数据库的链接
            Connection conn = DriverManager.getConnection(url, username, password);
            //3.获取用于向数据库发送sql语句的statement
            Statement st = conn.createStatement();
            String sql = "select id,userName,userPwd,email,birthday from login where userName = '" + userName +"' and userPwd = '" + userPwd + "'";
            System.out.println(sql);
            //4.向数据库发sql,并获取代表结果集的resultset
            ResultSet rs = st.executeQuery(sql);
            User user = new User();
            //5.取出结果集的数据
            while(rs.next()){
                user.setId(String.valueOf(rs.getObject("id")));
                user.setEmail(String.valueOf(rs.getObject("email")));
                user.setUserName(String.valueOf(rs.getObject("userName")));
                user.setUserPwd(String.valueOf(rs.getObject("userPwd")));
                String birth = String.valueOf(rs.getObject("birthday"));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                user.setBirthday(sdf.parse(birth));
            }
            System.out.println(user.toString());
            return user;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void add(User user) {
        try{
            //1.加载驱动
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());不推荐使用这种方式来加载驱动
            Class.forName("com.mysql.jdbc.Driver");//推荐使用这种方式来加载驱动
            //2.获取与数据库的链接
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO login(id,userName,userPwd,email,birthday)VALUES( ?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            String birth = user.getBirthday().toString();
            SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
            String birthday = sdf2.format(sdf1.parse(birth));
            pstm.setString(1, user.getId());
            pstm.setString(2, user.getUserName());
            pstm.setString(3, user.getUserPwd());
            pstm.setString(4, user.getEmail());
            pstm.setString(5, birthday);
            int row = pstm.executeUpdate();
            if(row > 0){
                System.out.println("插入成功！");
            }
        }catch (Exception e) {
            System.out.println("错错错！！！");
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(String userName) {
        try{
            //1.加载驱动
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());不推荐使用这种方式来加载驱动
            Class.forName("com.mysql.jdbc.Driver");//推荐使用这种方式来加载驱动
            //2.获取与数据库的链接
            Connection conn = DriverManager.getConnection(url, username, password);
            //3.获取用于向数据库发送sql语句的statement
            Statement st = conn.createStatement();
            String sql = "select id,userName,userPwd,email,birthday from login where userName = '" + userName + "'";
            //4.向数据库发sql,并获取代表结果集的resultset
            ResultSet rs = st.executeQuery(sql);
            User user = new User();
            //5.取出结果集的数据
            while(rs.next()){
                user.setId(String.valueOf(rs.getObject("id")));
                user.setEmail(String.valueOf(rs.getObject("email")));
                user.setUserName(String.valueOf(rs.getObject("userName")));
                user.setUserPwd(String.valueOf(rs.getObject("userPwd")));
                String birth = String.valueOf(rs.getObject("birthday"));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                user.setBirthday(sdf.parse(birth));
            }
            return user;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
