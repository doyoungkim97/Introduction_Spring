package hello.hellospring.services;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    public Long join(Member member){
        //중복 이름 회원 허용 X
        /**
         * 중복 회원을 허용 하지 않는 로직을 구현하느 것이 핵심이다.
         * 먼저 회원이 있나 찾아야함
         *
         * 영한님은 옵셔널 반환을 굳이 안한다
         */
        // Option 반환 인자 선언 후 실행 이 아닌 바로 isPresnet 적용
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m->{
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        validateDuplicate(member); // 중복 회원 검증

        memberRepository.save(member);
        return  member.getId();
    }

    private void validateDuplicate(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw  new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
