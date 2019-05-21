//package com.microtip.Mapper;
//
//import com.microtip.entity.Device_List;
//import org.apache.ibatis.annotations.Result;
//import org.apache.ibatis.annotations.Results;
//import org.apache.ibatis.annotations.Select;
//
//import java.util.List;
//
//public interface Device_ListMapper {
//    @Select("SELECT * FROM device_list")
//    @Results({
//            @Result(property = "id",  column = "id", javaType = Device_List.class),
//            @Result(property = "device", column = "device"),
//            @Result(property = "productkey", column = "productkey"),
//            @Result(property = "clientname", column = "clientname"),
//            @Result(property = "create_Name", column = "create_Name"),
//            @Result(property = "create_Time", column = "create_Time"),
//            @Result(property = "version", column = "version"),
//    })
//    List<Device_List> getDevice();
//
//}
