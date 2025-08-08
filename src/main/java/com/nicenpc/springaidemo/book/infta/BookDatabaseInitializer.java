package com.nicenpc.springaidemo.book.infta;

import com.nicenpc.springaidemo.book.application.repository.BookEntity;
import com.nicenpc.springaidemo.book.application.service.BookApplicationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookDatabaseInitializer {

    private final BookApplicationService bookApplicationService;

    @PostConstruct
    public void init() {

        String[][] dataArray = {
                {"Java核心技術", "Cay S. Horstmann"},
                {"Spring Boot實戰", "Craig Walls"},
                {"深入理解Java虛擬機", "周志明"},
                {"演算法導論", "Thomas H. Cormen"},
                {"設計模式", "Erich Gamma"},
                {"人工智慧：一種現代方法", "Stuart Russell"},
                {"機器學習實戰", "Peter Harrington"},
                {"Python程式設計：從入門到實踐", "Eric Matthes"},
                {"JavaScript高級程式設計", "Nicholas C. Zakas"},
                {"資料結構與演算法分析", "Mark Allen Weiss"},
                {"電腦網路", "Andrew S. Tanenbaum"},
                {"作業系統概念", "Abraham Silberschatz"},
                {"資料庫系統概念", "Abraham Silberschatz"},
                {"軟體工程", "Ian Sommerville"},
                {"編譯原理", "Alfred V. Aho"},
                {"電腦組織與結構", "David A. Patterson"},
                {"離散數學", "Kenneth H. Rosen"},
                {"線性代數", "Gilbert Strang"},
                {"機率論與數理統計", "Sheldon Ross"},
                {"微積分", "James Stewart"}
        };

        for (String[] data : dataArray) {
            BookEntity bookEntity = new BookEntity();
            bookEntity.setTitle(data[0]);
            bookEntity.setAuthor(data[1]);
            bookApplicationService.create(bookEntity);
        }
        // 印出新增幾本書
        log.info("Initialized " + bookApplicationService.findAll().size() + " books");
    }
}
