//import persistence.MyBatisConnectionFactory;
//import persistence.dao.*;
import persistence.DAO.TestDAO;
import persistence.DTO.TestDTO;
import persistence.MyBatisConnectionFactory;
//import service.*;

import java.util.List;

public class Main {

    public static void main(String args[]){

        //DAO 생성
        TestDTO testDTO = new TestDTO();
        TestDAO test = new TestDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        List<TestDTO> arr;
        String s = "";
        s += test.getRandom();

//        String[] arr = test.음식추천();

//        for(int i=0; i< arr.size(); i++){
//            System.out.println(arr.get(i));
//        }
        System.out.println(s);


    }

}