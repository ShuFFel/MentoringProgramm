package com.instinctools.egor.mentoring.noSQL.DAO.bookDAOImpl;

import com.instinctools.egor.mentoring.noSQL.DAO.BookDAO;
import com.instinctools.egor.mentoring.noSQL.DAO.DAOUtil.DAOUtil;
import com.instinctools.egor.mentoring.noSQL.entity.Book;
import com.instinctools.egor.mentoring.noSQL.entity.MySQLEntity.BookSQLEntity;
import com.instinctools.egor.mentoring.noSQL.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public class MySQLBookDAOImpl implements BookDAO {

    @Override
    public void createBook(Book book) {
        EntityManager manager = DAOUtil.getSessionFactory().createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(book);
            manager.getTransaction().commit();
        } finally {
            if (manager != null && manager.isOpen())
                manager.close();
        }
    }

    @Override
    public void updateBook(Book book) {
        EntityManager manager = DAOUtil.getSessionFactory().createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.merge(book);
            manager.getTransaction().commit();
        } finally {
            if (manager != null && manager.isOpen())
                manager.close();
        }

    }

    @Override
    public void deleteBook(Book book) {
        EntityManager manager = DAOUtil.getSessionFactory().createEntityManager();
        Book bookToDelete = manager.find(BookSQLEntity.class, book.get_id());
        try {
            manager.getTransaction().begin();
            manager.remove(bookToDelete);
            manager.getTransaction().commit();
        } finally {
            if (manager != null && manager.isOpen())
                manager.close();
        }

    }

    @Override
    public List<Book> getBooksByOwnerId(String id) {
        EntityManager manager = DAOUtil.getSessionFactory().createEntityManager();
        List<Book> bookToReturn;
        try {
            manager.getTransaction().begin();
            bookToReturn = manager.createQuery("from Book where owner._id = :id").setParameter("id", id).getResultList();
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
        return bookToReturn;
    }

    @Override
    public List<Book> getAllBooks() {
        EntityManager manager = DAOUtil.getSessionFactory().createEntityManager();
        List<Book> bookList = null;
        try {
            manager.getTransaction().begin();
            bookList = manager.createQuery("from Book", Book.class).getResultList();
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
        return bookList;
    }
}
