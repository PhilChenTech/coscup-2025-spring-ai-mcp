package com.nicenpc.springaidemo.book.application.mcp;

import com.nicenpc.springaidemo.book.application.repository.BookEntity;
import com.nicenpc.springaidemo.book.application.service.BookApplicationService;
import com.nicenpc.springaidemo.share.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 書籍管理工具類
 * 提供 AI 工具功能，用於書籍的查詢、刪除等操作
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookMcpTool {

    private final BookApplicationService bookApplicationService;

    @Tool(name = "create-book", description = "新增書籍")
    public BookEntity createBook(String title, String author) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(title);
        bookEntity.setAuthor(author);
        return bookApplicationService.create(bookEntity);
    }


    /**
     * 取得所有書籍清單
     *
     * @return 書籍清單，永不為 null
     * @throws RuntimeException 當服務層發生錯誤時
     */
    @Tool(name = "find-books", description = "取得書籍清單")
    @NonNull
    public List<BookEntity> getBooks() {
        log.info("開始獲取所有書籍清單");

        try {
            final List<BookEntity> books = bookApplicationService.findAll();
            Objects.requireNonNull(books, "書籍服務返回了 null 清單");

            log.info("成功獲取書籍清單，共 {} 本書", books.size());

            if (log.isDebugEnabled()) {
                books.forEach(book -> log.debug("書籍: {} - {}", book.getTitle(), book.getAuthor()));
            }

            return books;

        } catch (Exception e) {
            log.error("獲取書籍清單時發生異常", e);
            throw new RuntimeException("無法獲取書籍清單: " + e.getMessage(), e);
        }
    }

    /**
     * 根據書名查詢單一書籍
     *
     * @param title 書名，不能為空
     * @return 找到的書籍實體，未找到時返回 null
     * @throws IllegalArgumentException 當書名參數無效時
     * @throws RuntimeException         當服務層發生錯誤時
     */
    @Tool(name = "find-book-by-name", description = "根據書名取得單一本書")
    @Nullable
    public BookEntity getBook(@NonNull final String title) {
        if (!StringUtils.hasText(title)) {
            final String errorMsg = "書名參數不能為空或空白";
            log.warn(errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }

        final String normalizedTitle = title.trim();
        log.info("開始搜尋書名為 '{}' 的書籍", normalizedTitle);

        try {
            final BookEntity book = bookApplicationService.findByTitle(normalizedTitle).orElse(null);

            if (book != null) {
                log.info("成功找到書籍: {} (作者: {}, ID: {})",
                        book.getTitle(), book.getAuthor(), book.getId());
            } else {
                log.warn("未找到書名為 '{}' 的書籍", normalizedTitle);
            }

            return book;

        } catch (Exception e) {
            log.error("搜尋書籍 '{}' 時發生異常", normalizedTitle, e);
            throw new RuntimeException("無法搜尋書籍: " + e.getMessage(), e);
        }
    }

    /**
     * 刪除指定書名的書籍
     *
     * @param title 要刪除的書名，不能為空
     * @return 操作結果訊息
     * @throws IllegalArgumentException 當書名參數無效時
     * @throws RuntimeException         當服務層發生錯誤時
     */
    @Tool(name = "delete-book", description = "刪除書籍")
    @NonNull
    public String deleteBook(@NonNull final String title) {
        if (!StringUtils.hasText(title)) {
            final String errorMsg = "書名參數不能為空或空白";
            log.warn("刪除書籍失敗: {}", errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }

        final String normalizedTitle = title.trim();
        log.info("開始刪除書名為 '{}' 的書籍", normalizedTitle);

        try {
            // 先檢查書籍是否存在
            final boolean exists = bookApplicationService.findByTitle(normalizedTitle).isPresent();
            if (!exists) {
                final String warningMsg = String.format("書籍 '%s' 不存在，無法刪除", normalizedTitle);
                log.warn(warningMsg);
                return warningMsg;
            }

            bookApplicationService.deleteByTitle(normalizedTitle);

            final String successMsg = String.format("成功刪除書籍: %s", normalizedTitle);
            log.info(successMsg);

            return AppConstants.DONE;

        } catch (Exception e) {
            log.error("刪除書籍 '{}' 時發生異常", normalizedTitle, e);
            throw new RuntimeException("無法刪除書籍: " + e.getMessage(), e);
        }
    }

    /**
     * 刪除所有書籍
     *
     * @return 操作結果訊息
     * @throws RuntimeException 當服務層發生錯誤時
     */
    @Tool(name = "delete-all-book", description = "刪除所有書籍")
    @NonNull
    public String deleteAllBooks() {
        log.warn("收到刪除所有書籍的請求 - 這是危險操作");

        try {
            // 先獲取當前書籍數量用於日誌記錄
            final List<BookEntity> currentBooks = bookApplicationService.findAll();
            final int bookCount = currentBooks != null ? currentBooks.size() : 0;

            if (bookCount == 0) {
                final String infoMsg = "沒有書籍需要刪除";
                log.info(infoMsg);
                return infoMsg;
            }

            log.info("開始刪除所有書籍，共 {} 本", bookCount);

            bookApplicationService.deleteAll();

            final String successMsg = String.format("成功刪除所有書籍，共 %d 本", bookCount);
            log.info(successMsg);

            return AppConstants.DONE;

        } catch (Exception e) {
            log.error("刪除所有書籍時發生異常", e);
            throw new RuntimeException("無法刪除所有書籍: " + e.getMessage(), e);
        }
    }
}
