package pranshu.library.management.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import pranshu.library.management.model.Book;
import pranshu.library.management.model.Loans;
import pranshu.library.management.model.Loans.Status;
import pranshu.library.management.model.User;
import pranshu.library.management.repository.LoanRepository;

@Service
public class LoanService {

	@Autowired 
	LoanRepository loanRepository;
	
	@Autowired
	BookService bookService;

	public String addNewLoan(@Valid Loans loans) {

		LocalDate today=LocalDate.now();
		Book book=loans.getBook();
		User user=loans.getUser();
		if(book.getAvailableCopy()==0) {
			return "No Extra Copy Available";
		}
		if(loanRepository.findAllLoansByUserId(user.getId())==5) {
			return "User has Already Exceeded Borrow Limit";
		}
		loans.setDueDate(today.plusDays(15));
		loans.setStatus(Status.Borrowed);
		book.setAvailableCopy(book.getAvailableCopy()-1);
		loanRepository.save(loans);
		
		return null;
		
	}

	public List<Loans> findAllLoans() {
		
		return loanRepository.findAll();
	}
	
	public List<Loans> findFilteredLoan(Status status, String search){
		
		return loanRepository.findFilteredLoan(status,search);
	}

	public long returnBook(Loans loans) {
		
		long extraDays=0;
		LocalDate today=LocalDate.now();
		Loans loan=loanRepository.findById(loans.getId()).orElse(null);
		LocalDate dueDate=loan.getDueDate();
		loan.setReturnDate(today);
		
		if(today.isAfter(dueDate))
		extraDays=ChronoUnit.DAYS.between(today, loan.getDueDate());
		else extraDays=0;
		
		loan.setFineAmount(Math.toIntExact(extraDays*100));
		loan.setStatus(Status.Returned);
		loan.getBook().setAvailableCopy(loan.getBook().getAvailableCopy()+1);
		loanRepository.save(loan);
		
		return loan.getFineAmount();
	}

	public long getTotalLoans() {
		
		return loanRepository.getTotalLoans();
	}

	public long getTotalOverdue() {
		
		LocalDate today=LocalDate.now();
		return loanRepository.getTotalOverdue(today);
	}
}
