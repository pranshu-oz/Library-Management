package pranshu.library.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pranshu.library.management.repository.LoanRepository;

@Service
public class LoanService {

	@Autowired 
	LoanRepository loanRepository;
}
