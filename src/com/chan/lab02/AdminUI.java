// Chan Wai Chi, 19060801d
package com.chan.lab02;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.Iterator;

public class AdminUI extends JFrame {
    private MyLinkedList<Book> bookList;
    private JTextField txtISBN;
    private JTextField txtTitle;
    DefaultTableModel model;

    public AdminUI() {
        Date dateCreated = new Date();
        bookList = new MyLinkedList<>();

        /* Declare GUI component */
        JTextArea txtDisplayAdmin = new JTextArea(
                "Student Name and ID: Chan Wai Chi (19060801d)\n" +
                        dateCreated, 6, 30);
        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnSave = new JButton("Save");
        JButton btnDelete = new JButton("Delete");
        JButton btnSearch = new JButton("Search");
        JButton btnMore = new JButton("More>>");
        JButton btnLoadTestData = new JButton("Load Test Data");
        JButton btnDisplayAll = new JButton("Display All");
        JButton btnDisplayAllByISBN = new JButton("Display All by ISBN");
        JButton btnDisplayAllByTitle = new JButton("Display All by Title");
        JButton btnExit = new JButton("Exit");
        txtISBN = new JTextField(15);
        txtTitle = new JTextField(15);
        JLabel lblISBN = new JLabel("ISBN: ");
        JLabel lblTitle = new JLabel("Title: ");
        JPanel pnlTxtBookRecord = new JPanel();
        JPanel pnlAction = new JPanel();
        JPanel pnlButton01 = new JPanel();
        JPanel pnlButton02 = new JPanel();
        model = new DefaultTableModel();

        /* Set the status of component */
        btnSave.setEnabled(false);

        /* Set table */
        JTable tblDisplayBook = new JTable(model);
        model.addColumn("ISBN");
        model.addColumn("Title");
        model.addColumn("Available");
        tblDisplayBook.setRowSelectionAllowed(true);
        tblDisplayBook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        /* Add the GUI component into panels*/
        pnlTxtBookRecord.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        pnlTxtBookRecord.add(lblISBN);
        pnlTxtBookRecord.add(txtISBN);
        pnlTxtBookRecord.add(lblTitle);
        pnlTxtBookRecord.add(txtTitle);
        pnlButton01.setLayout(new FlowLayout(FlowLayout.CENTER, 10,
                15));
        pnlButton01.add(btnAdd);
        pnlButton01.add(btnEdit);
        pnlButton01.add(btnSave);
        pnlButton01.add(btnDelete);
        pnlButton01.add(btnSearch);
        pnlButton01.add(btnMore);
        pnlButton02.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        pnlButton02.add(btnLoadTestData);
        pnlButton02.add(btnDisplayAll);
        pnlButton02.add(btnDisplayAllByISBN);
        pnlButton02.add(btnDisplayAllByTitle);
        pnlButton02.add(btnExit);
        pnlAction.setLayout(new GridLayout(3, 1));
        pnlAction.add(pnlTxtBookRecord);
        pnlAction.add(pnlButton01);
        pnlAction.add(pnlButton02);

        /* Add the GUI component into this UI page*/
        add(pnlAction, BorderLayout.SOUTH);
        add(new JScrollPane(tblDisplayBook), BorderLayout.CENTER);
        add(txtDisplayAdmin, BorderLayout.NORTH);

        /* After clicking this button, exit the program */
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /* After clicking this button,
         * check if the ISBN text field is empty OR
         * title text field is empty OR ISBN exist in the bookList.
         * If yes, display error message.
         * Otherwise, create Book object and add them to the bookList.
         * Display the updated table and clear the text field.
         */
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // TODO: not finish loadTestData button
        /* After clicking these button, append to the current linked list.
         * Display all book record in the table. Clear the text field.
         */
        btnLoadTestData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book HTMLBook = new Book("HTML How to Program", "0131450913");
                Book CplusplusBook = new Book("C++ How to Program", "0131857576");
                Book JavaBook = new Book("Java How to Program", "0132222205");

                if (ifContains(HTMLBook)) {
                    JOptionPane.showMessageDialog(new AdminUI(),
                            setContainError(HTMLBook),
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    bookList.addLast(HTMLBook);
                }

                if (ifContains(CplusplusBook)) {
                    JOptionPane.showMessageDialog(new AdminUI(),
                            setContainError(CplusplusBook),
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    bookList.addLast(CplusplusBook);
                }

                if (ifContains(JavaBook)) {
                    JOptionPane.showMessageDialog(new AdminUI(),
                            setContainError(JavaBook), "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    bookList.addLast(JavaBook);
                }
                showDataFromList(bookList);
            }
        });

        /* After clicking this button, check if bookList is empty OR
         * bookList does not contains the input ISBN.
         * If yes, display error message.
         * Otherwise, "Save" button becomes enabled and other button becomes disabled.
         */
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* After clicking this button, check if no change in ISBN text field OR
         * bookList does not contain the modified error message.
         * If yes, save the modified information to Book object.
         * Display updated record in the table. Clear the text field.
         */
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* After clicking this button, check if linked list is empty OR
         * bookList does not contain the input ISBN OR ISBN text field is not empty
         * If yes, display error message.
         * Otherwise, remove the selected book from the linked list
         * Display all records and clear the text field.
         */
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if(bookList.size == 0 || ifContains(txtISBN.getText())){
//
//
//                }
//                else {
//
//                }
            }
        });

        /* After clicking this button, check if text filed is not empty.
         * If yes, display records that contains the keyword in the ISBN text field or
         * the title text field.
         * Otherwise, do nothing.
         */
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* After clicking this button, display all book record in
         * order of adding nodes to the bookList
         */
        btnDisplayAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* After clicking this button, check if the clicking time.
         * If it is odd, display all book record in ascending order of Book ISBN.
         * If it is even, display all book record in descending order of Book ISBN.
         */
        btnDisplayAllByISBN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* After clicking this button, check if the clicking time.
         * If it is odd, display all book record in ascending order of Book title.
         * If it is even, display all book record in descending order of Book title.
         */
        btnDisplayAllByTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* After clicking this button, check if ISBN text field is empty OR
         * ISBN is not existed in the linked list.
         * If yes, display the error message.
         * Otherwise, display a modal dialog page (BookControlUI).
         */
        btnMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book book = new Book();
                BookControlUI bookAction = new BookControlUI(book,new AdminUI());
                bookAction.setTitle(txtTitle.getText());
                bookAction.setSize(600, 500);
                bookAction.setVisible(true);
            }
        });

        /* After selcting a row on the table, get ISBN and title of this row
         * show them in the text field
         */
        tblDisplayBook.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowSelected = tblDisplayBook.getSelectedRow();
                TableModel tblModel = tblDisplayBook.getModel();
                txtISBN.setText(tblModel.getValueAt(rowSelected,
                        0).toString());
                txtTitle.setText(tblModel.getValueAt(rowSelected,
                        1).toString());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
    /* Get data from bookList and show this data in the table */
    public void showDataFromList(MyLinkedList<Book> list) {
        model.setRowCount(0);
        for (int i = 0; i < list.size; i++) {
            Book book = list.get(i);
            model.addRow(new Object[]{
                    book.getISBN(), book.getTitle(), book.isAvailable()
            });
        }
    }

    /**
     * If the bookList contains input ISBN, return true.
     * Otherwise, return false.
     */
    private boolean ifContains(String targetISBN) {
        boolean ifFind = false;
        Iterator<Book> iterator = bookList.iterator();
        Book book;
        while (iterator.hasNext()) {
            book = iterator.next();
            if (book.getISBN().contains(targetISBN)) {
                ifFind = true;
                break;
            }
        }
        return ifFind;
    }

    /**
     * If the bookList contains input ISBN, return true.
     * Otherwise, return false.
     */
    private boolean ifContains(Book targetBook) {
        boolean ifFind = false;
        Iterator<Book> iterator = bookList.iterator();
        Book sourceBook;
        while (iterator.hasNext()) {
            sourceBook = iterator.next();
            if (sourceBook.getISBN().contains(targetBook.getISBN())) {
                ifFind = true;
                break;
            }
        }
        return ifFind;
    }

    /* Return an error message String
     * when the bookList contains this record */
    private String setContainError(Book book) {
        StringBuilder strErrorMessage = new StringBuilder("Error: ");
        strErrorMessage.append("the database already contains this book\n");
        strErrorMessage.append("(ISBN: " + book.getISBN() + ")");
        return strErrorMessage.toString();
    }

    /* Return an error message String
     * when the bookList does not contains this record */
    private String strNotContainError(Book book) {
        StringBuilder strErrorMessage = new StringBuilder("Error: ");
        strErrorMessage.append("the database does not contain this book ");
        strErrorMessage.append("(ISBN: " + book.getISBN() + ")");
        return strErrorMessage.toString();
    }


    public static void main(String[] args) {
        AdminUI frame = new AdminUI();
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Library Admin System");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
