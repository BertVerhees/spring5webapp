package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Ewans", "Eric");
        Book book1 = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(book1);
        book1.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(book1);

        Author rod = new Author("Johnson", "Rod");
        Book book2 = new Book("J2EE Development without EJB", "456456");
        rod.getBooks().add(book2);
        book2.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(book2);

        System.out.println("Started in Bootstrap");
        System.out.println(("Number of books:" + bookRepository.count()));

        Publisher publisher = new Publisher("Publisher", "Street 1", "NYC", "NY", "1234");
        publisherRepository.save(publisher);
        book2.setPublisher(publisher);
        book1.setPublisher(publisher);
        publisher.getBooks().add(book1);
        publisher.getBooks().add(book2);
        bookRepository.save(book1);
        bookRepository.save(book2);
        publisherRepository.save(publisher);
        System.out.println(("Number of publishers:" + publisherRepository.count()));

    }
}
