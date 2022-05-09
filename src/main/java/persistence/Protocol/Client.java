//package persistence.Protocol;
//
//import java.net.*;
//import java.io.*;
//import java.util.Scanner;
//
//import static persistence.Protocol.Protocol.*;
//
//public class Client {
//    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
//
//        Scanner sc = new Scanner(System.in);
//
//        Socket socket = new Socket("localhost", 7777);
//        OutputStream os = socket.getOutputStream();
//        InputStream is = socket.getInputStream();
//
//        //서버에게 전달할 프로토콜 미리 생성
//
//        String 사용자구분 = "";
//        String id = "";
//        String password = "";
//
//
//        while(true){
//
//
//            Protocol 수신프로토콜 = new Protocol(Protocol.PT_LOGIN,Protocol.UT_UNDEFINED);
//            byte[] buf = 수신프로토콜.getPacket();
//            is.read(buf);
//            int packetType = buf[0];
//            int packetUserType = buf[1];
//
//            수신프로토콜.setPacket(packetType,packetUserType,buf); // 패킷 타입을 Protocol 객체의 packet 멤버변수에 buf를 복사
//
//            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
//
//            switch(packetType){
//
//                case Protocol.PT_LOGIN:
//
//                    switch (packetUserType) {
//
//                        case Protocol.LOGIN_UT_ID요청:
//
//                            userIn = new BufferedReader(new InputStreamReader(System.in));
//                            System.out.print("ID를 입력하세요 : ");
//                            id = userIn.readLine();
//
//                            ID전송.setData(id);
//                            os.write(ID전송.getPacket());
//                            break;
//
//
//                        case Protocol.LOGIN_UT_PASSWORD요청:
//
//                            userIn = new BufferedReader(new InputStreamReader(System.in));
//                            System.out.print("PASSWORD를 입력하세요 : ");
//                            password = userIn.readLine();
//
//                            PASSWORD전송.setData(password);
//                            os.write(PASSWORD전송.getPacket());
//                            break;
//
//
//                        case Protocol.LOGIN_UT_결과:
//
//                            if (수신프로토콜.getProtocolCode() == 0) { // 로그인 실패
//
//                                userIn = new BufferedReader(new InputStreamReader(System.in));
//                                System.out.println("로그인 실패!");
//
//                                System.out.println("종료합니다");
//                                os.write(EXIT.getPacket());
//                                return;
//
//
//                            } else if (수신프로토콜.getProtocolCode() == 1) { // 로그인 성공
//
//                                System.out.println("로그인 성공!");
//
//                                if (수신프로토콜.getProtocolFlag() == 1) { // 관리자
//                                    사용자구분 = "관리자";
//                                } else if (수신프로토콜.getProtocolFlag() == 2) { // 학생
//                                    사용자구분 = "학생";
//                                } else if (수신프로토콜.getProtocolFlag() == 3) { // 교수
//                                    사용자구분 = "교수";
//                                }
//
//                                System.out.println(사용자구분 + "권한으로 접속합니다...");
//
//                                if (사용자구분.equals("관리자")) {
//
//                                    int menu = 0;
//
//                                    while (true) {
//
//                                        System.out.println("\n         <메뉴>");
//                                        System.out.println(" 1. 관리자 계정 생성 \n 2. 교수 계정 생성 \n 3. 학생 계정 생성 \n 4. 교과목 생성 \n 5. 교과목 수정 \n 6. 교과목 삭제 \n 7. 개설교과목 생성  \n 8. 개설교과목 수정  \n 9. 개설교과목 삭제 \n 10. 강의계획서 입력 기간 설정  \n 11. 학년별 수강 신청 기간 설정  \n 12. 교수 정보 조회 \n 13. 학생 정보 조회 \n 14. 개설 교과목 정보 조회 \n 15. 교과목 조회 \n 16. 종료 ");
//                                        System.out.print("항목을 입력하세요 : ");
//                                        menu = sc.nextInt();
//                                        switch(menu){
//
//                                            case 1:
//                                                //System.out.println("관리자 계정생성에 필요한 정보를 입력하시오.(ID|NAME|PN|PWD)");
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                String str = "";
//
//                                                System.out.print("생성할 관리자 ID를 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 관리자 이름을 입력하세요 :");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 관리자 휴대폰 번호를 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 관리자 비밀번호를 입력하세요 : ");
//                                                str += userIn.readLine();
//
//                                                관리자계정생성요청.setData(str);
//                                                os.write(관리자계정생성요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                if(수신프로토콜.getProtocolCode()==0)
//                                                {
//                                                    System.out.println("계정생성에 실패했습니다");
//                                                }else{
//                                                    System.out.println("계정생성 성공");
//                                                }
//
//                                                break;
//
//                                            case 2:
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                str = "";
//                                                System.out.print("생성할 교수 ID를 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 교수 이름을 입력하세요 :");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 교수 연구실을 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 교수 이메일을 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 교수 전화번호를 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 교수 생일을 입력하세요 :");
//                                                str += userIn.readLine();
//
//                                                교수계정생성요청.setData(str);
//                                                os.write(교수계정생성요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                if(수신프로토콜.getProtocolCode()==0)
//                                                {
//                                                    System.out.println("계정생성에 실패했습니다");
//                                                }else{
//                                                    System.out.println("계정생성 성공");
//                                                }
//                                                break;
//
//                                            case 3: ;
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                str = "";
//
//                                                System.out.print("생성할 학생 ID를 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 학생 이름을 입력하세요 :");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 학생 전화번호를 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 학생 학과를 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 학생 학년을 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 학생 생일을 입력하세요 :");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 학생 이메일을 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 학생 지도교수코드를 입력하세요 : ");
//                                                str += userIn.readLine();
//
//                                                학생계정생성요청.setData(str);
//                                                os.write(학생계정생성요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                if(수신프로토콜.getProtocolCode()==0)
//                                                {
//                                                    System.out.println("계정생성에 실패했습니다");
//                                                }else{
//                                                    System.out.println("계정생성 성공");
//                                                }
//                                                break;
//
//                                            case 4:
//
//                                                os.write(교과목목록요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("네트워크 오류");
//                                                    }
//                                                    else
//                                                    {
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println("교과목");
//                                                        System.out.println(data);
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("교과목");
//                                                    System.out.println(tStr);
//
//                                                }
//
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                str = "";
//
//                                                System.out.print("생성할 교과목ID를 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 교과목 이름을 입력하세요 :");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 교과목 학점을 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 교과목 학년을 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 교과목구분을 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 교과목학기를 입력하세요 : ");
//                                                str += userIn.readLine();
//
//                                                교과목생성요청.setData(str);
//                                                os.write(교과목생성요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                if(수신프로토콜.getProtocolCode()==0)
//                                                {
//                                                    System.out.println("교과목생성에 실패했습니다");
//                                                }else{
//                                                    System.out.println("교과목생성 성공");
//                                                }
//                                                break;
//
//                                            case 5:
//
//                                                os.write(교과목목록요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("네트워크 오류");
//                                                    }
//                                                    else
//                                                    {
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println("교과목");
//                                                        System.out.println(data);
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("교과목");
//                                                    System.out.println(tStr);
//
//                                                }
//
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                str = "";
//
//                                                System.out.print("수정할 교과목ID를 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                menu = 0;
//                                                System.out.println("수정할 항목을 선택하세요 : 0.교과목명 1.학점 2.학년 3.교과목구분 4.학기");
//                                                System.out.print("입력 : ");
//                                                menu = Integer.parseInt(userIn.readLine());
//                                                str += Integer.toString(menu);
//                                                str += "|";
//
//                                                switch (menu){
//                                                    case 0:
//                                                        System.out.print("수정할 교과목이름을 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        break;
//                                                    case 1:
//                                                        System.out.print("수정할 교과목 학점을 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        break;
//                                                    case 2:
//                                                        System.out.print("수정할 교과목 학년을 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        break;
//                                                    case 3:
//                                                        System.out.print("수정할 교과목구분을 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        break;
//                                                    case 4:
//                                                        System.out.print("수정할 교과목 학기를 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        break;
//                                                }
//                                                교과목수정요청.setData(str);
//                                                os.write(교과목수정요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                if(수신프로토콜.getProtocolCode()==0)
//                                                {
//                                                    System.out.println("교과목수정에 실패했습니다");
//                                                }else{
//                                                    System.out.println("교과목수정 성공");
//                                                }
//                                                break;
//                                            case 6:
//
//                                                os.write(교과목목록요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("네트워크 오류");
//                                                    }
//                                                    else
//                                                    {
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println("교과목");
//                                                        System.out.println(data);
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("교과목");
//                                                    System.out.println(tStr);
//
//                                                }
//
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                str = "";
//
//                                                System.out.print("삭제할 교과목ID를 입력하세요 : ");
//                                                str += userIn.readLine();
//
//                                                교과목삭제요청.setData(str);
//                                                os.write(교과목삭제요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                if(수신프로토콜.getProtocolCode()==0)
//                                                {
//                                                    System.out.println("교과목삭제에 실패했습니다");
//                                                }else{
//                                                    System.out.println("교과목삭제 성공");
//                                                }
//                                                break;
//                                            case 7:
//
//                                                os.write(개설교과목조회요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("개설교과목조회 실패");
//                                                    }
//                                                    else
//                                                    {
//                                                        System.out.println("개설교과목조회 성공");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("개설교과목조회 성공");
//                                                    System.out.println(tStr);
//
//                                                }
//
//                                                System.out.println();
//
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                str = "";
//
//
//                                                System.out.print("생성할 개설교과목의 교과목ID를 입력하세요 :");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 개설교과목 담당교수ID를 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 개설교과목 수강신청학년을 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 개설교과목의 최대수강인원 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 개설교과목의 분반을 입력하세요 : ");
//                                                str += "0";
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 개설교과목의 강의시간을 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("생성할 개설교과목의 강의실을 입력하세요 : ");
//                                                str += userIn.readLine();
//
//                                                개설교과목생성요청.setData(str);
//                                                os.write(개설교과목생성요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                if(수신프로토콜.getProtocolCode()==0)
//                                                {
//                                                    System.out.println("이미 개설된 교과목입니다");
//                                                }
//                                                else if(수신프로토콜.getProtocolCode()==1)
//                                                {
//                                                    System.out.println("개설교과목생성 성공");
//                                                }
//                                                else if(수신프로토콜.getProtocolCode()==2)
//                                                {
//                                                    System.out.println("존재하지 않는 교과목입니다");
//                                                }
//                                                else if(수신프로토콜.getProtocolCode()==3)
//                                                {
//                                                    System.out.println("존재하지 않는 교수입니다");
//                                                }
//                                                break;
//                                            case 8:
//
//                                                os.write(개설교과목조회요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("개설교과목조회 실패");
//                                                    }
//                                                    else
//                                                    {
//                                                        System.out.println("개설교과목조회 성공");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("개설교과목조회 성공");
//                                                    System.out.println(tStr);
//
//                                                }
//
//                                                System.out.println();
//
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                str = "";
//
//                                                System.out.print("수정할 개설교과목ID를 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                menu = 0;
//                                                System.out.println("수정할 항목을 선택하세요 : 0.교수ID 1.수강신청학년 2.최대수강인원 3.신청인원 4.분반 5.수강신청가능여부 6.강의시간 7.강의실");
//                                                System.out.print("입력 : ");
//                                                menu = Integer.parseInt(userIn.readLine());
//                                                str += Integer.toString(menu);
//                                                str += "|";
//
//                                                switch (menu){
//                                                    case 0:
//                                                        System.out.print("수정할 교수ID를 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        break;
//                                                    case 1:
//                                                        System.out.print("수정할 수강신청학년 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        break;
//                                                    case 2:
//                                                        System.out.print("수정할 최대수강인원을 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        break;
//                                                    case 3:
//                                                        System.out.print("수정할 신청인원을 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        break;
//                                                    case 4:
//                                                        System.out.print("수정할 분반을 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        break;
//                                                    case 5:
//                                                        System.out.print("수정할 수강신청가능여부를 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        break;
//                                                    case 6:
//                                                        System.out.print("수정할 강의시간을 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        break;
//                                                    case 7:
//                                                        System.out.print("수정할 강의실을 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        break;
//                                                }
//                                                개설교과목수정요청.setData(str);
//                                                os.write(개설교과목수정요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                if(수신프로토콜.getProtocolCode()==0)
//                                                {
//                                                    System.out.println("개설교과목수정에 실패했습니다");
//                                                }else{
//                                                    System.out.println("개설교과목수정 성공");
//                                                }
//                                                break;
//
//                                            case 9:
//
//                                                os.write(개설교과목조회요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("개설교과목조회 실패");
//                                                    }
//                                                    else
//                                                    {
//                                                        System.out.println("개설교과목조회 성공");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("개설교과목조회 성공");
//                                                    System.out.println(tStr);
//
//                                                }
//
//                                                System.out.println();
//
//
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                str = "";
//
//                                                System.out.print("삭제할 개설교과목ID를 입력하세요 : ");
//                                                str += userIn.readLine();
//
//                                                개설교과목삭제요청.setData(str);
//                                                os.write(개설교과목삭제요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                if(수신프로토콜.getProtocolCode()==0)
//                                                {
//                                                    System.out.println("개설교과목삭제에 실패했습니다");
//                                                }else{
//                                                    System.out.println("개설교과목삭제 성공");
//                                                }
//                                                break;
//
//                                            case 10:
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//                                                menu = 0;
//                                                str = "";
//
//                                                System.out.println("동작할 기능을 선택하세요 : 0. 신규 강의계획서 입력기간 추가 1. 강의계획서 입력기간 수정");
//                                                System.out.print("입력 : ");
//                                                menu = Integer.parseInt(userIn.readLine());
//                                                str += Integer.toString(menu);
//                                                str += "|";
//
//                                                switch (menu) {
//                                                    case 0 :
//                                                    case 1 :
//                                                        System.out.print("연도, 학기를 입력하시오(ex: 2021-02) : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        System.out.print("시작일을 입력하시오(ex: 11-26) : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        System.out.print("끝일을 입력하시오(ex: 11-27) : ");
//                                                        str += userIn.readLine();
//
//                                                        강의계획서입력기간설정요청.setData(str);
//                                                        os.write(강의계획서입력기간설정요청.getPacket());
//                                                        break;
//                                                }
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                if(수신프로토콜.getProtocolCode()==0)
//                                                {
//                                                    System.out.println("설정 실패");
//                                                }else{
//                                                    System.out.println("설정 성공");
//                                                }
//                                                break;
//
//                                            case 11:
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                str = "";
//
//                                                System.out.print("기간을 설정할 학년을 입력하세요 : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("시작일을 입력하시오(ex: 2021-11-26) : ");
//                                                str += userIn.readLine();
//                                                str += "|";
//
//                                                System.out.print("끝일을 입력하시오(ex: 2021-11-27) : ");
//                                                str += userIn.readLine();
//
//                                                수강신청기간설정요청.setData(str);
//                                                os.write(수강신청기간설정요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                if(수신프로토콜.getProtocolCode()==0)
//                                                {
//                                                    System.out.println("설정 실패");
//                                                }else{
//                                                    System.out.println("설정 성공");
//                                                }
//                                                break;
//
//                                            case 12:
//                                                os.write(교수정보조회요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("교수정보조회요청 실패");
//                                                    }
//                                                    else
//                                                    {
//                                                        System.out.println("교수정보조회요청 성공");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("교수정보조회요청 성공");
//                                                    System.out.println(tStr);
//
//                                                }
//
//                                                break;
//
//                                            case 13:
//                                                os.write(학생정보조회요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("학생정보조회요청 실패");
//                                                    }
//                                                    else
//                                                    {
//                                                        System.out.println("학생정보조회요청 성공");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("학생정보조회요청 성공");
//                                                    System.out.println(tStr);
//
//                                                }
//
//                                                break;
//
//                                            case 14:
//                                                os.write(개설교과목정보조회요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                if(packetUserType == Protocol.MANAGER_UT_개설_교과목_정보_조회_목록전송)
//                                                {
//
//                                                    if(수신프로토콜.getProtocolFlag() == 0){
//                                                        if(수신프로토콜.getProtocolCode()==0)
//                                                        {
//                                                            System.out.println("네트워크 오류");
//                                                        }
//                                                        else
//                                                        {
//                                                            String tStr = new String(수신프로토콜.getByteData(),0,수신프로토콜.getProtocolLen());
//                                                            System.out.println(tStr);
//
//                                                            System.out.print("조회할 개설 교과목의 코드를 입력하시오 : ");
//                                                            userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                            str = "";
//                                                            str += userIn.readLine();
//                                                            개설교과목정보조회목록선택.setData(str);
//                                                            os.write(개설교과목정보조회목록선택.getPacket());
//
//                                                            is.read(buf);
//                                                            packetType = buf[0];
//                                                            packetUserType = buf[1];
//                                                            수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                            if(수신프로토콜.getProtocolCode()==0)
//                                                            {
//                                                                System.out.println("개설 교과목 조회 오류");
//                                                            }else{
//                                                                String data = 수신프로토콜.getData();
//                                                                System.out.println(data);
//                                                            }
//
//                                                        }
//                                                    }else if(수신프로토콜.getProtocolFlag() == 1) {
//
//                                                        byte[] tempBuf = new byte[500000];
//                                                        System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0, 수신프로토콜.getProtocolLen());
//
//                                                        int i = 0;
//                                                        while (true) {
//
//                                                            i += 2000;
//                                                            is.read(buf);
//                                                            packetType = buf[0];
//                                                            packetUserType = buf[1];
//                                                            수신프로토콜.setPacket(packetType, packetUserType, buf);
//
//                                                            if (수신프로토콜.getProtocolLast() == 0) {
//
//                                                                System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i, 수신프로토콜.getProtocolLen());
//
//                                                            } else if (수신프로토콜.getProtocolLast() == 1) {
//
//                                                                System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i, 수신프로토콜.getProtocolLen());
//                                                                break;
//                                                            }
//
//
//                                                        }
//
//                                                        String tStr = new String(tempBuf, 0, i + 수신프로토콜.getProtocolLen());
//                                                        System.out.println(tStr);
//
//                                                        System.out.print("조회할 개설 교과목의 코드를 입력하시오 : ");
//                                                        userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                        str = "";
//                                                        str += userIn.readLine();
//                                                        개설교과목정보조회목록선택.setData(str);
//                                                        os.write(개설교과목정보조회목록선택.getPacket());
//
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType, packetUserType, buf);
//
//                                                        if (수신프로토콜.getProtocolCode() == 0) {
//                                                            System.out.println("존재하지 않느 개설 교과목입니다");
//                                                        } else {
//                                                            String data = 수신프로토콜.getData();
//                                                            System.out.println(data);
//                                                        }
//                                                    }
//                                                }
//                                                break;
//                                            case 15:
//                                                os.write(교과목목록요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("네트워크 오류");
//                                                    }
//                                                    else
//                                                    {
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println("교과목");
//                                                        System.out.println(data);
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("교과목");
//                                                    System.out.println(tStr);
//
//                                                }
//
//                                                break;
//                                            case 16:
//                                                System.out.println("종료합니다");
//                                                os.write(EXIT.getPacket());
//                                                return;
//                                        }
//
//
//                                    }
//                                } else if (사용자구분.equals("교수")) {
//
//                                    int menu = 0;
//                                    String str = "";
//                                    while (true) {
//
//                                        System.out.println("\n         <메뉴>");
//                                        System.out.println(" 1. 개인정보 수정 \n 2. 비밀번호 수정 \n 3. 강의계획서 입력 \n 4. 강의계획서 수정 \n 5. 본인 개설 교과목 목록 조회 \n 6. 개설 교과목 강의 계획서 조회 \n 7. 개설 교과목 수강 신청 학생 목록 조회 \n 8. 개설 교과목 시간표 조회 \n 9. 종료");
//                                        System.out.print("항목을 입력하세요 : ");
//                                        menu = sc.nextInt();
//                                        switch (menu) {
//
//                                            case 1:
//
//                                                System.out.println("1.이름 , 2. 연구실, 3. 이메일, 4.전화번호");
//                                                System.out.print("항목을 입력하세요 : ");
//                                                int menu2 = sc.nextInt();
//
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                if(menu2 == 1){
//
//
//                                                    System.out.print("수정할 이름을 입력하세요 : ");
//                                                    String input1 = userIn.readLine();
//                                                    교수_개인정보수정요청.setData(input1);
//                                                    교수_개인정보수정요청.setProtocolFlag(1);
//
//                                                    os.write(교수_개인정보수정요청.getPacket());
//                                                    is.read(buf);
//                                                    packetType = buf[0];
//                                                    packetUserType = buf[1];
//                                                    수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("수정 실패");
//                                                    }else{
//                                                        System.out.println("수정 성공");
//                                                    }
//
//
//
//                                                }else if (menu2 == 2){
//
//                                                    System.out.print("수정할 연구실을 입력하세요 : ");
//                                                    String input1 = userIn.readLine();
//                                                    교수_개인정보수정요청.setData(input1);
//                                                    교수_개인정보수정요청.setProtocolFlag(2);
//                                                    os.write(교수_개인정보수정요청.getPacket());
//                                                    is.read(buf);
//                                                    packetType = buf[0];
//                                                    packetUserType = buf[1];
//                                                    수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("수정 실패");
//                                                    }else{
//                                                        System.out.println("수정 성공");
//                                                    }
//
//
//
//                                                }else if (menu2 == 3){
//
//                                                    System.out.print("수정할 이메일을 입력하세요 : ");
//                                                    String input1  = userIn.readLine();
//                                                    교수_개인정보수정요청.setData(input1);
//                                                    교수_개인정보수정요청.setProtocolFlag(3);
//
//                                                    os.write(교수_개인정보수정요청.getPacket());
//                                                    is.read(buf);
//                                                    packetType = buf[0];
//                                                    packetUserType = buf[1];
//                                                    수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("수정 실패");
//                                                    }else{
//                                                        System.out.println("수정 성공");
//                                                    }
//
//                                                }else if (menu2 == 4){
//
//                                                    System.out.print("수정할 전화번호를 입력하세요 : ");
//                                                    String input1  = userIn.readLine();
//                                                    교수_개인정보수정요청.setData(input1);
//                                                    교수_개인정보수정요청.setProtocolFlag(4);
//                                                    os.write(교수_개인정보수정요청.getPacket());
//                                                    is.read(buf);
//                                                    packetType = buf[0];
//                                                    packetUserType = buf[1];
//                                                    수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("수정 실패");
//                                                    }else{
//                                                        System.out.println("수정 성공");
//                                                    }
//
//                                                }
//                                                break;
//                                            case 2:
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                str = "";
//
//                                                System.out.print("수정할 비밀번호를 입력하세요 : ");
//                                                str += userIn.readLine();
//
//                                                교수_비밀번호수정요청.setData(str);
//                                                os.write(교수_비밀번호수정요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                if(수신프로토콜.getProtocolCode()==0)
//                                                {
//                                                    System.out.println("비밀번호 수정에 실패했습니다");
//                                                }
//                                                else {
//                                                    System.out.println("비밀번호 수정 완료");
//                                                    password = str;
//
//                                                }
//                                                break;
//                                            case 3:
//
//                                                교수_개설과목조회요청.setData(id);
//                                                os.write(교수_개설과목조회요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("현재 개설한 교과목이 없습니다");
//                                                        break;
//                                                    }
//                                                    else
//                                                    {
//                                                        System.out.println("현재 개설한 교과목");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("현재 본인이 개설한 교과목");
//                                                    System.out.println(tStr);
//
//                                                }
//
//                                                os.write(교수_강의계획서입력기간확인요청.getPacket());
//
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolCode()==0)
//                                                {
//                                                    userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                    str = "";
//
//                                                    System.out.print("강의계획서의 개설과목ID를 입력하세요 : ");
//                                                    str += userIn.readLine();
//                                                    str += "|";
//
//                                                    System.out.print("강의계획서의 교재명 입력하세요 : ");
//                                                    str += userIn.readLine();
//                                                    str += "|";
//
//                                                    System.out.print("강의계획서의 참고문헌을 입력하세요 : ");
//                                                    str += userIn.readLine();
//                                                    str += "|";
//
//                                                    System.out.print("강의계획서의 과목개요를 입력하세요 : ");
//                                                    str += userIn.readLine();
//                                                    str += "|";
//
//                                                    System.out.print("강의계획서의 교수목표을 입력하세요 : ");
//                                                    str += userIn.readLine();
//                                                    str += "|";
//
//
//                                                    System.out.print("강의계획서의 수업방법을 입력하세요 : ");
//                                                    str += userIn.readLine();
//
//                                                    강의계획서입력요청.setData(str);
//                                                    os.write(강의계획서입력요청.getPacket());
//
//                                                    is.read(buf);
//                                                    packetType = buf[0];
//                                                    packetUserType = buf[1];
//                                                    수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("강의계획서 입력에 실패했습니다");
//                                                        System.out.println("존재하지 않는 개설과목Id입니다.");
//                                                    }else if(수신프로토콜.getProtocolCode()==1){
//                                                        System.out.println("강의계획서 입력에 실패했습니다");
//                                                        System.out.println("이미 존재하는 강의계획서입니다.");
//                                                    }
//                                                    else if(수신프로토콜.getProtocolCode()==2){
//                                                        System.out.println("강의계획서 입력에 실패했습니다");
//                                                        System.out.println("담당하는 개설 과목이 아닙니다");
//                                                    }
//                                                    else {
//                                                        System.out.println("강의계획서 입력 성공");
//                                                    }
//                                                }
//                                                else {
//                                                    System.out.println("강의계획서 입력기간이 아닙니다.");
//                                                }
//
//                                                break;
//                                            case 4:
//
//
//                                                os.write(교수_강의계획서ID요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("작성된 강의계획서가 없습니다");
//                                                    }
//                                                    else
//                                                    {
//
//                                                        System.out.println("강의계획서가 작성된 개설교과목ID 목록");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//
//
//                                                        userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                        str = "";
//
//                                                        System.out.print("수정 할 강의계획서의 개설과목ID를 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        System.out.print("수정 할 강의계획서의 교재명 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        System.out.print("수정 할 강의계획서의 참고문헌을 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        System.out.print("강의계획서의 과목개요를 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        System.out.print("강의계획서의 교수목표을 입력하세요 : ");
//                                                        str += userIn.readLine();
//                                                        str += "|";
//
//                                                        System.out.print("강의계획서의 수업방법을 입력하세요 : ");
//                                                        str += userIn.readLine();
//
//                                                        강의계획서수정요청.setData(str);
//                                                        os.write(강의계획서수정요청.getPacket());
//
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                        if(수신프로토콜.getProtocolCode()==0)
//                                                        {
//                                                            System.out.println("강의계획서 수정에 실패했습니다");
//                                                            System.out.println("존재하지 않는 강의계획서입니다.");
//                                                        }
//                                                        else  if(수신프로토콜.getProtocolCode()==1){
//                                                            System.out.println("강의계획서 수정에 실패했습니다");
//                                                            System.out.println("담당하는 개설과목이 아닙니다.");
//                                                        }
//                                                        else{
//                                                            System.out.println("강의계획서 수정 성공");
//                                                        }
//
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("강의계획서가 작성된 개설교과목ID 목록");
//                                                    System.out.println(tStr);
//
//
//                                                    userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                    str = "";
//
//                                                    System.out.print("수정 할 강의계획서의 개설과목ID를 입력하세요 : ");
//                                                    str += userIn.readLine();
//                                                    str += "|";
//
//                                                    System.out.print("수정 할 강의계획서의 교재명 입력하세요 : ");
//                                                    str += userIn.readLine();
//                                                    str += "|";
//
//                                                    System.out.print("수정 할 강의계획서의 참고문헌을 입력하세요 : ");
//                                                    str += userIn.readLine();
//                                                    str += "|";
//
//                                                    System.out.print("강의계획서의 과목개요를 입력하세요 : ");
//                                                    str += userIn.readLine();
//                                                    str += "|";
//
//                                                    System.out.print("강의계획서의 교수목표을 입력하세요 : ");
//                                                    str += userIn.readLine();
//                                                    str += "|";
//
//                                                    System.out.print("강의계획서의 수업방법을 입력하세요 : ");
//                                                    str += userIn.readLine();
//
//                                                    강의계획서수정요청.setData(str);
//                                                    os.write(강의계획서수정요청.getPacket());
//
//                                                    is.read(buf);
//                                                    packetType = buf[0];
//                                                    packetUserType = buf[1];
//                                                    수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("강의계획서 수정에 실패했습니다");
//                                                        System.out.println("존재하지 않는 강의계획서입니다.");
//                                                    }
//                                                    else  if(수신프로토콜.getProtocolCode()==1){
//                                                        System.out.println("강의계획서 수정에 실패했습니다");
//                                                        System.out.println("담당하는 개설과목이 아닙니다.");
//                                                    }
//                                                    else{
//                                                        System.out.println("강의계획서 수정 성공");
//                                                    }
//
//                                                }
//
//
//                                                break;
//
//                                            case 5:
//                                                교수_개설과목조회요청.setData(id);
//                                                os.write(교수_개설과목조회요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("개설교과목조회 실패");
//                                                    }
//                                                    else
//                                                    {
//                                                        System.out.println("개설교과목조회 성공");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("개설교과목조회 성공");
//                                                    System.out.println(tStr);
//
//                                                }
//
//                                                break;
//                                            case 6:
//
//
//                                                os.write(교수_강의계획서ID요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("작성된 강의계획서가 없습니다");
//                                                    }
//                                                    else
//                                                    {
//                                                        System.out.println("강의계획서가 작성된 개설교과목ID 목록");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//
//
//                                                        System.out.print("개설교과목ID를 입력하세요 : ");
//                                                        String input1  = userIn.readLine();
//                                                        교수_개설교과목강의계획서조회요청.setData(input1);
//                                                        os.write(교수_개설교과목강의계획서조회요청.getPacket());
//
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                            if(수신프로토콜.getProtocolCode()==0)
//                                                            {
//                                                                System.out.println("개설교과목강의계획서조회요청 실패");
//                                                            }
//                                                            else
//                                                            {
//                                                                System.out.println("개설교과목강의계획서조회요청 성공");
//                                                                String data2 = 수신프로토콜.getData();
//                                                                System.out.println(data2);
//                                                            }
//
//
//                                                        }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                            byte[] tempBuf2 = new byte[500000];
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf2, 0,수신프로토콜.getProtocolLen());
//
//                                                            int i = 0;
//                                                            while(true){
//
//                                                                i += 2000;
//                                                                is.read(buf);
//                                                                packetType = buf[0];
//                                                                packetUserType = buf[1];
//                                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                                if(수신프로토콜.getProtocolLast() == 0){
//
//                                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf2, i,수신프로토콜.getProtocolLen());
//
//                                                                }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf2, i,수신프로토콜.getProtocolLen());
//                                                                    break;
//                                                                }
//
//
//                                                            }
//
//                                                            String tStr = new String(tempBuf2,0,i + 수신프로토콜.getProtocolLen());
//                                                            System.out.println("개설교과목강의계획서조회요청 성공");
//                                                            System.out.println(tStr);
//
//                                                        }
//
//
//
//
//
//
//
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("강의계획서가 작성된 개설교과목ID 목록");
//                                                    System.out.println(tStr);
//
//
//                                                    System.out.print("개설교과목ID를 입력하세요 : ");
//                                                    String input1  = userIn.readLine();
//                                                    교수_개설교과목강의계획서조회요청.setData(input1);
//                                                    os.write(교수_개설교과목강의계획서조회요청.getPacket());
//
//                                                    is.read(buf);
//                                                    packetType = buf[0];
//                                                    packetUserType = buf[1];
//                                                    수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                    if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                        if(수신프로토콜.getProtocolCode()==0)
//                                                        {
//                                                            System.out.println("개설교과목강의계획서조회요청 실패");
//                                                        }
//                                                        else
//                                                        {
//                                                            System.out.println("개설교과목강의계획서조회요청 성공");
//                                                            String data = 수신프로토콜.getData();
//                                                            System.out.println(data);
//                                                        }
//
//
//                                                    }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                        byte[] tempBuf2 = new byte[500000];
//                                                        System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf2, 0,수신프로토콜.getProtocolLen());
//
//                                                        i = 0;
//                                                        while(true){
//
//                                                            i += 2000;
//                                                            is.read(buf);
//                                                            packetType = buf[0];
//                                                            packetUserType = buf[1];
//                                                            수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                            if(수신프로토콜.getProtocolLast() == 0){
//
//                                                                System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf2, i,수신프로토콜.getProtocolLen());
//
//                                                            }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                                System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf2, i,수신프로토콜.getProtocolLen());
//                                                                break;
//                                                            }
//                                                        }
//
//                                                        tStr = new String(tempBuf2,0,i + 수신프로토콜.getProtocolLen());
//                                                        System.out.println("개설교과목강의계획서조회요청 성공");
//                                                        System.out.println(tStr);
//
//                                                    }
//
//                                                }
//
//                                                break;
//
//                                            case 7:
//
//
//                                                os.write(개설교과목조회요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("개설교과목조회 실패");
//                                                    }
//                                                    else
//                                                    {
//                                                        System.out.println("개설교과목조회 성공");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//
//                                                        System.out.print("개설교과목ID를 입력하세요 : ");
//                                                        String input2 = userIn.readLine();
//                                                        교수_교과목수강신청학생목록조회요청.setData(input2);
//                                                        os.write(교수_교과목수강신청학생목록조회요청.getPacket());
//
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                            if(수신프로토콜.getProtocolCode()==0)
//                                                            {
//                                                                System.out.println("학생목록조회 실패");
//                                                            }
//                                                            else
//                                                            {
//                                                                System.out.println("학생목록조회 성공");
//                                                                data = 수신프로토콜.getData();
//                                                                System.out.println(data);
//                                                            }
//
//
//                                                        }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                            byte[] tempBuf = new byte[500000];
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                            int i = 0;
//                                                            while(true){
//
//                                                                i += 2000;
//                                                                is.read(buf);
//                                                                packetType = buf[0];
//                                                                packetUserType = buf[1];
//                                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                                if(수신프로토콜.getProtocolLast() == 0){
//
//                                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                                }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                                    break;
//                                                                }
//
//
//                                                            }
//
//                                                            String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                            System.out.println("학생목록조회 성공");
//                                                            System.out.println(tStr);
//
//                                                        }
//
//
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("개설교과목조회 성공");
//                                                    System.out.println(tStr);
//
//                                                    System.out.print("개설교과목ID를 입력하세요 : ");
//                                                    String input2 = userIn.readLine();
//                                                    교수_교과목수강신청학생목록조회요청.setData(input2);
//                                                    os.write(교수_교과목수강신청학생목록조회요청.getPacket());
//
//                                                    is.read(buf);
//                                                    packetType = buf[0];
//                                                    packetUserType = buf[1];
//                                                    수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                    if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                        if(수신프로토콜.getProtocolCode()==0)
//                                                        {
//                                                            System.out.println("학생목록조회 실패");
//                                                        }
//                                                        else
//                                                        {
//                                                            System.out.println("학생목록조회 성공");
//                                                            String data = 수신프로토콜.getData();
//                                                            System.out.println(data);
//                                                        }
//
//
//                                                    }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                        tempBuf = new byte[500000];
//                                                        System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                        i = 0;
//                                                        while(true){
//
//                                                            i += 2000;
//                                                            is.read(buf);
//                                                            packetType = buf[0];
//                                                            packetUserType = buf[1];
//                                                            수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                            if(수신프로토콜.getProtocolLast() == 0){
//
//                                                                System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                            }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                                System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                                break;
//                                                            }
//
//
//                                                        }
//
//                                                        tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                        System.out.println("학생목록조회 성공");
//                                                        System.out.println(tStr);
//
//                                                    }
//
//                                                }
//
//                                                break;
//
//                                            case 8:
//                                                교수시간표조회요청.setData(id);
//                                                os.write(교수시간표조회요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("교수시간표조회 실패");
//                                                    }
//                                                    else
//                                                    {
//                                                        System.out.println("교수시간표조회 성공");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("교수시간표조회요청 성공");
//                                                    System.out.println(tStr);
//
//                                                }
//
//                                                break;
//
//                                            case 9:
//                                                System.out.println("종료합니다");
//                                                os.write(EXIT.getPacket());
//                                                return;
//                                        }
//                                    }
//
//                                } else if (사용자구분.equals("학생")) {
//
//                                    int menu = 0;
//                                    String str = "";
//                                    while (true) {
//
//                                        System.out.println("\n         <메뉴>");
//                                        System.out.println(" 1. 개인정보 수정 \n 2. 비밀번호 수정 \n 3. 수강 신청  \n 4. 수강 취소 \n 5. 개설 교과목 목록(전학년) 조회 \n 6. 개설 교과목 강의 계획서 조회 \n 7. 본인 시간표 조회 \n 8. 종료");
//                                        System.out.print("항목을 입력하세요 : ");
//                                        menu = sc.nextInt();
//                                        switch (menu) {
//
//                                            case 1:
//
//                                                System.out.println("1. 이름, 2. 전화번호, 3. 이메일");
//                                                System.out.print("항목을 입력하세요 : ");
//                                                int menu2 = sc.nextInt();
//
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                if(menu2 == 1){
//                                                    System.out.print("수정할 이름을 입력하세요 : ");
//                                                    String input1 = userIn.readLine();
//                                                    학생_개인정보수정요청.setData(input1);
//                                                    학생_개인정보수정요청.setProtocolFlag(1);
//
//                                                    os.write(학생_개인정보수정요청.getPacket());
//                                                    is.read(buf);
//                                                    packetType = buf[0];
//                                                    packetUserType = buf[1];
//                                                    수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("수정 실패");
//                                                    }else{
//                                                        System.out.println("수정 성공");
//                                                    }
//                                                }else if(menu2 == 2){
//                                                    System.out.print("수정할 전화번호를 입력하세요 : ");
//                                                    String input1 = userIn.readLine();
//                                                    학생_개인정보수정요청.setData(input1);
//                                                    학생_개인정보수정요청.setProtocolFlag(2);
//
//                                                    os.write(학생_개인정보수정요청.getPacket());
//                                                    is.read(buf);
//                                                    packetType = buf[0];
//                                                    packetUserType = buf[1];
//                                                    수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("수정 실패");
//                                                    }else{
//                                                        System.out.println("수정 성공");
//                                                    }
//                                                }else if(menu2 == 3){
//                                                    System.out.print("수정할 이메일을 입력하세요 : ");
//                                                    String input1 = userIn.readLine();
//                                                    학생_개인정보수정요청.setData(input1);
//                                                    학생_개인정보수정요청.setProtocolFlag(3);
//
//                                                    os.write(학생_개인정보수정요청.getPacket());
//                                                    is.read(buf);
//                                                    packetType = buf[0];
//                                                    packetUserType = buf[1];
//                                                    수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("수정 실패");
//                                                    }else{
//                                                        System.out.println("수정 성공");
//                                                    }
//                                                }
//                                                break;
//                                            case 2:
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                str = "";
//
//                                                System.out.print("수정할 비밀번호를 입력하세요 : ");
//                                                str += userIn.readLine();
//
//                                                학생_비밀번호수정요청.setData(str);
//                                                os.write(학생_비밀번호수정요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                if(수신프로토콜.getProtocolCode()==0)
//                                                {
//                                                    System.out.println("비밀번호 수정에 실패했습니다");
//                                                }
//                                                else {
//                                                    System.out.println("비밀번호 수정 완료");
//                                                    password = str;
//                                                }
//                                                break;
//
//                                            case 3:
//
//                                                os.write(개설교과목조회요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("개설교과목조회 실패");
//                                                    }
//                                                    else
//                                                    {
//                                                        System.out.println("개설교과목조회 성공");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("개설교과목조회 성공");
//                                                    System.out.println(tStr);
//
//                                                }
//
//                                                System.out.println();
//
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                수강신청본인현황요청.setData(id);
//                                                os.write(수강신청본인현황요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("현재 수강신청 내역이 존재하지 않습니다");
//                                                        System.out.println();
//                                                    }
//                                                    else
//                                                    {
//                                                        System.out.println("현재 본인의 수강신청 내역");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//                                                        System.out.println();
//
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("현재 본인의 수강신청 내역");
//                                                    System.out.println(tStr);
//
//
//                                                }
//
//                                                str = "";
//
//                                                System.out.print("수강할 과목의 개설과목ID를 입력하세요. : ");
//                                                str += userIn.readLine();
//
//                                                수강신청요청.setData(str);
//                                                os.write(수강신청요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                if(수신프로토콜.getProtocolCode()==0)
//                                                {
//                                                    System.out.println("수강신청에 실패했습니다");
//                                                }else{
//                                                    System.out.println("수강신청 성공");
//                                                }
//
//
//
//                                                break;
//
//
//                                            case 4:
//
//                                                userIn = new BufferedReader(new InputStreamReader(System.in));
//
//                                                수강신청본인현황요청.setData(id);
//                                                os.write(수강신청본인현황요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("수강신청 내역이 존재하지 않습니다");
//                                                    }
//                                                    else
//                                                    {
//                                                        System.out.println("현재 본인의 수강신청 내역");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//
//                                                        str = "";
//
//                                                        System.out.print("수강 삭제할 과목의 개설과목ID를 입력하세요. : ");
//                                                        str += userIn.readLine();
//
//                                                        수강삭제요청.setData(str);
//                                                        os.write(수강삭제요청.getPacket());
//
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                        if(수신프로토콜.getProtocolCode()==0)
//                                                        {
//                                                            System.out.println("수강신청 취소에 실패했습니다");
//                                                            System.out.println("수강신청한 기록이 없습니다.");
//                                                        }else{
//                                                            System.out.println("수강신청 취소 성공");
//                                                        }
//
//
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("현재 본인의 수강신청 내역");
//                                                    System.out.println(tStr);
//
//                                                    str = "";
//
//                                                    System.out.print("수강 삭제할 과목의 개설과목ID를 입력하세요. : ");
//                                                    str += userIn.readLine();
//
//                                                    수강삭제요청.setData(str);
//                                                    os.write(수강삭제요청.getPacket());
//
//                                                    is.read(buf);
//                                                    packetType = buf[0];
//                                                    packetUserType = buf[1];
//                                                    수신프로토콜.setPacket(packetType,packetUserType,buf);
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("수강신청 취소에 실패했습니다");
//                                                        System.out.println("수강신청한 기록이 없습니다.");
//                                                    }else{
//                                                        System.out.println("수강신청 취소 성공");
//                                                    }
//
//                                                }
//
//
//                                                break;
//
//                                            case 5:
//
//                                                os.write(개설교과목조회요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("개설교과목조회 실패");
//                                                    }
//                                                    else
//                                                    {
//                                                        System.out.println("개설교과목조회 성공");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("개설교과목조회 성공");
//                                                    System.out.println(tStr);
//
//                                                }
//
//                                                break;
//
//                                            case 6:
//
//                                                os.write(교수_강의계획서ID요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("작성된 강의계획서가 없습니다");
//                                                    }
//                                                    else
//                                                    {
//                                                        System.out.println("강의계획서가 작성된 개설교과목ID 목록");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//
//
//                                                        System.out.print("개설교과목ID를 입력하세요 : ");
//                                                        String input1  = userIn.readLine();
//                                                        학생_선택교과목강의계획서조회요청.setData(input1);
//                                                        os.write(학생_선택교과목강의계획서조회요청.getPacket());
//
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                            if(수신프로토콜.getProtocolCode()==0)
//                                                            {
//                                                                System.out.println("개설교과목강의계획서조회요청 실패");
//                                                            }
//                                                            else
//                                                            {
//                                                                System.out.println("개설교과목강의계획서조회요청 성공");
//                                                                data = 수신프로토콜.getData();
//                                                                System.out.println(data);
//                                                            }
//
//
//                                                        }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                            byte[] tempBuf = new byte[500000];
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                            int i = 0;
//                                                            while(true){
//
//                                                                i += 2000;
//                                                                is.read(buf);
//                                                                packetType = buf[0];
//                                                                packetUserType = buf[1];
//                                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                                if(수신프로토콜.getProtocolLast() == 0){
//
//                                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                                }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                                    break;
//                                                                }
//
//
//                                                            }
//
//                                                            String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                            System.out.println("개설교과목강의계획서조회요청 성공");
//                                                            System.out.println(tStr);
//
//                                                        }
//
//
//
//
//
//
//
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("강의계획서가 작성된 개설교과목ID 목록");
//                                                    System.out.println(tStr);
//
//                                                    System.out.print("개설교과목ID를 입력하세요 : ");
//                                                    String input1  = userIn.readLine();
//                                                    학생_선택교과목강의계획서조회요청.setData(input1);
//                                                    os.write(학생_선택교과목강의계획서조회요청.getPacket());
//
//                                                    is.read(buf);
//                                                    packetType = buf[0];
//                                                    packetUserType = buf[1];
//                                                    수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                    if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                        if(수신프로토콜.getProtocolCode()==0)
//                                                        {
//                                                            System.out.println("개설교과목강의계획서조회요청 실패");
//                                                        }
//                                                        else
//                                                        {
//                                                            System.out.println("개설교과목강의계획서조회요청 성공");
//                                                            String data = 수신프로토콜.getData();
//                                                            System.out.println(data);
//                                                        }
//
//
//                                                    }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                        tempBuf = new byte[500000];
//                                                        System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                        i = 0;
//                                                        while(true){
//
//                                                            i += 2000;
//                                                            is.read(buf);
//                                                            packetType = buf[0];
//                                                            packetUserType = buf[1];
//                                                            수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                            if(수신프로토콜.getProtocolLast() == 0){
//
//                                                                System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                            }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                                System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                                break;
//                                                            }
//
//
//                                                        }
//
//                                                        tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                        System.out.println("개설교과목강의계획서조회요청 성공");
//                                                        System.out.println(tStr);
//
//                                                    }
//
//                                                }
//
//                                                break;
//
//
//                                            case 7:
//
//                                                학생시간표조회요청.setData(id);
//                                                os.write(학생시간표조회요청.getPacket());
//
//                                                is.read(buf);
//                                                packetType = buf[0];
//                                                packetUserType = buf[1];
//                                                수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                if(수신프로토콜.getProtocolFlag() == 0){
//
//                                                    if(수신프로토콜.getProtocolCode()==0)
//                                                    {
//                                                        System.out.println("학생시간표조회요청 실패");
//                                                    }
//                                                    else
//                                                    {
//                                                        System.out.println("학생시간표조회요청 성공");
//                                                        String data = 수신프로토콜.getData();
//                                                        System.out.println(data);
//                                                    }
//
//
//                                                }else if(수신프로토콜.getProtocolFlag() == 1){
//
//                                                    byte[] tempBuf = new byte[500000];
//                                                    System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, 0,수신프로토콜.getProtocolLen());
//
//                                                    int i = 0;
//                                                    while(true){
//
//                                                        i += 2000;
//                                                        is.read(buf);
//                                                        packetType = buf[0];
//                                                        packetUserType = buf[1];
//                                                        수신프로토콜.setPacket(packetType,packetUserType,buf);
//
//                                                        if(수신프로토콜.getProtocolLast() == 0){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//
//                                                        }else if (수신프로토콜.getProtocolLast() == 1){
//
//                                                            System.arraycopy(수신프로토콜.getByteData(), 0, tempBuf, i,수신프로토콜.getProtocolLen());
//                                                            break;
//                                                        }
//
//
//                                                    }
//
//                                                    String tStr = new String(tempBuf,0,i + 수신프로토콜.getProtocolLen());
//                                                    System.out.println("학생시간표조회요청 성공");
//                                                    System.out.println(tStr);
//
//                                                }
//
//                                                break;
//
//
//                                            case 8:
//
//                                                System.out.println("종료합니다");
//                                                os.write(EXIT.getPacket());
//                                                return;
//                                        }
//                                    }
//                                }
//                            }
//
//
//                            break;
//
//                    }
//                    break;
//            }
//
//        }
//
//
//
//    }
//
//
//}
//
//
