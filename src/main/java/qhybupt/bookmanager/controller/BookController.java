package qhybupt.bookmanager.controller;

import qhybupt.bookmanager.model.Book;
import qhybupt.bookmanager.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
    
    @RequestMapping(path = {"{bookId:[0-9]+}/borrowBook"}, method = {RequestMethod.GET})
    public String borrowBook(
    		@PathVariable("bookId") int bookId
	) {
		bookService.borrowBook(bookId);
		return "redirect:/index";
	}
    
    @RequestMapping(path = {"{bookId:[0-9]+}/returnBook"}, method = {RequestMethod.GET})
    public String returnBook(
    		@PathVariable("bookId") int bookId
	) {
    	bookService.returnBook(bookId);
    	return "redirect:/index";
	}
    
    @RequestMapping("editBook")
    public String editBook(Model model){
    	model.addAttribute("books", bookService.getAllBooks());
        return "/books/editBook";
    }
    
//    @RequestMapping("updateBook")
//    public String updateBook(Model model, Long id) {
//        model.addAttribute("book", book);
//    	bookService.updateBook(book);
//        return "redirect:editBook";
//    }
    
    @RequestMapping("addBook")
    public String addBook(
    		@RequestParam("name") String name,
    	    @RequestParam("author") String author,
    	    @RequestParam("price") String price
    	) {
    	if (name == null || name.length() == 0 
    			|| author == null || author.length() == 0
    			|| price == null || author.length() == 0) {
    		return "redirect:/books/editBook";
    	}
    	Book book = new Book();
    	book.setName(name);
    	book.setAuthor(author);
    	book.setPrice(price);
    	bookService.addBook(book);
        return "redirect:/books/editBook";
    }
    
    @RequestMapping("{bookId:[0-9]+}/deleteBook")
    public String deleteBook(
    		@PathVariable("bookId") int bookId
	) {
    	bookService.delete(bookId);
        return "redirect:/books/editBook";
    }
 
}
