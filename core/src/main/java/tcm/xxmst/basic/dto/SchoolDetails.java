package tcm.xxmst.basic.dto;

/**
 * Auto Generated By Hap Code Generator
 **/

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.hand.hap.mybatis.annotation.Condition;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.hand.hap.system.dto.BaseDTO;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

@ExtensionAttribute(disable = true)
@Table(name = "xxmst_school_details")
public class SchoolDetails extends BaseDTO {

    public static final String FIELD_SCHOOL_ID = "schoolId";
    public static final String FIELD_LE_CODE = "leCode";
    public static final String FIELD_SCHOOL_NUMBER = "schoolNumber";
    public static final String FIELD_SCHOOL_NAME = "schoolName";
    public static final String FIELD_ADDRESS = "address";
    public static final String FIELD_DIRECTOR = "director";
    public static final String FIELD_CONTACT_INFO = "contactInfo";
    public static final String FIELD_INFOR_ATTR1 = "inforAttr1";
    public static final String FIELD_INFOR_ATTR2 = "inforAttr2";
    public static final String FIELD_INFOR_ATTR3 = "inforAttr3";
    public static final String FIELD_INFOR_ATTR4 = "inforAttr4";
    public static final String FIELD_INFOR_ATTR5 = "inforAttr5";
    public static final String FIELD_PROGRAM_APPLICATION_ID = "programApplicationId";
    public static final String FIELD_PROGRAM_UPDATE_DATE = "programUpdateDate";


    @Id
    @GeneratedValue
    private Long schoolId; //表ID，主键，供其他表做外键

    @NotEmpty
    @Length(max = 30)
    private String leCode; //品牌

    @Condition(
            operator = "LIKE"
    )
    @Length(max = 30)
    private String schoolNumber; //学校编码

    @Condition(
            operator = "LIKE"
    )
    @Length(max = 250)
    private String schoolName; //学校名称

    @Length(max = 1000)
    private String address; //学校地址

    private Long director; //管理员

    @Length(max = 100)
    private String contactInfo; //联系方式

    @Length(max = 240)
    private String inforAttr1;

    @Length(max = 240)
    private String inforAttr2;

    @Length(max = 240)
    private String inforAttr3;

    @Length(max = 240)
    private String inforAttr4;

    @Length(max = 240)
    private String inforAttr5;
    @Transient
    private String directorName;

    private Long programApplicationId;

    private Date programUpdateDate;


    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setLeCode(String leCode) {
        this.leCode = leCode;
    }

    public String getLeCode() {
        return leCode;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public String getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setDirector(Long director) {
        this.director = director;
    }

    public Long getDirector() {
        return director;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setInforAttr1(String inforAttr1) {
        this.inforAttr1 = inforAttr1;
    }

    public String getInforAttr1() {
        return inforAttr1;
    }

    public void setInforAttr2(String inforAttr2) {
        this.inforAttr2 = inforAttr2;
    }

    public String getInforAttr2() {
        return inforAttr2;
    }

    public void setInforAttr3(String inforAttr3) {
        this.inforAttr3 = inforAttr3;
    }

    public String getInforAttr3() {
        return inforAttr3;
    }

    public void setInforAttr4(String inforAttr4) {
        this.inforAttr4 = inforAttr4;
    }

    public String getInforAttr4() {
        return inforAttr4;
    }

    public void setInforAttr5(String inforAttr5) {
        this.inforAttr5 = inforAttr5;
    }

    public String getInforAttr5() {
        return inforAttr5;
    }

    public void setProgramApplicationId(Long programApplicationId) {
        this.programApplicationId = programApplicationId;
    }

    public Long getProgramApplicationId() {
        return programApplicationId;
    }

    public void setProgramUpdateDate(Date programUpdateDate) {
        this.programUpdateDate = programUpdateDate;
    }

    public Date getProgramUpdateDate() {
        return programUpdateDate;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
}
