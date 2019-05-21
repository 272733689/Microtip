//package com.microtip.Mapper;
//
//import com.microtip.entity.Device_List;
//import org.apache.ibatis.annotations.*;
//
//import java.util.List;
//
//public interface UserMapper {
//
//    @Select("SELECT * FROM Device_List")
//    @Results({
//            @Result(property = "userSex",  column = "user_sex", javaType = Device_List.class),
//            @Result(property = "nickName", column = "nick_name")
//    })
//    List<Device_List> getAll();
//
//    @Select("SELECT * FROM users WHERE id = #{id}")
//    @Results({
//            @Result(property = "userSex",  column = "user_sex", javaType = Device_List.class),
//            @Result(property = "nickName", column = "nick_name")
//    })
//    Device_List getOne(Long id);
//
//    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
//    void insert(Device_List user);
//
//    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
//    void update(Device_List user);
//
//    @Delete("DELETE FROM users WHERE id =#{id}")
//    void delete(Long id);
//
//}
//
///*
//@Select 是查询类的注解，所有的查询均使用这个
//@Result 修饰返回的结果集，关联实体类属性和数据库字段一一对应，如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。
//@Insert 插入数据库使用，直接传入实体类会自动解析属性到对应的值
//@Update 负责修改，也可以直接传入对象
//@delete 负责删除*/
