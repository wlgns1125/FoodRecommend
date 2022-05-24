package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.MemberDTO;
import persistence.mapper.MemberMapper;

import java.util.List;

public class MemberDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public MemberDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    private boolean idExist(String memberId) {
        List<MemberDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        MemberMapper mapper = session.getMapper(MemberMapper.class);
        try {
            list = mapper.selectById(memberId);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return !list.isEmpty();
    }

    private boolean passwordExist(String memberPassword) {
        List<MemberDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        MemberMapper mapper = session.getMapper(MemberMapper.class);
        try {
            list = mapper.selectByPassword(memberPassword);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return !list.isEmpty();
    }

    public boolean insertMember(String memberId, String memberPassword){
        MemberDTO memberDTO = new MemberDTO(memberId, memberPassword);
        if(idExist(memberId))
            return false;
        SqlSession session = sqlSessionFactory.openSession();
        MemberMapper mapper = session.getMapper(MemberMapper.class);
        try{
            mapper.insert(memberDTO);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
        return true;
    }

    public boolean login(String memberId, String memberPassword){

        if((!idExist(memberId)) || (!passwordExist(memberPassword)))
            return false;

        return true;
    }
}
