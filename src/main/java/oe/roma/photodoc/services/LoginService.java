package oe.roma.photodoc.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service("loginService")
public class LoginService {
    private JdbcTemplate jdbcTemplate;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> getUsers(){

        return jdbcTemplate.query("SELECT * from dbo.users", usersRowMapper);
    }

    public User getUser(String login, String password){
        String sql = "SELECT u.*, r.id as r_id, r.name as rem_name from dbo.users u left join dbo.rem r on u.rem_id=r.id where u.login=? and u.password=?";
        try{
        return jdbcTemplate.queryForObject(sql, new Object[]{login, password}, usersRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    RowMapper<User> usersRowMapper = new RowMapper<User>() {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.getRem().setId(rs.getInt("r_id"));
            user.getRem().setName(rs.getString("rem_name"));
            return user;
        }
    };
}
