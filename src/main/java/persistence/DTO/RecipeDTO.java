package persistence.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class RecipeDTO {
    private int foodNum;
    private int weatherNum;
    private int seasonNum;

    private String foodName;
    private String imgLink;
    private String youtubeLink;

    public String toString(){
        return foodName + ", "  + imgLink;
    }

}
