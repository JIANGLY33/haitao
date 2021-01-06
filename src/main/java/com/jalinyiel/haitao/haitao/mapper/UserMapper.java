package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user")
    @Results(id = "userDo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "password", property = "password"),
            @Result(column = "description", property = "description"),
            @Result(column = "portrait", property = "portrait"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "type", property = "type"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<User> findAll();

    @Select("SELECT * FROM user WHERE user_name = #{username}")
    @ResultMap("userDo")
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM user WHERE id = #{id}")
    @ResultMap("userDo")
    User findById(@Param("id") Long id);

    @Insert({"<script>",
            "INSERT INTO user(id, user_name, password, description, portrait, gender, type, gmt_create, gmt_modified) ",
            "VALUES (#{id}, #{userName}, #{password} ,#{description},#{portrait},#{gender},#{type},now(),now()",
            ")",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long createUser(User user);

    @UpdateProvider(type = UserMapperProvider.class, method = "updateUser")
    Integer updateUser(User user);

    class UserMapperProvider {

        public String updateUser(User user) {
            return new SQL(){{
                UPDATE("user");
                if (null != user.getPassword()) {
                    SET("password = #{password}");
                }
                if (null != user.getDescription()) {
                    SET("description = #{description}");
                }
                if (null != user.getPortrait()) {
                    SET("portrait = #{portrait}");
                }
                if (null != user.getGender()) {
                    SET("gender = #{gender}");
                }
                SET("gmt_modified = now()");
                WHERE("id = #{id}");
            }
            }.toString();
        }
    }

}
