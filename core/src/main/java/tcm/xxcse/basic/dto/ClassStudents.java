package tcm.xxcse.basic.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
@ExtensionAttribute(disable=true)
@Table(name = "xxcse_class_students")
public class ClassStudents extends BaseDTO {

     public static final String FIELD_CLASS_STUDENT_ID = "classStudentId";
     public static final String FIELD_STUDENT_ID = "studentId";
     public static final String FIELD_CLASS_ID = "classId";
     public static final String FIELD_START_DATE = "startDate";
     public static final String FIELD_END_DATE = "endDate";
     public static final String FIELD_COMMENTS = "comments";
     public static final String FIELD_PROGRAM_APPLICATION_ID = "programApplicationId";
     public static final String FIELD_PROGRAM_UPDATE_DATE = "programUpdateDate";


     @Id
     @GeneratedValue
     private Long classStudentId; //表ID，主键，供其他表做外键

     private Long studentId;

     private Long classId;

     private Date startDate;

     private Date endDate;

     @Length(max = 4000)
     private String comments;

     private Long programApplicationId;

     private Date programUpdateDate;


     public void setClassStudentId(Long classStudentId){
         this.classStudentId = classStudentId;
     }

     public Long getClassStudentId(){
         return classStudentId;
     }

     public void setStudentId(Long studentId){
         this.studentId = studentId;
     }

     public Long getStudentId(){
         return studentId;
     }

     public void setClassId(Long classId){
         this.classId = classId;
     }

     public Long getClassId(){
         return classId;
     }

     public void setStartDate(Date startDate){
         this.startDate = startDate;
     }

     public Date getStartDate(){
         return startDate;
     }

     public void setEndDate(Date endDate){
         this.endDate = endDate;
     }

     public Date getEndDate(){
         return endDate;
     }

     public void setComments(String comments){
         this.comments = comments;
     }

     public String getComments(){
         return comments;
     }

     public void setProgramApplicationId(Long programApplicationId){
         this.programApplicationId = programApplicationId;
     }

     public Long getProgramApplicationId(){
         return programApplicationId;
     }

     public void setProgramUpdateDate(Date programUpdateDate){
         this.programUpdateDate = programUpdateDate;
     }

     public Date getProgramUpdateDate(){
         return programUpdateDate;
     }

     }
