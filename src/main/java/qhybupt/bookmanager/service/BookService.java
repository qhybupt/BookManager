package qhybupt.bookmanager.service;

import qhybupt.bookmanager.dao.BookDAO;
import qhybupt.bookmanager.model.Book;
import qhybupt.bookmanager.model.enums.BookStatusEnum;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	private BookDAO bookDAO;

	public List<Book> getAllBooks() {
		return bookDAO.selectAll();
	}

	public void addBook(Book book) {
		bookDAO.addBook(book);
	}

	public void borrowBook(long id) {
		bookDAO.updateBookStatus(id, BookStatusEnum.BORROW.getValue());
	}

	public void returnBook(long id) {
		bookDAO.updateBookStatus(id, BookStatusEnum.NORMAL.getValue());
	}
	
	public void updateBook(Book book) {
		bookDAO.updateBook(book);
	}
	
	public Book get(long id) {
		return bookDAO.get(id);
	}
	
	public void delete(long id) {
		bookDAO.deleteBook(id);
	}
}
