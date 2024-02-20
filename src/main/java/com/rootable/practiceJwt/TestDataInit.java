package com.rootable.practiceJwt;

import com.rootable.practiceJwt.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;

//@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    /*
    * 테스트용 데이터 추가
    * */

    @PostConstruct
    public void init() {
//
//        /*
//         * 도서
//         * */
//
//        Book book1 = Book.builder()
//                .title("원씽")
//                .writer("게리 켈러")
//                .price(12600)
//                .stock(5)
//                .status(Status.PERMISSION)
//                .build();
//
//        Book book2 = Book.builder()
//                .title("밤의 문화사")
//                .writer("로저 에커치")
//                .price(25000)
//                .stock(0)
//                .status(Status.DENIED)
//                .build();
//
//        Book book3 = Book.builder()
//                .title("잘될 수밖에 없는 너에게")
//                .writer("최서영")
//                .price(14400)
//                .stock(3)
//                .status(Status.PERMISSION)
//                .build();
//
//        Book book4 = Book.builder()
//                .title("야구 잘하는 법")
//                .writer("오타니 쇼헤이")
//                .price(18000)
//                .stock(0)
//                .status(Status.PERMISSION)
//                .build();
//
//        bookRepository.save(book1);
//        bookRepository.save(book2);
//        bookRepository.save(book3);
//        bookRepository.save(book4);
//
    }

}
