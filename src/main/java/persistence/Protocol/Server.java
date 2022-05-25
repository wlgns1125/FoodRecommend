package persistence.Protocol;

import persistence.DAO.MemberDAO;
import persistence.DAO.RecipeDAO;
import persistence.DTO.RecipeDTO;
import persistence.MyBatisConnectionFactory;

import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

import static persistence.Protocol.Protocol.*;

//import static persistence.Protocol.Protocol.TYPE_REQUEST;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Socket conn = null;
        ServerSocket sSocket = null;

        try {

            sSocket = new ServerSocket(3000, 10);
            //* nullexception 발생시  - > 데이터베이스 접속하기 + 포트번호 수정하기
            System.out.println("클라이언트 접속 대기중...");


            while (true) {

                conn = sSocket.accept();
                System.out.println("클라이언트 " + conn.getInetAddress().getHostName() + " 가 접속하였습니다.");
                new handler(conn).start();

            }
        } catch (IOException e) {
            System.err.println("IOException");
        }

        try {
            sSocket.close();
        } catch (IOException ioException) {
            System.err.println("Unable to close. IOexception");
        }

    }


    static class handler extends Thread {

        private Socket conn;


        handler(Socket conn) {
            this.conn = conn;
        }


        public void run() {

            Protocol recommendFood = new Protocol(TYPE_REQUEST, CODE_RECOMMENDFOOD);

            RecipeDAO recipeDAO = new RecipeDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            MemberDAO memberDAO = new MemberDAO(MyBatisConnectionFactory.getSqlSessionFactory());

            try {
                OutputStream os = conn.getOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(os);
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);

                String id = "";
                String password = "";
                String data = "";



                boolean isEnd = false;

                while (!isEnd) {

                    byte[] buf = recommendFood.getPacket();
                    is.read(buf);
                    int protocolCode = buf[0];
                    int protocolType = buf[1];

                    recommendFood.setPacket(protocolCode, protocolType, buf); // 패킷 타입을 Protocol 객체의 packet 멤버변수에 buf를 복사

                    switch (protocolCode) {
                        case CODE_RECOMMENDFOOD:
                            switch (protocolType) {
                                case TYPE_REQUEST:
                                    System.out.println("요리추천패킷정상수신");
                                    Protocol proto = new Protocol(TYPE_RESPONSE, CODE_RECOMMENDFOOD);
                                    int pos = 2;

                                    byte[] sendBuf = proto.getPacket();//실제 최종 보낼 패킷


                                    //temp += testDAO.getRandom();
                                    String oneFoodName;
                                    String oneFoodURLName;
                                    int oneFoodNameLength;
                                    int oneFoodURLNameLength;



                                    List<RecipeDTO> tmp = recipeDAO.getRandom();

                                    for(int i = 0; i < tmp.size(); i++){
                                        oneFoodName = tmp.get(i).getFoodName();
                                        System.out.println(oneFoodNameLength = oneFoodName.getBytes().length);
                                        oneFoodURLName = tmp.get(i).getImgLink();
                                        System.out.println(oneFoodURLNameLength = oneFoodURLName.getBytes().length);

                                        byte[] temp1 = proto.intToByteArray(oneFoodNameLength); // 이름 길이
                                        System.arraycopy(temp1, 0, sendBuf, pos, 4);
                                        pos += 4; // 4증가

                                        byte[] temp2 = oneFoodName.getBytes(); //이름 실제 데이터
                                        System.arraycopy(temp2, 0, sendBuf, pos, temp2.length);
                                        pos += temp2.length;

                                        byte[] temp3 = proto.intToByteArray(oneFoodURLNameLength); //URL 길이
                                        System.arraycopy(temp3, 0, sendBuf, pos, 4);
                                        pos += 4;

                                        byte[] temp4 = oneFoodURLName.getBytes(); //URL 실제 데이터
                                        System.arraycopy(temp4, 0, sendBuf, pos, temp4.length);
                                        pos += temp4.length;

                                    }

                                    bos.write(sendBuf);
                                    bos.flush();

                                    break;
                            }
                            break;

                        case CODE_LOGIN:
                            switch (protocolType) {
                                case TYPE_REQUEST:
                                    System.out.println("로그인요청 정상수신");
                                    Protocol proto = new Protocol(TYPE_RESPONSE, CODE_LOGIN);
                                    String loginId = null, loginPassword = null;
                                    int pos = 2;


                                    int loginIdLength = proto.byteArrayToInt(Arrays.copyOfRange(buf, pos, pos + 4));
                                    pos +=4;

                                    byte[] loginIdArr = Arrays.copyOfRange(buf, pos, pos + loginIdLength);
                                    try {
                                            loginId = new String(loginIdArr, "UTF-8");//추출 이름 String 변환해 저장
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                    pos += loginIdLength;

                                    int loginPasswordLength = proto.byteArrayToInt(Arrays.copyOfRange(buf, pos, pos + 4));
                                    pos +=4;

                                    byte[] loginPasswordArr = Arrays.copyOfRange(buf, pos, pos + loginPasswordLength);
                                    try {
                                        loginPassword = new String(loginPasswordArr, "UTF-8");//추출 이름 String 변환해 저장
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                    pos += loginPasswordLength;

                                    boolean loginSuccess = memberDAO.login(loginId, loginPassword);
                                    if(loginSuccess)
                                        proto.setProtocolType(TYPE_RESPONSE);
//                                        proto = new Protocol(CODE_LOGIN, TYPE_RESPONSE);
                                    else
                                        proto.setProtocolType(TYPE_RESPONSE_ERROR);

                                    bos.write(proto.getPacket());
                                    bos.flush();
                                    break;
                            }
                            break;
                    }



//                    isEnd = true;
//                    System.out.println(conn.getInetAddress().getHostName() + "가 종료했습니다.");
                    
//                            break;

//                    }


                }
                conn.close();

            } catch (IOException e) {
                System.out.println("IOException on socket : " + e);
                e.printStackTrace();
            }

        }


    }
}






