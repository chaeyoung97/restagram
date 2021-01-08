package com.example.restagram.domain;

import com.example.restagram.domain.users.Users;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EqualsAndHashCode
@Getter
@MappedSuperclass   //JAP entity클래스들이 supertimeentity를 상속할 경우 이 클래스의 필드들도 칼럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class)  //이 클래스에 Auditing기능을 포함시킴
public abstract class BaseTimeEntity {

    @Column(name = "created_date")
    @CreatedDate    //entity생성되어 저장될 떄 시간이 자동 저장됨
    private LocalDateTime createdDate;

    @LastModifiedDate   //조회한 entity의 값을 변견할 때 시간이 자동 저장 됨
    private LocalDateTime modifiedDate;

    public String getFormattedCreatedDate(){
        return getFormattedDate(createdDate, "yyyy.MM.dd HH:mm:ss");
    }

    public String getFormattedModifiedDate(){
        return getFormattedDate(modifiedDate, "yyyy.MM.dd HH:mm:ss");
    }

    private String getFormattedDate(LocalDateTime dateTime, String format){
        if(dateTime == null)
            return "";
        return dateTime.format(DateTimeFormatter.ofPattern(format));

    }

}
