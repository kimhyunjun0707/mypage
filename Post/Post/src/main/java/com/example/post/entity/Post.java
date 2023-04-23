package com.example.post.entity;
import com.example.post.dto.RequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor

public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;            //고유아이디
    @Column(nullable = false)
    private String title;       //제목
    @Column(nullable = false)
    private String content;     //내용
    @Column(nullable = false)
    private String username;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id", insertable = true, updatable = true)
    private Users user;
    //name 속성은 Join Column의 이름
    //referencedColumnName은 Join Column이 참조하는 대상의 컬럼명
    //insertable은 엔티티가 생성될 때 해당 필드를 삽입 가능한지를 결정하는 옵션
    //updatable은 엔티티가 업데이트될 때 해당 필드를 업데이트 가능한지를 결정



    //requestDto로 받아온 정보를 받아주고 초기화.
    //service에 있는createPost에서 repository.save를 통해서 데이터베이스에저장
    //
    public Post(RequestDto requestDto, Users user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = user;
        this.username = user.getUsername();


    }
    //수정할데이터를 가저온다.
    public void update(RequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }


}
