package com.wonder.bring.mapper;

import com.wonder.bring.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //
    @Select("SELECT * FROM USERS WHERE id = #{id} AND passwd = #{passwd}")
    User findByIdAndPassword(@Param("id") final String id, @Param("passwd") final String password);

    //회원 고유 번호로 조회
    @Select("SELECT * FROM USERS WHERE userIdx = #{user_Idx}")
    User findByUserIdx(@Param("user_Idx") final int userIdx);
}

