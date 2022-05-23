//import persistence.MyBatisConnectionFactory;
//import persistence.dao.*;
import persistence.DAO.RecipeDAO;
import persistence.DTO.RecipeDTO;
import persistence.GpsTransfer;
import persistence.MyBatisConnectionFactory;
//import service.*;

import java.util.List;

/**
 * 이 클래스는 DB연결 함수확인용
 */

public class Main {

    public static void main(String args[]){

        //DAO 생성
        RecipeDTO testDTO = new RecipeDTO();
        RecipeDAO test = new RecipeDAO(MyBatisConnectionFactory.getSqlSessionFactory());

//        GpsTransfer gpsTransfer = new GpsTransfer(33.500946412305076, 126.54663058817043);
//        gpsTransfer.transfer();
//        System.out.println("결과 : " + gpsTransfer.getxLat() + "," + gpsTransfer.getyLon());

        List<RecipeDTO> arr;
        String s = "";
        s += test.getRandom();




        System.out.println(s);

        //추가


    }

}