package persistence.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private int memberNumber;
    private String memberID;
    private String memberPassword;

    public MemberDTO(){}

    public MemberDTO(String memberId, String memberPassword){
        this.memberID = memberId;
        this.memberPassword = memberPassword;
    }

    public String getMemberID(){
        return memberID;
    }

    public String toString(){
        return memberID + ", "  + memberPassword;
    }

}