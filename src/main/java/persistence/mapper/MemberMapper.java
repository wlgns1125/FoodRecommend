package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.DTO.MemberDTO;
import persistence.DTO.RecipeDTO;

import java.util.List;

public interface MemberMapper {

    final String selectAll = "SELECT * from member ";
    @Select(selectAll)
    @Results(id = "resultSet", value = {
            @Result(property = "memberNumber", column = "memberNumber"),
            @Result(property = "memberID", column = "memberID"),
            @Result(property = "memberPassword", column = "memberPassword"),
    })
    List<MemberDTO> selectAll();

    final String selectById = "SELECT * from member WHERE MemberID = #{MemberID};";
    @Select(selectById)
    @ResultMap("resultSet")
    List<MemberDTO> selectById(String memberId);

    final String selectByPassword = "SELECT * from member WHERE MemberPassword = #{MemberPassword};";
    @Select(selectByPassword)
    @ResultMap("resultSet")
    List<MemberDTO> selectByPassword(String memberPassword);

    final String INSERT = "INSERT INTO member (memberId, memberPassword) VALUES (#{memberId}, #{memberPassword})";
    @Insert(INSERT)
    @ResultMap("resultSet")
    public void insert(MemberDTO memberDTO);

}
