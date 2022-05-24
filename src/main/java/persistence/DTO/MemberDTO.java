package persistence.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private int memberNumber;
    private String memberId;
    private String memberPassword;

    public MemberDTO(){}

    public MemberDTO(String memberId, String memberPassword){
        this.memberId = memberId;
        this.memberPassword = memberPassword;
    }

    public String getMemberId(){
        return memberId;
    }

    public String toString(){
        return memberId + ", "  + memberPassword;
    }

}