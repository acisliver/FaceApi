import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.nullValue;

public class VerifyTest {
    private static String[] verifiedArray = {
            "https://img.hankyung.com/photo/202112/BF.28305426.1.jpg", //비교대상 이미지
            "https://i.ytimg.com/vi/UAQT5Hgrm1Q/maxresdefault.jpg" //피 비교대상 이미지
    };

    public String extract(String jsonString, String extractString){
        if (jsonString.charAt(0) == '[') {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject = new JSONObject(jsonArray.get(0).toString());
            return jsonObject.getString(extractString);
        } else if (jsonString.charAt(0) == '{') {
            JSONObject jsonObject = new JSONObject(jsonString);
            return jsonObject.getString(extractString);
        } else {
            return jsonString;
        }
    }

    @Test
    public void verify(){
        String extractString = "faceId";
        Face face = new Face();
        String detected1 = face.detect(verifiedArray[0]);
        String detected2 = face.detect(verifiedArray[1]);

        String result = face.verify(extract(detected1, extractString), extract(detected2, extractString));
        System.out.println(result);
        String isError = result.substring(2, 7);
        assertThat(isError, not("isError"));
    }
}
