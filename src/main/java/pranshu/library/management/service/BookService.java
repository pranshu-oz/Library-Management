package pranshu.library.management.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import pranshu.library.management.model.Book;
import pranshu.library.management.model.Loans.Status;
import pranshu.library.management.model.User;
import pranshu.library.management.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	public void saveNewBook(Book book) {
		book.setAvailableCopy(book.getTotalCopy());
		bookRepository.save(book);
	}

	public Page<Book> findAllBookByFilter(String filter, Pageable pageable) {

		return bookRepository.findAllBookByFilter(filter, pageable);
	}

	public Book findBookById(long id) {

		return bookRepository.findById(id).orElse(null);
	}

	public void deleteBookById(long id) {
		
		bookRepository.deleteById(id);
	}

	public List<Book> findAllBooks() {
		
		return bookRepository.findAll();
	}

	public long getTotalBooks() {

		return bookRepository.getTotalBooks();
	}

	public Map<String,List<Object>> getCurrentWeekStats() {
		LocalDate today =LocalDate.now();
		LocalDate startOfWeek=today.with(DayOfWeek.MONDAY);
		LocalDate endOfWeek=today.with(DayOfWeek.SUNDAY);
		
		Status status=Status.Borrowed;
		List<Object[]> result=bookRepository.getCurrentWeekStats(startOfWeek,endOfWeek,status);
		Map<String,Object[]> map=new HashMap<>();
		
		for(Object[] obj : result) {
			map.put(obj[0].toString(), obj);
		}
		
		ArrayList<Object> days =new ArrayList<>();
		ArrayList<Object> borrow=new ArrayList<>();
		LocalDate day=startOfWeek;
		while(!day.isAfter(endOfWeek)) {
			
			days.add(day.getDayOfWeek().toString());
			
			if(map.containsKey(day.toString())) {
				borrow.add(map.get(day.toString())[1]);
			}else borrow.add(0.0);
			day=day.plusDays(1);
			
		}
		
		HashMap<String,List<Object>> response=new HashMap();
		response.put("days", days);
		response.put("borrow", borrow);
		return response;
	}

	public Map<String, List<Object>> getAvailableStats() {
		
		List<Object[]> result=bookRepository.getAvailableStats();
		ArrayList<Object> titles=new ArrayList();
		ArrayList<Object> copy=new ArrayList();
		
		for(Object[]obj : result) {
			
			titles.add(obj[0]);
			copy.add(obj[1]);
		}
		
		HashMap<String,List<Object>> map=new HashMap();
		map.put("title", titles);
		map.put("copy", copy);
		return map;
	}

}
