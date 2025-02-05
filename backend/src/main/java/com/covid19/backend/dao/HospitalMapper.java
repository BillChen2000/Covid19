package com.covid19.backend.dao;

import com.covid19.backend.model.Hospital;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

// MyBatis
@Mapper
@Component
public interface HospitalMapper {
    @Select("SELECT * from hospital where hospital_id=#{hospital_id}")
    public Hospital selectHospitalByID(@Param("hospital_id") long hospital_id);

    @Select("SELECT * from hospital where " +
            "hospital_name like concat('%',#{hospital_name},'%') and address like concat('%',#{address},'%')")
    public ArrayList<Hospital> selectHospital(Hospital hospital);

    @Insert("insert into hospital(hospital_name, address) " +
            "values (#{hospital_name}, #{address})"
    )
    @SelectKey(statement = "SELECT LAST_INSERT_ID() as hospital_id", keyProperty = "hospital_id", before = false, resultType = long.class)
    long insertHospital(Hospital hospital);

    @Update("Update hospital set " +
            "hospital_name=#{hospital_name}, address=#{address} " +
            "where hospital_id=#{hospital_id}")
    void updateHospital(Hospital hospital);

    @Delete("delete from hospital where hospital_id = #{hospital_id}")
    void deleteHospitalByID(@Param("hospital_id") long hospital_id);
}
