package nvduy1997.com.easytoeic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestPart5 {

@SerializedName("Id")
@Expose
private String id;
@SerializedName("Ten")
@Expose
private String ten;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getTen() {
return ten;
}

public void setTen(String ten) {
this.ten = ten;
}

}