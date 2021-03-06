package tcm.xxcse.basic.dto;

/**
 * Auto Generated By Hap Code Generator
 **/

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.hand.hap.system.dto.BaseDTO;

import java.util.Date;

@ExtensionAttribute(disable = true)
@Table(name = "xxcse_class_schedule")
public class ClassSchedule extends BaseDTO {

    public static final String FIELD_SCHEDULE_ID = "scheduleId";
    public static final String FIELD_CLASS_ID = "classId";
    public static final String FIELD_START_DATETIME = "startDatetime";
    public static final String FIELD_END_DATETIME = "endDatetime";
    public static final String FIELD_CLASSROOM = "classroom";
    public static final String FIELD_TIME_LONG = "timeLong";
    public static final String FIELD_COMMENTS = "comments";
    public static final String FIELD_PROGRAM_APPLICATION_ID = "programApplicationId";
    public static final String FIELD_PROGRAM_UPDATE_DATE = "programUpdateDate";


    @Id
    @GeneratedValue
    private Long scheduleId; //表ID，主键，供其他表做外键

    private Long classId;

    private Date startDatetime;

    private Date endDatetime;

    @Length(max = 100)
    private String classroom;

    private Long timeLong;

    @Length(max = 4000)
    private String comments;

    @Transient
    private String teacherName;
    @Transient
    private String teacherNum;
    @Transient
    private String classType; //类型
    @Transient
    private String classNum; //班次编号：课程编号+老师编号＋2位流水号
    @Transient
    private String className; //班次名称
    @Transient
    private Long teacherId; //教师
    @Transient
    private String courseCode; //课程

    private Long programApplicationId;

    private Date programUpdateDate;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setTimeLong(Long timeLong) {
        this.timeLong = timeLong;
    }

    public Long getTimeLong() {
        return timeLong;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(String teacherNum) {
        this.teacherNum = teacherNum;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
