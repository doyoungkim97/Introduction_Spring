package hello.hellospring.services;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public Long join(Member member){
        //중복 이름 회원 허용 X
        // Option 반환 인자 선언 후 실행 이 아닌 바로 isPresnet 적용
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m->{
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw  new IllegalStateException("이미 존재하는 회원");
                        });

        memberRepository.save(member);
        return  member.getId();
    }


}
