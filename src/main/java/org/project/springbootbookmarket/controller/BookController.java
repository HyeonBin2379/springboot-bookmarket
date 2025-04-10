package org.project.springbootbookmarket.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.project.springbootbookmarket.domain.Book;
import org.project.springbootbookmarket.service.BookService;
import org.project.springbootbookmarket.validator.BookValidator;
import org.project.springbootbookmarket.validator.UnitsInStockValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Value("${file.uploadDir}")
    String fileDir;

    @Autowired
    private BookValidator bookValidator;

    @GetMapping
    public String requestBookList(Model model) {
        List<Book> list = bookService.getAllBookList();

        model.addAttribute("bookList", list);

        return "books";
    }

    @GetMapping("/all")
    public ModelAndView requestAllBooks() {
        ModelAndView modelAndView = new ModelAndView();
        List<Book> list = bookService.getAllBookList();

        modelAndView.addObject("bookList", list);
        modelAndView.setViewName("books");

        return modelAndView;
    }

    @GetMapping("/book")
    public String requestBookById(@RequestParam("id") String bookId, Model model) {
        Book bookById = bookService.getBookById(bookId);
        model.addAttribute("book", bookById);
        return "book";
    }

    @GetMapping("/{category}")
    public String requestBookByCategory(@PathVariable("category") String bookCategory, Model model) {
        List<Book> booksByCategory = bookService.getBookListByCategory(bookCategory);
        model.addAttribute("bookList", booksByCategory);
        return "books";
    }

    @GetMapping("/filter/{bookFilter}")
    public String requestBookByFilter(
            @MatrixVariable(pathVar = "bookFilter") Map<String, List<String>> bookFilter,
            Model model) {
        Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);
        model.addAttribute("bookList", booksByFilter);
        return "books";
    }

    @GetMapping("/add")
    public String requestAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/add")
    public String submitAddNewBook(@Valid @ModelAttribute Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "addBook";
        }

        MultipartFile bookImage = book.getBookImage();
        String saveName = bookImage.getOriginalFilename();
        File saveFile = new File(fileDir, saveName);

        if (!bookImage.isEmpty()) {
            try {
                bookImage.transferTo(saveFile);
            } catch (IOException e) {
                throw new RuntimeException("도서 이미지 업로드가 실패하였습니다.");
            }
        }
        bookService.setNewBook(book);

        return "redirect:/books";
    }

    @GetMapping("/download")
    public void downloadBookImage(@RequestParam("file") String paramKey,
            HttpServletResponse response) throws IOException {
        File imageFile = new File(fileDir + paramKey);
        response.setContentType("application/download");
        response.setContentLength((int) imageFile.length());
        response.setHeader("Content-disposition", "attachment;filename=\"" + imageFile.getName() + "\"");

        OutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(imageFile);
        FileCopyUtils.copy(fis, os);
        fis.close();
        os.close();
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("addTitle", "신규 도서 등록");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields("bookId", "name", "unitPrice", "author", "description",
                "publisher", "category", "unitsInStock", "totalPages", "releaseDate",
                "condition", "fileName", "bookImage");
        binder.setValidator(bookValidator);
    }
}
